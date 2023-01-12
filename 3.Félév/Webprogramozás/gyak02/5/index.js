const url = document.querySelector('#url');
const button = document.querySelector('#button');
const pic = document.querySelector('#pic');

button.addEventListener('click',searchImg);

function searchImg() {
    //Beolvas
    const src = url.value;
    //Kiir
    pic.src = src; 
}