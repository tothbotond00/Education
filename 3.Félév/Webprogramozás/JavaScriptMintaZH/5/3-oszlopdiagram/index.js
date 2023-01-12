red = document.getElementById("red")
redSpan = document.getElementById("redSpan")
green = document.getElementById("green")
greenSpan = document.getElementById("greenSpan")
blue = document.getElementById("blue")
blueSpan = document.getElementById("blueSpan")
canvas = document.getElementById("canvas")
animate = document.getElementById("animate")
var ctx = canvas.getContext("2d");
let prevTime = performance.now();
itson=false;
animate.addEventListener('change', function() {
    if (this.checked) {
        itson=true;
    } else {
        itson=false;
    }
  });

red.addEventListener('input',onInp)
green.addEventListener('input', onInp)
blue.addEventListener('input', onInp)

function onInp(){
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    redSpan.innerHTML=red.value;
    greenSpan.innerHTML=green.value;
    blueSpan.innerHTML=blue.value;
    ctx.fillStyle="red"
    ctx.fillRect(0, 400-red.value, 100, red.value);
    ctx.fillStyle="green"
    ctx.fillRect(100, 400-green.value, 100, green.value);
    ctx.fillStyle="blue"
    ctx.fillRect(200, 400-blue.value, 100, blue.value);
}

function changeValues() {
    if (Math.random() < 0.5) {
        red.value++
        red.value++
    }
    else {
        red.value--
        red.value--
    }
    if (Math.random() < 0.5) {
        blue.value++
        blue.value++
    }
    else {
        blue.value--
        blue.value--
    }
    if (Math.random() < 0.5) {
        green.value++;
        green.value++;
    }
    else {
        green.value--;
        green.value--;
    }
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    redSpan.innerHTML=red.value;
    greenSpan.innerHTML=green.value;
    blueSpan.innerHTML=blue.value;
    ctx.fillStyle="red"
    ctx.fillRect(0, 400-red.value, 100, red.value);
    ctx.fillStyle="green"
    ctx.fillRect(100, 400-green.value, 100, green.value);
    ctx.fillStyle="blue"
    ctx.fillRect(200, 400-blue.value, 100, blue.value);
}

function gameloop(now = performance.now()) {
    prevTime = now
    if(itson==true)
    {
        changeValues();
    }
    if (redSpan.innerHTML > greenSpan.innerHTML && redSpan.innerHTML > blueSpan.innerHTML) {    
        ctx.fillStyle="black"
        ctx.fillRect(0, 400-redSpan.innerHTML, 300, 4);
    }
    else if (blueSpan.innerHTML > greenSpan.innerHTML && blueSpan.innerHTML > redSpan.innerHTML) {    
        ctx.fillStyle="black"
        ctx.fillRect(0, 400-blueSpan.innerHTML, 300, 4);
    }
    else if (greenSpan.innerHTML > blueSpan.innerHTML && greenSpan.innerHTML > redSpan.innerHTML) {
        ctx.fillStyle="black"
        ctx.fillRect(0, 400-greenSpan.innerHTML, 300, 4);
    }
    requestAnimationFrame(gameloop);
}
gameloop();