//variables
var genresA= [];
var platformsA = [];
const header = document.querySelector(".header");
const INFOSTATES= {
    SUCCESS : "green",
    DANGEROUS: "red",
    WARNING : "yellow",
    INFORMATIVE: "blue"
}
var info = document.getElementById("info");

//Events
window.addEventListener("load", ()=> {
    //load data
    loadElements("http://localhost:8080/api/game/genre",genres);
    loadElements("http://localhost:8080/api/game/platforms",platforms);
    loadElements("http://localhost:8080/api/game/esrb",esrb);
    fetchAllGames();
    //clearInputs();
    validateTransparency()
})
window.addEventListener("scroll", () => {
    validateTransparency();
})
add_genre.addEventListener("click",e =>{
    e.preventDefault();
    var entry  = parseInt(genres.value);
    if( genresA.indexOf(entry) !== -1) return;
    genresA.push(entry);
    selectedGenres.appendChild(createEntry(genres.options[genres.selectedIndex].innerHTML, genres.value,selectedGenres,genresA));
})
add_platforms.addEventListener("click", e=>{
    e.preventDefault();
    let entry = parseInt(platforms.value);
    if( platformsA.indexOf(entry) !== -1) return;
    platformsA.push(entry);
    selectedPlatforms.appendChild(createEntry(platforms.options[platforms.selectedIndex].innerHTML, platforms.value, selectedPlatforms, platformsA));
})
addGame.addEventListener("click", async() =>{

    if(!validateValues()){return};
    const fromData = new FormData();
    fromData.append("file", image.files[0]);
    const jsonBlob = new Blob([JSON.stringify(getJsonValues())],{
        type: "application/json"
    })
    fromData.append("videoGameInDTO", jsonBlob);
    data = getJsonValues();
    try{

        const response = await fetch(
        "http://localhost:8080/api/game/add",
        {
            method: "POST",
           body: fromData
        }
    )
        if (response.ok) {
            printInfo("VideoJuego agregado" , INFOSTATES.SUCCESS);
            clearInputs();
        } else {
            printInfo("Hubo un error al enviar el registro", INFOSTATES.SUCCESS);
        }
    }catch(e){
        printInfo("Se cayo el server", INFOSTATES.DANGEROUS)
    }
    
});
//methods
const loadElements = async(url, parent) =>{
    await fetch(
        url,
        {
            headers: {
                'Content-Type': 'application/json; charset=UTF-8' 
            }
        }
    ).then(data => {
        return  data.json();
    })
    .then(json =>{
        json.forEach(element => {
            createOptionElement(element.id, element.name, parent);
        });
    })
}
const createOptionElement = (value, name, parent) =>{
    let option = document.createElement("OPTION");
    option.value=value;
    option.innerHTML=name;
    parent.appendChild(option);
}
const fetchAllGames = async ()=>{
    const response = await fetch("http://localhost:8080/api/getGames",{
        headers: {
            'Content-Type': 'application/json; charset=UTF-8' 
        }
    })
    if(!response.ok){
        printInfo("Error del servidor, no se pudieron cargar los datos", INFOSTATES.DANGEROUS);
    }else{
        response.json().forEach(element =>{
            createCardsElements(element)
        })
    }

}
const createCardsElements= element=>{
    const article = document.createElement("ARTICLE");
    const card_header =document.createElement("DIV");
    const card_body =document.createElement("DIV");
    const card_footer =document.createElement("DIV");

    const title =document.createElement("H4")
    title.innerHTML=`Title: ${element.name}`;

    card_header.appendChild(title);
    card_header.appendChild(createTag("author",element.author));
    const image = document.createElement("IMG")
    image.src =element.imgUrl
    card_body.appendChild(image);
    card_body.appendChild(createTag("Categoria:", element.esrbDTO.name));
    card_body.appendChild(createBodyElements("Generos",element.genres))
    card_body.appendChild(createBodyElements("Plataformas",element.platforms))
    card_body.appendChild(createTag(Specs, element.specs));
    card_body.appendChild(createTag("price",element.price));
    card_body.appendChild(createTag("stock",element.stock));
    const a = document.createElement("A");
    a.src=`${element.demo}`
    card_footer.appendChild(a);
    card_footer.appendChild(createButton("Update", ()=>{
        // to do
    }))
    card_footer.appendChild(createButton("Delete", ()=>{
        // to do
    }))
    article.appendChild(card_header);
    article.appendChild(card_body);
    article.appendChild(card_footer);
    videogames.appendChild(article);
}
const createBodyElements = (title,array = []) =>{
    let div_temp = document.createElement("DIV")
    div_temp.className= "card__body__elements";
    let Etitle =document.createElement("H5")
    title.innerHTML=title
    div_temp.appendChild(Etitle)
    array.forEach(g => {
        let tmp = document.createElement("SPAN")
        tmp.innerHTML= g.name;
        tmp.id = g.id;
        div_temp.appendChild(tmp);
    })
    return div_temp;
}
const createTag= (text, value)=>{
    let p =document.createElement("P")
    p.innerHTML=`${text}: ${value}`;
    return p;
}
const createButton = (title, e) =>{
    const btn = document.createElement("button").innerHTML = title;
    btn.addEventListener("click", ()=>{
        e();
    })
}
const validateTransparency = ()=>{
        if(scrollY >= 50){
        if(!header.classList.contains("transparent"))
        header.classList.add("transparent");
        else return;
    }else{
        if(header.classList.contains("transparent"))
        header.classList.remove("transparent");
        else return;
    }
}

const validateValues = ()=>{
    let campos= document.querySelectorAll("input[type='text'], textarea");
    var bool = true;
    campos.forEach(element=>{
        if(element === ""){
            printInfo(`campo ${element.id} vacio`, INFOSTATES.DANGEROUS )    
            bool = false;
        }
    })
    console.log(bool)
    if(!bool) return bool;
    if(esrb.value == -1){
        printInfo("Es necesaria una categoria", INFOSTATES.DANGEROUS )    
        return false;
    }
    if(!image.files[0]){
        printInfo("Es necesaria una imagen", INFOSTATES.DANGEROUS )
        return false;
    }
    return true;
}
const printInfo = (msg = "", state = INFOSTATES.INFORMATIVE)=>{
    info.innerHTML= "";
    info.className = "";
    switch(state){
        case INFOSTATES.DANGEROUS:
            info.innerHTML = msg;
            info.classList().add("danger"); 
        break;
        case INFOSTATES.INFORMATIVE:
            info.innerHTML = msg;
            info.classList().add("info"); 
        break;
        case INFOSTATES.WARNING:
            info.innerHTML = msg;
            info.classList().add("warning"); 
        break;
        case INFOSTATES.SUCCESS:
            info.innerHTML = msg;
            info.classList().add("succes"); 
        break;
        default : info.innerHTML = "Algo malo ha ocurrido";
            info.classList().add("");
    }
}
const getJsonValues = ()=>{
    return {
        name : nameInput.value,
        esrbid : parseInt(esrb.value),
        author :author.value,
        specs: specs.value,
        price: parseFloat(precio.value),
        genres : genresA,
        platforms : platformsA 
    };
}
const clearInputs = () =>{
    nameInput.value="";
    esrb.value= -1;
    image.value= '';
    author.value= "";
    specs.value='';
    precio.value=0;
    genres.value= -1;
    selectedGenres.innerHTML="";
    selectedPlatforms.innerHTML= "";
    platforms.value = -1;
    genresA.length =0;
    platformsA.length =0;
    demo.value = "",
    stock.value =0
}
const createEntry = (name = "", id =0, parent,array)=>{
    let div = document.createElement("DIV");
    let label= document.createElement("LABEL");
    let btn = document.createElement("INPUT");
    btn.setAttribute("type", "button")
    btn.value= "X";
    label.innerHTML= name;
    div.id = getFormattedId(id,name);
    btn.addEventListener("click", e=> {
        e.preventDefault();
        deleteEntry(getFormattedId(id, name),parent, array);
    })
    div.appendChild(label);
    div.appendChild(btn);
    return div;
}
const getFormattedId = (id, name) => name+"_"+id;
const deleteEntry = (id, parent, array) => {
    let div = document.getElementById(id);
    div.innerHTML = "";    
    parent.removeChild(div);
    let index = array.indexOf(id.split("_")[1])
    array.splice(index, 1);
}
