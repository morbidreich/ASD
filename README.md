<img src="https://user-images.githubusercontent.com/58663723/147891905-926a7b8e-0f58-4fac-bf0e-b637a4127044.png">

# AirspaceDisplay 

This is my take on creating surveillance system similar to what is found on most of world's air traffic control centres. I work as an air traffic controller at Olsztyn-Mazury airport. As a small airport it is a bit underinvested, some essential systems are painfully lacking. One of them is radar/surveilance system that shows current position of airplanes nearby. All we have is radio, pencil (recently electronic) and position repots given by pilots - good old [procedural control](https://en.wikipedia.org/wiki/Procedural_control) as known since 50s. Of course this is sufficient to provide safety in an low-flights-density environment, but a bit ancient and oldschool at the same time :) I decided to fill that gap by writing my own airplane tracking solution.

<img src="https://user-images.githubusercontent.com/58663723/147891470-dec6f31a-4b47-4e9e-9ce3-beba1c2f9958.jpg" alt="click for full size image" width="800">

Due to whole variety of security and procedural concerns this tool is not intended for operational use. It is rather training playground, kind of proof of concept.

## Core features:
* Live air traffic tracking and display mimicking industry standards
* Flexible lightweight architecture
* Geospatial airspace data display, such as aerodrome terminal areas, military activity zones, standard arrival/departure routes, danger/prohibited/restricted zones etc.
* Helper tools - distance/bearing measurements, search tools, flight parameters display


## Data sources:

### Airplane suveillance data - [www.opensky-network.org](www.opensky-network.org)

OpenSky Network is a non-profit organisation based in Switzerland. It provides real world air traffic data collected via vast, global network of [Automatic Dependant Surveillance-Broadcast](https://en.wikipedia.org/wiki/Automatic_Dependent_Surveillance%E2%80%93Broadcast) receivers listening to publicly available 1090MHz radio frequency. This is the same technology that is used by well known [www.flightradar24.com](www.flightradar24.com), but accessible for free (for educational or research purposes) via dedicated API.


### Airspace structures data - [www.ais.pansa.pl/publikacje/aip-ifr/](www.ais.pansa.pl/publikacje/aip-ifr/)

Most of European states publish freely available AIP - Aeronautical Information Publications. This is a document containing data essential to air navigation, ranging widely from details of regulations, procedures, through airspace structures to layouts of aerodrome aprons and airplane stands. I used data from 2 parts of AIP: ENR (*enroute*) for country-wide airspace structures and areas, and AD (*aerodrome*) for terminal specific procedures.

## Technologies

* Core Java
* H2 relational database coupled with Hibernate for storing and reading airspace data
* Flyway for database migrations
* Heavily modified and refactored [MiniGeo](https://github.com/ChristopheJacquet/Minigeo) library by Christophe Jacquet as plotting framework
* JUnit5 for (somewhat lacking) unit tests

