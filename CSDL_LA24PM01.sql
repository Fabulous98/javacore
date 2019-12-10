CREATE DATABASE  IF NOT EXISTS `CSDLLA24PM01` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `CSDLLA24PM01`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: CSDLLA24PM01
-- ------------------------------------------------------
-- Server version	5.1.38-community

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `diadiem_phong`
--

DROP TABLE IF EXISTS `diadiem_phong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diadiem_phong` (
  `MaPhong` int(11) NOT NULL DEFAULT '0',
  `Diadiem` varchar(15) NOT NULL DEFAULT '',
  PRIMARY KEY (`MaPhong`,`Diadiem`),
  CONSTRAINT `DIADIEM_PHG_FK_PHONGBAN` FOREIGN KEY (`MaPhong`) REFERENCES `phongban` (`MaPhong`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diadiem_phong`
--

LOCK TABLES `diadiem_phong` WRITE;
/*!40000 ALTER TABLE `diadiem_phong` DISABLE KEYS */;
INSERT INTO `diadiem_phong` VALUES (1,'Phu Nhuan'),(4,'Go Vap'),(5,'Phu Nhuan'),(5,'Tan Binh'),(5,'Thu Duc');
/*!40000 ALTER TABLE `diadiem_phong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `duan`
--

DROP TABLE IF EXISTS `duan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `duan` (
  `MaDuAn` int(11) NOT NULL DEFAULT '0',
  `TenDuAn` varchar(15) DEFAULT NULL,
  `Diadiem` varchar(15) DEFAULT NULL,
  `MaPhong` int(11) DEFAULT NULL,
  PRIMARY KEY (`MaDuAn`),
  KEY `DUAN_FK_PHONGBAN` (`MaPhong`),
  CONSTRAINT `DUAN_FK_PHONGBAN` FOREIGN KEY (`MaPhong`) REFERENCES `phongban` (`MaPhong`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `duan`
--

LOCK TABLES `duan` WRITE;
/*!40000 ALTER TABLE `duan` DISABLE KEYS */;
INSERT INTO `duan` VALUES (1,'San pham X','Tan Binh',5),(2,'San pham Y','Thu Duc',5),(3,'San pham Z','Phu Nhuan',5),(10,'Tin hoc hoa','Go Vap',4),(20,'Tai to chuc','Phu Nhuan',1),(30,'Phuc loi','Go Vap',4);
/*!40000 ALTER TABLE `duan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nhanvien` (
  `MaNhanVien` char(9) NOT NULL DEFAULT '',
  `HoNhanVien` varchar(15) DEFAULT NULL,
  `Tenlot` varchar(15) DEFAULT NULL,
  `TenNhanVien` varchar(15) DEFAULT NULL,
  `NgaySinh` date DEFAULT NULL,
  `DiaChi` varchar(30) DEFAULT NULL,
  `GioiTinh` varchar(3) DEFAULT NULL,
  `Luong` double DEFAULT NULL,
  `MaNguoiQuanLy` char(9) DEFAULT NULL,
  `MaPhong` int(11) DEFAULT NULL,
  PRIMARY KEY (`MaNhanVien`),
  KEY `NHANVIEN_FK_NHANVIEN` (`MaNguoiQuanLy`),
  KEY `NHANVIEN_FK_PHONGBAN` (`MaPhong`),
  CONSTRAINT `NHANVIEN_FK_NHANVIEN` FOREIGN KEY (`MaNguoiQuanLy`) REFERENCES `nhanvien` (`MaNhanVien`),
  CONSTRAINT `NHANVIEN_FK_PHONGBAN` FOREIGN KEY (`MaPhong`) REFERENCES `phongban` (`MaPhong`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES ('123456789','Nguyen','Bao','Hung','1965-01-09','731 Tran Hung Dao, Q1, TpHCM','M',450000,'333445555',5),('333445555','Phan','Van','Nghia','1955-12-08','638 Nguyen Van Cu, Q5, TpHCM','M',57600,'888665555',5),('453453453','Hoang','Kim','Yen','1972-07-31','543 Mai Thi Luu, Q1, TpHCM','F',42250,'333445555',5),('666884444','Tran',NULL,'Nam','1962-09-15','975 Ba Ria Vung Tau','M',68000,'333445555',5),('888665555','Le','Van','Bo','1937-11-10','450 Trung Vuong, Ha Noi','M',55000,NULL,1),('987654321','Du','Thi','Hau','1951-06-20','291 Ho Van Hue, QPN, TpHCM','F',43000,'888665555',4),('987987987','Nguyen','Van','Giap','1969-03-09','980 Le Hong Phong, Q10, TpHCM','M',25000,'987654321',4),('999887777','Au',NULL,'Vuong','1968-07-19','332 Nguyen Thai Hoc, Q1, TpHCM','F',25000,'987654321',4),('999999999','Nguyen','Van','An','1986-10-10','Ha Noi','M',10000,'123456789',1);
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phancong`
--

DROP TABLE IF EXISTS `phancong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phancong` (
  `MaNhanVien` char(9) NOT NULL DEFAULT '',
  `MaDuAn` int(11) NOT NULL DEFAULT '0',
  `ThoiGian` decimal(6,1) DEFAULT NULL,
  PRIMARY KEY (`MaNhanVien`,`MaDuAn`),
  KEY `PHANCONG_FK_DUAN` (`MaDuAn`),
  CONSTRAINT `PHANCONG_FK_DUAN` FOREIGN KEY (`MaDuAn`) REFERENCES `duan` (`MaDuAn`),
  CONSTRAINT `PHANCONG_FK_NHANVIEN` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phancong`
--

LOCK TABLES `phancong` WRITE;
/*!40000 ALTER TABLE `phancong` DISABLE KEYS */;
INSERT INTO `phancong` VALUES ('123456789',1,32.5),('123456789',2,7.5),('333445555',2,10.0),('333445555',3,10.0),('333445555',10,10.0),('333445555',20,10.0),('453453453',1,20.0),('453453453',2,20.0),('666884444',3,40.0),('888665555',20,NULL),('987654321',20,15.0),('987654321',30,20.0),('987987987',10,35.0),('987987987',30,5.0),('999887777',10,10.0),('999887777',30,30.0);
/*!40000 ALTER TABLE `phancong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phongban`
--

DROP TABLE IF EXISTS `phongban`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phongban` (
  `MaPhong` int(11) NOT NULL DEFAULT '0',
  `TenPhong` varchar(15) DEFAULT NULL,
  `MaTruongPhong` char(9) DEFAULT NULL,
  `NgayNhanChuc` date DEFAULT NULL,
  PRIMARY KEY (`MaPhong`),
  KEY `PHONGBAN_FK_NHANVIEN` (`MaTruongPhong`),
  CONSTRAINT `PHONGBAN_FK_NHANVIEN` FOREIGN KEY (`MaTruongPhong`) REFERENCES `nhanvien` (`MaNhanVien`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phongban`
--

LOCK TABLES `phongban` WRITE;
/*!40000 ALTER TABLE `phongban` DISABLE KEYS */;
INSERT INTO `phongban` VALUES (1,'Quan ly','888665555','1981-06-19'),(4,'Dieu hanh','987987987','1995-01-01'),(5,'Nghien cuu','333445555','1988-05-22');
/*!40000 ALTER TABLE `phongban` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thannhan`
--

DROP TABLE IF EXISTS `thannhan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thannhan` (
  `MaNhanVien` char(9) NOT NULL DEFAULT '',
  `TenThanNhan` varchar(15) NOT NULL DEFAULT '',
  `GioiTinh` varchar(3) DEFAULT NULL,
  `NgaySinh` date DEFAULT NULL,
  `QuanHe` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`MaNhanVien`,`TenThanNhan`),
  CONSTRAINT `THANNHAN_FK_NHANVIEN` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thannhan`
--

LOCK TABLES `thannhan` WRITE;
/*!40000 ALTER TABLE `thannhan` DISABLE KEYS */;
INSERT INTO `thannhan` VALUES ('123456789','Anh','F','1988-12-30','Con gai'),('123456789','Minh','M','1988-01-01','Con trai'),('123456789','Yen','F','1967-05-05','Vo'),('333445555','Anh','F','1986-04-05','Con gai'),('333445555','Loi','F','1958-05-03','Vo'),('333445555','The','M','1983-10-25','Con trai'),('987654321','An','M','1942-02-28','Chong');
/*!40000 ALTER TABLE `thannhan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-04  8:19:29
