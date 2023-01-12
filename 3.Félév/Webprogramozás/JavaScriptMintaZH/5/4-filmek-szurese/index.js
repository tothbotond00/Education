const genresDiv = document.querySelector('#genres')
const movieList = document.querySelector('#movie-list')
const selectedMovieDiv = document.querySelector('#selected-movie')
const form = document.querySelector('form')

movies.forEach((movie, i) => {
  movie.id = i;
  if (movie.originalTitle) {
    movie.title = movie.originalTitle
  }
})
console.log(movies);

// Itt kezdd el!
