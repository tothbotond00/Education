const main = document.querySelector('section');

main.addEventListener('click',changeSection);

//a
function changeSection(e) {
    if (!e.target.matches('span')){
        return
    }
    main.style.backgroundColor = e.target.dataset.color;
}

//b
document.onclick = function changeSection(e){
    if (!e.target.matches('span')){
        return
    }
    let x = e.target;
    if (x.parentElement.matches('section')){
        e.target.parentElement.style.backgroundColor = e.target.dataset.color;
    }
    else{
        while (!x.parentElement.matches('section')){
            x = x.parentElement;
        }
        x.style.backgroundColor = e.target.dataset.color;
    }
}

//c

function listColors() {
    const list = document.querySelectorAll('span');
    const static = document.querySelector('static');
    const arr = Array.prototype.slice.call(list);
    console.log(arr.map(x => x.dataset.color).filter(x => x !== undefined).map(x => `<span style="background-color: ${x}"></span>`).split);
    ///ezt már nem sikerült befejezni
    static.innerHTML = arr.map(x => x.dataset.color).filter(x => x !== undefined).map(x => `<span style="background-color: ${x}"></span>`);

}

listColors();