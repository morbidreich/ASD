
create table fix (
    id int not null auto_increment,
    coordinates varchar(30) not null,
    fix_name varchar(30) not null,
    fix_type_id int not null,
    primary key (id)-- ,
    -- foreign key (fix_type_id) references fix_type(id)
    );

--entry point
insert into fix (coordinates, fix_name, fix_type_id) values ('53°38''41"N 020°02''30"E', 'NIVON', 0);
insert into fix (coordinates, fix_name, fix_type_id) values ('53°15''39"N 020°50''00"E', 'UDROV', 0);
insert into fix (coordinates, fix_name, fix_type_id) values ('53°49''31"N 021°16''59"E', 'LUSUL', 0);
insert into fix (coordinates, fix_name, fix_type_id) values ('53°33''46"N 019°58''50"E', 'IBINO', 0);
insert into fix (coordinates, fix_name, fix_type_id) values ('53°51''29"N 021°10''02"E', 'ARDUT', 0);

insert into fix (coordinates, fix_name, fix_type_id) values ('53°29''33"N 020°56''37"E', 'DER01', 3);
insert into fix (coordinates, fix_name, fix_type_id) values ('53°32''55"N 020°58''30"E', 'SY411', 1);
insert into fix (coordinates, fix_name, fix_type_id) values ('53°36''59"N 020°47''13"E', 'SY412', 1);
insert into fix (coordinates, fix_name, fix_type_id) values ('53°33''57"N 020°26''46"E', 'SY413', 1);
insert into fix (coordinates, fix_name, fix_type_id) values ('53°28''16"N 020°24''26"E', 'SY414', 1);
insert into fix (coordinates, fix_name, fix_type_id) values ('53°40''51"N 021°07''18"E', 'SY416', 1);
insert into fix (coordinates, fix_name, fix_type_id) values ('53°44''40"N 021°11''34"E', 'SY417', 1);

insert into fix (coordinates, fix_name, fix_type_id) values ('53°28''16"N 020°55''54"E', 'DER19', 3);
insert into fix (coordinates, fix_name, fix_type_id) values ('53°24''50"N 020°53''59"E', 'SY511', 2);
insert into fix (coordinates, fix_name, fix_type_id) values ('53°26''37"N 020°45''00"E', 'SY512', 2);
insert into fix (coordinates, fix_name, fix_type_id) values ('53°33''32"N 020°26''26"E', 'SY513', 2);
insert into fix (coordinates, fix_name, fix_type_id) values ('53°34''30"N 020°49''23"E', 'SY514', 2);
insert into fix (coordinates, fix_name, fix_type_id) values ('53°36''36"N 021°09''06"E', 'SY516', 2);
insert into fix (coordinates, fix_name, fix_type_id) values ('53°32''43"N 020°58''23"E', 'SY517', 2);

-- departure end of runway (der)

insert into fix (coordinates, fix_name, fix_type_id) values ('53°31''53"N 020°58''13"E', 'SY363', 4);
insert into fix (coordinates, fix_name, fix_type_id) values ('53°48''54"N 020°59''13"E', 'SY362', 4);
insert into fix (coordinates, fix_name, fix_type_id) values ('53°33''44"N 020°47''56"E', 'SY364', 4);
insert into fix (coordinates, fix_name, fix_type_id) values ('53°27''10"N 020°21''13"E', 'SY361', 4);
insert into fix (coordinates, fix_name, fix_type_id) values ('53°21''49"N 020°39''10"E', 'SY366', 4);
insert into fix (coordinates, fix_name, fix_type_id) values ('53°42''45"N 020°54''37"E', 'SY357', 4);

insert into fix (coordinates, fix_name, fix_type_id) values ('53°26''34"N 020°25''40"E', 'SY791', 5);
insert into fix (coordinates, fix_name, fix_type_id) values ('53°33''17"N 020°36''13"E', 'SY792', 5);
insert into fix (coordinates, fix_name, fix_type_id) values ('53°49''01"N 020°55''42"E', 'SY793', 5);
insert into fix (coordinates, fix_name, fix_type_id) values ('53°44''23"N 020°52''25"E', 'SY801', 5);
insert into fix (coordinates, fix_name, fix_type_id) values ('53°41''38"N 021°03''29"E', 'SY802', 5);

insert into fix (coordinates, fix_name, fix_type_id) values ('53°32''40"N 020°49''13"E', 'B',  6);
insert into fix (coordinates, fix_name, fix_type_id) values ('53°28''26"N 020°58''03"E', 'E',   6);
insert into fix (coordinates, fix_name, fix_type_id) values ('53°30''07"N 020°53''55"E', 'W',6);
insert into fix (coordinates, fix_name, fix_type_id) values ('53°25''13"N 021°07''05"E', 'Z',   6);



