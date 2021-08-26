create table polygons (
    id int not null auto_increment,
    polygon_name varchar(30) not null,
    primary key (id));

insert into polygons (polygon_name) values ('CTR');
insert into polygons (polygon_name) values ('TMA_A');
insert into polygons (polygon_name) values ('TMA_B');
insert into polygons (polygon_name) values ('TMA_C');
insert into polygons (polygon_name) values ('TMA_D');