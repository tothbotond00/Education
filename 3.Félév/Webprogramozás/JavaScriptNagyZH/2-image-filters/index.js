const filters = [
  { label: 'Blur', filter: 'blur(#px)', min: 0, max: 10, value: 3 },
  { label: 'Brightness', filter: 'brightness(#%)', min: 0, max: 500, value: 80},
  { label: 'Contrast', filter: 'contrast(#%)', min: 0, max: 500, value: 200 },
  { label: 'Grayscale', filter: 'grayscale(#%)', min: 0, max: 100, value: 50 },
  { label: 'Hue rotate', filter: 'hue-rotate(#deg)', min: 0, max: 360, value: 90 },
  { label: 'Invert', filter: 'invert(#%)', min: 0, max: 100, value: 80 },
  { label: 'Opacity', filter: 'opacity(#%)', min: 0, max: 100, value: 50 },
  { label: 'Saturate', filter: 'saturate(#%)', min: 0, max: 500, value: 200 },
  { label: 'Sepia', filter: 'sepia(#%)', min: 0, max: 100, value: 50 },
];

const theFiltersDiv = document.querySelector('#the-filters')
const theImage = document.querySelector('#the-image')

theFiltersDiv.addEventListener('input',onInput)

function onInput(e) {
  var labels = Array.from(document.querySelectorAll('label'))
  var checked = labels.filter(x => x.firstElementChild.checked)
  var changes = checked.map(x => (`${x.firstElementChild.value}`)
                     .split("#")[0]
                     .concat(`${x.lastElementChild.value}`)
                     .concat(`${x.firstElementChild.value}`.split("#")[1]))
  theImage.style.filter = changes.join(" ")
}

function onload() {
  return filters.map(x => 
    `<label>
      <input type="checkbox" value="${x.filter}">
      ${x.label}
      <input type="range" min="${x.min}" max="${x.max}" value="${x.value}"> 
    </label>`).join('')
}

theFiltersDiv.innerHTML = onload()