create table procedures (
id int auto_increment primary key,
procedure_name varchar(20),
runway int,
procedure_type int
);

INSERT INTO procedures (procedure_name, runway, procedure_type) values ('NIVON1S', 0, 0);
INSERT INTO procedures (procedure_name, runway, procedure_type) values ('UDROV1S', 0, 0);
INSERT INTO procedures (procedure_name, runway, procedure_type) values ('LUSUL1S', 0, 0);
INSERT INTO procedures (procedure_name, runway, procedure_type) values ('NIVON1W', 1, 0);
INSERT INTO procedures (procedure_name, runway, procedure_type) values ('UDROV1W', 1, 0);
INSERT INTO procedures (procedure_name, runway, procedure_type) values ('LUSUL1W', 1, 0);
INSERT INTO procedures (procedure_name, runway, procedure_type) values ('IBINO1R', 0, 1);
INSERT INTO procedures (procedure_name, runway, procedure_type) values ('ARDUT1R', 0, 1);
INSERT INTO procedures (procedure_name, runway, procedure_type) values ('UDROV1R', 0, 1);
INSERT INTO procedures (procedure_name, runway, procedure_type) values ('IBINO2T', 1, 1);
INSERT INTO procedures (procedure_name, runway, procedure_type) values ('ARDUT2T', 1, 1);
INSERT INTO procedures (procedure_name, runway, procedure_type) values ('UDROV2T', 1, 1);