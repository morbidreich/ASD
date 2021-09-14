create table procedure_fix (
id int auto_increment,
procedure_id int,
fix_id int,
primary key(id, procedure_id, fix_id),
foreign key (procedure_id) references procedures(id),
foreign key (fix_id) references fix(id));

INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (1,6);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (1,7);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (1,8);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (1,9);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (1,1);

INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (2,6);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (2,7);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (2,8);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (2,9);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (2,10);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (2,2);

INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (3,6);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (3,7);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (3,11);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (3,12);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (3,3);

INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (4,13);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (4,14);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (4,15);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (4,16);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (4,1);

INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (5,13);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (5,14);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (5,15);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (5,17);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (5,19);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (5,2);

INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (6,13);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (6,14);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (6,15);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (6,17);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (6,18);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (6,3);

INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (7,4);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (7,23);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (7,24);

INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (8,5);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (8,21);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (8,22);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (8,24);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (8,25);

INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (9,2);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (9,20);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (9,22);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (9,24);

INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (10,4);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (10,26);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (10,27);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (10,29);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (10,30);

INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (11,5);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (11,28);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (11,29);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (11,30);

INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (12,2);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (12,27);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (12,29);
INSERT INTO procedure_fix (procedure_id, fix_id) VALUES (12,30);