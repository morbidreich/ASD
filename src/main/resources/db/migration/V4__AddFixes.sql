create table fixes (
    id int not null auto_increment,
    fix_name varchar(15) not null,
    coordinates varchar(30) not null,
    fix_type varchar(15),
    primary key (id));


insert into fixes (fix_name, coordinates, fix_type) values ('NIVON', '53°38''41"N 020°02''30"E', 'ENTRY')
insert into fixes (fix_name, coordinates, fix_type) values ('UDROV', '53°15''39"N 020°50''00"E', 'ENTRY')
insert into fixes (fix_name, coordinates, fix_type) values ('LUSUL', '53°49''31"N 021°16''59"E', 'ENTRY')
insert into fixes (fix_name, coordinates, fix_type) values ('IBINO', '53°33''46"N 019°58''50"E', 'ENTRY')
insert into fixes (fix_name, coordinates, fix_type) values ('ARDUT', '53°51''29"N 021°10''02"E', 'ENTRY')

insert into fixes (fix_name, coordinates, fix_type) values ('SY363', '53°31''53"N 020°58''13"E', 'STAR01')
insert into fixes (fix_name, coordinates, fix_type) values ('SY362', '53°48''54"N 020°59''13"E', 'STAR01')
insert into fixes (fix_name, coordinates, fix_type) values ('SY364', '53°33''44"N 020°47''56"E', 'STAR01')
insert into fixes (fix_name, coordinates, fix_type) values ('SY361', '53°27''10"N 020°21''13"E', 'STAR01')
insert into fixes (fix_name, coordinates, fix_type) values ('SY366', '53°21''49"N 020°39''10"E', 'STAR01')
insert into fixes (fix_name, coordinates, fix_type) values ('SY357', '53°42''45"N 020°54''37"E', 'STAR01')

insert into fixes (fix_name, coordinates, fix_type) values ('SY791', '53°26''34"N 020°25''40"E', 'STAR19')
insert into fixes (fix_name, coordinates, fix_type) values ('SY792', '53°33''17"N 020°36''13"E', 'STAR19')
insert into fixes (fix_name, coordinates, fix_type) values ('SY793', '53°49''01"N 020°55''42"E', 'STAR19')
insert into fixes (fix_name, coordinates, fix_type) values ('SY801', '53°44''23"N 020°52''25"E', 'STAR19')
insert into fixes (fix_name, coordinates, fix_type) values ('SY802', '53°41''38"N 021°03''29"E', 'STAR19')

insert into fixes (fix_name, coordinates, fix_type) values ('SY411', '53°32''55"N 020°58''30"E', 'SID01')
insert into fixes (fix_name, coordinates, fix_type) values ('SY412', '53°36''59"N 020°47''13"E', 'SID01')
insert into fixes (fix_name, coordinates, fix_type) values ('SY413', '53°33''57"N 020°26''46"E', 'SID01')
insert into fixes (fix_name, coordinates, fix_type) values ('SY414', '53°28''16"N 020°24''26"E', 'SID01')
insert into fixes (fix_name, coordinates, fix_type) values ('SY416', '53°40''51"N 021°07''18"E', 'SID01')
insert into fixes (fix_name, coordinates, fix_type) values ('SY417', '53°44''40"N 021°11''34"E', 'SID01')

insert into fixes (fix_name, coordinates, fix_type) values ('SY511', '53°24''50"N 020°53''59"E', 'SID19')
insert into fixes (fix_name, coordinates, fix_type) values ('SY512', '53°26''37"N 020°45''00"E', 'SID19')
insert into fixes (fix_name, coordinates, fix_type) values ('SY513', '53°33''32"N 020°26''26"E', 'SID19')
insert into fixes (fix_name, coordinates, fix_type) values ('SY514', '53°34''30"N 020°49''23"E', 'SID19')
insert into fixes (fix_name, coordinates, fix_type) values ('SY516', '53°36''36"N 021°09''06"E', 'SID19')
insert into fixes (fix_name, coordinates, fix_type) values ('SY517', '53°32''43"N 020°58''23"E', 'SID19')

insert into fixes (fix_name, coordinates, fix_type) values ('BRAVO',  '53°32''40"N 020°49''13"E', 'VFR')
insert into fixes (fix_name, coordinates, fix_type) values ('ECHO',   '53°28''26"N 020°58''03"E', 'VFR')
insert into fixes (fix_name, coordinates, fix_type) values ('WHISKEY','53°30''07"N 020°53''55"E', 'VFR')
insert into fixes (fix_name, coordinates, fix_type) values ('ZULU',   '53°25''13"N 021°07''05"E', 'VFR')


