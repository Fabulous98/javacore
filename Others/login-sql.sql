
CREATE Database IF NOT EXISTS `csdl_dangnhap`;
USE `csdl_dangnhap` ;
CREATE TABLE IF NOT EXISTS `csdl_dangnhap`.`taikhoan` (
  `id` INT(10) NOT NULL,
  `userName` VARCHAR(20) NULL DEFAULT NULL,
  `password` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`));
  insert taikhoan values (1,"dungbuimanh","12345678");