create table Clients (
	ID char(10) not null
		constraint CLIENTS_PK
			primary key,
	first_name varchar(15) not null,
	last_name varchar(15) not null,
	email varchar(50),
	phone varchar(13) not null,
	birthday date not null,
	driver_lic varchar(50) not null,
	CONSTRAINT CK_CL_VALID_ID CHECK ( LENGTH(ID) = 10 ),
	CONSTRAINT CK_CL_VALID_BDAY CHECK (birthday > '1990-01-01')
);

create table ClientEmployee (
	ID int not null
		constraint CLIENTS_PK
			primary key,
	first_name varchar(15) not null,
	last_name varchar(15) not null,
	email varchar(50),
	phone varchar(13) not null,
	birthday date not null,
	CONSTRAINT CK_CLEMP_VALID_ID CHECK ( LENGTH(ID) = 10 ),
	CONSTRAINT CK_CLEMP_VALID_BDAY CHECK (birthday > '1990-01-01')
);

create table Employees(
	ID varchar(50) not null
		constraint CLIENTS_PK
			primary key,
	first_name varchar(15) not null,
	last_name varchar(15) not null,
	email varchar(50),
	phone varchar(13) not null,
	birthday date not null,
	work_number varchar(50) not null,
	branch_name varchar(50) not null,
	manager_id varchar(50),
	CONSTRAINT CK_EMP_VALID_ID CHECK ( LENGTH(ID) = 10 ),
	CONSTRAINT CK_EMP_VALID_BDAY CHECK (birthday > '1990-01-01')
);

create table Vehicles (
	license_plate varchar(10) not null
		constraint VEHICLES_PK
			primary key,
	model varchar(50) not null,
	is_available int DEFAULT 1,
	mileage int default 0,
	insurance varchar(50) not null,
	price double not null,
	branch_name varchar(50) not null,
	CONSTRAINT CK_VEH_AVAILABLE check ( is_available = 0 OR is_available = 1 ),
	CONSTRAINT CK_VEH_MILEAGE check ( mileage >= 0 )
);

create table Branches (
	name varchar(50) not null
		constraint BRANCHES_PK
			primary key,
	address varchar(50) not null,
	manager_id varchar(50)
);

create table Requires (
    rdate date not null,
    client_id varchar(50) not null,
    branch_name varchar(50) not null,
    model varchar(50),
    max_price double,
    PRIMARY KEY (rdate, client_id, branch_name),
    constraint FK_REQUIRES_CLIENTID foreign key (client_id) references Clients(ID),
    constraint FK_REQUIRES_BRANCHNAME foreign key (branch_name) references Branches(name)
);

create table Deals (
    start_date date not null ,
    end_date date,
    payment varchar(50),
    client_id varchar(50) not null,
    employee_id varchar(50) not null,
    vehicle_licPlate varchar(50) not null,
    branch_name varchar(50) not null,
    primary key (client_id,employee_id,vehicle_licPlate,branch_name,start_date),
    constraint FK_DEALS_EMPLOYEEID foreign key (employee_id) references Employees(ID),
    constraint FK_DEALS_CLIENTID foreign key (client_id) references Clients(ID),
    constraint FK_DEALS_VEHLIC foreign key (vehicle_licPlate) references Vehicles(license_plate),
    constraint FK_DEALS_BRANCHNAME foreign key (branch_name) references Branches(name)
);

ALTER TABLE Employees
add constraint FK_BRANCHNAME_BRANCH
foreign key (branch_name) references Branches(name);

ALTER TABLE Employees
add constraint FK_MANAGERID_EMPLOYEE
foreign key (manager_id) references Employees(ID);

ALTER TABLE Vehicles
add constraint FK_BRANCHNAME_BRANCHES
foreign key (branch_name) references Branches(name);
