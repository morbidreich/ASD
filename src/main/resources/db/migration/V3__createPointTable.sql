create table point (
    id int not null auto_increment,
    coordinates varchar(30) not null,
    polygon_id int,
    primary key (id),
    foreign key (polygon_id) references polygon(id)

    );

--    polygon ids:
--    ctr - 1
--    tma-a 2
--    tma-b 3
--    tma-c 4
--    tma-d 5


-- EPSY CTR
insert into point (coordinates, polygon_id) values ('53°38''46"N 020°57''22"E', 1);
insert into point (coordinates, polygon_id) values ('53°36''21"N 021°06''45"E', 1);
insert into point (coordinates, polygon_id) values ('53°29''21"N 021°05''37"E', 1);
insert into point (coordinates, polygon_id) values ('53°25''31"N 021°03''48"E', 1);
insert into point (coordinates, polygon_id) values ('53°17''57"N 020°56''02"E', 1);
insert into point (coordinates, polygon_id) values ('53°19''49"N 020°46''46"E', 1);
insert into point (coordinates, polygon_id) values ('53°28''21"N 020°46''35"E', 1);
insert into point (coordinates, polygon_id) values ('53°32''40"N 020°49''13"E', 1);
insert into point (coordinates, polygon_id) values ('53°38''46"N 020°57''22"E', 1);

-- epsy tma a
insert into point (coordinates, polygon_id) values ('53°43''02"N 020°57''18"E', 2);
insert into point (coordinates, polygon_id) values ('53°41''44"N 021°03''15"E', 2);
insert into point (coordinates, polygon_id) values ('53°40''49"N 021°07''28"E', 2);
insert into point (coordinates, polygon_id) values ('53°36''21"N 021°06''45"E', 2);
insert into point (coordinates, polygon_id) values ('53°29''21"N 021°05''37"E', 2);
insert into point (coordinates, polygon_id) values ('53°25''49"N 021°03''57"E', 2);
insert into point (coordinates, polygon_id) values ('53°25''31"N 021°03''48"E', 2);
insert into point (coordinates, polygon_id) values ('53°17''57"N 020°56''02"E', 2);
insert into point (coordinates, polygon_id) values ('53°13''42"N 020°51''37"E', 2);
insert into point (coordinates, polygon_id) values ('53°17''21"N 020°34''42"E', 2);
insert into point (coordinates, polygon_id) values ('53°23''36"N 020°34''24"E', 2);
insert into point (coordinates, polygon_id) values ('53°33''07"N 020°40''19"E', 2);
insert into point (coordinates, polygon_id) values ('53°41''53"N 020°49''55"E', 2);
insert into point (coordinates, polygon_id) values ('53°43''02"N 020°57''18"E', 2);

-- epsy tma b
insert into point (coordinates, polygon_id) values ('53°40''40"N 020°41''06"E', 3);
insert into point (coordinates, polygon_id) values ('53°43''55"N 020°49''32"E', 3);
insert into point (coordinates, polygon_id) values ('53°47''10"N 020°58''00"E', 3);
insert into point (coordinates, polygon_id) values ('53°47''37"N 021°02''27"E', 3);
insert into point (coordinates, polygon_id) values ('53°46''54"N 021°08''43"E', 3);
insert into point (coordinates, polygon_id) values ('53°45''16"N 021°15''51"E', 3);
insert into point (coordinates, polygon_id) values ('53°43''54"N 021°19''14"E', 3);
insert into point (coordinates, polygon_id) values ('53°41''19"N 021°18''36"E', 3);
insert into point (coordinates, polygon_id) values ('53°38''42"N 021°17''11"E', 3);
insert into point (coordinates, polygon_id) values ('53°36''21"N 021°15''55"E', 3);
insert into point (coordinates, polygon_id) values ('53°25''31"N 021°03''48"E', 3);
insert into point (coordinates, polygon_id) values ('53°17''57"N 020°56''02"E', 3);
insert into point (coordinates, polygon_id) values ('53°13''42"N 020°51''37"E', 3);
insert into point (coordinates, polygon_id) values ('53°17''21"N 020°34''42"E', 3);
insert into point (coordinates, polygon_id) values ('53°18''55"N 020°28''15"E', 3);
insert into point (coordinates, polygon_id) values ('53°23''33"N 020°15''25"E', 3);
insert into point (coordinates, polygon_id) values ('53°37''08"N 020°29''23"E', 3);
insert into point (coordinates, polygon_id) values ('53°40''40"N 020°41''06"E', 3);

-- epsy tma c
insert into point (coordinates, polygon_id) values ('53°23''33"N 020°15''25"E', 4);
insert into point (coordinates, polygon_id) values ('53°30''12"N 019°51''45"E', 4);
insert into point (coordinates, polygon_id) values ('53°38''49"N 020°02''36"E', 4);
insert into point (coordinates, polygon_id) values ('53°40''49"N 020°10''59"E', 4);
insert into point (coordinates, polygon_id) values ('53°37''08"N 020°29''23"E', 4);
insert into point (coordinates, polygon_id) values ('53°23''33"N 020°15''25"E', 4);

-- epsy tma d
insert into point (coordinates, polygon_id) values ('53°40''45"N 020°41''20"E', 5);
insert into point (coordinates, polygon_id) values ('53°40''40"N 020°41''06"E', 5);
insert into point (coordinates, polygon_id) values ('53°45''44"N 020°46''25"E', 5);
insert into point (coordinates, polygon_id) values ('53°50''48"N 020°51''46"E', 5);
insert into point (coordinates, polygon_id) values ('53°51''26"N 020°55''17"E', 5);
insert into point (coordinates, polygon_id) values ('53°52''17"N 021°00''00"E', 5);
insert into point (coordinates, polygon_id) values ('53°52''16"N 021°00''41"E', 5);
insert into point (coordinates, polygon_id) values ('53°52''07"N 021°07''54"E', 5);
insert into point (coordinates, polygon_id) values ('53°50''44"N 021°12''49"E', 5);
insert into point (coordinates, polygon_id) values ('53°49''32"N 021°17''00"E', 5);
insert into point (coordinates, polygon_id) values ('53°46''04"N 021°19''38"E', 5);
insert into point (coordinates, polygon_id) values ('53°43''54"N 021°19''14"E', 5);
insert into point (coordinates, polygon_id) values ('53°45''16"N 021°15''51"E', 5);
insert into point (coordinates, polygon_id) values ('53°46''54"N 021°08''43"E', 5);
insert into point (coordinates, polygon_id) values ('53°47''37"N 021°02''27"E', 5);
insert into point (coordinates, polygon_id) values ('53°47''10"N 020°58''00"E', 5);
insert into point (coordinates, polygon_id) values ('53°47''02"N 020°57''39"E', 5);
insert into point (coordinates, polygon_id) values ('53°40''45"N 020°41''20"E', 5);

-- tma gd
insert into point (coordinates, polygon_id) values ('55°08''07"N 016°15''01"E', 6);
insert into point (coordinates, polygon_id) values ('55°17''24"N 018°24''03"E', 6);
insert into point (coordinates, polygon_id) values ('54°38''16"N 019°21''20"E', 6); -- IPLIT
insert into point (coordinates, polygon_id) values ('54°26''52"N 019°39''15"E', 6); -- GOMED
insert into point (coordinates, polygon_id) values ('54°25''18"N 020°00''00"E', 6);
insert into point (coordinates, polygon_id) values ('53°33''51"N 020°18''20"E', 6);
insert into point (coordinates, polygon_id) values ('53°26''21"N 019°24''33"E', 6);
insert into point (coordinates, polygon_id) values ('53°19''05"N 018°44''35"E', 6);
insert into point (coordinates, polygon_id) values ('53°11''34"N 018°06''35"E', 6);
insert into point (coordinates, polygon_id) values ('52°44''05"N 017°34''42"E', 6);
insert into point (coordinates, polygon_id) values ('53°11''18"N 016°34''37"E', 6);
insert into point (coordinates, polygon_id) values ('53°37''29"N 016°56''09"E', 6);
insert into point (coordinates, polygon_id) values ('54°31''54"N 015°33''12"E', 6);
insert into point (coordinates, polygon_id) values ('54°41''06"N 015°43''09"E', 6);
insert into point (coordinates, polygon_id) values ('54°55''00"N 015°52''00"E', 6);
insert into point (coordinates, polygon_id) values ('55°08''07"N 016°15''01"E', 6);

-- TSA02A
insert into point (coordinates, polygon_id) values ('53°16''37"N 019°42''04"E', 7);
insert into point (coordinates, polygon_id) values ('53°28''08"N 019°37''06"E', 7);
insert into point (coordinates, polygon_id) values ('53°45''46"N 021°51''03"E', 7);
insert into point (coordinates, polygon_id) values ('53°38''43"N 021°18''37"E', 7);
insert into point (coordinates, polygon_id) values ('53°24''02"N 020°13''42"E', 7);
insert into point (coordinates, polygon_id) values ('53°16''37"N 019°42''04"E', 7);

-- TSA02B
insert into point (coordinates, polygon_id) values ('53°16''37"N 019°42''04"E', 8);
insert into point (coordinates, polygon_id) values ('53°24''02"N 020°13''42"E', 8);
insert into point (coordinates, polygon_id) values ('53°23''33"N 020°15''25"E', 8);
insert into point (coordinates, polygon_id) values ('53°18''55"N 020°28''15"E', 8);
insert into point (coordinates, polygon_id) values ('53°17''21"N 020°34''42"E', 8);
insert into point (coordinates, polygon_id) values ('53°13''42"N 020°51''37"E', 8);
insert into point (coordinates, polygon_id) values ('53°17''57"N 020°56''02"E', 8);
insert into point (coordinates, polygon_id) values ('53°25''31"N 021°03''48"E', 8);
insert into point (coordinates, polygon_id) values ('53°38''43"N 021°18''37"E', 8);
insert into point (coordinates, polygon_id) values ('53°45''46"N 021°51''03"E', 8);
insert into point (coordinates, polygon_id) values ('53°46''21"N 022°20''29"E', 8);
insert into point (coordinates, polygon_id) values ('52°41''55"N 020°59''26"E', 8);
insert into point (coordinates, polygon_id) values ('52°42''37"N 020°46''02"E', 8);
insert into point (coordinates, polygon_id) values ('52°46''13"N 020°24''44"E', 8);
insert into point (coordinates, polygon_id) values ('52°51''16"N 020°15''26"E', 8);
insert into point (coordinates, polygon_id) values ('53°06''59"N 019°46''11"E', 8);
insert into point (coordinates, polygon_id) values ('53°16''37"N 019°42''04"E', 8);

-- TSA02C
insert into point (coordinates, polygon_id) values ('53°23''33"N 020°15''25"E', 9);
insert into point (coordinates, polygon_id) values ('53°24''02"N 020°13''42"E', 9);
insert into point (coordinates, polygon_id) values ('53°38''43"N 021°18''37"E', 9);
insert into point (coordinates, polygon_id) values ('53°25''31"N 021°03''48"E', 9);
insert into point (coordinates, polygon_id) values ('53°17''57"N 020°56''02"E', 9);
insert into point (coordinates, polygon_id) values ('53°13''42"N 020°51''37"E', 9);
insert into point (coordinates, polygon_id) values ('53°17''21"N 020°34''42"E', 9);
insert into point (coordinates, polygon_id) values ('53°18''55"N 020°28''15"E', 9);
insert into point (coordinates, polygon_id) values ('53°23''33"N 020°15''25"E', 9);

--TSA02D
insert into point (coordinates, polygon_id) values ('52°41''55"N 020°59''26"E', 10);
insert into point (coordinates, polygon_id) values ('52°41''11"N 021°12''44"E', 10);
insert into point (coordinates, polygon_id) values ('52°45''30"N 021°44''07"E', 10);
insert into point (coordinates, polygon_id) values ('52°49''58"N 022°17''56"E', 10);
insert into point (coordinates, polygon_id) values ('53°42''28"N 022°35''57"E', 10);
insert into point (coordinates, polygon_id) values ('53°46''30"N 022°28''17"E', 10);
insert into point (coordinates, polygon_id) values ('53°46''21"N 022°20''29"E', 10);
insert into point (coordinates, polygon_id) values ('52°41''55"N 020°59''26"E', 10);

--TSA02E
insert into point (coordinates, polygon_id) values ('52°45''30"N 021°44''07"E', 11);
insert into point (coordinates, polygon_id) values ('52°41''11"N 021°12''44"E', 11);
insert into point (coordinates, polygon_id) values ('52°32''02"N 021°24''06"E', 11);
insert into point (coordinates, polygon_id) values ('52°26''15"N 021°29''01"E', 11);
insert into point (coordinates, polygon_id) values ('52°26''14"N 021°36''43"E', 11);
insert into point (coordinates, polygon_id) values ('52°45''30"N 021°44''07"E', 11);

--TSA02F
insert into point (coordinates, polygon_id) values ('52°49''58"N 022°17''56"E', 12);
insert into point (coordinates, polygon_id) values ('52°29''49"N 022°10''49"E', 12);
insert into point (coordinates, polygon_id) values ('52°25''45"N 022°04''45"E', 12);
insert into point (coordinates, polygon_id) values ('52°26''08"N 021°42''31"E', 12);
insert into point (coordinates, polygon_id) values ('52°26''14"N 021°36''43"E', 12);
insert into point (coordinates, polygon_id) values ('52°45''30"N 021°44''07"E', 12);
insert into point (coordinates, polygon_id) values ('52°49''58"N 022°17''56"E', 12);

--TSA01A
insert into point (coordinates, polygon_id) values ('54°06''38"N 019°29''32"E', 14);
insert into point (coordinates, polygon_id) values ('54°11''39"N 019°37''02"E', 14);
insert into point (coordinates, polygon_id) values ('53°50''19"N 019°44''32"E', 14);
insert into point (coordinates, polygon_id) values ('53°48''49"N 019°28''06"E', 14);
insert into point (coordinates, polygon_id) values ('53°54''17"N 019°30''31"E', 14);
insert into point (coordinates, polygon_id) values ('54°06''38"N 019°29''32"E', 14);

--TSA01B
insert into point (coordinates, polygon_id) values ('54°11''39"N 019°37''02"E', 15);
insert into point (coordinates, polygon_id) values ('54°21''00"N 019°51''00"E', 15);
insert into point (coordinates, polygon_id) values ('54°19''48"N 020°30''00"E', 15);
insert into point (coordinates, polygon_id) values ('53°59''25"N 020°35''24"E', 15);
insert into point (coordinates, polygon_id) values ('53°50''19"N 019°44''32"E', 15);
insert into point (coordinates, polygon_id) values ('54°11''39"N 019°37''02"E', 15);

--TSA01C
insert into point (coordinates, polygon_id) values ('54°19''48"N 020°30''00"E', 16);
insert into point (coordinates, polygon_id) values ('54°17''59"N 021°19''04"E', 16);
insert into point (coordinates, polygon_id) values ('54°06''19"N 021°19''56"E', 16);
insert into point (coordinates, polygon_id) values ('53°59''25"N 020°35''24"E', 16);
insert into point (coordinates, polygon_id) values ('54°19''48"N 020°30''00"E', 16);












