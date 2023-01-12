const body = document.querySelector('body');

body.addEventListener('click',hideParagaph);

function hideParagaph(e) {
    if(e.target.matches('div.faq>h2')){
        e.target.nextElementSibling.hidden = !e.target.nextElementSibling.hidden;
    }
}