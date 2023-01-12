// TŰRI ERIK - Webprogramozás
// 4. szorgalmi feladat mintamegoldása

const table  = document.querySelector("table")
const size   = document.querySelector("input")
const start  = document.querySelector("#start")
const extend = document.querySelector("#extend")
const cont   = document.querySelector("#controls")
let n = 0

function handleStartClick(){
    cont.style.display = "none"
    extend.style.display = "inline"
    n = size.valueAsNumber
    for (let i = 0; i <= n; i++){
        let tr = document.createElement("tr")
        for (let j = 0; j <= n; j++){
            let td = document.createElement("td")
            if (i == 0 && j > 0)       td.innerText = String.fromCharCode(65 + j - 1)
            else if (j == 0 && i > 0)  td.innerText = i
            else if (j != 0 && i != 0){ 
                td.classList.add("border")
                td.style.backgroundColor = "white"
            }
            tr.appendChild(td)
        }
        table.appendChild(tr)
    }
}

function handleTdClick(){
    if (this.classList.contains("border"))
        this.style.backgroundColor = this.style.backgroundColor == "white" ? "black" : "white"
}

function handleExtendClick(){
    table.querySelectorAll("tr").forEach(function(tr, i){
        let black = Array.from(tr.querySelectorAll("td")).filter(td => td.style.backgroundColor == "black").length
        let td = document.createElement("td")
        if (i == 0){
            td.innerText = String.fromCharCode(65 + n)
        } else {
            td.style.backgroundColor = black % 2 == 0 ? "white" : "black"
            td.classList.add("border")
        }
        tr.appendChild(td)
    })
    n++
}

start.addEventListener("click", handleStartClick)
extend.addEventListener("click", handleExtendClick)
delegate(table, "click", "td", handleTdClick)

function delegate(parent, type, selector, handler) {
    parent.addEventListener(type, function (event) {
        const targetElement = event.target.closest(selector)
        if (this.contains(targetElement)) handler.call(targetElement, event)
    })
}
