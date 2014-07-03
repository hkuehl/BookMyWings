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
		aircraftType.setTypeString(this.getClass().getSimpleName());
		AircraftType addAircraftType = aircraftTypeService
				.addAircraftType(aircraftType);
		Assert.assertNotNull(addAircraftType.getId());

		addAircraftType.setTypeString(this.getClass().getName());
		Assert.assertEquals(this.getClass().getName(), aircraftTypeService
				.updateAircraftType(addAircraftType).getTypeString());
		Assert.assertEquals(this.getClass().getName(), aircraftTypeService
				.getAircraftType(addAircraftType.getId()).getTypeString());

		aircraftTypeService.deleteAircraftType(addAircraftType.getId());

		Assert.assertNull(aircraftTypeService.getAircraftType(addAircraftType
				.getId()));
	}

}
