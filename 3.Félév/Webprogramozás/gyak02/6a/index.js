const button = document.querySelector('#button')
const input = document.querySelector('#input')
const output = document.querySelector('#output')

button.addEventListener('click',copyPaste)

function copyPaste() {
    const data = input.value;
    output.value = data;
}