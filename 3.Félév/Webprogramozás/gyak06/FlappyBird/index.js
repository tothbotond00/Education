const canvas = document.querySelector('#flappy-canvas');
const audio = document.querySelector('#audio');
const backaudio = document.querySelector("#backaudio");
const overaudio = document.querySelector('#overaudio');
const ctx = canvas.getContext('2d');

//Helper
function random(a, b) {
    return Math.floor(Math.random() * (b - a + 1)) + a;
}

function isCollide(a, b) {
    return !(
        b.y + b.height  < a.y ||
        a.x + a.width < b.x ||
        a.y + a.height  < b.y ||
        b.x + b.width < a.x
    );
}

// State = data
let prevTime = performance.now();
const bird = {
    x: 50,
    y:canvas.height/2 - 25,
    width: 50,
    height: 30,
    vy: 0, // px/s
    ay: 375, // px/s^2
};
let counter = 0;
const columns = []
let GAP  = 150;    // px, felső és alsó oszlop közötti rés
let COLUMN_DISTANCE = 300;  // px, egymást követő oszlopok közötti távolság
let COLUMN_VELOCITY = -200;  // px/s, az oszlopok vízszintes sebessége
let GameState = {
    INGAME :0,
    GAMEOVER: 1,
}
let gamestate = GameState.INGAME;
const images = {
    madar: new Image(),
    hatter: new Image(),
    oszlop: new Image(),
    gameover : new Image(),
}

// Start

function newColumns() {
    const h = random(10, canvas.height / 2);
    columns.push(
        {
            x: canvas.width,
            y: 0,
            width: 30,
            height: h,
        },
        {
            x: canvas.width,
            y: h + GAP,
            width: 30,
            height: canvas.height - GAP - h,
        },
    );
}

//gameloop
function gameloop(now = performance.now()) {
    const dt = (now - prevTime) / 1000
    prevTime = now

    update(dt);
    draw();
    if (gamestate === GameState.INGAME){
        requestAnimationFrame(gameloop);
    }
}
function update(dt) {
    //Bird
    bird.vy += bird.ay*dt;
    bird.y += bird.vy * dt;
    if(bird.y  > canvas.height - bird.height || bird.y < 0 ){
        gamestate = GameState.GAMEOVER;
    }
    //New colums
    const lastcolumn = columns[columns.length -1];
    if (columns.length ===0 || lastcolumn.x < canvas.width -COLUMN_DISTANCE ){
        newColumns();
    }
    //Remove columns
    const firstColumn = columns[0];
    if (firstColumn.x < -firstColumn.width ){
        counter++;
        //MAKE HARDER
        if (counter % 10 == 0 && counter % 20 != 0) GAP = GAP-15;
        if (counter % 20 == 0) COLUMN_VELOCITY = COLUMN_VELOCITY -25;
        columns.shift();
        columns.shift();
    }
    //Columns
    columns.forEach(column => {
        column.x += COLUMN_VELOCITY * dt;
        if(isCollide(bird,column)){
            gamestate = GameState.GAMEOVER;
        }
    })
}
function draw() {
    //Sky
    ctx.drawImage(images.hatter,0,0,canvas.width,canvas.height);
    //Bird
    ctx.drawImage(images.madar,bird.x,bird.y,bird.width,bird.height);
    //Columns
    columns.forEach(column => {
        ctx.fillStyle = 'lightgreen';
        ctx.drawImage(images.oszlop,column.x,column.y,column.width,column.height);
    });
    //Counter
    ctx.fillStyle = 'purple';
    ctx.font ='30px serif';
    ctx.fillText(`Points: ${counter}`, 10, 35)
    //GameOver
    if (gamestate === GameState.GAMEOVER) {
        ctx.drawImage(images.gameover, 90, 50); 
        overaudio.play();
        overaudio.volume = 0.3;
        backaudio.pause();
    }
}

//Event hadler
document.addEventListener('keydown',function (e){
    if (gamestate === GameState.GAMEOVER && e.code ==='Space'){
        e.preventDefault();
    }
    else if(e.code ==='Space'){
        backaudio.play();
        audio.play();
        audio.volume = 0.2;
        e.preventDefault();
        bird.vy -=200;
    }
})
 
//Start
gameloop();

images.madar.src = 'bird.png';
images.hatter.src = 'bg.png';
images.oszlop.src = 'column.png';
images.gameover.src ='gameover.png';