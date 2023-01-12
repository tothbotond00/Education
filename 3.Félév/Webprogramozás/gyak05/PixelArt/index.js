// State
let pixels = [];
let usedColors = [];
let current = '';

const pixelArts = [
  {
      id: 1,
      pixels: [
          ['#123456', '#234567', '#345678']
      ]
  },
  {
      id: 2,
      pixels: [
          ['#123456'],
          ['#234567'],
          ['#345678']
      ]
  },
]

function initPixels(w, h) {
  pixels = (new Array(h)).fill(0).map(() => (new Array(w)).fill(''));
}

// Selected elements
const widthInput = document.querySelector('#width');
const heightInput = document.querySelector('#height');
const generateButton = document.querySelector('#generate-button');
const editorDiv = document.querySelector('#editor');
const previewDiv = document.querySelector('#used');
const previousDiv = document.querySelector('#previous');
const thisCol = document.querySelector('#actualColor');
const selected = document.querySelector('#colorChanger');

// Event handlers
generateButton.addEventListener('click', onGenerate);
function onGenerate(e) {
  current = selected.value;
  const w = widthInput.valueAsNumber;
  const h = heightInput.valueAsNumber;
  
  initPixels(w, h);

  editorDiv.innerHTML = genTable(pixels,true);
}

editorDiv.addEventListener('click',paint);
function paint(e) {
  if (e.target.matches('td')){
    let i = e.target.parentElement.id;
    let j = parseInt(e.target.id) % pixels[0].length;
    pixels[i][j] = current;
    thisCol.style.backgroundColor = current;
    thisCol.innerHTML = current;
    if (!usedColors.includes(current)) {
      usedColors.push(current);
      previewDiv.innerHTML = genPreview(usedColors);
    }
    editorDiv.innerHTML = genTable(pixels,true);
  }
}

editorDiv.addEventListener('contextmenu',clear);
function clear(e){
  if (e.target.matches('td')){
    e.preventDefault();
    let i = e.target.parentElement.id;
    let j = parseInt(e.target.id) % pixels[0].length;
    pixels[i][j] = '';
    editorDiv.innerHTML = genTable(pixels,true);
  }
}

selected.addEventListener('input',function change() { current=selected.value;
  thisCol.style.backgroundColor = selected.value;
  thisCol.innerHTML = selected.value;  
}) 

previewDiv.addEventListener('click',usedChange);
function usedChange(e) {
  if(e.target.matches('td')){
    const rgba2hex = (rgba) => `#${rgba.match(/^rgba?\((\d+),\s*(\d+),\s*(\d+)(?:,\s*(\d+\.{0,1}\d*))?\)$/).slice(1).map((n, i) => (i === 3 ? Math.round(parseFloat(n) * 255) : parseFloat(n)).toString(16).padStart(2, '0').replace('NaN', '')).join('')}`
    current = rgba2hex(e.target.style.backgroundColor);
    thisCol.style.backgroundColor = current;
    thisCol.innerHTML = current;
  }
}

// HTML generator
function genTable(pixels,isEdit) {
  let i = -1;
  let j = -1;
  return `
    <table class="${isEdit ? 'edit' : 'previous'}">
      ${pixels.map(row => `
        <tr id="${i=i+1}">
          ${row.map(color => `
            <td style="background-color: ${color}" id="${j=j+1}" ></td>
          `).join('')}
        </tr> 
      `).join('')}
    </table>
  `;
}

function genPreview(usedColors){
  return `<table class="preview"><tr>${usedColors.map(color => `<td style="background-color: ${color}"></td>`).join('')}</tr></table>`
}

function genPrevious(pixelArts){
  return pixelArts.map(element => genTable(element.pixels,false)).join('');
}

previousDiv.innerHTML = genPrevious(pixelArts);