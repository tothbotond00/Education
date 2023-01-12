function delegate(parent, type, selector, handler) {
  parent.addEventListener(type, function (event) {
    const targetElement = event.target.closest(selector);

    if (this.contains(targetElement)) {
      handler.call(targetElement, event);
    }
  });
}

const data = document.querySelector("#adatok");

function handleProductClick() {
  const product = this;
  product.classList.toggle("termek");
}
delegate(data, "click", ".szallitmany ul li", handleProductClick);

function handleArrivalClick() {
  const arrival = this;
  const shipment = arrival.parentNode;
  shipment.classList.toggle("erkezes");
}
delegate(data, "click", ".szallitmany div:first-of-type", handleArrivalClick);

function handleShelfClick() {
  const shelf = this;
  const shipment = shelf.parentNode;
  shipment.classList.toggle("polc");
}
delegate(data, "click", ".szallitmany div:last-of-type", handleShelfClick);