package com.apap.tutorial7.service;
import com.apap.tutorial7.model.FlightModel;
import java.util.List;
import java.util.Optional;

public interface FlightService {
	FlightModel addFlight(FlightModel flight);
	//void deleteFlight(long id);
	void updateFlight(long flightId,FlightModel flight);
	FlightModel getFlightDetailByLicenseNumber(String licenseNumber);
	FlightModel getFlightDetailByFlightNumber(String flightNumber);
	FlightModel getFlight(long id);
	List<FlightModel> getFlightList();
	Optional<FlightModel> getFlightDetailById(long flightId);
	void deleteFlight(FlightModel flight);
	
}
