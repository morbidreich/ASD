create table point (
    id int not null auto_increment,
    coordinates varchar(30) not null,
    polygon_id int,
    primary key (id),
    foreign key (polygon_id) references polygons(id)

    );

    insert into point (coordinates, polygon_id) values ('53°38''46"N 020°57''22"E', 1);
    insert into point (coordinates, polygon_id) values ('53°36''21"N 021°06''45"E', 1);
    insert into point (coordinates, polygon_id) values ('53°29''21"N 021°05''37"E', 1);
    insert into point (coordinates, polygon_id) values ('53°25''31"N 021°03''48"E', 1);
    insert into point (coordinates, polygon_id) values ('53°17''57"N 020°56''02"E', 1);
    insert into point (coordinates, polygon_id) values ('53°19''49"N 020°46''46"E', 1);
    insert into point (coordinates, polygon_id) values ('53°28''21"N 020°46''35"E', 1);
    insert into point (coordinates, polygon_id) values ('53°32''40"N 020°49''13"E', 1);
    insert into point (coordinates, polygon_id) values ('53°38''46"N 020°57''22"E', 1);

create table fix (
    id int not null auto_increment,
    coordinates varchar(30) not null,
    fix_name varchar(15) not null,
    fix_type_id int not null,
    primary key (id)-- ,
    -- foreign key (fix_type_id) references fix_type(id)
    );

    insert into fix (coordinates, fix_name, fix_type_id) values ('53°38''41"N 020°02''30"E', 'NIVON', 0);
    insert into fix (coordinates, fix_name, fix_type_id) values ('53°15''39"N 020°50''00"E', 'UDROV', 0);
    insert into fix (coordinates, fix_name, fix_type_id) values ('53°49''31"N 021°16''59"E', 'LUSUL', 0);
    insert into fix (coordinates, fix_name, fix_type_id) values ('53°33''46"N 019°58''50"E', 'IBINO', 0);
    insert into fix (coordinates, fix_name, fix_type_id) values ('53°51''29"N 021°10''02"E', 'ARDUT', 0);


create table polygon (
    id int not null auto_increment,
    polygon_name varchar(30) not null,
    polygon_type_id int not null,
    primary key (id),
    foreign key (polygon_type_id) references polygon_type(id)
    );

insert into polygon (polygon_name, polygon_type_id) values ('CTR', 0);
insert into polygon (polygon_name, polygon_type_id) values ('TMA_A', 1);
insert into polygon (polygon_name, polygon_type_id) values ('TMA_B', 1);
insert into polygon (polygon_name, polygon_type_id) values ('TMA_C', 1);
insert into polygon (polygon_name, polygon_type_id) values ('TMA_D', 1);