
var genresA= []
var platformsA = [];
window.addEventListener("load", ()=> {
    //load Metadata
    loadElements("http://localhost:8080/api/game/genre",genres);
    loadElements("http://localhost:8080/api/game/platforms",platforms);
    loadElements("http://localhost:8080/api/game/esrb",esrb);
})
const loadElements = (url, parent) =>{
    fetch(
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
const header = document.querySelector(".header");
window.addEventListener("scroll", () => {
    if(scrollY >= 50){
        if(!header.classList.contains("transparent"))
        header.classList.add("transparent");
        else return;
    }else{
        if(header.classList.contains("transparent"))
        header.classList.remove("transparent");
        else return;
    }
})
addGame.addEventListener("click", () =>{
    let info = document.getElementById("info");
    if(!validateValues()){return}
    data = getJsonValues();
    try{
        fetch(
        "localhost:8080/api/game/add",
        {
            method: "POST",
            headers: {
                'Content-Type': 'application/json; charset=UTF-8' // *Headers* specify the content type
            },
           body: {
            "DTO" : getJsonValues(),
            "image": image.value
           }
        }
    ).then( response =>{
            
            if(response.ok){
                info.innerHTML = "VideoJuego agregado"
                info.classList().add("success");
            }else{
                info.innerHTML = "Videojuego no agregado hubo un error al enviar el registro"
                info.classList().add("warning");
            }
        } 
    )
    }catch(e){
        info.innerHTML = "Llamen a dios"
        info.classList().add("danger");
    }
    clearInputs();
    });
const clearInputs = () =>{
    nameInput.value="";
    esrb.value= -1;
    image.value= '';
    author.value= "";
    specs.value='';
    precio.value=0;
    genres.value= -1;
    selectedGenres.innerHTML="";
    platforms.value = -1;
    genresA.length =0;
    platformsA.length =0;
};
const validateValues = ()=>{
    let campos= document.querySelectorAll("input[type='text'], textarea");
    campos.forEach(element=>{
        if(element === ""){
            info.innerHTML = "Campos vacios, necesarios"
            info.classList().add("danger");
            return false;
        }
    })
    if(esrb.value == -1){
            info.innerHTML = "Es necesaria una categoria"
            info.classList().add("danger");
        return false;
    }
    if(image.value ===""){
        info.innerHTML = "Es necesaria una imagen";
        info.classList().add("danger");
        return false;
    }
    return true;
}
const getJsonValues = ()=>{
    return {
        named : nameInput.value,
        esrbid : esrb.value,
        author :author.value,
        specs: specs.value,
        price: precio.value,
        genres : genresA,
        platforms : platformsA 
    }
}
add_genre.addEventListener("click",e =>{
    e.preventDefault();
    var entry  = genres.value;
    if( genresA.indexOf(entry) !== -1) return;
    genresA.push(entry);
    selectedGenres.appendChild(createEntry(genres.options[genres.selectedIndex].innerHTML, genres.value,selectedGenres,genresA));
})
add_platforms.addEventListener("click", e=>{
    e.preventDefault();
    let entry = platforms.value;
    if( platformsA.indexOf(entry) !== -1) return;
    platformsA.push(entry);
    selectedPlatforms.appendChild(createEntry(platforms.options[platforms.selectedIndex].innerHTML, platforms.value, selectedPlatforms, platformsA));
})
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
const deleteEntry = (id, parent, array) => {
    let div = document.getElementById(id);
    div.innerHTML = "";    
    parent.removeChild(div);
    let index = array.indexOf(id.split("_")[1])
    array.splice(index, 1);
}
const getFormattedId = (id, name) => name+"_"+id;