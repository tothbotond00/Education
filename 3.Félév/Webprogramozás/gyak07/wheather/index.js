const adressInput = document.querySelector('#adress');
const button = document.querySelector('button');

///Harder
//button.addEventListener('click', function () {
//    const address = adressInput.value;
//    const xhr = new XMLHttpRequest();
//
//    xhr.addEventListener("load", responseHandler)
//    xhr.open("GET", `https://nominatim.openstreetmap.org/?q=${address}&format=json&limit=1`)
//    xhr.responseType = "json";
//    xhr.send(null);
//})

//function responseHandler() {
//    const {lat, lon : long} = this.response[0];
//   console.log(lat,long);
//}

///Easier
button.addEventListener('click',async function () {
    const address = adressInput.value;
    //const response = await fetch(`https://nominatim.openstreetmap.org/?q=${address}&format=json&limit=1`);
    const response = await fetch(`nominative.json`);
    const addressDetails = await response.json();
    const {lat, lon : long} = addressDetails[0];
    console.log(lat,long);
})

///A többi az időjárás