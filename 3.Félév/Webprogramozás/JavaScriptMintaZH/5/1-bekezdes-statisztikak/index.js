const textarea = document.querySelector('#text')
const task1 = document.querySelector('#task1')
const task2 = document.querySelector('#task2')
const task3 = document.querySelector('#task3')
const task4 = document.querySelector('#task4')
const task5 = document.querySelector('#task5')

task1.innerHTML=textarea.value.split("").length;
    task2.innerHTML=textarea.value.split(".").length+textarea.value.split("?").length+textarea.value.split("!").length;
    task3.innerHTML=textarea.value.split(" ").length;
    str="aáeéiíoóöőuúüűAÁEÉIÍOÓÖŐUÚÜŰ"
    //console.log(textarea.value.split(" ").test(str.split("")));
    task5.innerHTML=longestWord()

textarea.addEventListener('input', function () {
    task1.innerHTML=textarea.value.split("").length;
    task2.innerHTML=textarea.value.split(".").length+textarea.value.split("?").length+textarea.value.split("!").length;
    task3.innerHTML=textarea.value.split(" ").length;
    str="aáeéiíoóöőuúüűAÁEÉIÍOÓÖŐUÚÜŰ"
    //console.log(textarea.value.split(" ").test(str.split("")));
    task5.innerHTML=longestWord()
})

function longestWord() {
    maxLength=0
    theLongestWord = "str"
    words = textarea.value.split(" ")
    for (let i = 0; i < words.length; i++) {
        if(words[i].length > maxLength) {
            maxLength = words[i].length
            theLongestWord = words[i]
        }
    }
    return theLongestWord
}