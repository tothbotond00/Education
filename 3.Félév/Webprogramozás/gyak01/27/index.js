//a
const filmek = [["HP",100,["fantasy","misztikus"],2004,["George","Willow"],[("Hp","David"), ("Ron","Chris"),("Hermi","Julia")] ],
                ["LoR",130,["fantasy","akcio"], 1999,["Bob"],[("Frofo","Bobby"),("Gandalf","John")]],
                ["SW",90,["sci-fi","action"],1989,["Lucas"],[("Luke","Fred"),("Leila","Amanda")]]];
console.log(filmek);
//b
const names = ["as","adas"]
console.log(names.length)
for (let index = 0; index < filmek.length; index++) {
    let szerzo = filmek[index][4];
    if((szerzo.length) > 1){
        console.log(filmek[index])
    }
}