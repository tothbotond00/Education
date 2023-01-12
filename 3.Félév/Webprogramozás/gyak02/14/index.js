/*
Lépések:
1.Tervezési fázis
2.Statikus html prototype
3.Adatok és feldologzó függvények ( felületfüggetlenek, üzleti logika)
4.Eseménykezelés (beolvasás. feldolgozás, visszaadás)
*/
const catalogue = [
    {
    author: 'J.K Rowling',
    title: "HP: and the Sorcerers's of stone",
    year: 1998,
    },
    {
        author: 'J.K Rowling',
        title: "HP: Chamber of Secrets",
        year: 1998,
    },
    {
        author: 'Tolkien',
        title: "Lord of the Rigns",
        year: 1954,
    },
]

function booksFromYear(catalogue,year) {
    return catalogue.filter(x => x.year===year)
}

//Eseménykezelők

const button = document.querySelector('#search');
const yearInput = document.querySelector('#year');
const list = document.querySelector('#list');

button.addEventListener('click',onSearch);

function onSearch() {
    //Beolvas
    const year = parseInt( yearInput.value );
    //Feldolgoz
    const result = booksFromYear(catalogue,year);
    //Kiir
    list.innerHTML = genList(result);
}

function genList(result){
    return result.map(book => `<li>${book.author}:${book.title} </li>`).join('');
}