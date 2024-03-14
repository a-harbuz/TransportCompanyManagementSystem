create table if not exists company (
                                       id binary(16) primary key,
                                       company_name varchar(50) not null ,
                                       contact_first_name varchar(20) NOT NULL,
                                       contact_last_name varchar(20) NOT NULL,
                                       email varchar(60),
                                       address varchar(80),
                                       phone varchar(20) NOT NULL,
                                       created_at timestamp NOT NULL
);
