///State
let rulesShowed = false

const Game = {
    Players : 0,
    Treasures : 0,
    CurPlayer : 1,
}

const Pool = {
    Straight : 13,
    Turn  : 15,
    Tri  : 6
}

let curAction = true

const players = []

let unused = null

const labirinth = [   [1,2,3,4,5,6,7]
                      ,[1,2,3,4,5,6,7]
                      ,[1,2,3,4,5,6,7]
                      ,[1,2,3,4,5,6,7]
                      ,[1,2,3,4,5,6,7]
                      ,[1,2,3,4,5,6,7]
                      ,[1,2,3,4,5,6,7]  ]

let free = [    [false, true,true,true,true,true,false]
                ,[true, true,true,true,true,true,true]
                ,[true, true,true,true,true,true,true]
                ,[true, true,true,true,true,true,true]
                ,[true, true,true,true,true,true,true]
                ,[true, true,true,true,true,true,true]
                ,[false, true,true,true,true,true,false]  ]

///Helper functions
function random(a, b) {
    return Math.floor(Math.random() * (b - a + 1)) + a;
}

function rotate(type,rotate, n, tr){
    if (type ==3){
        switch ((n + rotate) % 4) {
            case 0:
                return { Down : true, Up : false,  Left : true, Right : true, Type : 3 , Rotate : 1, Treasure : tr, Selected: false}
            case 1 :
                return { Down : true, Up : true,  Left : true, Right : false, Type : 3 , Rotate : 2, Treasure : tr, Selected: false}
            case 2:
                return { Down : false, Up : true,  Left : true, Right : true, Type : 3 , Rotate : 3, Treasure : tr, Selected: false}
            default:
                return { Down : true, Up : true,  Left : false, Right : true, Type : 3 , Rotate : 4, Treasure : tr, Selected: false}
    }   
    }else if (type ==2){
        switch ((n + rotate) % 4) {
            case 0:
                return { Down : true, Up : false,  Left : false, Right : true, Type : 2 , Rotate : 1, Treasure : tr, Selected: false}
            case 1 :
                return { Down : true, Up : false,  Left : true, Right : false, Type : 2 , Rotate : 2, Treasure : tr, Selected: false}
            case 2:
                return { Down : false, Up : true,  Left : true, Right : false, Type : 2 , Rotate : 3, Treasure : tr, Selected: false}
            default:
                return { Down : false, Up : true,  Left : false, Right : true, Type : 2 , Rotate : 4, Treasure : tr, Selected: false}
        }
    }else {
        if (( (rotate+n) % 2) == 1) return { Down : true, Up : true,  Left : false, Right : false, Type : 1 , Rotate : 2, Treasure : tr, Selected: false}
        else return { Down : false, Up : false,  Left : true, Right : true, Type : 1 , Rotate : 1, Treasure : tr, Selected: false}
    }
}

function getPossibleSteps(labirinth, i, j) {
    let n = false
    let x = parseInt(i)
    let y = parseInt(j)
    if (x > 0 && labirinth[x-1][y].Down && labirinth[x][y].Up){
        if (!labirinth[x-1][y].Selected){
            labirinth[x-1][y].Selected = true
            getPossibleSteps(labirinth, x-1, y)
        }
        n = true
    }if (x < 6 && labirinth[x+1][y].Up && labirinth[x][y].Down){
        if (!labirinth[x+1][y].Selected){
            labirinth[x+1][y].Selected = true
            getPossibleSteps(labirinth, x+1, y)
        }
        n= true
    }if (y> 0 && labirinth[x][y-1].Right && labirinth[x][y].Left){
        if (!labirinth[x][y-1].Selected){
            labirinth[x][y-1].Selected = true
            getPossibleSteps(labirinth, x, y-1)
        }
        n = true
    }if (y < 6 && labirinth[x][y+1].Left && labirinth[x][y].Right){
        if (!labirinth[x][y+1].Selected){
            labirinth[x][y+1].Selected = true
            getPossibleSteps(labirinth, x, y+1)
        }
        n = true
    }
    return n
}

function deSelectAll(labirinth) {
    for (let i = 0; i < 7; i++) {
        for (let j = 0; j < 7; j++) {
            labirinth[i][j].Selected = false
        }
    }
}

///Selectors
const playersNUM = document.querySelector("#players")
const treasures = document.querySelector("#treasures")
const start = document.querySelector("#start")
const rulesButton = document.querySelector("#rulesButton")
const rulesDiv = document.querySelector("#rulesDiv")
const stage = document.querySelector("#stage")
const data = document.querySelector('#data')
const box = document.querySelector('#unused')
const restart = document.querySelector('#restart')
const current = document.querySelector('#current')
const scores = document.querySelector('#scores')
const target = document.querySelector('#target')
const action = document.querySelector('#action')
const skip = document.querySelector('#skip')

///EventListeners
rulesButton.addEventListener('click',showRules)
playersNUM.addEventListener('input',fixPlayerNum)
treasures.addEventListener('input',fixTreasureNum)
start.addEventListener('click',generateStage)
box.addEventListener('contextmenu',rotateBox)
restart.addEventListener('click', reloadWindow)
stage.addEventListener('click', mainAction)
skip.addEventListener('click', skipMove)

///Input and Standard Functions
function reloadWindow() {
    location.reload()
}

function rotateBox(e) {
    e.preventDefault()
    unused = rotate(unused.Type,unused.Rotate-1, 1, unused.Treasure)
    box.innerHTML = genUsable(unused)
}

function showRules(e) {
    if(!rulesShowed){
        rulesShowed = true
        rulesButton.innerHTML = "Hide rules"
        rulesDiv.hidden = false
    }else{
        rulesShowed = false
        rulesButton.innerHTML = "Show rules"
        rulesDiv.hidden = true
    }
}

function fixPlayerNum(e) {
    treasures.max = Math. floor(24/playersNUM.value)
    if ( treasures.value > Math. floor(24/playersNUM.value)){
        treasures.value = Math. floor(24/playersNUM.value)
    }
    if (playersNUM.value > 4){
        playersNUM.value = 4
    }else if (playersNUM.value < 1){
        playersNUM.value = 1
    }
    if (treasures.value < 1){
        treasures.value = 1
    }
}

function fixTreasureNum(e) {
    if (treasures.value >  Math. floor(24/playersNUM.value)){
        treasures.value =  Math. floor(24/playersNUM.value)
    }else if (treasures.value < 1){
        treasures.value = 1
    }
}

///Stage Generation
function generateStage(e) {
    //Getting Data
    Game.Players = playersNUM.value;
    Game.Treasures = treasures.value;
    //Reset Data
    curAction = true
    free = [    [false, true,true,true,true,true,false]
        ,[true, true,true,true,true,true,true]
        ,[true, true,true,true,true,true,true]
        ,[true, true,true,true,true,true,true]
        ,[true, true,true,true,true,true,true]
        ,[true, true,true,true,true,true,true]
        ,[false, true,true,true,true,true,false]  ]
    players.splice(0,players.length)
    Pool.Straight = 13
    Pool.Turn = 15
    Pool.Tri = 6

    //Generate Data
    genLabData(labirinth)
    genPlayers(players, Game.Players,Game.Treasures,labirinth,free)

    //Generating Stage
    data.hidden = false
    box.innerHTML =  genUsable(unused)
    start.innerHTML = "Generate new Game"
    stage.innerHTML = genCSSGrid(labirinth,players)
    scores.innerHTML = genScroes(players,Game.Treasures)
    target.innerHTML = genTarget(players,Game.CurPlayer)
    current.innerHTML = `Player ${Game.CurPlayer}'s turn !`
    action.innerHTML = "Click to an arrow, to put the usable element into the stage."
}

///MAIN ACTION
function mainAction(e) {
    if(e.target.id == "arrow" && curAction){
        //Data Changing
        let base = e.target.classList[0]
        let place = base.slice(0,1)
        let direction = base.slice(1,base.length)
        unused = put(players, labirinth, unused, place, direction)
        curAction = false

        //ReGenerating
        stage.innerHTML = genCSSGrid(labirinth,players)
        box.innerHTML = genUsable(unused)
        action.innerHTML = "Move your character!"

        //Preparing the step
        let no = getPossibleSteps(labirinth, players[Game.CurPlayer-1].x,players[Game.CurPlayer-1].y)

        if (!no){
            //Data Changing
            curAction = true
            Game.CurPlayer = (Game.CurPlayer + 1) > Game.Players ? 1 : (Game.CurPlayer + 1)

            //Regenerating
            current.innerHTML = `Player ${Game.CurPlayer}'s turn !`
            action.innerHTML = "Click to an arrow, to put the usable element into the stage."
            target.innerHTML = genTarget(players,Game.CurPlayer)
        }else {
            //Regenerating for the step
            stage.innerHTML = genCSSGrid(labirinth,players)
            skip.hidden = false
        }
    }else if (e.target.classList[0] == "selected" && !curAction){
        let i = e.target.dataset.x
        let j = e.target.dataset.y

        //Data Changing
        let win = step(labirinth, players[Game.CurPlayer-1], i,j, players[Game.CurPlayer -1].Type)
        deSelectAll(labirinth)
        if (win){
            stage.innerHTML = genCSSGrid(labirinth,players)
            action.innerHTML = `<h2>Player ${Game.CurPlayer} wins!</h2> `
            skip.hidden = true
        }else {
            Game.CurPlayer = (Game.CurPlayer + 1) > Game.Players ? 1 : (Game.CurPlayer + 1)
            curAction = true

            //ReGenerating
            skip.hidden = true
            current.innerHTML = `Player ${Game.CurPlayer}'s turn !`
            action.innerHTML = "Click to an arrow, to put the usable element into the stage."
            target.innerHTML = genTarget(players,Game.CurPlayer)
            scores.innerHTML = genScroes(players,Game.Treasures)
            stage.innerHTML = genCSSGrid(labirinth,players)
            box.innerHTML = genUsable(unused)
        }
    }
}

function skipMove(e) {
    deSelectAll(labirinth)
    Game.CurPlayer = (Game.CurPlayer + 1) > Game.Players ? 1 : (Game.CurPlayer + 1)
    curAction = true

    skip.hidden = true
    current.innerHTML = `Player ${Game.CurPlayer}'s turn !`
    action.innerHTML = "Click to an arrow, to put the usable element into the stage."
    target.innerHTML = `<img src="./assets/treasure${Game.CurPlayer}.png" id="target-pic">`
    scores.innerHTML = genScroes(players,Game.Treasures)
    stage.innerHTML = genCSSGrid(labirinth,players)
    box.innerHTML = genUsable(unused)
}

///Generators
function genCSSGrid(labirinth,players) {
    var res = ""
    res = res.concat(genBorder(1,"DOWN"))
    let tres = players.filter(x => x.Treasures != null).map(y => y.Treasures[y.Treasures.length - 1])
    for (let i = 0; i < labirinth.length; i++) {
        if (i % 2 == 1) res = res.concat(`<div class="grid-item"><img src ="./assets/arrow4.png" id ="arrow" class="${i}RIGHT" ></div>`)
        else res = res.concat(`<div class="grid-item" id =""nothing><img src ="./assets/border.png"></div>`)
        for (let j = 0; j < labirinth.length; j++) {
            if (players.some(pl => pl.x == i && pl.y == j)){
                let sol = players.filter(pl =>pl.x == i && pl.y == j)
                res = res.concat(`<div class="grid-item">
                                  <img src="./assets/tile${labirinth[i][j].Type}.png"id="kep${labirinth[i][j].Rotate}" 
                                  ${labirinth[i][j].Selected ? `class="selected" data-x ="${i}" data-y="${j}"` : `class="unselected"`}>`)
                for (let player of sol){
                    res = res.concat(`<img src="./assets/player${player.Type}.png" id="kep${player.Type + 4}">`)
                }
                if (labirinth[i][j].Treasure != null && tres.some(t => t.x == i && t.y == j)){
                    res = res.concat(`<img src ="./assets/treasure${labirinth[i][j].Treasure.Type}.png" id="kep9">`)
                }
                res = res.concat(`</div>`)
            }else{
                res = res.concat(`<div class="grid-item"><img src="./assets/tile${labirinth[i][j].Type}.png" id="kep${labirinth[i][j].Rotate}"
                                ${labirinth[i][j].Selected ? `class="selected" data-x ="${i}" data-y="${j}"` : `class="unselected"`}>`)
                if (labirinth[i][j].Treasure != null && tres.some(t => t.x == i && t.y == j)){
                    res = res.concat(`<img src ="./assets/treasure${labirinth[i][j].Treasure.Type}.png" id="kep9">`)
                }
                res = res.concat(`</div>`)
            }     
        }
        if (i % 2 == 1) res = res.concat(`<div class="grid-item" ><img src ="./assets/arrow2.png" id ="arrow" class="${i}LEFT" ></div>`)
        else res = res.concat(`<div class="grid-item" id =""nothing><img src ="./assets/border.png"></div>`)
    }
    res = res.concat(genBorder(3,"UP"))
    return res
}

function genBorder(n,dir) {
    var sol = ""
    for (let index = 0; index < 9; index++) {
        if (index < 8 && index > 1 && index % 2 == 0){
            sol = sol.concat(`<div class="grid-item"><img src ="./assets/arrow${n}.png" id ="arrow" class="${index-1}${dir}"></div>`)
        }else {
            sol = sol.concat(`<div class="grid-item" id =""nothing><img src ="./assets/border.png"></div>`)
        }
    }
    return sol
}

function genScroes(players, tresNum) {
    return players.map(x => `<p>Player ${x.Type}: ${x.Treasures == null ? tresNum : tresNum-x.Treasures.length}/${tresNum}</p>`).join('')
}

function genUsable(unused) {
    let tres = players.filter(x => x.Treasures != null).map(y => y.Treasures[y.Treasures.length-1])
    if (unused.Treasure != null && tres.some(t => t.x == -1 && t.y == -1)){
        return `<img src="./assets/tile${unused.Type}.png" id="kep${unused.Rotate}">
                <img src="./assets/treasure${unused.Treasure.Type}.png" id="kep10">`
    }else {
        return `<img src="./assets/tile${unused.Type}.png" id="kep${unused.Rotate}">`
    }
}

function genTarget(players, cur) {
    if (players[cur-1].Treasures == null) return `<p>GO GOME!</p>`
    return`<img src="./assets/treasure${cur}.png" id="target-pic">`
}

//Data Generators
function genLabData(labirinth) {
    labirinth [0][0] = { Down : true,  Up : false, Left : false, Right : true,  Type : 2 , Rotate : 1, Treasure: null, Selected: false}
    labirinth [0][2] = { Down : true,  Up : false, Left : true,  Right : true,  Type : 3 , Rotate : 1, Treasure: null, Selected: false}
    labirinth [0][4] = { Down : true,  Up : false, Left : true,  Right : true,  Type : 3 , Rotate : 1, Treasure: null, Selected: false}
    labirinth [0][6] = { Down : true,  Up : false, Left : true,  Right : false, Type : 2 , Rotate : 2, Treasure: null, Selected: false}
    labirinth [2][0] = { Down : true,  Up : true,  Left : false, Right : true,  Type : 3 , Rotate : 4, Treasure: null, Selected: false}
    labirinth [2][2] = { Down : true,  Up : true,  Left : false, Right : true,  Type : 3 , Rotate : 4, Treasure: null, Selected: false}
    labirinth [2][4] = { Down : true,  Up : false, Left : true,  Right : true,  Type : 3 , Rotate : 1, Treasure: null, Selected: false}
    labirinth [2][6] = { Down : true,  Up : true,  Left : true , Right : false, Type : 3 , Rotate : 2, Treasure: null, Selected: false}
    labirinth [4][0] = { Down : true,  Up : true,  Left : false, Right : true,  Type : 3 , Rotate : 4, Treasure: null, Selected: false}
    labirinth [4][2] = { Down : false, Up : true,  Left : true,  Right : true,  Type : 3 , Rotate : 3, Treasure: null, Selected: false}
    labirinth [4][4] = { Down : true,  Up : true,  Left : true,  Right : false, Type : 3 , Rotate : 2, Treasure: null, Selected: false}
    labirinth [4][6] = { Down : true,  Up : true,  Left : true,  Right : false, Type : 3 , Rotate : 2, Treasure: null, Selected: false}
    labirinth [6][0] = { Down : false, Up : true,  Left : false, Right : true,  Type : 2 , Rotate : 4, Treasure: null, Selected: false}
    labirinth [6][2] = { Down : false, Up : true,  Left : true,  Right : true,  Type : 3 , Rotate : 3, Treasure: null, Selected: false}
    labirinth [6][4] = { Down : false, Up : true,  Left : true,  Right : true,  Type : 3 , Rotate : 3, Treasure: null, Selected: false}
    labirinth [6][6] = { Down : false, Up : true,  Left : true,  Right : false, Type : 2 , Rotate : 3, Treasure: null, Selected: false}
    for (let i = 0; i < 7; i++) {
        for (let j = 0; j < 7; j++) {
            if (i % 2 == 1 || j % 2 == 1){
                labirinth[i][j] = genRandLabElement(Pool)
            }
        }
    }
    unused = genRandLabElement(Pool)
}

function genRandLabElement(Pool) {
    var rand = random (1 ,Pool.Tri + Pool.Straight + Pool.Turn)
    if (rand > Pool.Straight + Pool.Turn){
        Pool.Tri--
        return rotate(3,0,random(0,3), null)
    }else if (rand > Pool.Turn){
        Pool.Straight--
        rand = random (1,2)
        return rotate(1, 0, random(0,1), null)
    }else{
        Pool.Turn--
        return rotate(2,0,random(0,3), null)
    } 
}

function genPlayers(players ,num, treasures,labirinth,free) {
    for (let index = 0; index < num; index++) {
        switch (index) {
            case 0:
                players.push({ x : 0, y : 0 , Type : 1,  Treasures : genTreasures(treasures, 1,labirinth,free)})
                break
            case 1:
                players.push({ x : 0 , y : 6 , Type : 2,  Treasures : genTreasures(treasures, 2,labirinth,free)})
                break
            case 2:
                players.push ({ x : 6, y : 0 , Type : 3, Treasures : genTreasures(treasures, 3,labirinth,free)})
                break
            default:
                players.push({ x : 6 , y : 6 , Type : 4,  Treasures : genTreasures(treasures, 4,labirinth,free)})
        }
        
    }
}

function genTreasures (treasures, n,labirinth, free){
    let ar = []
    for (let index = 0; index < treasures; index++) {
        let i = random(0,6)
        let j = random(0,6)
        while (!free[i][j]){
            i = random(0,6)
            j = random(0,6)
        }
        free[i][j] = false
        labirinth[i][j].Treasure = {x : i , y: j, Type: n}
        ar.push({x : i , y: j, Type: n})
    }
    return ar
}

/*
    Itt tudom hogy nagyon csúnya ez a kódrészlet..
    VISZONT mikor refaktoráltam és kiszerveztem függvénybe a megoldó alogritmus broke-olta az egész programot
    Sajnos csak így tudtam rendesen futtatni :(
*/
///Data changers
function put(players, labirinth, unused, place, direction){
    let unused1 = unused
    //RIGHT
    if (direction == "RIGHT"){
        for (let j = 0; j < 7; j++) {
            //Room changing
            var cur = labirinth[place][j]
            labirinth[place][j] = unused1
            unused1 = cur
            //Treasure and Player place changing
            for (p in players){
                for(tr in players[p].Treasures){
                    let tres = players[p].Treasures[tr]
                    if(tres.x ==place && tres.y == j){ tres.x = -1;  tres.y = -1; unused1.Treasure = {x: -1, y: -1, Type: tres.Type} }
                    else if (tres.x == -1 && tres.y == -1) { tres.x = place ; tres.y = j; labirinth[place,j].Treasure = {x: place, y: j, Type: tres.Type} }
                }
                if(players[p].x == place && players[p].y == 6){ players[p].x = place ; players[p].y = 0 }
                else if(players[p].x == place && players[p].y == j){ players[p].x = -1 ; players[p].y = -1 } 
                else if(players[p].x == -1 && players[p].y == -1){ players[p].x = place ; players[p].y = j }
            }
        }
    //LEFT
    }else if (direction =="LEFT"){
        for (let j = 6; j >= 0; j--) {
            //Room changing
            var cur = labirinth[place][j]
            labirinth[place][j] = unused1
            unused1 = cur
            //Treasure and Player place changing
            for (p in players){
                for(tr in players[p].Treasures){
                    let tres = players[p].Treasures[tr]
                    if(tres.x ==place && tres.y == j){ tres.x = -1;  tres.y = -1; unused1.Treasure = {x: -1, y: -1, Type: tres.Type} }
                    else if (tres.x == -1 && tres.y == -1) { tres.x = place ; tres.y = j; labirinth[place,j].Treasure = {x: place, y: j, Type: tres.Type} }
                }
                if(players[p].x == place && players[p].y == 0){ players[p].x = place ; players[p].y = 6 }
                else if(players[p].x == place && players[p].y == j){ players[p].x = -1 ; players[p].y = -1 } 
                else if(players[p].x == -1 && players[p].y == -1){ players[p].x = place ; players[p].y = j }
            }
        }
    //DOWN
    }else if(direction=="DOWN"){
        for (let i = 0; i < 7; i++ ){
            //Room changing
            var cur = labirinth[i][place]
            labirinth[i][place] = unused1
            unused1 = cur
            //Treasure and Player place changing
            for (p in players){
                for(tr in players[p].Treasures){
                    let tres = players[p].Treasures[tr]
                    if(tres.x ==i && tres.y == place){ tres.x = -1;  tres.y = -1; unused1.Treasure = {x: -1, y: -1, Type: tres.Type} }
                    else if (tres.x == -1 && tres.y == -1) { tres.x = i ; tres.y = place; labirinth[i,place].Treasure = {x: i, y: place, Type: tres.Type} }
                }
                if(players[p].x == 6 && players[p].y == place){ players[p].x = 0 ; players[p].y = place }
                else if(players[p].x == i && players[p].y == place){ players[p].x = -1 ; players[p].y = -1 } 
                else if(players[p].x == -1 && players[p].y == -1){ players[p].x = i ; players[p].y = place }
            }
        }
    //UP
    }else {
        for (let i = 6; i >= 0; i--) {
            //Room changing
            var cur = labirinth[i][place]
            labirinth[i][place] = unused1
            unused1 = cur
            //Treasure and Player place changing
            for (p in players){
                for(tr in players[p].Treasures){
                    let tres = players[p].Treasures[tr]
                    if(tres.x ==i && tres.y == place){ tres.x = -1;  tres.y = -1; unused1.Treasure = {x: -1, y: -1, Type: tres.Type} }
                    else if (tres.x == -1 && tres.y == -1) { tres.x = i ; tres.y = place; labirinth[i,place].Treasure = {x: i, y: place, Type: tres.Type} }
                }
                if(players[p].x == 0 && players[p].y == place){ players[p].x = 6 ; players[p].y = place }
                else if(players[p].x == i && players[p].y == place){ players[p].x = -1 ; players[p].y = -1 } 
                else if(players[p].x == -1 && players[p].y == -1){ players[p].x = i ; players[p].y = place }
            }
        }
    }
    return unused1
}

function step(labirinth, player, i,j, type) {
    player.x = i
    player.y = j
   if (player.Treasures != null && player.Treasures[player.Treasures.length - 1].x == i && player.Treasures[player.Treasures.length - 1].y == j){
        if (player.Treasures.length == 1){
            player.Treasures = null
        }else {
        player.Treasures.pop()
        }
        labirinth[parseInt(i)][parseInt(j)].Treasure = null 
    }else if (player.Treasures == null){
        switch (type){
            case 1 :
                return player.x == 0 && player.y == 0
            case 2 :
                return player.x == 0 && player.y == 6
            case 3 :
                return player.x == 6 && player.y == 0
            default :
                return player.x == 6 && player.y == 6
        }
    }
    return false
}