-- Important requires the view Book_storage
-- Create View Book_Storage as
-- Select books.Title, book_availability.availability, book_availability.lended_ID
-- From books
-- Inner Join book_availability
-- On books.id = book_availability.id

-- Create this view first
-- Create view Deadlines as
-- Select lended_person.Time, book_storage.Title
-- from lended_person
-- Inner Join book_storage
-- On book_storage.Lended_ID = lended_person.Lended_ID

-- Select * from deadlines
-- where title = "manasa"
