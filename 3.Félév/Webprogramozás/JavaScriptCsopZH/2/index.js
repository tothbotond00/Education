//a

const main = document.querySelector('#main');
const controls = document.querySelector('#controls');
const degree = document.querySelector('#degree');
console.log(main);
console.log(controls);
console.log(degree);

//b-c

controls.addEventListener('input',displayNum);

function displayNum(e) {
    degree.innerHTML = e.target.value;
    main.style.transform = `rotateX(${e.target.value}deg)`
}