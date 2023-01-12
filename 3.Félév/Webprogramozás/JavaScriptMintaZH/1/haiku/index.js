const textarea = document.querySelector('#haiku-editor')
const button = document.querySelector('#btn-copy-haiku')
const spanCharacters = document.querySelector('#number-of-characters')
const spanRows = document.querySelector('#number-of-rows')
const rowList = document.querySelector('#vowels-per-row')
const haikus = document.querySelector('#haikus')

textarea.addEventListener('input', onInput)
function onInput(e) {
  const str = this.value
  spanCharacters.innerHTML = str.length
  const rows = str.split('\n')
  spanRows.innerHTML = rows.length
  const vowelsPerRow = rows.map(numberOfVowels) 
  rowList.innerHTML = vowelsPerRow
    .map(n => `<li>${n}</li>`)
    .join('')
  textarea.closest('p').classList.toggle('haiku', 
    vowelsPerRow.length === 3 && vowelsPerRow[0] === 5 && vowelsPerRow[1] === 7 && vowelsPerRow[2] === 5 
  )
}
function numberOfVowels(row) {
  const vowels = 'aáeéiíoóöőuúüű'
  return row.split('').filter(c => vowels.includes(c)).length
}

button.addEventListener('click', onClick)
function onClick(e) {
  haikus.innerHTML += `<pre>${textarea.value}</pre>`
}