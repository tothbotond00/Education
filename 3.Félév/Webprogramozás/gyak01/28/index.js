const balaton = [27,24,25,23,26,28,21,22,24,22,22,22]

//a
console.log(balaton.reduce((a,b) => a+b ,0)/balaton.length);
//b
console.log(balaton.filter(a => a > 26).length);
//c
console.log(balaton.reduce((a,b) => a < b ? b : a, -Infinity));
//d
console.log(balaton.findIndex(t => t == balaton.reduce((a,b) => a > b ? b : a, Infinity))+1);
//e
console.log(balaton.findIndex(t => t == 23)+1);
//f
let l = false;
for (let index = 0; index < balaton.length-1; index++) {
    let x = balaton[index]+3;
    let y = balaton[index]-3
    l = l || x <=balaton[index+1] || y >=balaton[index+1];
}
console.log(l);
//g
let max = 0;
for (let index = 0; index < balaton.length; index++) {
    if(balaton[index]>25){
        let x = 0;
        while(balaton.length > index && balaton[index]>25){
            x++;
            index++;
        }
        if(x > max){
            max = x;
        }
    }
}
console.log(max);