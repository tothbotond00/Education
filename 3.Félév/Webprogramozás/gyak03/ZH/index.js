const contact = document.querySelector('#contacts');

contact.addEventListener('click',onClickA);
contact.addEventListener('click',onClickB);
//contact.addEventListener('click',onClickC);
contact.addEventListener('click',onClickD);

//a

function onClickA(e) {
    if(e.target.matches('button')){
        console.log(e.target.innerHTML);
        console.log(e.target.dataset.toggle);
    }
}

//b

function onClickB(e) {
    if(e.target.matches('button')){
        console.log(e.target.parentElement.parentElement.querySelector('p.name').innerHTML);
    }
}

//c

/*
function onClickC(e) {
 if(e.target.matches('button')){
        e.target.parentElement.parentElement.querySelector('p.email').hidden = !e.target.parentElement.parentElement.querySelector('p.email').hidden;
    }
}
*/

//d

function onClickD(e) {
    if(e.target.matches('button')){
        const type = e.target.dataset.toggle;
        e.target.parentElement.parentElement.querySelector(`p.${type}`).hidden = !e.target.parentElement.parentElement.querySelector(`p.${type}`).hidden;
    }
}

