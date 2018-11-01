package com.apap.tutorial7.service;

import com.apap.tutorial7.model.PilotModel;
import com.apap.tutorial7.repository.PilotDB;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * PilotServiceImpl
 */

@Service
@Transactional
public class PilotServiceImpl implements PilotService {
	@Autowired
	private PilotDB pilotDb;
	
	
	@Override
	public PilotModel addPilot(PilotModel pilot) {
		return pilotDb.save(pilot);
	}

	@Override
	public void deletePilot(PilotModel pilot) {
		 pilotDb.delete(pilot);
	}

	@Override
	public Optional<PilotModel> getPilotDetailByLicenseNumber(String licenseNumber) {
		// TODO Auto-generated method stub
		return pilotDb.findByLicenseNumber(licenseNumber);
	}

	@Override
	public Optional<PilotModel> getPilotDetailById(long id) {
		// TODO Auto-generated method stub
		return pilotDb.findById(id);
	}

}
