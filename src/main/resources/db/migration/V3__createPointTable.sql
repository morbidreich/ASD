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

--EPGDCTR
insert into point (coordinates, polygon_id) values ('54°29''00"N 018°13''07"E', 17);
insert into point (coordinates, polygon_id) values ('54°27''47"N 018°20''02"E', 17);
insert into point (coordinates, polygon_id) values ('54°23''13"N 018°38''26"E', 17);
insert into point (coordinates, polygon_id) values ('54°20''13"N 018°45''29"E', 17);
insert into point (coordinates, polygon_id) values ('54°18''49"N 018°44''30"E', 17);
insert into point (coordinates, polygon_id) values ('54°17''06"N 018°43''18"E', 17);
insert into point (coordinates, polygon_id) values ('54°17''25"N 018°37''12"E', 17);
insert into point (coordinates, polygon_id) values ('54°16''44"N 018°30''30"E', 17);
insert into point (coordinates, polygon_id) values ('54°19''56"N 018°11''15"E', 17);
insert into point (coordinates, polygon_id) values ('54°24''24"N 018°09''47"E', 17);
insert into point (coordinates, polygon_id) values ('54°29''00"N 018°13''07"E', 17);

--EPSCCTR
insert into point (coordinates, polygon_id) values ('53°36''10"N 015°10''05"E', 18);
insert into point (coordinates, polygon_id) values ('53°32''00"N 015°13''41"E', 18);
insert into point (coordinates, polygon_id) values ('53°29''30"N 015°04''40"E', 18);
insert into point (coordinates, polygon_id) values ('53°23''40"N 015°02''57"E', 18);
insert into point (coordinates, polygon_id) values ('53°24''14"N 014°58''15"E', 18);
insert into point (coordinates, polygon_id) values ('53°25''01"N 014°56''50"E', 18);
insert into point (coordinates, polygon_id) values ('53°37''56"N 014°39''33"E', 18);
insert into point (coordinates, polygon_id) values ('53°41''09"N 014°36''56"E', 18);
insert into point (coordinates, polygon_id) values ('53°42''00"N 014°37''03"E', 18);
insert into point (coordinates, polygon_id) values ('53°42''47"N 014°37''32"E', 18);
insert into point (coordinates, polygon_id) values ('53°44''34"N 014°41''31"E', 18);
insert into point (coordinates, polygon_id) values ('53°45''24"N 014°44''47"E', 18);
insert into point (coordinates, polygon_id) values ('53°45''57"N 014°54''46"E', 18);
insert into point (coordinates, polygon_id) values ('53°36''10"N 015°10''05"E', 18);

--EPBYCTR
insert into point (coordinates, polygon_id) values ('53°08''46"N 017°40''46"E', 19);
insert into point (coordinates, polygon_id) values ('53°10''42"N 017°53''47"E', 19);
insert into point (coordinates, polygon_id) values ('53°12''57"N 018°14''27"E', 19);
insert into point (coordinates, polygon_id) values ('53°11''56"N 018°18''58"E', 19);
insert into point (coordinates, polygon_id) values ('53°09''37"N 018°21''57"E', 19);
insert into point (coordinates, polygon_id) values ('53°06''05"N 018°22''39"E', 19);
insert into point (coordinates, polygon_id) values ('53°03''44"N 018°21''15"E', 19);
insert into point (coordinates, polygon_id) values ('53°00''10"N 018°14''42"E', 19);
insert into point (coordinates, polygon_id) values ('52°56''35"N 017°53''57"E', 19);
insert into point (coordinates, polygon_id) values ('52°56''36"N 017°48''15"E', 19);
insert into point (coordinates, polygon_id) values ('52°57''44"N 017°44''10"E', 19);
insert into point (coordinates, polygon_id) values ('52°58''44"N 017°41''30"E', 19);
insert into point (coordinates, polygon_id) values ('53°03''47"N 017°39''38"E', 19);
insert into point (coordinates, polygon_id) values ('53°08''11"N 017°40''03"E', 19);
insert into point (coordinates, polygon_id) values ('53°08''46"N 017°40''46"E', 19);

--EPMOCTR
insert into point (coordinates, polygon_id) values ('52°29''01"N 020°21''30"E', 20);
insert into point (coordinates, polygon_id) values ('52°34''53"N 020°38''04"E', 20);
insert into point (coordinates, polygon_id) values ('52°35''18"N 020°39''16"E', 20);
insert into point (coordinates, polygon_id) values ('52°34''22"N 020°46''55"E', 20);
insert into point (coordinates, polygon_id) values ('52°29''57"N 020°51''26"E', 20);
insert into point (coordinates, polygon_id) values ('52°27''06"N 020°52''20"E', 20);
insert into point (coordinates, polygon_id) values ('52°22''54"N 020°36''00"E', 20);
insert into point (coordinates, polygon_id) values ('52°22''56"N 020°30''27"E', 20);
insert into point (coordinates, polygon_id) values ('52°26''00"N 020°21''45"E', 20);
insert into point (coordinates, polygon_id) values ('52°29''01"N 020°21''30"E', 20);

--EPWACTR
insert into point (coordinates, polygon_id) values ('52°12''28"N 020°42''01"E', 21);
insert into point (coordinates, polygon_id) values ('52°15''32"N 020°46''15"E', 21);
insert into point (coordinates, polygon_id) values ('52°15''47"N 020°50''10"E', 21);
insert into point (coordinates, polygon_id) values ('52°15''48"N 020°55''56"E', 21);
insert into point (coordinates, polygon_id) values ('52°15''38"N 020°57''05"E', 21);
insert into point (coordinates, polygon_id) values ('52°15''30"N 020°57''30"E', 21);
insert into point (coordinates, polygon_id) values ('52°15''08"N 021°00''53"E', 21);
insert into point (coordinates, polygon_id) values ('52°11''03"N 021°06''13"E', 21);
insert into point (coordinates, polygon_id) values ('52°07''57"N 021°10''36"E', 21);
insert into point (coordinates, polygon_id) values ('52°02''23"N 021°07''19"E', 21);
insert into point (coordinates, polygon_id) values ('52°00''43"N 021°00''38"E', 21);
insert into point (coordinates, polygon_id) values ('52°08''52"N 020°45''47"E', 21);
insert into point (coordinates, polygon_id) values ('52°12''28"N 020°42''01"E', 21);

--EPPOCTR - 22
insert into point (coordinates, polygon_id) values ('52°33''21"N 016°31''18"E', 22);
insert into point (coordinates, polygon_id) values ('52°26''13"N 016°59''20"E', 22);
insert into point (coordinates, polygon_id) values ('52°24''29"N 017°06''02"E', 22);
insert into point (coordinates, polygon_id) values ('52°20''13"N 017°04''15"E', 22);
insert into point (coordinates, polygon_id) values ('52°21''12"N 016°55''25"E', 22);
insert into point (coordinates, polygon_id) values ('52°23''33"N 016°42''47"E', 22);
insert into point (coordinates, polygon_id) values ('52°26''17"N 016°28''09"E', 22);
insert into point (coordinates, polygon_id) values ('52°33''21"N 016°31''18"E', 22);

--EPWRCTR - 23
insert into point (coordinates, polygon_id) values ('51°06''01"N 016°33''52"E', 23);
insert into point (coordinates, polygon_id) values ('51°14''11"N 016°39''28"E', 23);
insert into point (coordinates, polygon_id) values ('51°05''33"N 017°08''42"E', 23);
insert into point (coordinates, polygon_id) values ('50°57''38"N 017°02''40"E', 23);
insert into point (coordinates, polygon_id) values ('51°06''01"N 016°33''52"E', 23);

--EPKTCTR - 24
insert into point (coordinates, polygon_id) values ('50°31''40"N 018°46''07"E', 24);
insert into point (coordinates, polygon_id) values ('50°34''08"N 019°01''17"E', 24);
insert into point (coordinates, polygon_id) values ('50°34''11"N 019°10''21"E', 24);
insert into point (coordinates, polygon_id) values ('50°31''31"N 019°21''46"E', 24);
insert into point (coordinates, polygon_id) values ('50°25''50"N 019°22''02"E', 24);
insert into point (coordinates, polygon_id) values ('50°24''14"N 019°06''17"E', 24);
insert into point (coordinates, polygon_id) values ('50°24''10"N 019°00''41"E', 24);
insert into point (coordinates, polygon_id) values ('50°25''24"N 018°46''27"E', 24);
insert into point (coordinates, polygon_id) values ('50°31''40"N 018°46''07"E', 24);

--EPKKCTR - 25
insert into point (coordinates, polygon_id) values ('49°59''26"N 019°35''41"E', 25);
insert into point (coordinates, polygon_id) values ('50°04''13"N 020°00''56"E', 25);
insert into point (coordinates, polygon_id) values ('50°05''06"N 020°02''17"E', 25);
insert into point (coordinates, polygon_id) values ('50°09''04"N 020°00''34"E', 25);
insert into point (coordinates, polygon_id) values ('50°10''09"N 019°46''35"E', 25);
insert into point (coordinates, polygon_id) values ('50°07''59"N 019°36''20"E', 25);
insert into point (coordinates, polygon_id) values ('50°04''17"N 019°31''07"E', 25);
insert into point (coordinates, polygon_id) values ('50°01''21"N 019°32''19"E', 25);
insert into point (coordinates, polygon_id) values ('49°59''26"N 019°35''41"E', 25);

--EPLLCTR - 26
insert into point (coordinates, polygon_id) values ('51°39''28"N 019°09''31"E', 26);
insert into point (coordinates, polygon_id) values ('51°42''28"N 019°07''54"E', 26);
insert into point (coordinates, polygon_id) values ('51°45''17"N 019°08''10"E', 26);
insert into point (coordinates, polygon_id) values ('51°48''18"N 019°18''48"E', 26);
insert into point (coordinates, polygon_id) values ('51°49''22"N 019°24''51"E', 26);
insert into point (coordinates, polygon_id) values ('51°50''13"N 019°36''07"E', 26);
insert into point (coordinates, polygon_id) values ('51°44''15"N 019°39''23"E', 26);
insert into point (coordinates, polygon_id) values ('51°39''39"N 019°23''52"E', 26);
insert into point (coordinates, polygon_id) values ('51°38''01"N 019°17''21"E', 26);
insert into point (coordinates, polygon_id) values ('51°37''54"N 019°15''47"E', 26);
insert into point (coordinates, polygon_id) values ('51°39''28"N 019°09''31"E', 26);

--EPLBCTR - 27
insert into point (coordinates, polygon_id) values ('51°15''40"N 022°33''48"E', 27);
insert into point (coordinates, polygon_id) values ('51°16''13"N 022°37''54"E', 27);
insert into point (coordinates, polygon_id) values ('51°18''09"N 022°52''17"E', 27);
insert into point (coordinates, polygon_id) values ('51°17''24"N 022°56''14"E', 27);
insert into point (coordinates, polygon_id) values ('51°10''55"N 022°58''37"E', 27);
insert into point (coordinates, polygon_id) values ('51°08''25"N 022°54''16"E', 27);
insert into point (coordinates, polygon_id) values ('51°07''18"N 022°49''16"E', 27);
insert into point (coordinates, polygon_id) values ('51°04''14"N 022°35''26"E', 27);
insert into point (coordinates, polygon_id) values ('51°06''05"N 022°30''39"E', 27);
insert into point (coordinates, polygon_id) values ('51°07''22"N 022°30''13"E', 27);
insert into point (coordinates, polygon_id) values ('51°09''22"N 022°29''33"E', 27);
insert into point (coordinates, polygon_id) values ('51°14''11"N 022°27''56"E', 27);
insert into point (coordinates, polygon_id) values ('51°15''40"N 022°33''48"E', 27);

--EPRZCTR - 28
insert into point (coordinates, polygon_id) values ('50°14''08"N 021°45''03"E', 28);
insert into point (coordinates, polygon_id) values ('50°14''02"N 022°17''04"E', 28);
insert into point (coordinates, polygon_id) values ('50°01''00"N 022°17''00"E', 28);
insert into point (coordinates, polygon_id) values ('50°01''00"N 021°45''00"E', 28);
insert into point (coordinates, polygon_id) values ('50°04''26"N 021°43''40"E', 28);
insert into point (coordinates, polygon_id) values ('50°10''39"N 021°43''31"E', 28);
insert into point (coordinates, polygon_id) values ('50°14''08"N 021°45''03"E', 28);