const numbers = [5, 2, 15, -3, 6, -8, -2];

 const matrix = [
   [1, 0, 3],
   [0, 2, 0],
   [4, 5, 6],
   [0, 0, 0],
 ]

 const searchResults = {
   "Search": [
     {
       "Title": "The Hobbit: An Unexpected Journey",
       "Year": "2012",
       "imdbID": "tt0903624",
       "Type": "movie"
     },
     {
       "Title": "The Hobbit: The Desolation of Smaug",
       "Year": "2013",
       "imdbID": "tt1170358",
       "Type": "movie"
     },
     {
       "Title": "The Hobbit: The Battle of the Five Armies",
       "Year": "2014",
       "imdbID": "tt2310332",
       "Type": "movie"
     },
     {
       "Title": "The Hobbit",
       "Year": "1977",
       "imdbID": "tt0077687",
       "Type": "movie"
     },
     {
       "Title": "Lego the Hobbit: The Video Game",
       "Year": "2014",
       "imdbID": "tt3584562",
       "Type": "game"
     },
     {
       "Title": "The Hobbit",
       "Year": "1966",
       "imdbID": "tt1686804",
       "Type": "movie"
     },
     {
       "Title": "The Hobbit",
       "Year": "2003",
       "imdbID": "tt0395578",
       "Type": "game"
     },
     {
       "Title": "A Day in the Life of a Hobbit",
       "Year": "2002",
       "imdbID": "tt0473467",
       "Type": "movie"
     },
     {
       "Title": "The Hobbit: An Unexpected Journey - The Company of Thorin",
       "Year": "2013",
       "imdbID": "tt3345514",
       "Type": "movie"
     },
     {
       "Title": "The Hobbit: The Swedolation of Smaug",
       "Year": "2014",
       "imdbID": "tt4171362",
       "Type": "movie"
     }
   ],
   "totalResults": "51",
   "Response": "True"
 }

 //a
console.log(numbers.map(t =>t*t));
 //b
console.log(numbers.reduce((a,b)=>a < b ? a : b,Infinity));
 //c
console.log(matrix.findIndex(t => t.every(t => t==0)));
 //d
 console.log(searchResults.Search.filter(t => t.Year >2010 && t.Type =="movie").map(t=>t.imdbID));
