const numbers = [3,5,12,1,-1,3,-6];

function filter(x,fn) {
    const result = [];
    //for (const e of x) if e < 0 result-push(e)
    for (let i =0; i <x.length; i++){
        if(fn(x[i])){
            result.push(x[i]);
        }
    }
    return result;
}

console.log(filter(numbers, e => e < 0 ));

console.log(filter(numbers, function(e) {
    return e > 0;
}));

console.log(numbers.filter(e=>e<0));
