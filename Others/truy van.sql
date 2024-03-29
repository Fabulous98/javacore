﻿#1
select * from nhanvien as n order by n.tennhanvien desc;
#2 Lập danh sách sinh viên có tên bắt đầu bằng H ( đầy đủ )
# Nhan vien co ten 2 ky tu
select concat(n.honhanvien,' ',n.tennhanvien) as 'Ten day du', n.ngaysinh 
from nhanvien n 
where n.tennhanvien like '__';
# Ten va ngay sinh cac nhan vien nu sinh thang 6
select Tennhanvien, ngaysinh
from nhanvien n 
where gioitinh = 'F' and month(ngaysinh) = 6;

select Tennhanvien, ngaysinh
from nhanvien n 
where gioitinh = 'F' and ngaysinh like '%-06-%' ;

#4 Lap danh sach gom ten va luong cua nhan vien nam trong 15k-25k

select Tennhanvien, Luong
from nhanvien n
where luong between 15000 and 25000;
#where luong >= 15000 and luong <=25000;

select Manhanvien, concat(Honhanvien, Tenlot, Tennhanvien) as 'Hovaten', manguoiquanly 
from nhanvien n where Manguoiquanly is not null;

select count(*) as 'so nhan vien'
from nhanvien n;

select maphong, count(manhanvien) as 'So nhan vien'
from nhanvien nhanvien 
group by maphong;

select d.MaDuAn, d.TenDuAn, count(pc.MaNhanVien) as 'So Nhan Vien'
from duan d, phancong pc
where d.maduan = pc.Maduan
group by d.maduan
having count(pc.manhanvien) > 2;

select p.Tenphong, count(n.manhanvien) as 'So nhan vien'
from phongban p, nhanvien n
where p.maphong = n.maphong
group by p.tenphong
having count(n.manhanvien) > 2;

select n.tennhanvien, p.tenphong
from nhanvien n inner join phongban p
				on (n.maphong = p.maphong);

select n.tennhanvien, p.tenphong
from nhanvien n inner join phongban p
				using(maphong);

select n.tennhanvien, p.tenphong
from nhanvien n right join phongban p 
				on (n.manhanvien = p.matruongphong);

select n1.tennhanvien, n2.tennhanvien as 'Ten QUL'
from nhanvien n1 left join nhanvien n2
				 on (n1.manguoiquanly = n2.manhanvien);

select n.Tennhanvien, n.ngaysinh, n.gioitinh
from nhanvien n
union
select tn.Tenthannhan, tn.ngaysinh, tn.gioitinh
from thannhan tn;

select n1.Tennhanvien
from nhanvien n1
where n1.maphong in (
	select n2.maphong
	from nhanvien n2
	where n2.tennhanvien = 'Hung'
) and tennhanvien <> 'Hung';

select n.tennhanvien
from nhanvien n
where n.luong = (
	select max(n2.luong)
	from nhanvien n2
);

select p.tenphong
from phongban p
where not exists (
	select * from nhanvien n
	where n.maphong = p.maphong
);

delimiter $$
create procedure inloichao()
begin
	select 'Xin chao cac ban' as 'loi chao';
end$$

delimiter ;

delimiter $$
create procedure inloitambiet()
begin
	select 'Xin tambiet cac ban' as 'loi chao';
end$$

delimiter ;

call inloitambiet();

delimiter $$
create procedure dsnhanvien(in ma int)
begin
	select * from nhanvien n
	where n.maphong = ma;
end$$

delimiter ;
call dsnhanvien(5);

insert into nhanvien values();

select 'abc' like '%mn%';



select p.tenphong
from phongban p inner join nhanvien n using(maphong)
group by p.MaPhong having count(n.manhanvien) >= all(select count(n2.manhanvien)
from phongban p2 inner join nhanvien n2
using(maphong)
group by p2.maphong
);

set @x := 5;
select @x;

select @stt:=@stt+1 as 'So TT',
n.TenNhanVien, n.GioiTinh
from nhanvien n, (select @stt:=0) d;

select @stt;

select n.tennhanvien,
case n.gioitinh when 'M' then 'Nam'
when 'f' then 'Nu'
end as 'Gioi Tinh'
from nhanvien n;

#Thong ke so nhan vien cua moi phong ban theo gioi tinh
#cross tab query

select p.tenphong,
	   count(case when n.gioitinh='M' then n.manhanvien else null end) as 'So NV Nam',
	   count(case when n.gioitinh='F' then n.manhanvien else null end) as 'So NV Nu',
	   count(n.manhanvien) as 'tong so NV'
from nhanvien n inner join phongban p
					  using(Maphong)
group by p.maphong;

select p.tenphong,
	   count(case when n.luong >= 25000 then n.manhanvien else null end) as 'Thu nhap tot',
	   count(case when n.luong < 25000 then n.manhanvien else null end) as 'Du song'
	   
from nhanvien n inner join phongban p
					  using(Maphong)
group by p.maphong;

delimiter $$
create procedure dsnv2(in mp int)

begin
	if exists(select * from phongban p
					where p.maphong = mp) then
	select *
	from nhanvien n
	where n.Maphong = mp;
	else
		select 'Khong ton tai'
		as 'Thong bao';
	end if;
end$$

delimiter ;

call dsnv2(15);

select * from phongban p;


delimiter $$

create trigger onInsertPhongban
before insert
on phongban for each row
begin
	if new.TenPhong is null then
	set new.TenPhong = 'Phong ban moi';
	end if;
end$$

delimiter ;

create table nhanvien_log (
	manv char(9) default null,
	luong_cu double default null,
	luong_moi double default null,
	taikhoan varchar(30) default null,
	thoidiem datetime default null
);

delimiter $$
create trigger onUpdateNhanvien
after update
on nhanvien for each row
begin
	insert into nhanvien_log
	values(new.MaNhanVien, old.Luong,
			new.Luong, user(), now());
end$$

delimiter ;

select * from nhanvien;

update nhanvien
set Luong = 90000000
where MaNhanVien = '123456789';

select * from nhanvien_log;


create user 'la24pm01'@'localhost'
identified by '12345678'; 

grant all privileges
on la24pm01.*
to 'la24pm01'@'localhost';

revoke all privileges
on la24pm01.nhanvien
from 'la24pm01'@'localhost';

#Tao tai khoan test cho phep truy cap tu may bat ky mat khau 1234567

create user 'test'@'%'
identified by '1234567';

grant all privileges
on la24pm01.*
to 'test'@'%';

select concat(Honhanvien,' ', Tenlot,' ', Tennhanvien) as 'Hovaten', Luong from nhanvien where nhanvien.luong > 25000;
select * from nhanvien where month(nhanvien.ngaysinh) = 6;
select phongban.tenphong, maphong, count(manhanvien) as 'So nhan vien'
from nhanvien inner join phongban using (maphong)
group by maphong having count(manhanvien) >3;

select phongban.tenphong, maphong, count(manhanvien) as 'So nhan vien'
from nhanvien inner join phongban using (maphong)
group by maphong having count(manhanvien) >3;
select p.tenphong,
	   count(case when n.gioitinh='M' then n.manhanvien else null end) as 'So NV Nam',
	   count(case when n.gioitinh='F' then n.manhanvien else null end) as 'So NV Nu',
	   count(n.manhanvien) as 'tong so NV'
from nhanvien n inner join phongban p
					  using(Maphong)
group by p.maphong;






