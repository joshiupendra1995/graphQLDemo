# graphQLDemo
Springboot with GraphQL to query results in single Api call

To Test the exposed endpoint.

Use below url and request Body and test it via postman

http://localhost:9999/rest/books

body:-
// for single book based on id to fetch just the book title.

{
  book(id:"123"){
    title
  }
}


//for mutiple books to fetch specific fields

{
  allBooks {
     isn
     title
     publisher
     authors
  }
}

//If you want to fetch allBooks and book at a time you can use below body
{ 
book(id:"123"){ title }
allBooks { isn title publisher authors } 
}



