-- MySQL dump 10.13  Distrib 8.0.14, for Win64 (x86_64)
--
-- Host: localhost    Database: web_your_fleet_manager
-- ------------------------------------------------------
-- Server version	8.0.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `address`
--

LOCK TABLES address WRITE;
/*!40000 ALTER TABLE address DISABLE KEYS */;
INSERT INTO address (id, street, building_number, city, zip_code, country) VALUES (4,'Kazimierza Wielkiego','56','Wroclaw','50-670','Poland');
INSERT INTO address (id, street, building_number, city, zip_code, country) VALUES (5,'Nottingham Place','37','London','WC2N 5DU','United Kingdom');
/*!40000 ALTER TABLE address ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `car`
--

LOCK TABLES car WRITE;
/*!40000 ALTER TABLE car DISABLE KEYS */;
INSERT INTO car (id, company_department_id, brand, model, production_year, registration_number, vehicle_responder_id) VALUES (2,2,'Toyota','Auris',2016,'DW 783421',2);
INSERT INTO car (id, company_department_id, brand, model, production_year, registration_number, vehicle_responder_id) VALUES (3,2,'Toyota','Auris',2015,'DW 84231',3);
INSERT INTO car (id, company_department_id, brand, model, production_year, registration_number, vehicle_responder_id) VALUES (4,2,'Ford','Focus',2017,'DW 92312',4);
INSERT INTO car (id, company_department_id, brand, model, production_year, registration_number, vehicle_responder_id) VALUES (5,2,'Ford','Fiesta',2012,'DW 482451',5);
INSERT INTO car (id, company_department_id, brand, model, production_year, registration_number, vehicle_responder_id) VALUES (6,3,'Renault','Megane',2016,'DW 86421',6);
INSERT INTO car (id, company_department_id, brand, model, production_year, registration_number, vehicle_responder_id) VALUES (7,3,'Renault','clio',2015,'DW 723123',7);
INSERT INTO car (id, company_department_id, brand, model, production_year, registration_number, vehicle_responder_id) VALUES (8,4,'Mini','Cooper',2012,'MA57 ERP',8);
INSERT INTO car (id, company_department_id, brand, model, production_year, registration_number, vehicle_responder_id) VALUES (9,5,'Toyota','Prius',2015,'MA51 ESA',9);
/*!40000 ALTER TABLE car ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `company`
--

LOCK TABLES company WRITE;
/*!40000 ALTER TABLE company DISABLE KEYS */;
INSERT INTO company (id, c_name, contact_details_id, address_id) VALUES (4,'Super Soft ',7,4);
INSERT INTO company (id, c_name, contact_details_id, address_id) VALUES (5,'Dream Systems',16,5);
/*!40000 ALTER TABLE company ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `company_department`
--

LOCK TABLES company_department WRITE;
/*!40000 ALTER TABLE company_department DISABLE KEYS */;
INSERT INTO company_department (id, c_d_name, company_id, contact_details_id) VALUES (2,'IT Department',4,8);
INSERT INTO company_department (id, c_d_name, company_id, contact_details_id) VALUES (3,'HR Department',4,9);
INSERT INTO company_department (id, c_d_name, company_id, contact_details_id) VALUES (4,'IT Department',5,17);
INSERT INTO company_department (id, c_d_name, company_id, contact_details_id) VALUES (5,'Supply Department',5,18);
/*!40000 ALTER TABLE company_department ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `contact_details`
--

LOCK TABLES contact_details WRITE;
/*!40000 ALTER TABLE contact_details DISABLE KEYS */;
INSERT INTO contact_details (id, telephone_number, email_address) VALUES (7,'743-234-923','supersoft@gmail.com');
INSERT INTO contact_details (id, telephone_number, email_address) VALUES (8,'865-349-231','supersoft.it@gmail.com');
INSERT INTO contact_details (id, telephone_number, email_address) VALUES (9,'867 433 292','supersoft.hr@gmail.com');
INSERT INTO contact_details (id, telephone_number, email_address) VALUES (10,'865-673-342','mariusz.nowak@gmail.com');
INSERT INTO contact_details (id, telephone_number, email_address) VALUES (11,'873 321 132','zbigniew.kowalczyk@gmail.com');
INSERT INTO contact_details (id, telephone_number, email_address) VALUES (12,'873-123-231','marcin.nowak@gmail.com');
INSERT INTO contact_details (id, telephone_number, email_address) VALUES (13,'893-231-233','ewelina.nowak@gmail.com');
INSERT INTO contact_details (id, telephone_number, email_address) VALUES (14,'634-234-923','nowakmonika@gmail.com');
INSERT INTO contact_details (id, telephone_number, email_address) VALUES (15,'782-231-321','kowalski.tadeusz@gmail.com');
INSERT INTO contact_details (id, telephone_number, email_address) VALUES (16,'756-321-231','dream-systems@gmail.com');
INSERT INTO contact_details (id, telephone_number, email_address) VALUES (17,'207-946-0676','dream.it@gmail.com');
INSERT INTO contact_details (id, telephone_number, email_address) VALUES (18,'983-312-3211','dream.supply@gmail.com');
INSERT INTO contact_details (id, telephone_number, email_address) VALUES (19,'465-243-3243','oliver.smith@gmail.com');
INSERT INTO contact_details (id, telephone_number, email_address) VALUES (20,'465-234-2434','harry.williams@gmail.com');
/*!40000 ALTER TABLE contact_details ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `vehicle_responder`
--

LOCK TABLES vehicle_responder WRITE;
/*!40000 ALTER TABLE vehicle_responder DISABLE KEYS */;
INSERT INTO vehicle_responder (id, first_name, last_name, contact_details_id) VALUES (2,'Mariusz','Nowak',10);
INSERT INTO vehicle_responder (id, first_name, last_name, contact_details_id) VALUES (3,'Zbigniew','Kowalczyk',11);
INSERT INTO vehicle_responder (id, first_name, last_name, contact_details_id) VALUES (4,'Marcin','Nowak',12);
INSERT INTO vehicle_responder (id, first_name, last_name, contact_details_id) VALUES (5,'Ewelina','Nowak',13);
INSERT INTO vehicle_responder (id, first_name, last_name, contact_details_id) VALUES (6,'Monika','Nowak',14);
INSERT INTO vehicle_responder (id, first_name, last_name, contact_details_id) VALUES (7,'Tadeusz','Kowalski',15);
INSERT INTO vehicle_responder (id, first_name, last_name, contact_details_id) VALUES (8,'Oliver','Smith',19);
INSERT INTO vehicle_responder (id, first_name, last_name, contact_details_id) VALUES (9,'Harry','Williams',20);
/*!40000 ALTER TABLE vehicle_responder ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
