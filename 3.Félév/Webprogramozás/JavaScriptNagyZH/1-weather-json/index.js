const textarea = document.querySelector('#the-textarea');
const button = document.querySelector('#the-button');
const task1 = document.querySelector('#task1')
const task2 = document.querySelector('#task2')
const task3 = document.querySelector('#task3')
const task4 = document.querySelector('#task4')
const task5 = document.querySelector('#task5')

button.addEventListener('click',handleClick)

function handleClick(e) {
    const data = JSON.parse(textarea.value)
    console.log(data)
    //task1
    task1.innerHTML = 
        data.daily
            .find(x=> x.wind_deg > 180-45
                && x.wind_deg < 180+45 
                && x.weather[0].main ==="Clouds" ).dt
    //task2
    task2.innerHTML = data.daily.reduce((max,a) => (a.temp.max) > max ? a : max,-Infinity).temp.max
    //task3
    task3.innerHTML = 
        data.daily
                .every(x => x.pressure > 1010)
    //task4
    task4.innerHTML =
        data.daily
            .reduce((s, x)  => s + x.feels_like.day  ,0 )/data.daily.length
    //task5
    task5.innerHTML = 
        data.hourly
            .reduce((s,x) => x.wind_speed > 3 ? s + 1 : s + 0 ,0)
}
