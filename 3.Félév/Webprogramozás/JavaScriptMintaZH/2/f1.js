const button = document.querySelector("button");
let div;

function handleButtonClick() {
  if (div !== undefined) return;

  let headings = document.querySelectorAll("h1, h2, h3");
  let level = 1;

  let toc = document.createElement("ul");
  let lastLevel = toc;
  
  for (const heading of headings) {
    let currentLevel = heading.tagName.slice(-1);
    while (currentLevel != level) {
      if (currentLevel > level) {
        let sublist = document.createElement("ul");
        lastLevel.lastChild.appendChild(sublist);
        lastLevel = sublist;
        level++;
      } else if (currentLevel < level) {
        lastLevel = lastLevel.parentNode;
        level--;
      }
    }

    item = document.createElement("li");
    item.innerText = heading.innerText;
    
    lastParent = item;
    lastLevel.appendChild(item);
  }

  div = document.createElement("div");
  div.appendChild(toc);
  button.insertAdjacentElement("beforebegin", div);
}

button.addEventListener("click", handleButtonClick);