## Use to run mysql db docker image
# docker run --name mysqldb -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -d mysql

#Create Databases
CREATE DATABASE spring_dev;
CREATE DATABASE spring_prod;

#Create Database service accounts
CREATE USER 'spring_dev_user'@'localhost' IDENTIFIED BY 'prueba';
CREATE USER 'spring_prod_user'@'localhost' IDENTIFIED BY 'prueba';

#Database grants
GRANT SELECT ON spring_dev.* to 'spring_dev_user'@'localhost';
GRANT INSERT ON spring_dev.* to 'spring_dev_user'@'localhost';
GRANT DELETE ON spring_dev.* to 'spring_dev_user'@'localhost';
GRANT UPDATE ON spring_dev.* to 'spring_dev_user'@'localhost';
GRANT SELECT ON spring_prod.* to 'spring_prod_user'@'localhost';
GRANT INSERT ON spring_prod.* to 'spring_prod_user'@'localhost';
GRANT DELETE ON spring_prod.* to 'spring_prod_user'@'localhost';
GRANT UPDATE ON spring_prod.* to 'spring_prod_user'@'localhost';

Modificar