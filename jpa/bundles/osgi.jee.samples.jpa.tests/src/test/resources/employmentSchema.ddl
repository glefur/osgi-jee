DROP TABLE PHONE;
CREATE TABLE PHONE (
ID BIGINT,
AREACODE VARCHAR(255),
NUMBER VARCHAR(255),
TYPE VARCHAR(255),
PRIMARY KEY (ID)
);
DROP TABLE EMPLOYEE_EMPLOYEE;
CREATE TABLE EMPLOYEE_EMPLOYEE (
EMPLOYEE_ID BIGINT,
MANAGEDEMPLOYEES_ID BIGINT
);
DROP TABLE EMPLOYEE;
CREATE TABLE EMPLOYEE (
ID BIGINT,
ENDDATE TIMESTAMP,
STARTDATE TIMESTAMP,
FIRSTNAME VARCHAR(255),
LASTNAME VARCHAR(255),
SALARY NUMERIC,
ADDRESS_ID BIGINT,
MANAGER_ID BIGINT,
PRIMARY KEY (ID)
);
DROP TABLE ADDRESS;
CREATE TABLE ADDRESS (
ID BIGINT,
CITY VARCHAR(255),
COUNTRY VARCHAR(255),
POSTALCODE VARCHAR(255),
PROVINCE VARCHAR(255),
STREET VARCHAR(255),
PRIMARY KEY (ID)
);
DROP TABLE PROJECT;
CREATE TABLE PROJECT (
DTYPE VARCHAR(31),
ID BIGINT,
NAME VARCHAR(255),
BUDGET NUMERIC,
PRIMARY KEY (ID)
);
DROP TABLE EMPLOYEE_PROJECT;
CREATE TABLE EMPLOYEE_PROJECT (
EMPLOYEE_ID BIGINT,
PROJECTS_ID BIGINT
);
DROP TABLE EMPLOYEE_PHONE;
CREATE TABLE EMPLOYEE_PHONE (
EMPLOYEE_ID BIGINT,
PHONES_ID BIGINT
);
ALTER TABLE EMPLOYEE_EMPLOYEE ADD CONSTRAINT FK_EMPLOYEE_EMPLOYEE_EMPLOYEE FOREIGN KEY (EMPLOYEE_ID) REFERENCES EMPLOYEE (ID);
ALTER TABLE EMPLOYEE_EMPLOYEE ADD CONSTRAINT FK_EMPLOYEE_EMPLOYEE_EMPLOYEE FOREIGN KEY (MANAGEDEMPLOYEES_ID) REFERENCES EMPLOYEE (ID);
ALTER TABLE EMPLOYEE ADD CONSTRAINT FK_EMPLOYEE_ADDRESS FOREIGN KEY (ADDRESS_ID) REFERENCES ADDRESS (ID);
ALTER TABLE EMPLOYEE ADD CONSTRAINT FK_EMPLOYEE_EMPLOYEE FOREIGN KEY (MANAGER_ID) REFERENCES EMPLOYEE (ID);
ALTER TABLE EMPLOYEE_PROJECT ADD CONSTRAINT FK_EMPLOYEE_PROJECT_EMPLOYEE FOREIGN KEY (EMPLOYEE_ID) REFERENCES EMPLOYEE (ID);
ALTER TABLE EMPLOYEE_PROJECT ADD CONSTRAINT FK_EMPLOYEE_PROJECT_PROJECT FOREIGN KEY (PROJECTS_ID) REFERENCES PROJECT (ID);
ALTER TABLE EMPLOYEE_PHONE ADD CONSTRAINT FK_EMPLOYEE_PHONE_EMPLOYEE FOREIGN KEY (EMPLOYEE_ID) REFERENCES EMPLOYEE (ID);
ALTER TABLE EMPLOYEE_PHONE ADD CONSTRAINT FK_EMPLOYEE_PHONE_PHONE FOREIGN KEY (PHONES_ID) REFERENCES PHONE (ID);