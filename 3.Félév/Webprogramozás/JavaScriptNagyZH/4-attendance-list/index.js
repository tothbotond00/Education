const table = document.querySelector('table');

function xyCoord(td) {
  return {
    x: td.cellIndex,
    y: td.parentNode.sectionRowIndex,
  }
}

function delegate(parent, type, selector, handler) {
  parent.addEventListener(type, function (event) {
    const targetElement = event.target.closest(selector);

    if (this.contains(targetElement)) {
      handler.call(targetElement, event);
    }
  });
}

table.addEventListener('click', focusIt)

document.addEventListener('keydown', function (e) {
  if (e.key == 'ArrowUp' ||e.key == 'ArrowDown' || e.key == 'ArrowLeft' || e.key =='ArrowRight'){
    var ar = Array.from(document. querySelectorAll('td'));
  }
})

function focusIt(e) {
  if (e.target.matches('td')){
    e.target.focus()
    onLoad()
  }
  else onLoad()
}

function onLoad() {
  var ar = Array.from(document. querySelectorAll('td'));
  var af = ar.filter(a => a.firstChild.checked)
  ar.map(x => x.classList.remove('present'))
  af.map(x => x.classList.add('present'))
}

onLoad()