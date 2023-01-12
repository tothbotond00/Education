const incomeButton = document.querySelector('#income')
const spendButton = document.querySelector('#spend')
const table = document.querySelector('table')
const goldInput = document.querySelector('#gold');
const silverInput = document.querySelector('#silver')

async function handleUpdate(multi){
    let change = multi * (goldInput.valueAsNumber * 12 + silverInput.valueAsNumber)
    let resp = await fetch('ajax.php?change=' + change);
    let data = await resp.json()
    if (data['success']){
        let tr = document.createElement('tr')
        table.appendChild(tr)
        let td1 = document.createElement('td')
        td1.innerText = data['time']
        tr.appendChild(td1)
        let td2 = document.createElement('td')
        td2.innerText = data['balance']
        tr.appendChild(td2)
    }
}

incomeButton.addEventListener('click', () => handleUpdate(1));
spendButton.addEventListener('click', () => handleUpdate(-1));
