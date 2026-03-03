//prueba
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
                'Content-Type': 'application/json; charset=UTF-8' // *Headers* specify the content type
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
    const data = {}
    fetch(
        "localhost:8080/api/game/add",
        {
            headers: {
                'Content-Type': 'application/json; charset=UTF-8' // *Headers* specify the content type
            },
            data
        }
    ).then( response =>{
            if(!response.ok){
                // excepcion
            }
            return response.json();
        } 
    ).then(json=>{
        })
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