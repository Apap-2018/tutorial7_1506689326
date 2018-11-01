package com.apap.tutorial7.service;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.repository.FlightDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 
 *  FlightServiceImpl
 */
@Service
@Transactional
public class FlightServiceImpl implements FlightService {
	@Autowired
	private FlightDB flightDb;
	
	@Override
	public FlightModel addFlight(FlightModel flight) {
		return flightDb.save(flight);
	}

	@Override
	public void deleteFlight(FlightModel flight) {
		// TODO Auto-generated method stub
		flightDb.delete(flight);
	}

	@Override
	public void updateFlight(long flightId,FlightModel flight ) {
		// TODO Auto-generated method stub
		FlightModel updateFlight = flightDb.findFlightById(flightId);
		updateFlight.setFlightNumber(flight.getFlightNumber());
		updateFlight.setOrigin(flight.getOrigin());
		updateFlight.setDestination(flight.getDestination());
		updateFlight.setTime(flight.getTime());
		flightDb.save(updateFlight);
		
	}

	@Override
	public FlightModel getFlightDetailByLicenseNumber(String licenseNumber) {
		// TODO Auto-generated method stub
		return flightDb.findByPilotLicenseNumber(licenseNumber);
	}

	@Override
	public FlightModel getFlight(long id) {
		// TODO Auto-generated method stub
		return flightDb.findFlightById(id);
	}

	@Override
	public List<FlightModel> getFlightList() {
		// TODO Auto-generated method stub
		return flightDb.findAll();
	}

	@Override
	public FlightModel getFlightDetailByFlightNumber(String flightNumber) {
		// TODO Auto-generated method stub
		return flightDb.findFLightByFlightNumber(flightNumber);
	}

	@Override
	public Optional<FlightModel> getFlightDetailById(long flightId) {
		// TODO Auto-generated method stub
		return flightDb.findById(flightId);
	}
	
	
	
}
