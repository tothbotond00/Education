//11
const arr = [1,2,3,-6,3,-10];

console.log(arr.find(t => t < 0));
//12
console.log(arr.filter(t => t % 2 == 0).length);

//13
//Kicsit favágó módszer de nem volt jobb ötletem...
const ertek  = 2;
const arr2 = [];
for (let index = 1; index < arr.length-1; index++){
    let x = arr[index]+ertek;
    let y = arr[index]-ertek;
    if ((y <= arr[index-1] && x >= arr[index-1] )&&
        (y <= arr[index+1] && x >= arr[index+1])){
        arr2.push(arr[index]);
    }   
}
console.log(arr2);
