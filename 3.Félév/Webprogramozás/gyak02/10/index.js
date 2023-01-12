const counter = document.querySelector('#counter');
const plus = document.querySelector('#plus');
const minus = document.querySelector('#minus');

const min = -10;
const max = 10;

plus.addEventListener('click',addPlus);
minus.addEventListener('click',addMinus);

function addPlus() {
    counter.value = parseInt(counter.value) + 1
    plus.hidden = parseInt( counter.value) >= max 
    minus.hidden = parseInt( counter.value) <= min 
}

function addMinus() {
    counter.value = parseInt(counter.value) - 1 
    plus.hidden = parseInt( counter.value) >= max 
    minus.hidden = parseInt( counter.value) <= min 
}