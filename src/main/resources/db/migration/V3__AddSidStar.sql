create table star (
    id int not null auto_increment,
    star_name varchar(7) not null,
    coordinates varchar(30) not null,
    fix_name varchar(5),
    primary key (id));

insert into star (star_name, coordinates) values ('IBINO1R', '53°33''46"N 019°58''50"E');
insert into star (star_name, coordinates, fix_name) values ('IBINO1R', '53°27''10"N 020°21''13"E', 'SY361');
insert into star (star_name, coordinates, fix_name) values ('IBINO1R', '53°21''49"N 020°39''10"E', 'SY366');

insert into star (star_name, coordinates) values ('ARDUT1R', '53°21''49"N 020°39''10"E');
insert into star (star_name, coordinates, fix_name) values ('ARDUT1R', '53°48''54"N 020°59''13"E', 'SY362');
insert into star (star_name, coordinates, fix_name) values ('ARDUT1R', '53°42''45"N 020°54''37"E', 'SY357');
insert into star (star_name, coordinates, fix_name) values ('ARDUT1R', '53°33''44"N 020°47''56"E', 'SY364');
insert into star (star_name, coordinates, fix_name) values ('ARDUT1R', '53°21''49"N 020°39''10"E', 'SY366');

insert into star (star_name, coordinates) values ('UDROV1R', '53°21''49"N 020°39''10"E');
insert into star (star_name, coordinates, fix_name) values ('UDROV1R', '53°31''53"N 020°58''13"E', 'SY363');
insert into star (star_name, coordinates, fix_name) values ('UDROV1R', '53°33''44"N 020°47''56"E', 'SY364');
insert into star (star_name, coordinates, fix_name) values ('UDROV1R', '53°21''49"N 020°39''10"E', 'SY366');
