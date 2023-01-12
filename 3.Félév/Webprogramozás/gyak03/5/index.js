const body = document.querySelector('body');

body.addEventListener('click',disableLinks);

function disableLinks(e){
    if(e.target.matches('a')){
        if(e.target.href != "http://www.elte.hu/"){
            e.preventDefault();
        }
    }
}
