const saveButton   = document.querySelector("#save")
const clearButton  = document.querySelector("#clear")
const neptunInput  = document.querySelector("#neptun")
const pointsInput  = document.querySelector("#points")
const table        = document.querySelector("table")

async function handleSaveButtonClick(){
    let neptun = neptunInput.value
    let points = pointsInput.valueAsNumber
    let resp = await fetch(`table.php?neptun=${neptun}&points=${points}`)
    let data = await resp.json()
    document.querySelectorAll("tr:not(:first-child)").forEach(tr => tr.parentNode.removeChild(tr))
    Object.keys(data).forEach(function(row){
        let tr = document.createElement("tr")
        const fields = ["neptun", "1", "2", "3", "4", "sum"]
        fields.forEach(function(field){
            let td = document.createElement("td")
            td.innerText = data[row][field]
            tr.appendChild(td)
        })
        table.appendChild(tr)
    })
}

async function handleClearButtonClick(){
    let resp = await fetch(`table.php?clear=1`)
    await resp.json()
    document.querySelectorAll("tr:not(:first-child)").forEach(tr => tr.parentNode.removeChild(tr))
}

saveButton.addEventListener("click", handleSaveButtonClick)
clearButton.addEventListener("click", handleClearButtonClick)