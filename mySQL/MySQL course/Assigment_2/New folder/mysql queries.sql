select count(Title) as Amount_of_Books_per_Genre, Genre
from books
group by Genre
