package com.apap.tutorial7.repository;

import com.apap.tutorial7.model.FlightModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface FlightDB extends JpaRepository<FlightModel, Long>{
	FlightModel findByPilotLicenseNumber (String licenseNumber);
	FlightModel findFLightByFlightNumber(String flightNumber);
	FlightModel findFlightById (Long id);
	Optional<FlightModel> findFlightByFlightNumber(String flightNumber);
	void deleteByFlightNumber(String flightNumber);
	Optional<FlightModel> findByFlightNumber(String flightNumber);
}
