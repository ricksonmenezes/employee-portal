
create table employee
(
    code                        int(11) AUTO_INCREMENT PRIMARY KEY,
    first_name              varchar(200) not null,
    last_name               varchar(100) not null,
    middle_name             varchar(100) default null,
    birth_date            datetime not null,
    gender            enum('MALE','FEMALE','OTHER') not null,
    marital_status    enum('MARRIED','SINGLE','WIDOWED') not null,
    hired_date datetime not null,
    position varchar(50) not null,
    created_at                timestamp,
    updated_at                timestamp

);

CREATE TABLE employee_contact (
  id INT AUTO_INCREMENT PRIMARY KEY,
  code INT NOT NULL,
  contact_no VARCHAR(20) NOT NULL,
  is_primary BOOLEAN NOT NULL DEFAULT FALSE,
  created_at                timestamp,
  updated_at                timestamp,
  FOREIGN KEY (code) REFERENCES employee(code),
  UNIQUE (code, contact_no));

CREATE TABLE employee_address (
  id INT(11) AUTO_INCREMENT PRIMARY KEY,
  code INT(11) NOT NULL,
  address1 VARCHAR(50) NOT NULL,
  address2 VARCHAR(50) NOT NULL,
  is_primary BOOLEAN NOT NULL DEFAULT FALSE,
  created_at                timestamp,
  updated_at                timestamp,
  FOREIGN KEY (code) REFERENCES employee(code));