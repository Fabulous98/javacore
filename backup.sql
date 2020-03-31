-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.7.17-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema 21_buimanhdung_manageuser
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ 21_buimanhdung_manageuser;
USE 21_buimanhdung_manageuser;

--
-- Table structure for table `21_buimanhdung_manageuser`.`mst_group`
--

DROP TABLE IF EXISTS `mst_group`;
CREATE TABLE `mst_group` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(255) NOT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `21_buimanhdung_manageuser`.`mst_group`
--

/*!40000 ALTER TABLE `mst_group` DISABLE KEYS */;
INSERT INTO `mst_group` (`group_id`,`group_name`) VALUES 
 (1,'Phòng phát triển số 1'),
 (2,'Phòng phát triển số 2'),
 (3,'Phòng phát triển số 3'),
 (4,'Phòng phát triển số 4');
/*!40000 ALTER TABLE `mst_group` ENABLE KEYS */;


--
-- Table structure for table `21_buimanhdung_manageuser`.`mst_japan`
--

DROP TABLE IF EXISTS `mst_japan`;
CREATE TABLE `mst_japan` (
  `code_level` varchar(2) NOT NULL,
  `name_level` varchar(255) NOT NULL,
  PRIMARY KEY (`code_level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `21_buimanhdung_manageuser`.`mst_japan`
--

/*!40000 ALTER TABLE `mst_japan` DISABLE KEYS */;
INSERT INTO `mst_japan` (`code_level`,`name_level`) VALUES 
 ('N1','Trình độ tiếng Nhật cấp 1'),
 ('N2','Trình độ tiếng Nhật cấp 2'),
 ('N3','Trình độ tiếng Nhật cấp 3'),
 ('N4','Trình độ tiếng Nhật cấp 4'),
 ('N5','Trình độ tiếng Nhật cấp 5');
/*!40000 ALTER TABLE `mst_japan` ENABLE KEYS */;


--
-- Table structure for table `21_buimanhdung_manageuser`.`tbl_detail_user_japan`
--

DROP TABLE IF EXISTS `tbl_detail_user_japan`;
CREATE TABLE `tbl_detail_user_japan` (
  `detail_user_japan_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `code_level` varchar(15) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `total` int(11) NOT NULL,
  PRIMARY KEY (`detail_user_japan_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `tbl_detail_user_japan_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `21_buimanhdung_manageuser`.`tbl_detail_user_japan`
--

/*!40000 ALTER TABLE `tbl_detail_user_japan` DISABLE KEYS */;
INSERT INTO `tbl_detail_user_japan` (`detail_user_japan_id`,`user_id`,`code_level`,`start_date`,`end_date`,`total`) VALUES 
 (1,1,'N5','2005-07-08','2006-07-08',90),
 (2,4,'N1','2005-05-20','2006-05-20',100),
 (3,2,'N2','2005-05-21','2006-05-21',98),
 (4,3,'N3','2005-05-22','2006-05-22',88),
 (5,5,'N5','2005-05-23','2006-05-23',100);
/*!40000 ALTER TABLE `tbl_detail_user_japan` ENABLE KEYS */;


--
-- Table structure for table `21_buimanhdung_manageuser`.`tbl_user`
--

DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `login_name` varchar(15) NOT NULL,
  `password` varchar(50) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `full_name_kana` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `tel` varchar(15) NOT NULL,
  `birthday` date NOT NULL,
  `rule` int(1) NOT NULL,
  `salt` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `21_buimanhdung_manageuser`.`tbl_user`
--

/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
INSERT INTO `tbl_user` (`user_id`,`group_id`,`login_name`,`password`,`full_name`,`full_name_kana`,`email`,`tel`,`birthday`,`rule`,`salt`) VALUES 
 (1,1,'ntmhuong','6efa2190956f362117b0b7d66d8bb15911a8b333','Nguyễn Thị Mai Hương','Hương Kana','huong@gmail.com','123456789','1983-07-08',0,'abcxyz1'),
 (2,1,'hieudt','6efa2190956f362117b0b7d66d8bb15911a8b333','Đoàn Trọng Hiếu','Hiếu Kana','hieu@gmail.com','987456123','1983-08-08',1,'abcxyz2'),
 (3,2,'longth','6efa2190956f362117b0b7d66d8bb15911a8b333','Tần Hoàng Long','Long Kana','long@gmail.com','654123789','1983-09-08',1,'abcxyz3'),
 (4,2,'dungdv','6efa2190956f362117b0b7d66d8bb15911a8b333','Đỗ Văn Dũng','Dũng Kana','dung@gmail.com','789123456','1983-10-08',1,'abcxyz4'),
 (5,3,'phuongnv','6efa2190956f362117b0b7d66d8bb15911a8b333','Nguyễn Việt Phương','Phương Kana','phuong@gmail.com','456987213','1983-11-08',1,'abcxyz5'),
 (6,3,'abc1','6efa2190956f362117b0b7d66d8bb15911a8b333','Nguyễn Việt Phương','ABC Kana','phuong@gmail.com','456987213','1983-11-08',1,'abcxyz5'),
 (7,4,'abc2','6efa2190956f362117b0b7d66d8bb15911a8b333','Nguyễn Việt Phương','ABC Kana','phuong@gmail.com','456987213','1983-11-08',1,'abcxyz6');
INSERT INTO `tbl_user` (`user_id`,`group_id`,`login_name`,`password`,`full_name`,`full_name_kana`,`email`,`tel`,`birthday`,`rule`,`salt`) VALUES 
 (8,4,'abc3','6efa2190956f362117b0b7d66d8bb15911a8b333','Đỗ Văn Dũng','Dũng Kana','dung1@gmail.com','456987213','1983-08-08',1,'abcxyz7'),
 (9,4,'abc4','6efa2190956f362117b0b7d66d8bb15911a8b333','Đỗ Văn Duy','Duy Kana','duy1@gmail.com','456987213','1983-08-08',1,'abcxyz8'),
 (10,1,'abc5','6efa2190956f362117b0b7d66d8bb15911a8b333','Vũ Duy','Duy Kana','duy2@gmail.com','456987213','1983-08-08',1,'abcxyz9'),
 (11,2,'abc6','6efa2190956f362117b0b7d66d8bb15911a8b333','Việt Hương','Hương Kana','duy3@gmail.com','456987213','1983-08-08',1,'abcxz10'),
 (12,3,'abc7','6efa2190956f362117b0b7d66d8bb15911a8b333','Huy Ha','Huy Kana','ha@vnu.edu.vn','492835834','1997-12-12',1,'abcabc1'),
 (13,1,'abc8','6efa2190956f362117b0b7d66d8bb15911a8b333','Huy Ha1','Huy1 Kana','ha@vnu.edu.vn','492835834','1997-12-12',1,'abcabc2'),
 (14,2,'abc9','6efa2190956f362117b0b7d66d8bb15911a8b333','Huy Ha2','Huy2 Kana','ha@vnu.edu.vn','492835834','1997-12-12',1,'abcabc3'),
 (15,2,'abc10','6efa2190956f362117b0b7d66d8bb15911a8b333','Huy Ha3','Huy3 Kana','ha@vnu.edu.vn','492835834','1997-12-12',1,'abcabc4');
INSERT INTO `tbl_user` (`user_id`,`group_id`,`login_name`,`password`,`full_name`,`full_name_kana`,`email`,`tel`,`birthday`,`rule`,`salt`) VALUES 
 (16,3,'abc11','6efa2190956f362117b0b7d66d8bb15911a8b333','Huy Ha4','Huy4 Kana','ha@vnu.edu.vn','492835834','1997-12-12',1,'abcabc5'),
 (17,4,'abc12','6efa2190956f362117b0b7d66d8bb15911a8b333','Huy Ha5','Huy5 Kana','ha@vnu.edu.vn','492835834','1997-12-12',1,'abcabc6');
/*!40000 ALTER TABLE `tbl_user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
