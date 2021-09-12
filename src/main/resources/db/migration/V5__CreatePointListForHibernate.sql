create table points (
    id int not null auto_increment,
    coordinates varchar(30) not null,
    polygon_id int,
    primary key (id),
    foreign key (polygon_id) references polygons(id)

    );

--    polygon ids:
--    ctr - 1
--    tma-a 2
--    tma-b 3
--    tma-c 4
--    tma-d 5


-- ctr
insert into points (coordinates, polygon_id) values ('53°38''46"N 020°57''22"E', 1);
insert into points (coordinates, polygon_id) values ('53°36''21"N 021°06''45"E', 1);
insert into points (coordinates, polygon_id) values ('53°29''21"N 021°05''37"E', 1);
insert into points (coordinates, polygon_id) values ('53°25''31"N 021°03''48"E', 1);
insert into points (coordinates, polygon_id) values ('53°17''57"N 020°56''02"E', 1);
insert into points (coordinates, polygon_id) values ('53°19''49"N 020°46''46"E', 1);
insert into points (coordinates, polygon_id) values ('53°28''21"N 020°46''35"E', 1);
insert into points (coordinates, polygon_id) values ('53°32''40"N 020°49''13"E', 1);
insert into points (coordinates, polygon_id) values ('53°38''46"N 020°57''22"E', 1);

-- tma a
insert into points (coordinates, polygon_id) values ('53°43''02"N 020°57''18"E', 2);
insert into points (coordinates, polygon_id) values ('53°41''44"N 021°03''15"E', 2);
insert into points (coordinates, polygon_id) values ('53°40''49"N 021°07''28"E', 2);
insert into points (coordinates, polygon_id) values ('53°36''21"N 021°06''45"E', 2);
insert into points (coordinates, polygon_id) values ('53°29''21"N 021°05''37"E', 2);
insert into points (coordinates, polygon_id) values ('53°25''49"N 021°03''57"E', 2);
insert into points (coordinates, polygon_id) values ('53°25''31"N 021°03''48"E', 2);
insert into points (coordinates, polygon_id) values ('53°17''57"N 020°56''02"E', 2);
insert into points (coordinates, polygon_id) values ('53°13''42"N 020°51''37"E', 2);
insert into points (coordinates, polygon_id) values ('53°17''21"N 020°34''42"E', 2);
insert into points (coordinates, polygon_id) values ('53°23''36"N 020°34''24"E', 2);
insert into points (coordinates, polygon_id) values ('53°33''07"N 020°40''19"E', 2);
insert into points (coordinates, polygon_id) values ('53°41''53"N 020°49''55"E', 2);
insert into points (coordinates, polygon_id) values ('53°43''02"N 020°57''18"E', 2);

-- tma b
insert into points (coordinates, polygon_id) values ('53°40''40"N 020°41''06"E', 3);
insert into points (coordinates, polygon_id) values ('53°43''55"N 020°49''32"E', 3);
insert into points (coordinates, polygon_id) values ('53°47''10"N 020°58''00"E', 3);
insert into points (coordinates, polygon_id) values ('53°47''37"N 021°02''27"E', 3);
insert into points (coordinates, polygon_id) values ('53°46''54"N 021°08''43"E', 3);
insert into points (coordinates, polygon_id) values ('53°45''16"N 021°15''51"E', 3);
insert into points (coordinates, polygon_id) values ('53°43''54"N 021°19''14"E', 3);
insert into points (coordinates, polygon_id) values ('53°41''19"N 021°18''36"E', 3);
insert into points (coordinates, polygon_id) values ('53°38''42"N 021°17''11"E', 3);
insert into points (coordinates, polygon_id) values ('53°36''21"N 021°15''55"E', 3);
insert into points (coordinates, polygon_id) values ('53°25''31"N 021°03''48"E', 3);
insert into points (coordinates, polygon_id) values ('53°17''57"N 020°56''02"E', 3);
insert into points (coordinates, polygon_id) values ('53°13''42"N 020°51''37"E', 3);
insert into points (coordinates, polygon_id) values ('53°17''21"N 020°34''42"E', 3);
insert into points (coordinates, polygon_id) values ('53°18''55"N 020°28''15"E', 3);
insert into points (coordinates, polygon_id) values ('53°23''33"N 020°15''25"E', 3);
insert into points (coordinates, polygon_id) values ('53°37''08"N 020°29''23"E', 3);
insert into points (coordinates, polygon_id) values ('53°40''40"N 020°41''06"E', 3);

-- tma c

insert into points (coordinates, polygon_id) values ('53°23''33"N 020°15''25"E', 4);
insert into points (coordinates, polygon_id) values ('53°30''12"N 019°51''45"E', 4);
insert into points (coordinates, polygon_id) values ('53°38''49"N 020°02''36"E', 4);
insert into points (coordinates, polygon_id) values ('53°40''49"N 020°10''59"E', 4);
insert into points (coordinates, polygon_id) values ('53°37''08"N 020°29''23"E', 4);
insert into points (coordinates, polygon_id) values ('53°23''33"N 020°15''25"E', 4);

-- tma d

insert into points (coordinates, polygon_id) values ('53°40''45"N 020°41''20"E', 5);
insert into points (coordinates, polygon_id) values ('53°40''40"N 020°41''06"E', 5);
insert into points (coordinates, polygon_id) values ('53°45''44"N 020°46''25"E', 5);
insert into points (coordinates, polygon_id) values ('53°50''48"N 020°51''46"E', 5);
insert into points (coordinates, polygon_id) values ('53°51''26"N 020°55''17"E', 5);
insert into points (coordinates, polygon_id) values ('53°52''17"N 021°00''00"E', 5);
insert into points (coordinates, polygon_id) values ('53°52''16"N 021°00''41"E', 5);
insert into points (coordinates, polygon_id) values ('53°52''07"N 021°07''54"E', 5);
insert into points (coordinates, polygon_id) values ('53°50''44"N 021°12''49"E', 5);
insert into points (coordinates, polygon_id) values ('53°49''32"N 021°17''00"E', 5);
insert into points (coordinates, polygon_id) values ('53°46''04"N 021°19''38"E', 5);
insert into points (coordinates, polygon_id) values ('53°43''54"N 021°19''14"E', 5);
insert into points (coordinates, polygon_id) values ('53°45''16"N 021°15''51"E', 5);
insert into points (coordinates, polygon_id) values ('53°46''54"N 021°08''43"E', 5);
insert into points (coordinates, polygon_id) values ('53°47''37"N 021°02''27"E', 5);
insert into points (coordinates, polygon_id) values ('53°47''10"N 020°58''00"E', 5);
insert into points (coordinates, polygon_id) values ('53°47''02"N 020°57''39"E', 5);
insert into points (coordinates, polygon_id) values ('53°40''45"N 020°41''20"E', 5);

