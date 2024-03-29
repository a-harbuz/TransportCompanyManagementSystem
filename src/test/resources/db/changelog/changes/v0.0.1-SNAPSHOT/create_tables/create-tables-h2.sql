CREATE TABLE IF NOT EXISTS vehicle (
                                       id  VARCHAR(36) PRIMARY KEY,
                                       vehicle_type VARCHAR(50) NOT NULL,
                                       name VARCHAR(50) NOT NULL,
                                       model VARCHAR(50) NOT NULL,
                                       year_manufacture VARCHAR(20),
                                       car_number VARCHAR(20) NOT NULL,
                                       initial_kilometers INT NOT NULL,
                                       price FLOAT,
                                       vehicle_status VARCHAR(50) NOT NULL,
                                       created_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS company (
                                       id  VARCHAR(36) PRIMARY KEY,
                                       company_name VARCHAR(50) NOT NULL,
                                       contact_first_name VARCHAR(20) NOT NULL,
                                       contact_last_name VARCHAR(20) NOT NULL,
                                       email VARCHAR(60),
                                       address VARCHAR(80),
                                       phone VARCHAR(20) NOT NULL,
                                       created_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS maintenance (
                                           id  VARCHAR(36) PRIMARY KEY,
                                           service_type VARCHAR(50) NOT NULL,
                                           maintenance_cost DECIMAL(8,2) NOT NULL,
                                           maintenance_kilometers INT NOT NULL,
                                           created_at	TIMESTAMP NOT NULL,
                                           vehicle_id 	 VARCHAR(36) NOT NULL,
                                           company_id	 VARCHAR(36) NOT NULL,
                                           FOREIGN KEY (vehicle_id) REFERENCES vehicle (id),
                                           FOREIGN KEY (company_id) REFERENCES company (id)
);

CREATE TABLE IF NOT EXISTS contract (
                                        id  VARCHAR(36) PRIMARY KEY NOT NULL,
                                        contract_number	VARCHAR(30) NOT NULL,
                                        contract_name VARCHAR(80),
                                        cost_transportation_under_contract DECIMAL(10,2),
                                        total_cost_transported_goods DECIMAL(11,2),
                                        contract_status VARCHAR(50) NOT NULL,
                                        created_at	TIMESTAMP NOT NULL,
                                        company_id	 VARCHAR(36) NOT NULL,
                                        FOREIGN KEY (company_id) REFERENCES company (id)
);

CREATE TABLE IF NOT EXISTS employee_info (
                                             id  VARCHAR(36) PRIMARY KEY NOT NULL,
                                             address VARCHAR(80),
                                             phone VARCHAR(20),
                                             driving_license_category VARCHAR(20),
                                             login VARCHAR(50),
                                             password VARCHAR(50),
                                             created_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS employee (
                                        id  VARCHAR(36) PRIMARY KEY NOT NULL,
                                        first_name	VARCHAR(20) NOT NULL,
                                        last_name	VARCHAR(20) NOT NULL,
                                        is_driver	BOOLEAN DEFAULT FALSE,
                                        is_working	BOOLEAN DEFAULT FALSE,
                                        created_at	TIMESTAMP NOT NULL,
                                        employee_info_id  VARCHAR(36) NOT NULL,
                                        FOREIGN KEY (employee_info_id) REFERENCES employee_info (id)
);

CREATE TABLE IF NOT EXISTS task (
                                    id  VARCHAR(36) PRIMARY KEY NOT NULL,
                                    transportation_date DATE NOT NULL,
                                    address_from VARCHAR(80) NOT NULL,
                                    address_to VARCHAR(80) NOT NULL,
                                    weight_cargo FLOAT,
                                    waybill_number VARCHAR(50) NOT NULL,
                                    waybill_cost DECIMAL(10,2) NOT NULL,
                                    distance_traveled_kilometers int,
                                    fuel_costs_traveled	DECIMAL(7,2),
                                    task_status	VARCHAR(50) NOT NULL,
                                    comment_if_task_canceled VARCHAR(150),
                                    created_at	TIMESTAMP,
                                    contract_id	 VARCHAR(36) NOT NULL,
                                    company_id	 VARCHAR(36) NOT NULL,
                                    vehicle_id	 VARCHAR(36) NOT NULL,
                                    employee_id	 VARCHAR(36) NOT NULL,
                                    FOREIGN KEY (contract_id) REFERENCES contract (id),
                                    FOREIGN KEY (company_id) REFERENCES company (id),
                                    FOREIGN KEY (vehicle_id) REFERENCES vehicle (id),
                                    FOREIGN KEY (employee_id) REFERENCES employee (id)
);

CREATE TABLE IF NOT EXISTS role (
                                    id  VARCHAR(36) PRIMARY KEY NOT NULL,
                                    role_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS authority (
                                         id  VARCHAR(36) PRIMARY KEY NOT NULL,
                                         authority_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS employee_info_role (
                                                  employee_info_id  VARCHAR(36) NOT NULL,
                                                  role_id  VARCHAR(36) NOT NULL,
                                                  PRIMARY KEY (employee_info_id, role_id),
                                                  FOREIGN KEY (employee_info_id) REFERENCES employee_info (id),
                                                  FOREIGN KEY (role_id) REFERENCES role (id)
);

CREATE TABLE IF NOT EXISTS role_authority (
                                role_id  VARCHAR(36) NOT NULL,
                                authority_id  VARCHAR(36) NOT NULL,
                                PRIMARY KEY (role_id, authority_id),
                                FOREIGN KEY (role_id) REFERENCES role (id),
                                FOREIGN KEY (authority_id) REFERENCES authority (id)
);
