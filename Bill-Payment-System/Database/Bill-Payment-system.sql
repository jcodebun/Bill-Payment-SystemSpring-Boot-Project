/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 8.0.22 : Database - bill-payment-system
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bill-payment-system` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `bill-payment-system`;

/*Table structure for table `bill` */

DROP TABLE IF EXISTS `bill`;

CREATE TABLE `bill` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_datetime` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_datetime` datetime DEFAULT NULL,
  `date` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `vendor_id` bigint DEFAULT NULL,
  `user` bigint NOT NULL,
  `vendor` bigint NOT NULL,
  `amount` bigint DEFAULT NULL,
  `vendor_type` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKveu65lq3jrtx0jfn6ewnbcid` (`user`),
  KEY `FK4q1vsrx51y7yiqmmf7qgfuxd3` (`vendor`),
  CONSTRAINT `FK4q1vsrx51y7yiqmmf7qgfuxd3` FOREIGN KEY (`vendor`) REFERENCES `vendor` (`id`),
  CONSTRAINT `FKveu65lq3jrtx0jfn6ewnbcid` FOREIGN KEY (`user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `bill` */

insert  into `bill`(`id`,`created_by`,`created_datetime`,`modified_by`,`modified_datetime`,`date`,`status`,`user_id`,`vendor_id`,`user`,`vendor`,`amount`,`vendor_type`) values 
(1,'root','2021-07-16 15:31:20','root','2021-07-16 15:31:20','2021-07-16','Paid',2,1,2,1,2500,NULL),
(2,'root','2021-07-16 23:09:18','root','2021-07-16 23:09:18','2021-07-16','Paid',2,1,2,1,2300,'Electricity'),
(3,'root','2021-07-16 23:12:55','root','2021-07-16 23:12:55','2021-07-16','Paid',2,1,2,1,500,'Telephone'),
(4,'root','2021-07-18 10:04:56','root','2021-07-18 10:04:56','2021-07-18','Paid',2,2,2,2,0,'Credit Card'),
(5,'root','2021-07-18 11:31:57','root','2021-07-18 11:31:57','2021-07-18','Paid',2,2,2,2,250,'Credit Card');

/*Table structure for table `feed_back` */

DROP TABLE IF EXISTS `feed_back`;

CREATE TABLE `feed_back` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_datetime` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_datetime` datetime DEFAULT NULL,
  `feed_by` varchar(225) DEFAULT NULL,
  `feedback` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `feed_back` */

insert  into `feed_back`(`id`,`created_by`,`created_datetime`,`modified_by`,`modified_datetime`,`feed_by`,`feedback`) values 
(1,'ginihiti@gmail.com','2021-07-16 19:22:52','ginihiti@gmail.com','2021-07-16 19:22:52','User','sdssd'),
(2,'Admin@gmail.com','2021-07-16 19:40:20','Admin@gmail.com','2021-07-16 19:40:20','Admin','adcsdv'),
(3,'root','2021-07-16 19:40:40','root','2021-07-16 19:40:40','Vendor','svdsvdfv');

/*Table structure for table `help` */

DROP TABLE IF EXISTS `help`;

CREATE TABLE `help` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_datetime` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_datetime` datetime DEFAULT NULL,
  `comment` varchar(2000) DEFAULT NULL,
  `questions` varchar(755) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `help` */

insert  into `help`(`id`,`created_by`,`created_datetime`,`modified_by`,`modified_datetime`,`comment`,`questions`) values 
(1,'Admin@gmail.com','2021-07-16 16:54:07','Admin@gmail.com','2021-07-16 16:54:07','dvsdvsdvsdfv','dcsdvsdvsv');

/*Table structure for table `invoice` */

DROP TABLE IF EXISTS `invoice`;

CREATE TABLE `invoice` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_datetime` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_datetime` datetime DEFAULT NULL,
  `bill_id` bigint DEFAULT NULL,
  `invoice_no` bigint DEFAULT NULL,
  `paid_amount` bigint DEFAULT NULL,
  `usre_id` bigint DEFAULT NULL,
  `vendor_type` varchar(225) DEFAULT NULL,
  `bill` bigint NOT NULL,
  `user` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKirjo03k0dxly2n1af24yhmboq` (`bill`),
  KEY `FKlt4ll3ngyfo5w5wmjqw7s94k3` (`user`),
  CONSTRAINT `FKirjo03k0dxly2n1af24yhmboq` FOREIGN KEY (`bill`) REFERENCES `bill` (`id`),
  CONSTRAINT `FKlt4ll3ngyfo5w5wmjqw7s94k3` FOREIGN KEY (`user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `invoice` */

insert  into `invoice`(`id`,`created_by`,`created_datetime`,`modified_by`,`modified_datetime`,`bill_id`,`invoice_no`,`paid_amount`,`usre_id`,`vendor_type`,`bill`,`user`) values 
(1,'ginihiti@gmail.com','2021-07-16 23:10:05','ginihiti@gmail.com','2021-07-16 23:10:05',2,797728449772,0,2,'Electricity',2,2),
(2,'ginihiti@gmail.com','2021-07-16 23:10:38','ginihiti@gmail.com','2021-07-16 23:10:38',2,388100665228,0,2,'Electricity',2,2),
(3,'ginihiti@gmail.com','2021-07-16 23:13:10','ginihiti@gmail.com','2021-07-16 23:13:10',3,381410068594,500,2,'Telephone',3,2),
(4,'ginihiti@gmail.com','2021-07-18 09:59:33','ginihiti@gmail.com','2021-07-18 09:59:33',1,304354418730,2500,2,NULL,1,2),
(5,'ginihiti@gmail.com','2021-07-18 11:33:32','ginihiti@gmail.com','2021-07-18 11:33:32',5,834619626579,250,2,'Credit Card',5,2),
(6,'ginihiti@gmail.com','2021-07-18 11:42:56','ginihiti@gmail.com','2021-07-18 11:42:56',4,152119395514,0,2,'Credit Card',4,2);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_datetime` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_datetime` datetime DEFAULT NULL,
  `aadhar` varchar(225) DEFAULT NULL,
  `age` varchar(225) DEFAULT NULL,
  `contact_no` varchar(225) DEFAULT NULL,
  `first_name` varchar(225) DEFAULT NULL,
  `gedner` varchar(225) DEFAULT NULL,
  `last_name` varchar(225) DEFAULT NULL,
  `pan` varchar(225) DEFAULT NULL,
  `password` varchar(225) DEFAULT NULL,
  `role_id` bigint DEFAULT NULL,
  `user_name` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user` */

insert  into `user`(`id`,`created_by`,`created_datetime`,`modified_by`,`modified_datetime`,`aadhar`,`age`,`contact_no`,`first_name`,`gedner`,`last_name`,`pan`,`password`,`role_id`,`user_name`) values 
(1,'root','2021-07-15 10:20:10','root','2021-07-15 10:20:10','1000101020','25','9685454658','Brielle','Male','Cote','8523565442','Pa$$w0rd!',1,'Admin@gmail.com'),
(2,'root','2021-07-16 15:25:36','root','2021-07-16 15:25:36','968545658525','25','9685455845','Jeremy','Male','Frost','6363332522','Pa$$w0rd!',2,'ginihiti@gmail.com');

/*Table structure for table `vendor` */

DROP TABLE IF EXISTS `vendor`;

CREATE TABLE `vendor` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_datetime` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_datetime` datetime DEFAULT NULL,
  `address` varchar(225) DEFAULT NULL,
  `certificate_isseed_date` date DEFAULT NULL,
  `certificate_validity_date` date DEFAULT NULL,
  `company_reg_no` bigint DEFAULT NULL,
  `contact_no` varchar(225) DEFAULT NULL,
  `country` varchar(225) DEFAULT NULL,
  `email_id` varchar(225) DEFAULT NULL,
  `name` varchar(225) DEFAULT NULL,
  `password` varchar(225) DEFAULT NULL,
  `payment_gateway` varchar(225) DEFAULT NULL,
  `state` varchar(225) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `user_name` varchar(225) DEFAULT NULL,
  `vendor_type` varchar(225) DEFAULT NULL,
  `web_site` varchar(225) DEFAULT NULL,
  `year_of_establishment` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `vendor` */

insert  into `vendor`(`id`,`created_by`,`created_datetime`,`modified_by`,`modified_datetime`,`address`,`certificate_isseed_date`,`certificate_validity_date`,`company_reg_no`,`contact_no`,`country`,`email_id`,`name`,`password`,`payment_gateway`,`state`,`status`,`user_name`,`vendor_type`,`web_site`,`year_of_establishment`) values 
(1,'root','2021-07-15 18:21:01','root','2021-07-15 18:21:01','Ipsa molestias ab v','1980-03-25','1984-05-25',1020002040,'9685456585','Obcaecati id qui et ','vadodugyki@mailinator.com','Wynter Dodson','Pa$$w0rd!','Eu in natus incidunt','Approve','Approve','Rypanazyho@gmail.com','Sit quae molestiae d','https://www.zovexym.co',NULL),
(2,'root','2021-07-18 10:02:48','root','2021-07-18 10:02:48','dfdfsf','2022-06-07','2026-06-07',0,'8585858585','india','vendor@gmail.com','bhupiVendor','Demo@123','fof','Approve','Approve','bhupiVendor123','Credit Card','dfds',NULL),
(3,'root','2021-07-18 11:27:20','root','2021-07-18 11:27:20','ftret','2022-05-08','2027-05-08',123434535,'8585858585','India','adf@gmail.com','vendor Name','De3mo@123','Gpay','Pending','Pending','Vendor123','Credit Card','www.codebun.com',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
