#drop database TestCuoiKy24_BuiManhDung;
CREATE Database IF NOT EXISTS `TestCuoiKy24_BuiManhDung`;
USE `TestCuoiKy24_BuiManhDung` ;
CREATE TABLE IF NOT EXISTS `user` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `Hoten` VARCHAR(30) NULL DEFAULT NULL,
  `Ngaysinh` Date NULL DEFAULT NULL,
  `sodienthoai` VARCHAR(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`));
  #insert user values (1,"Bui Manh Dung","1998-01-08","0326753535");


