drop database if exists monan;
Create database monan;
use monan;

create table nglieu(
manl int not null,
ten varchar(100) not null,
nhom int not null,
calori float,
protein float,
lipit float,
gluxit float,
canxi float,
photpho float,
sat float,
catoren float,
B1 float,
B2 float,
PP float,
C float,
primary key (manl)
);

create table nglieuky(
manl int not null,
manlk int not null,
primary key(manl, manlk),
foreign key (manl) references nglieu(manl),
foreign key (manlk) references nglieu(manl)
);

create table loaimonan(
loai int not null,
ten varchar(100) not null,
primary key(loai)
);

create table monan(
Mama int not null,
Ten varchar(100) not null,
loai int not null,
gia float not null,
calori float, 
proteindv float, 
proteintv float, 
lipitdv float, 
lipittv float,
gluxit float,
canxi float,
photpho float,
sat float,
caroten float,
B1 float,
B2 float,
PP float,
C float,
primary key (mama),
foreign key (loai) references loaimonan(loai)
);

create table monanky(
mama int not null,
mamak int not null,
primary key(mama, mamak),
foreign key (mama) references monan(mama),
foreign key (mamak) references monan(mama)
);

create table tpmonan(
mama int not null,
manl int not null,
luong float,
primary key(mama, manl),
foreign key (mama) references monan(mama),
foreign key (manl) references nglieu(manl)
);

create table buaan(
maba int not null,
gia float,
calori float,
protein float,
lipit float,
gluxit float,
primary key(maba)
);

create table tpbuaan(
maba int not null,
mama int not null,
primary key(maba, mama),
foreign key (maba) references buaan(maba),
foreign key (mama) references monan(mama)
);

insert into loaimonan values
(1, 'moncanh'),
(2, 'Monman'),
(3, 'trangmieng'),
(4, 'com');

insert into nglieu values
(1, 'Gao', 1, 346, 8.4, 1.6, 74.9, 16, 130, 1.2, 0, 0.16, 0.06, 130, 0),
(2, 'Dau Phu', 2, 95, 10.9, 5.4, 0.7, 24, 85, 2.2, 0, 0.03, 0.02, 85, 0),
(3, 'Su Hao', 4, 37, 2.8, 0.1, 6.2, 46, 50, 0.6, 22, 0.06, 0.05, 50, 40),
(4, 'Dau Oliu', 3, 900, 0, 100, 0, 1, 0, 0.56, 0, 0, 0, 0, 0), 
(5, 'Nam huong', 4, 44, 1, 0.2, 5, 40, 37, 0.4, 3, 0.05, 0.07, 40, 35),
(6, 'Khoai tây', 1, 93, 2, 0.1, 20.9, 10, 50, 1.2, 5, 0.1, 0.05, 50, 10 ),
(7, 'Trung Ga', 2, 166, 14.8, 11.6, 0.5, 55, 210, 2.7, 0, 0.16, 0.31, 210, 0),
(8, 'Ca', 2, 176, 14.8, 11.6, 0.5, 55, 110, 2.7, 0, 0.17, 0.31, 110, 0),
(9, 'Ca chua', 4, 45, 1, 0.3, 5, 45, 27, 0.5, 3.5, 0.05, 0.08, 30, 45),
(10, 'thit ba chi', 2, 166, 15.8, 12.6, 1.5, 55, 120, 2.7, 0, 0.1, 0.3, 120, 0);

insert into nglieuky values (2, 5), (4, 6);

insert into monan values
(1, 'Com rang', 4, 25000, 163, 0, 10, 0, 102, 75, 17, 130, 1.76, 0, 0.16, 0.06, 130, 0),
(2, 'Canh khoai tay', 1, 30000, 130, 0, 2, 0, 0.1,  20.9, 10, 50, 1.2, 5, 0.1, 0.06, 51, 10),
(3, 'Com chay', 4, 240, 160, 0, 10, 0, 102, 75, 17, 12, 1.76, 0, 0.16, 0.06, 12, 0),
(4, 'Ca sot ca', 2, 300, 30, 167, 16.8, 15.6, 1.7, 56, 130, 2.7, 0, 0.1, 0.3, 130, 10, 0),
(5, 'thit kho', 2, 500, 35, 160, 17.8, 14.6, 1.7, 56, 110, 2.7, 0, 0.2, 0.3, 110, 10, 0);

insert into monanky values (1, 2);
insert into monanky values (2, 4);

insert into tpmonan values 
(1, 1, 200), (1, 4, 50), 
(2, 6, 250),(2, 9, 50),
(4, 10, 300), (4, 9, 70), (4, 8, 250)
;

insert into buaan values (1, 55000, 293, 130, 10, 2);
insert into buaan values (2, 25300, 300, 140, 15, 3);

insert into tpbuaan values (1, 1), (1, 2);
insert into tpbuaan values (2, 3), (2, 4);

Select * from NGLIEU nl where nl.C is not null and nl.C > 0;

select distinct nl.manl, nl.ten 
from nglieu nl, tpmonan tpm
where nl.manl in 
(select tpm.manl 
from tpmonan tpm, monan 
where tpm.mama = (select mama 
				  from monan 
                  where ten='ca sot ca'
                  )
);

select distinct nl.manl, nl.ten 
from nglieu nl, tpmonan tpm
where nl.manl = tpm.manl 
and tpm.mama = (select mama 
				from monan 
				where ten='ca sot ca'
);

select ten
from monan
where gia <= (3000-275) and loai != 4;

select monan.ten 
from monan, tpmonan tp
where monan.mama = tp.mama 
and tp.manl = (select manl 
			   from nglieu 
               where ten ='Ca chua'
               );

select nhom, count(*) as 'so luong'
from nglieu
group by nhom
having count(*) > 2;

select monan.ten, 
	   ( select count(*)
         from monanky
         where (monan.mama = monanky.mama) 
         or (monan.mama = monanky.mamak)
	   ) as 'so mon ky'
from monan
where mama in 
			(select mama from monanky)
or mama in 
			(select mamak from monanky)
order by ten 
desc;

create table demtb as select loai,  count(*) as dem
from monan 
group by loai;

select *
from loaimonan lma 
inner join demtb
on lma.loai = demtb.loai 
and demtb.dem = (select max(dem) from demtb);

select ten
from monan 
where mama not in (select mama 
					   from tpbuaan
);

select ten, manl
from nglieu
where manl not in
				(select manl from nglieuky)
and manl not in 
				(select manlk from nglieuky)
; 

select ten, mama
from monan
where mama not in
				(select mama from monanky)
and mama not in 
				(select mamak from monanky)
;

