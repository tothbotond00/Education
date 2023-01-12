function interestCalc(money,interest,year){
    let M = [parseInt(money)];
    let number = parseInt(money);
    let mult = interest/100.0 + 1.0;
    for (let index = 0; index < year; index++) {
        number *= mult;
        M.push(number);
    }
    return M;
}

const money = document.querySelector('#money');
const interest = document.querySelector('#interest');
const year = document.querySelector('#year');
const button = document.querySelector('#button');
const result = document.querySelector('#result');
const calc = document.querySelector('#calc');

button.addEventListener('click',runNumbers)

function runNumbers() {
    const count = interestCalc(money.value,interest.value,year.value);
    result.innerHTML = `A pénz értéke így:${(count[count.length-1])} lesz`;
    calc.innerHTML = count.map(data => `<li>${data}</li>`).join('')
}