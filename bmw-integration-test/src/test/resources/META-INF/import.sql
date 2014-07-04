insert into air_aircraft_type values ('aircraftTypeId','Cessna');
insert into air_aircraft_type values ('com.prodyna.bmw.server.aircraft.service.AircraftServiceTest#aircraftType','Boeing');
insert into air_aircraft_type values ('com.prodyna.bmw.server.pilot.service.PilotLicenseServiceTest','PilotLicenseServiceTest');

insert into air_aircraft values ('aircraftId', 'com.prodyna.bmw.server.aircraft.service.AircraftBusinessServiceTest', 'aircraftTypeId');
insert into air_aircraft values ('com.prodyna.bmw.server.aircraft.service.AircraftServiceTest#aircraftId', 'com.prodyna.bmw.server.aircraft.service.AircraftServiceTest#registration', 'com.prodyna.bmw.server.aircraft.service.AircraftServiceTest#aircraftType');

insert into pil_roles values ('1', 'admin');
insert into pil_roles values ('2', 'admin');

insert into pil_pilot values ('pilotId', 'Vorname', 'Nachname', 'secret', 'username','1');
insert into pil_pilot values ('com.prodyna.bmw.server.pilot.service.PilotLicenseServiceTest', 'Vorname', 'Nachname', 'secret', 'PilotLicenseServiceTest', '1');

insert into lic_license values ('licenseId', '2013-01-01', '2015-01-01', 'aircraftTypeId', 'pilotId');