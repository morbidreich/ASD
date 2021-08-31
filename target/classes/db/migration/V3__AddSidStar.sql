create table star (
    id int not null auto_increment,
    star_name varchar(7) not null,
    coordinates varchar(30) not null,
    fix_name varchar(5),
    primary key (id));

create table sid (
    id int not null auto_increment,
    sid_name varchar(7) not null,
    coordinates varchar(30) not null,
    fix_name varchar(5),
    primary key (id));

-- STAR 01
insert into star (star_name, coordinates) values ('IBINO1R', '53°33''46"N 019°58''50"E');
insert into star (star_name, coordinates, fix_name) values ('IBINO1R', '53°27''10"N 020°21''13"E', 'SY361');
insert into star (star_name, coordinates, fix_name) values ('IBINO1R', '53°21''49"N 020°39''10"E', 'SY366');

insert into star (star_name, coordinates) values ('ARDUT1R', '53°51''29"N 021°10''02"E');
insert into star (star_name, coordinates, fix_name) values ('ARDUT1R', '53°48''54"N 020°59''13"E', 'SY362');
insert into star (star_name, coordinates, fix_name) values ('ARDUT1R', '53°42''45"N 020°54''37"E', 'SY357');
insert into star (star_name, coordinates, fix_name) values ('ARDUT1R', '53°33''44"N 020°47''56"E', 'SY364');
insert into star (star_name, coordinates, fix_name) values ('ARDUT1R', '53°21''49"N 020°39''10"E', 'SY366');

insert into star (star_name, coordinates) values ('UDROV1R', '53°15''39"N 020°50''00"E');
insert into star (star_name, coordinates, fix_name) values ('UDROV1R', '53°31''53"N 020°58''13"E', 'SY363');
insert into star (star_name, coordinates, fix_name) values ('UDROV1R', '53°33''44"N 020°47''56"E', 'SY364');
insert into star (star_name, coordinates, fix_name) values ('UDROV1R', '53°21''49"N 020°39''10"E', 'SY366');

-- STAR 19
insert into star (star_name, coordinates) values ('IBINO2T', '53°33''46"N 019°58''50"E');
insert into star (star_name, coordinates, fix_name) values ('IBINO2T', '53°26''34"N 020°25''40"E', 'SY791');
insert into star (star_name, coordinates, fix_name) values ('IBINO2T', '53°33''17"N 020°36''13"E', 'SY792');
insert into star (star_name, coordinates, fix_name) values ('IBINO2T', '53°44''23"N 020°52''25"E', 'SY801');
insert into star (star_name, coordinates, fix_name) values ('IBINO2T', '53°41''38"N 021°03''29"E', 'SY802');

insert into star (star_name, coordinates) values ('ARDUT2T', '53°51''29"N 021°10''02"E');
insert into star (star_name, coordinates, fix_name) values ('ARDUT2T', '53°49''01"N 020°55''42"E', 'SY793');
insert into star (star_name, coordinates, fix_name) values ('ARDUT2T', '53°44''23"N 020°52''25"E', 'SY801');
insert into star (star_name, coordinates, fix_name) values ('ARDUT2T', '53°41''38"N 021°03''29"E', 'SY802');

insert into star (star_name, coordinates) values ('UDROV2T', '53°15''39"N 020°50''00"E');
insert into star (star_name, coordinates, fix_name) values ('UDROV2T', '53°33''17"N 020°36''13"E', 'SY792');
insert into star (star_name, coordinates, fix_name) values ('UDROV2T', '53°44''23"N 020°52''25"E', 'SY801');
insert into star (star_name, coordinates, fix_name) values ('UDROV2T', '53°41''38"N 021°03''29"E', 'SY802');

-- SID 01
insert into sid (sid_name, coordinates) values ('NIVON1S', '53°29''33"N 020°56''37"E');
insert into sid (sid_name, coordinates, fix_name) values ('NIVON1S', '53°32''55"N 020°58''30"E', 'SY411');
insert into sid (sid_name, coordinates, fix_name) values ('NIVON1S', '53°36''59"N 020°47''13"E', 'SY412');
insert into sid (sid_name, coordinates, fix_name) values ('NIVON1S', '53°33''57"N 020°26''46"E', 'SY413');
insert into sid (sid_name, coordinates, fix_name) values ('NIVON1S', '53°38''41"N 020°02''30"E', 'NIVON');

insert into sid (sid_name, coordinates) values ('UDROV1S', '53°41''38"N 021°03''29"E');
insert into sid (sid_name, coordinates, fix_name) values ('UDROV1S', '53°32''55"N 020°58''30"E', 'SY411');
insert into sid (sid_name, coordinates, fix_name) values ('UDROV1S', '53°36''59"N 020°47''13"E', 'SY412');
insert into sid (sid_name, coordinates, fix_name) values ('UDROV1S', '53°33''57"N 020°26''46"E', 'SY413');
insert into sid (sid_name, coordinates, fix_name) values ('UDROV1S', '53°28''16"N 020°24''26"E', 'SY414');
insert into sid (sid_name, coordinates, fix_name) values ('UDROV1S', '53°15''39"N 020°50''00"E', 'UDROV');

insert into sid (sid_name, coordinates) values ('LUSUL1S', '53°41''38"N 021°03''29"E');
insert into sid (sid_name, coordinates, fix_name) values ('LUSUL1S', '53°32''55"N 020°58''30"E', 'SY411');
insert into sid (sid_name, coordinates, fix_name) values ('LUSUL1S', '53°40''51"N 021°07''18"E', 'SY416');
insert into sid (sid_name, coordinates, fix_name) values ('LUSUL1S', '53°44''40"N 021°11''34"E', 'SY417');
insert into sid (sid_name, coordinates, fix_name) values ('LUSUL1S', '53°49''31"N 021°16''59"E', 'LUSUL');

-- SID 19
insert into sid (sid_name, coordinates) values ('NIVON1W', '53°28''16"N 020°55''54"E');
insert into sid (sid_name, coordinates, fix_name) values ('NIVON1W', '53°24''50"N 020°53''59"E', 'SY511');
insert into sid (sid_name, coordinates, fix_name) values ('NIVON1W', '53°26''37"N 020°45''00"E', 'SY512');
insert into sid (sid_name, coordinates, fix_name) values ('NIVON1W', '53°33''32"N 020°26''26"E', 'SY513');
insert into sid (sid_name, coordinates, fix_name) values ('NIVON1W', '53°38''41"N 020°02''30"E', 'NIVON');

insert into sid (sid_name, coordinates) values ('UDROV1W', '53°28''16"N 020°55''54"E');
insert into sid (sid_name, coordinates, fix_name) values ('UDROV1W', '53°24''50"N 020°53''59"E', 'SY511');
insert into sid (sid_name, coordinates, fix_name) values ('UDROV1W', '53°26''37"N 020°45''00"E', 'SY512');
insert into sid (sid_name, coordinates, fix_name) values ('UDROV1W', '53°34''30"N 020°49''23"E', 'SY514');
insert into sid (sid_name, coordinates, fix_name) values ('UDROV1W', '53°32''43"N 020°58''23"E', 'SY517');
insert into sid (sid_name, coordinates, fix_name) values ('UDROV1W', '53°15''39"N 020°50''00"E', 'UDROV');

insert into sid (sid_name, coordinates) values ('LUSUL1W', '53°28''16"N 020°55''54"E');
insert into sid (sid_name, coordinates, fix_name) values ('LUSUL1W', '53°24''50"N 020°53''59"E', 'SY511');
insert into sid (sid_name, coordinates, fix_name) values ('LUSUL1W', '53°26''37"N 020°45''00"E', 'SY512');
insert into sid (sid_name, coordinates, fix_name) values ('LUSUL1W', '53°34''30"N 020°49''23"E', 'SY514');
insert into sid (sid_name, coordinates, fix_name) values ('LUSUL1W', '53°36''36"N 021°09''06"E', 'SY516');
insert into sid (sid_name, coordinates, fix_name) values ('LUSUL1W', '53°49''31"N 021°16''59"E', 'LUSUL');

