const animButton = document.querySelector('#animation');
const leftButton = document.querySelector('#left');
const rightButton = document.querySelector('#right');
const canvas = document.querySelector('canvas');
const ctx = canvas.getContext('2d');

let pixelsPerYear = 5;
let origoYear = 1000;
let dt = 0.2;

function draw(){
    for (let i = 1000; i < 1500 *pixelsPerYear; i = i + 50 ) {
        ctx.beginPath()
        ctx.moveTo((i-origoYear) *pixelsPerYear , 0)
        ctx.fillStyle = 'black';
        ctx.font ='20px serif';
        ctx.fillText(`${i}`,(i-origoYear) *pixelsPerYear , 20)
        ctx.lineTo((i-origoYear) *pixelsPerYear,canvas.height)
        ctx.strokeStyle ="red"
        ctx.stroke()
    }
    for (let i = 0; i < arpads.length; i++) {
        ctx.fillStyle = 'green'
        ctx.fillRect((arpads[i].from -origoYear) *pixelsPerYear, 50 , (arpads[i].to - arpads[i].from) * pixelsPerYear,90);
        ctx.fillStyle = 'black';
        ctx.font ='20px serif';
        ctx.fillText(`${arpads[i].name}`,(arpads[i].from -origoYear) *pixelsPerYear , 75)
        ctx.fillText(`${arpads[i].from} - ${arpads[i].to} `,(arpads[i].from -origoYear) *pixelsPerYear , 125)
    }
    for (let i = 0; i < plantanegets.length; i++) {
        ctx.fillStyle = 'red'
        ctx.fillRect((plantanegets[i].from -origoYear) *pixelsPerYear, 200 , (plantanegets[i].to - plantanegets[i].from) * pixelsPerYear,90);
        ctx.fillStyle = 'black';
        ctx.font ='20px serif';
        ctx.fillText(`${plantanegets[i].name}`,(plantanegets[i].from -origoYear) *pixelsPerYear , 225)
        ctx.fillText(`${plantanegets[i].from} - ${plantanegets[i].to}`,(plantanegets[i].from -origoYear) *pixelsPerYear , 275)
    }

}

leftButton.addEventListener('click',goLeft)

function goLeft(e) {
    origoYear = origoYear -10
    ctx.fillStyle = 'white';
    ctx.fillRect(0, 0, canvas.width,canvas.height)
    draw()
}

rightButton.addEventListener('click',goRight)

function goRight(e) {
    origoYear = origoYear +10
    ctx.fillStyle = 'white';
    ctx.fillRect(0, 0, canvas.width,canvas.height)
    draw()
}

draw()