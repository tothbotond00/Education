const button = document.querySelector('#gomb');
const sor = document.querySelector('#sor');

button.addEventListener('click',printHello);

function printHello() {
    sor.innerHTML = `Hello világ!`;
}