const hue = document.querySelector('#hue');
const sat = document.querySelector('#sat');
const light = document.querySelector('#light');
const button = document.querySelector('button');
const output = document.querySelector('#output');

button.addEventListener('click',changeHsl);
hue.addEventListener('input',changeHsl);
sat.addEventListener('input',changeHsl);
light.addEventListener('input',changeHsl);

function changeHsl() {
    output.value =`hsl(${hue.value},${sat.value}%,${light.value}%)`;
    document.body.style.backgroundColor = `hsl(${hue.value},${sat.value}%,${light.value}%)`;
}