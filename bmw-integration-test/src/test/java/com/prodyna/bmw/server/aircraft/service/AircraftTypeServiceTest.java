package com.prodyna.bmw.server.aircraft.service;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prodyna.bmw.server.aircraft.type.AircraftType;
import com.prodyna.bmw.server.aircraft.type.AircraftTypeService;

/**
 * @author Henry Kuehl, PRODYNA AG
 * 
 */
@RunWith(Arquillian.class)
public class AircraftTypeServiceTest {

	@Inject
	private AircraftTypeService aircraftTypeService;

	@Test
	public void testCRUDAircraftType() {
		AircraftType aircraftType = new AircraftType();
		aircraftType.setTypeString("Boeing");
		AircraftType addAircraftType = aircraftTypeService
				.addAircraftType(aircraftType);
		Assert.assertNotNull(addAircraftType.getId());

		addAircraftType.setTypeString("Cessna");
		Assert.assertEquals("Cessna",
				aircraftTypeService.updateAircraftType(addAircraftType)
						.getTypeString());
		Assert.assertEquals("Cessna",
				aircraftTypeService.getAircraftType(addAircraftType.getId())
						.getTypeString());

		aircraftTypeService.deleteAircraftType(addAircraftType.getId());

		Assert.assertNull(aircraftTypeService.getAircraftType(addAircraftType
				.getId()));
	}

}
