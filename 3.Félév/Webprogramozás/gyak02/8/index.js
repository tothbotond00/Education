const help = document.querySelectorAll("a");
const links = Array.prototype.slice.call(help);
const list = document.querySelector('#hivatkozasok');

window.onload = function hello(){
    list.innerHTML = genList(links);
}

function genList(links){
   const a = links.map(t => `<li>${t.href}</li>`).join('');
   return a;
}