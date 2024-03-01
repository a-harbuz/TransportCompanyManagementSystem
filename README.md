# Transport Company Management System Project [Backend]

There is a prototype of the BackEnd Transport Company Management System Project.

Data consist of Vehicle, Driver, Maintenance, Company, Contract, Task
___

* [ApiDoc Link](http://localhost:8080/swagger-ui/index.html)
* [JACOCO Link](http://localhost:63342/)
___
### Database scheme

https://dbdiagram.io/d/TransportCompany-65dbc3415cd0412774c60055

## Database structure

### Table vehicle ()

| Column name        | Type        | Description                                   |
|--------------------|-------------|-----------------------------------------------|
| id                 | binary(16)  | id key of row - unique, not null, primary key | 
| vehicle_type       | varchar(50) | car type                                      |
| name               | varchar(50) | name                                          |
| model              | varchar(50) | car model                                     |
| year_manufacture   | varchar(20) | year of manufacture                           |
| car_number         | varchar(20) | car number                                    |
| initial_kilometers | int         | initial kilometers                            |                               
| price              | float       | price                              |
| vehicle_status     | varchar(50) | client's phone                                |                                
| created_at         | timestamp   | timestamp of row creation                     |

### Table driver ()

| Column name   | Type          | Description                                   |
|---------------|---------------|-----------------------------------------------|
| id            | binary(16)    | id key of row - unique, not null, primary key |
| client_id     | binary(16)    | client id                                     |         
| name          | varchar(100)  | a name of account                             |                              
| type          | int(1)        | account type                                  |                                   
| status        | int(1)        | status of tne account                         |                          
| balance       | numeric(15,2) | balance of the account in currency            | 
| currency_code | int(2)        | account currency code                         |                          
| created_at    | timestamp     | timestamp of row creation                     |
| updated_at    | timestamp     | timestamp of last update                      |

### Table maintenance ()

| Column name   | Type           | Description                                                              |
|---------------|----------------|--------------------------------------------------------------------------|
| id            | int            | id key of row - unique, not null, primary key                            |
| manager_id    | int            | manager id                                                               |
| name          | varchar(70)    | product's name                                                           |
| status        | int(1)         | product's status                                                         |
| currency_code | int(2)         | currency of product                                                      |
| interest_rate | numeric(6,4)   | interest rate of product                                                 |
| limit         | numeric(15,2)  | limit of credit a product ( 0 - no limit, 0 < - limit which can be used) |
| created_at    | timestamp      | timestamp of row creation                                                |
| updated_at    | timestamp      | timestamp of last update                                                 |

### Table company ()

| Column name   | Type           | Description                                   |
|---------------|----------------|-----------------------------------------------|
| id            | int            | id key of row - unique, not null, primary key |
| account_id    | binary(16)     | client's account                              | 
| product_id    | int            | product id (table product)                    | 
| interest_rate | numeric(6,4)	  | current interest rate of agreement            | 
| status        | int            | agreement's status                            | 
| sum           | numeric(15,2)  | amount of agreement                           | 
| created_at    | timestamp      | timestamp of row creation                     | 
| updated_at    | timestamp      | timestamp of last update                      | 

### Table Contract ()

| Column name        | Type          | Description                                   |
|--------------------|---------------|-----------------------------------------------|
| 	id                | binary(16)    | id key of row - unique, not null, primary key | 
| 	debit_account_id  | binary(16)    | transaction's debit account                   | 
| 	credit_account_id | binary(16)    | transaction's credit account                  | 
| 	type              | int(1)        | transaction type                              | 
| 	amount            | numeric(12,2) | transaction amount in the account currency    | 
| 	description       | varchar(255)  | description of transaction                    | 
| 	created_at        | timestamp     | timestamp of row creation                     | 

### Table Task ()

| Column name  | Type          | Description                                   |
|--------------|---------------|-----------------------------------------------|
| 	id          | int           | id key of row - unique, not null, primary key | 
| 	first_name  | varchar(50)   | manager's name                                | 
| 	last_name   | varchar(50)   | manager's surname                             | 
| 	status      | int           | manager's status                              | 
| 	description | varchar(255)  | description of transaction                    | 
| 	created_at  | timestamp     | timestamp of row creation                     |


