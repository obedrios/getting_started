 # Hibernate Standalone Quick Start Project

Hibernate quickstart project for Java Standalone apps. Create a table in your Postgres database in order to use this example.

````postgresql
create table EMPLOYEE (
   id serial PRIMARY KEY,
   first_name VARCHAR(20) default NULL,
   last_name  VARCHAR(20) default NULL,
   email      VARCHAR(20)  default NULL);
````

