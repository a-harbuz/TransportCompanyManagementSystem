# Transport Company Management System Project [Backend]

This is a prototype of the BackEnd Transport Company Management System Project.

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
| vehicle_status     | varchar(50) | current status                                |                                
| created_at         | timestamp   | timestamp of row creation                     |

### Table driver ()

| Column name              | Type           | Description                                   |
|--------------------------|----------------|-----------------------------------------------|
| id                       | binary(16)     | id key of row - unique, not null, primary key |
| first_name               | varchar(20)    | driver's name                                 |         
| last_name                | varchar(20)    | driver's last name                            |                              
| address                  | varchar(80)    | driver's residential address                  |                                   
| phone                    | varchar(20)    | driver's phone number                         |                          
| driving_license_category | varchar(20)    | driving license category                      | 
| is_working               | boolean        | working or fired                              |                          
| created_at               | timestamp      | timestamp of row creation                     |

### Table maintenance ()

| Column name            | Type         | Description                                   |
|------------------------|--------------|-----------------------------------------------|
| id                     | binary(16)   | id key of row - unique, not null, primary key |
| service_type           | varchar(50)  | type of service                               |
| maintenance_cost       | decimal(8,2) | cost of maintenance                           |
| maintenance_kilometers | int          | At what kilometer was the maintenance made    |
| created_at             | timestamp    | date when maintenance was performed           |
| vehicle_id             | binary(16)   | id of vehicle                                 |
| company_id             | binary(16)   | id of maintenance company                     |

### Table company ()

| Column name        | Type          | Description                                   |
|--------------------|---------------|-----------------------------------------------|
| id                 | binary(16)    | id key of row - unique, not null, primary key |
| company_name       | varchar(50)   | company name                                  | 
| contact_first_name | varchar(20)   | first name of the contact person              | 
| contact_last_name  | varchar(20)	  | last name of the contact person               | 
| email              | varchar(60)   | email of the contact person                   | 
| address            | varchar(80)   | company office address                        | 
| phone              | varchar(20)   | phone of the contact person                   | 
| created_at         | timestamp     | timestamp of row creation                     | 


### Table Contract ()

| Column name                         | Type           | Description                                      |
|-------------------------------------|----------------|--------------------------------------------------|
| 	id                                 | binary(16)     | id key of row - unique, not null, primary key    | 
| 	contract_number                    | varchar(30)    | contract number                                  | 
| 	contract_name                      | varchar(80)    | contract name                                    | 
| 	cost_transportation_under_contract | decimal(10,2)  | cost of transportation according to the contract | 
| 	total_cost_transported_goods       | decimal(11,2)  | total cost of transported goods                  | 
| 	contract_status                    | varchar(50)    | current status of contract                       | 
| 	created_at                         | timestamp      | timestamp of row creation                        | 
| 	company_id                         | binary(16)     | id of counterparty company                       |

### Table Task ()

| Column name                   | Type          | Description                                   |
|-------------------------------|---------------|-----------------------------------------------|
| 	id                           | binary(16)    | id key of row - unique, not null, primary key | 
| 	transportation_date          | timestamp     | planned transportation date                   | 
| 	address_from                 | varchar(80)   | address from                                  | 
| 	address_to                   | varchar(80)   | address to                                    | 
| 	weight_cargo                 | float         | cargo weight                                  | 
| 	waybill_number               | varchar(50)   | waybill number                                |
| 	waybill_cost                 | decimal(10,2) | cost according to waybill                     |
| 	distance_traveled_kilometers | int           | distance traveled, kilometers                 |
| 	fuel_costs_traveled          | decimal(7,2)  | fuel costs per trip                           |
| 	task_status                  | varchar(50)   | current status of task                        |
| 	comment_if_task_canceled     | varchar(150)  | comment if task is cancelled                  |
| 	created_at                   | timestamp     | timestamp when the task was added             |
| 	contract_id                  | binary(16)    | id of contract                                |
| 	company_id                   | binary(16)    | id of counterparty company                    |
| 	vehicle_id                   | binary(16)    | id of vehicle                                 |
| 	driver_id                    | binary(16)    | id of driver                                  |
