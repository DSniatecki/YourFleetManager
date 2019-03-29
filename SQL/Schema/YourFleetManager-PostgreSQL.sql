-- 1. Create database named "web_your_fleet_manager"
-- 2. In a previously created database open new sql query
-- 3. Run this script in that query


CREATE TABLE address(
	id BIGSERIAL PRIMARY KEY,
	street VARCHAR(80),
	building_number VARCHAR(20),
	city VARCHAR(80),
	zip_code VARCHAR(20),
	country VARCHAR(40)
);

CREATE TABLE contact_details(
 	id BIGSERIAL PRIMARY KEY,
	telephone_number VARCHAR(20),
	email_address VARCHAR(40)
);

CREATE TABLE company(
	id BIGSERIAL PRIMARY KEY,
	c_name VARCHAR(100),
    contact_details_id BIGINT,
	address_id BIGINT,
    
    CONSTRAINT fk_c_contact_details_id
    FOREIGN KEY (contact_details_id)
    REFERENCES contact_details ( id),
	
    CONSTRAINT fk_address_id
    FOREIGN KEY (address_id)
    REFERENCES address ( id)
);

CREATE TABLE company_department(
	id BIGSERIAL PRIMARY KEY,
	c_d_name VARCHAR(100),
    company_id BIGINT,
    contact_details_id BIGINT,
   
	CONSTRAINT fk_company_id
    FOREIGN KEY (company_id)
	REFERENCES company ( id),
	
	CONSTRAINT fk_cd_contact_details_id
    FOREIGN KEY (contact_details_id)
    REFERENCES contact_details ( id)
   
);

CREATE TABLE vehicle_responder(
	id BIGSERIAL PRIMARY KEY,
	first_name VARCHAR(60),
	last_name VARCHAR(60),
	contact_details_id BIGINT,
    
    CONSTRAINT fk_v_r_contact_details_id
    FOREIGN KEY (contact_details_id)
    REFERENCES contact_details ( id)
);

CREATE TABLE car(
	id BIGSERIAL PRIMARY KEY,
    company_department_id BIGINT,
	brand VARCHAR(60),
	model VARCHAR(60),
	registration_number VARCHAR(40),
	vehicle_responder_id BIGINT,
    
    
	CONSTRAINT fk_company_department_id
    FOREIGN KEY (company_department_id)
	REFERENCES company_department ( id),
	
    CONSTRAINT fk_vehicle_responder_id
    FOREIGN KEY (vehicle_responder_id)
    REFERENCES vehicle_responder ( id)
);