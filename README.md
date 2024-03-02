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
| price              | float       | price                                         |
| vehicle_status     | varchar(50) | client's phone                                |                                
| created_at         | timestamp   | timestamp of row creation                     |

### Table driver ()

| Column name              | Type          | Description                                   |
|--------------------------|---------------|-----------------------------------------------|
| id                       | binary(16)    | id key of row - unique, not null, primary key |
| first_name               | varchar(20)    | client id                                     |         
| last_name                | varchar(20)  | a name of account                             |                              
| address                  | varchar(80)       | account type                                  |                                   
| phone                    | varchar(20)        | status of tne account                         |                          
| driving_license_category | varchar(20) | balance of the account in currency            | 
| is_working               | boolean        | account currency code                         |                          
| created_at               | timestamp     | timestamp of row creation                     |

### Table maintenance ()

| Column name            | Type           | Description                                                              |
|------------------------|----------------|--------------------------------------------------------------------------|
| id                     | int            | id key of row - unique, not null, primary key                            |
| service_type           | int            | manager id                                                               |
| maintenance_cost       | varchar(70)    | product's name                                                           |
| maintenance_kilometers | int(1)         | product's status                                                         |
| created_at             | int(2)         | currency of product                                                      |
| vehicle_id             | numeric(6,4)   | interest rate of product                                                 |
| company_id             | numeric(15,2)  | limit of credit a product ( 0 - no limit, 0 < - limit which can be used) |

### Table company ()

| Column name        | Type          | Description                                   |
|--------------------|---------------|-----------------------------------------------|
| id                 | binary(16)    | id key of row - unique, not null, primary key |
| company_name       | varchar(50)   | client's account                              | 
| contact_first_name | varchar(20)   | product id (table product)                    | 
| contact_last_name  | varchar(20)	  | current interest rate of agreement            | 
| email              | varchar(60)   | agreement's status                            | 
| address            | varchar(80)   | amount of agreement                           | 
| phone              | varchar(20)   | timestamp of last update                      | 
| created_at         | timestamp     | timestamp of row creation                     | 


### Table Contract ()

| Column name                         | Type           | Description                                   |
|-------------------------------------|----------------|-----------------------------------------------|
| 	id                                 | binary(16)     | id key of row - unique, not null, primary key | 
| 	contract_number                    | varchar(30)    | transaction's debit account                   | 
| 	contract_name                      | varchar(80)    | transaction's credit account                  | 
| 	cost_transportation_under_contract | decimal(10,2)  | transaction type                              | 
| 	total_cost_transported_goods       | decimal(11,2)  | transaction amount in the account currency    | 
| 	contract_status                    | varchar(50)    | description of transaction                    | 
| 	created_at                         | timestamp      | timestamp of row creation                     | 
| 	company_id                         | binary(16)     | timestamp of row creation                     |

### Table Task ()

| Column name                   | Type          | Description                                   |
|-------------------------------|---------------|-----------------------------------------------|
| 	id                           | binary(16)    | id key of row - unique, not null, primary key | 
| 	transportation_date          | timestamp     | manager's name                                | 
| 	address_from                 | varchar(80)   | manager's surname                             | 
| 	address_to                   | varchar(80)   | manager's status                              | 
| 	weight_cargo                 | float         | description of transaction                    | 
| 	waybill_number               | varchar(50)   | timestamp of row creation                     |
| 	waybill_cost                 | decimal(10,2) | timestamp of row creation                     |
| 	distance_traveled_kilometers | int           | timestamp of row creation                     |
| 	fuel_costs_traveled          | decimal(7,2)  | timestamp of row creation                     |
| 	task_status                  | varchar(50)   | timestamp of row creation                     |
| 	comment_if_task_canceled     | varchar(150)  | timestamp of row creation                     |
| 	created_at                   | timestamp     | timestamp of row creation                     |
| 	contract_id                  | binary(16)    | timestamp of row creation                     |
| 	company_id                   | binary(16)    | timestamp of row creation                     |
| 	vehicle_id                   | binary(16)    | timestamp of row creation                     |
| 	driver_id                    | binary(16)    | timestamp of row creation                     |




