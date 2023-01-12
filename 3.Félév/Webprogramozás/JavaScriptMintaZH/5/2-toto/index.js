guesses = []
for (let i = 0; i < 14; i++) {
    guesses[i]="_"
}
guess = document.getElementById("guess")
correct = document.getElementById("correct")
results = document.getElementById("results")
document.getElementById("tablazat").addEventListener("click", function(event) {
      console.log(event.target.parentNode.rowIndex);
    if (event.target.className=="option") {
        event.target.classList.add("selected");
        guesses[event.target.parentNode.rowIndex]=event.target.innerHTML;
    }
    if (event.target.innerHTML=="X") {
        if (event.target.nextElementSibling.className == "option selected" || event.target.previousElementSibling.className == "option selected") {
            event.target.nextElementSibling.className = "option"
            event.target.previousElementSibling.className = "option"
            event.target.className="option selected"
            guesses[event.target.parentNode.rowIndex]=event.target.innerHTML;
        }
    }
    if (event.target.innerHTML=="1") {
        if (event.target.nextElementSibling.className == "option selected" || event.target.nextElementSibling.nextElementSibling.className == "option selected") {
            event.target.nextElementSibling.className = "option"
            event.target.nextElementSibling.nextElementSibling.className = "option"
            event.target.className = "option selected"
            guesses[event.target.parentNode.rowIndex]=event.target.innerHTML;
        }
    }
    if (event.target.innerHTML=="2") {
        if (event.target.previousElementSibling.className == "option selected" || event.target.previousElementSibling.previousElementSibling.className == "option selected") {
            event.target.previousElementSibling.className = "option"
            event.target.previousElementSibling.previousElementSibling.className = "option"
            event.target.className = "option selected"
            guesses[event.target.parentNode.rowIndex]=event.target.innerHTML;
        }
    }
    if (!guesses.includes("_")) {
        counter=1;
        for (let i = 0; i < 14; i++) {
            array=results.value.split("")
            if(guesses[i]==array[i]) {
                correct.innerHTML=counter;
                console.log("we in");
                counter++;
            }
        }
    }
    guess.innerHTML=guesses.join('')
  });


 
  