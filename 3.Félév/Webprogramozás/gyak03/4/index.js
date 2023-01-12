const allowList = [
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
    'ArrowLeft', 'ArrowRight', 'F5', 'Tab', 'Backspace',
  ];

document.addEventListener('keydown',onKeyDown);

function onKeyDown(e) {
    if (!e.target.matches('input.szam')) {
      return;
    }
    else if (!allowList.includes(e.key)){
        e.preventDefault();
    }
}