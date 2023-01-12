// Data
const MAX_BAD_ATTEMPTS = 9;
const wordList = [
    'alma', 'körte','szilva','barack','szótár'
];
const word = wordList[random(0,wordList.length-1)];
const buttons = "aábcdeéfghiíjklmnoóöőpqrstuúüűvwxyz";
const guesses = [];
let gameState = 'game'; // 'won', 'lost'

function badAttempts(word,guesses) {
    return guesses.filter(guess => !word.includes(guess)).length;
}
function isWon(word,guesses) {
    return word.split('').every(letter => guesses.includes(letter));
}
function init() {
    word ='alma';
    guesses = [];
    gameState ='game';
}
function random(a,b){
    return Math.floor(Math.random() * (b-a+1)) + a;
}

//Selected elements
const wordDiv = document.querySelector('#the-word');
const lettersDiv = document.querySelector('#letters');
const scoreDiv = document.querySelector('#score');
const endOfGameDiv = document.querySelector('#end-of-game');
const endOfGameSpan = document.querySelector('#end-of-game span');

//EventHadler
lettersDiv.addEventListener('click',onLetterClick);
function onLetterClick(e) {
    if(e.target.matches('button')){
        //Read
        const guessedLetter = e.target.innerHTML;
        //Process
        guesses.push(guessedLetter);
        const bads = badAttempts(word,guesses);
        if (bads >= MAX_BAD_ATTEMPTS){
            gameState = 'lost';
        }
        if (isWon(word,guesses)){
            gameState = 'won';
        }
        //Write
        wordDiv.innerHTML = genWord(word,guesses,gameState);
        scoreDiv.innerHTML = genScore(word,guesses);
        e.target.disabled = true;
        const svgEl = document.querySelector(`svg *:nth-child(${bads})`);
        svgEl?.classList.add('show'); //? OP
        if(gameState === 'lost'){
            endOfGameDiv.hidden = false;
            endOfGameSpan.innerHTML = 'You lost!';
            lettersDiv.hidden = true;
        }if (gameState === 'won'){
            endOfGameDiv.hidden = false;
            endOfGameSpan.innerHTML = 'You won!';
            lettersDiv.hidden = true;
            wordDiv.classList.add('won');
        }
    }
}

//HTML generators
function genWord(word, guesses, gameState) {
    return word
    .split('')
    .map(letter => `
      <span class="${gameState === 'lost' && 
                     !guesses.includes(letter) ? 'missing' : ''}">
        ${guesses.includes(letter) || gameState === 'lost' ? letter : ''}
      </span>
    `)
    .join('');
}

function genScore(word,guesses) {
    return `Bad attempts: ${badAttempts(word, guesses)}/${MAX_BAD_ATTEMPTS}`;
}

//PageLoad
wordDiv.innerHTML = genWord(word,guesses,gameState);
lettersDiv.innerHTML = 
    buttons.split('').map(letter => `<button>${letter}</button>`).join('');
