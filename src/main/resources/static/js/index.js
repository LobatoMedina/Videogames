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
var currentId;
//Events
window.addEventListener("load", async()=> {
    //load data
    await loadElements("http://localhost:8080/api/game/genre",genres);
    await loadElements("http://localhost:8080/api/game/platforms",platforms);
    await loadElements("http://localhost:8080/api/game/esrb",esrb);
    await fetchAllGames();
    //clearInputs();
    validateTransparency()
})
window.addEventListener("scroll", () => {
    validateTransparency();
})
add_genre.addEventListener("click",e =>{
    e.preventDefault();
    var entry  = parseInt(genres.value);
    if( genresA.indexOf(entry) !== -1 || entry == -1) return;
    genresA.push(entry);
    createEntry(genres.options[genres.selectedIndex].innerHTML, genres.value,selectedGenres,genresA);
})
add_platforms.addEventListener("click", e=>{
    e.preventDefault();
    let entry = parseInt(platforms.value);
    if( platformsA.indexOf(entry) !== -1 || entry == -1) return;
    platformsA.push(entry);
    createEntry(platforms.options[platforms.selectedIndex].innerHTML, platforms.value, selectedPlatforms, platformsA);
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
            fetchAllGames();
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
    }).catch(e =>{
        printInfo("Se cayo el server xd", INFOSTATES.DANGEROUS);
    })
}
const createOptionElement = (value, name, parent) =>{
    let option = document.createElement("OPTION");
    option.value=value;
    option.innerHTML=name;
    parent.appendChild(option);
}
const fetchAllGames = async ()=>{
    videogames.innerHTML = "";
    try{
        const response = await fetch("http://localhost:8080/api/game/getGames",{
            headers: {
                'Content-Type': 'application/json; charset=UTF-8' 
            }
        })
        if(!response.ok){
            printInfo("Error del servidor, no se pudieron cargar los datos", INFOSTATES.WARNING);
        }else{
            tmp = await response.json()
            tmp.forEach(element =>{
                createCardsElements(element)
            })
        }
    }catch(e){
        printInfo("Se cayo el server xd", INFOSTATES.DANGEROUS);
    }

}
const createCardsElements= element=>{
    const article = document.createElement("ARTICLE");
    const card_header =document.createElement("DIV");
    const card_body =document.createElement("DIV");
    const card_footer =document.createElement("DIV");
    const title =document.createElement("H4")
    const image = document.createElement("IMG")
    const asereje = document.createElement("A");
    
    article.className = "card";
    card_header.className = "card__header";
    card_body.className = "card__body";
    card_footer.className = "card__footer";
    asereje.href=element.demo
    asereje.innerHTML= "Ver demo"
    title.innerHTML=`Title: ${element.name}`;
    image.src =element.imgUrl
    //card_header
    card_header.appendChild(title);
    card_header.appendChild(createTag("author",element.author));
    //card_body
    card_body.appendChild(image);
    card_body.appendChild(createTag("Categoria", element.esrbDTO.name));
    card_body.appendChild(createBodyElements("Generos",element.genres))
    card_body.appendChild(createBodyElements("Plataformas",element.platforms))
    card_body.appendChild(createTag("Specs", element.specs));
    card_body.appendChild(createTag("price",element.price));
    card_body.appendChild(createTag("stock",element.stock));
    //card_footer
    card_footer.appendChild(asereje);
    card_footer.appendChild(createButton("Update", ()=>{
        if(document.getElementById("btn_act")!=null)return; //provisional
        selectedGenres.innerHTML="";
        selectedPlatforms.innerHTML= "";
        addGame.disabled=true
        currentId = element.Id
        nameInput.value=element.name;
        esrb.value= element.esrbDTO.id;
        author.value= element.author;
        specs.value=element.specs;
        precio.value=element.price;
        genres.value= -1;
        platforms.value = -1;
        demo.value = element.demo,
        stock.value =element.stock;
        genresA.length =0;
        platformsA.length =0;
        element.genres.forEach(g =>{
            createEntry(g.name, g.id, selectedGenres,genresA);
            genresA.push(parseInt(g.id));
        })
        element.platforms.forEach(plt =>{
            createEntry(plt.name,plt.id,selectedPlatforms, platformsA);
            platformsA.push(parseInt(plt.id));
        })
        div_form.appendChild(createInputButton(element.imgUrl));
    }))
    card_footer.appendChild(createButton("Delete", async ()=>{
        await deleteById(element.Id);
        await fetchAllGames();
    }))
    article.appendChild(card_header);
    article.appendChild(card_body);
    article.appendChild(card_footer);
    videogames.appendChild(article);
}
const createInputButton = imageName=> {
    const btn = document.createElement("input")
    btn.type= "button"
    btn.id="btn_act"
    btn.value = "Actualizar";
    btn.addEventListener("click", async e=>{
        await updateVideogame(imageName,{id: parseInt(esrb.value),
            name :genres.options[genres.selectedIndex].innerHTML,
            EdadMin: 0
        });
    })
    return btn;
}
const updateVideogame = async (imageName,esrb)=>{
    if(!validateValues()){return};
    const fromData = new FormData();
    fromData.append("file", image.files[0]);
    const jsonBlob = new Blob([JSON.stringify(getJsonUpdateValues(imageName, esrb))],{
        type: "application/json"
    })
    fromData.append("videogame", jsonBlob);
    try{

        const response = await fetch(
        "http://localhost:8080/api/game/update",
        {
        method: "POST",
           body: fromData
        }
    )
        if (response.ok) {
            printInfo("VideoJuego Actualizado" , INFOSTATES.SUCCESS);
            clearInputs();
            await fetchAllGames();
            addGame.disabled = false;
            div_form.removeChild(document.getElementById("btn_act"))
        } else {
            printInfo("Hubo un error al enviar el registro", INFOSTATES.WARNING);
        }
    }catch(e){
        printInfo("Se cayo el server", INFOSTATES.DANGEROUS)
    }
}
const deleteById = async idp =>{
    try{
        const response = await fetch("http://localhost:8080/api/game/delete",{
            method: "POST",
            body : new URLSearchParams({
                id : idp
            })
        })
        if(!response.ok){
            printInfo("Error al eliminar el Videojuego", INFOSTATES.WARNING);
        }else{
            
            printInfo("Videojuego eliminado correctamente", INFOSTATES.SUCCESS)
        }

    }catch(e){
        printInfo("NOOO mi bombo", INFOSTATES.DANGEROUS)
    }
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
    const btn = document.createElement("button")
    btn.innerHTML = title;
    btn.addEventListener("click", ()=>{
        e();
    })
    return btn;
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
            info.classList.add("danger"); 
        break;
        case INFOSTATES.INFORMATIVE:
            info.innerHTML = msg;
            info.classList.add("info"); 
        break;
        case INFOSTATES.WARNING:
            info.innerHTML = msg;
            info.classList.add("warning"); 
        break;
        case INFOSTATES.SUCCESS:
            info.innerHTML = msg;
            info.classList.add("succes"); 
        break;
        default : info.innerHTML = "Algo malo ha ocurrido";
            info.classList.add("");
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
        platforms : platformsA,
        stock : parseInt(stock.value),
        demo : demo.value 
    };
}
const getJsonUpdateValues = (imageName,esrb)=>{
    return {
        Id : currentId,
        name : nameInput.value,
        esrbDTO : esrb,
        imgUrl : imageName,
        author :author.value,
        specs: specs.value,
        price: parseFloat(precio.value),
        genres : createObjectFromArray(genresA),
        platforms : createObjectFromArray(platformsA),
        stock : parseInt(stock.value),
        demo : demo.value 
    };
}
const createObjectFromArray= array=>{
    var tmp_array = [];
    array.forEach(element => {
        tmp_array.push({id : element,
            name : "xd"
        })
    });
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
    parent.appendChild(div);
}
const getFormattedId = (id, name) => name+"_"+id;
const deleteEntry = (id, parent, array) => {
    let div = document.getElementById(id);
    div.innerHTML = "";    
    parent.removeChild(div);
    let index = array.indexOf(id.split("_")[1])
    array.splice(index, 1);
}