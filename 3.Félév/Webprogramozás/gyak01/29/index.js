const temperatures = [12.3,23.4,-3,-4.5,4];
//a
function fagyottNapok(temperatures){
    const out = [];
    for (let i = 0; i < temperatures.length; i++){
        if (temperatures[i] < 0){
            out.push(temperatures[i])
        }
    }
    return out;
}

console.log(fagyottNapok(temperatures));
console.log( temperatures.filter(t => t < 0));

//b
console.log(temperatures.map(t => `${t} C`) );

//c
console.log(temperatures.reduce( (s, t) => s + t , 0) / temperatures.length);
console.log(temperatures.reduce( ( max,t) => t > max ? t : max, -Infinity))
console.log( Math.max( ...temperatures ));

//d
console.log( temperatures.filter( t => t<20).length)
console.log(temperatures.reduce ((m , t) => t < 20 ? m + 1 : m + 0, 0))

//e
console.log( temperatures.some(t => t > 40) );

//f
console.log (temperatures.every(t => t > 0));

//g
console.log (temperatures.find(t => t > 10));
//findIndex