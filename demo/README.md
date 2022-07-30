# demo
Demo is just a project that I used to test something.

### Used framework  
* AngularJS
* Elasticsearch
* Spring Boot

### Configuration
Need elasticsearch server running, use following command on ubuntu
```
sudo service elasticsearch start
```

> sorry for the inconvenient

### Example of how to consume GraphQL via HTTP
Use method POST to "http://localhost:8080/graphql"  

- Query with filter
```
{
    "query": "query { findTodo(term: \"t\") { text } }"
}
```
- Query to find all
```
{
    "query": "query { findAll { text } }"
}
```
- Query to find by id
```
{
    "query": "query { find(id: 1) { text } }"
}
```

