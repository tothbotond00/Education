const csuszka = document.querySelector('#csuszka');

document.addEventListener('input',insertValue);


function insertValue(e) {
    if(!e.target.matches('input.display-value')){
        return;
    }
    if(e.target.nextElementSibling.matches('div')){
        e.target.nextElementSibling.innerHTML = e.target.value;
    }
    else{
        var tempDiv = document.createElement('div');
        e.target.insertAdjacentElement('afterend',tempDiv);
        tempDiv.innerHTML=e.target.value;
    }
}