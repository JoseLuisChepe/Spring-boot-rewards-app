## ONIS test rewards application

This application is using 
 - Spring boot - https://spring.io/projects/spring-boot
 - Lombok - https://projectlombok.org/
 - Hibernate - https://hibernate.org/
 - Java 8 - https://www.java.com/es/download/help/java8_es.html
 - Maven - https://maven.apache.org/

In order to tun it you must have at least maven and Java 8 installed so the project can download
the missing libraries.

## How to download it
- The Url of the repository is: 
`https://github.com/JoseLuisChepe/Spring-boot-rewards-app`

- Configure git in your local environment:
`https://git-scm.com/book/en/v2/Getting-Started-First-Time-Git-Setup`
  
- Use the next instruction
```
git clone git@github.com:JoseLuisChepe/Spring-boot-rewards-app.git
```

## How to execute it
 - The project should be cleaned and build first with the next instruction:
```
mvn clean install
```

once the dependencies are downloaded and packaged you only have to execute the instruction
```
mvn spring-boot:run 
```

this will start the spring boot application and the tomcat will be started on port 8080

## How to see the output
Use the URL
```
localhost:8080/rewardByCustomer 
```

Another URLs available are: 
```
localhost:8080/transactions 
localhost:8080/customers
localhost:8080/transactions/{id}  (Delete)
localhost:8080/transactions       (Post) body required
localhost:8080/transactions/{id}  (Put)
```

#Update input data

All the data is contained in `data.sql` file, you can update or add rows to the query

## Output
Json file with the user name containing the rewards per month and a total of rewards
```
{
    "Jose": {
        "rewards": [
        {
            "month": "January",
            "amount": 844
        },
        {
            "month": "February",
            "amount": 928
        },
        {
            "month": "March",
            "amount": 3648
        }
        ],
        "total": 5420
    }
}
```

## Out of scope
 - Exceptin handle
 - Relation of databases
 - Integration and unit Test