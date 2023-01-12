const female = document.querySelector("[name=nem][value=no]");
const maiden = document.querySelector('#maiden');
const male = document.querySelector("[name=nem][value=ferfi]");

female.addEventListener('click',doEvent);
male.addEventListener('click',doEvent)
function doEvent(){
    maiden.hidden = !female.checked
}