## FINANCIAL RECORDS REST API

### PREREQUISITE
1. JDK 11
2. Maven
3. PostgreSQL

### Running  on local development guide
1. Clone this repo
2. create postgresql empty database manually
3. edit the `src\main\resources\application.yml`
```
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/{input your database name here}
    username: {your postgre user}
    password: {your postgre password}
    driver : org.postgresql.Driver
```
4. build and run spring application with maven, remember path the project folder first
```
> mvn clean
> mvn spring-boot:run
```

### API DOCS
1. make sure you already run the spring application
2. go to browser and type : ```http://localhost:8080/v2/swagger-ui```

### API USAGE
1. create `user` first
2. you can add some `wallet` data first (has been few auto create)
3. create `user_wallet`, required `wallet.id` and `user.id`
4. then you can use the `transaction_history` by `user_wallet.id`