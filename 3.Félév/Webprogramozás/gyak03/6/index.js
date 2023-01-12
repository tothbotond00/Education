// Adat + feld. fgvek
const movies = [
    {
      id: 1, 
      title: 'Avengers',
      year: 2012,
      director: 'Joss Whedon',
    },
    {
      id: 2,
      title: 'Avengers: Age of Ultron',
      year: 2015,
      director: 'Joss Whedon',
    },
    {
      id: 3,
      title: 'Star Wars: A new hope',
      year: 1977,
      director: 'George Lucas',
    },
  ];
  function moviesByTitle(movies, filterText) {
    return movies.filter(movie => movie.title.toUpperCase().includes(filterText.toUpperCase()));
  }
  // DOM elemek
  const titleInput = document.querySelector('#title');
  const list = document.querySelector('#list');
  const details = document.querySelector('#details');
  // Eseménykezelők
  titleInput.addEventListener('input', onFilter);
  function onFilter(e) {
    // console.log(e);
    // beolvasás
    const filterText = titleInput.value
    // const filterText = this.value
    // const filterText = e.target.value
    // feldolgozás
    const results = moviesByTitle(movies, filterText);
    // kiírás
    list.innerHTML = genList(results);
  }
  list.addEventListener('mouseover', onHover);
  function onHover(e) {
    if (e.target.matches('li')) {
      const li = e.target;
      // beolvasás
      const id = parseInt(li.dataset.id);
      // feldolgozás
      const movie = movies.find(movie => movie.id === id);
      // kiírás
      details.innerHTML = `
        <p>Title: ${movie.title}</p>
        <p>Director: ${movie.director}</p>
        <p>Year: ${movie.year}</p>
      `
    }
  }
  // HTML generátorok
  function genList(results) {
    return results.map(movie => `
      <li data-id="${movie.id}">
        ${movie.title}
      </li>
    `
    ).join('');
  }