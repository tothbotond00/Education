function multiplicate(n){
    let L = [];
    for (let i = 1; i <= n; i++) {
        let S = []
        for (let j = 1; j <= n; j++) {
            S.push(i*j);
        }
        L.push(S);
    }
    return L;
} 

const number = document.querySelector('#num');
const button = document.querySelector('#button');
const table = document.querySelector('#result')

button.addEventListener('click',makeMulTable);

function makeMulTable() {
    const M = multiplicate(number.value);
    table.innerHTML = genTable(M);
}

function genTable(M){
    const a = M.map(row => `<tr>${row.map(data => `<th>${data}</th>`).join('')}</tr>`).join('');
    return a;
}