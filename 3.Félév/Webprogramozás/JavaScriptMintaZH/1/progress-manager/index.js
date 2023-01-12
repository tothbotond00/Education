const todos = document.querySelector('#todos')

todos.addEventListener('click', onClick)
function onClick(e) {
  if (e.target.matches('ul>li')) {
    const li = e.target
    if (!li.classList.contains('done')) {
      li.classList.add('done')
      let actLi = li.previousElementSibling
      while (actLi) {
        actLi.classList.add('done')
        actLi = actLi.previousElementSibling
      }
      if (!li.nextElementSibling) {
        li.closest('ol>li').classList.add('done')
      }
    } else {
      let ok = true;
      let actLi = li.nextElementSibling
      while (actLi && ok) {
        ok &= !actLi.classList.contains('done')
        actLi = actLi.nextElementSibling
      }
      if (ok) {
        li.classList.remove('done')
        li.closest('ol>li').classList.remove('done')
      }
    }
  }
}