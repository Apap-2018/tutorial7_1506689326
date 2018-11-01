package com.apap.tutorial7.controller;

import com.apap.tutorial7.model.PilotModel;
import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.rest.PilotDetail;
import com.apap.tutorial7.rest.Setting;
import com.apap.tutorial7.service.FlightService;
import com.apap.tutorial7.service.PilotService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
/**
 * 
 * FlightController
 */

@RestController
@RequestMapping("/flight")
public class FlightController{
	@Autowired
	private FlightService flightService;
	
	@PostMapping(value = "/add")
	public FlightModel addFlightSubmit(@RequestBody FlightModel flight) {
		return flightService.addFlight(flight);
	}
	
	@GetMapping(value= "/view/{flightNumber}")
	public FlightModel flightView(@PathVariable("flightNumber") String flightNumber) {
		FlightModel flight  = flightService.getFlightDetailByFlightNumber(flightNumber);
		return flight;
	}
	
	@GetMapping(value="/all")
	public List<FlightModel> flightAll(){
		List<FlightModel> allFlight = flightService.getFlightList();
		return allFlight;
	}
	
	@DeleteMapping(value="/delete")
	private String deleteFlight(@RequestParam("flightId") long flightId) {
		FlightModel flight = flightService.getFlightDetailById(flightId).get();
		flightService.deleteFlight(flight);
		return "Flight has been deleted";
	}
	
	@PutMapping(value="/update/{flightId}")
	public String updateFlightSubmit(@PathVariable("flightId") long flightId,
			@RequestParam(value = "destination", required = false) String destination,
			@RequestParam(value = "origin", required = false) String origin,
			@RequestParam(value ="time" , required = false) Date time) {
		FlightModel flight = flightService.getFlightDetailById(flightId).get();
		if(flight.equals(null)) {
			return "Couldn't find your flight";
		}
		
		flight.setDestination(destination);
		flight.setOrigin(origin);
		flightService.updateFlight(flightId, flight);
		return "Flight Update Success"; 
	}
	
	
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@GetMapping(value = "/airport/{city}")
	public String getListAirport(@PathVariable("city") String city) throws Exception {
		String path = Setting.airportUrl + "&term=" + city;
		return restTemplate.getForEntity(path, String.class).getBody();
	}
	
}



//@Controller
//public class FlightController {
//	@Autowired
//	private FlightService flightService;
//	
//	@Autowired
//	private PilotService pilotService;
//
//	//private PilotModel pilot;
//
//	
//	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
//	private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
//		//FlightModel flight = new FlightModel();
//		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber).get();
//		//flight.setPilot(pilot);
//		pilot.setPilotFlight(new ArrayList<FlightModel>());
//		pilot.getPilotFlight().add(new FlightModel());
//		model.addAttribute("pilot", pilot);
//		return "addFlight";
//	}
//	
//	//controller add row 
//	@RequestMapping(value = "/flight/add", method = RequestMethod.POST)
//	private String addFlightSubmit(@ModelAttribute FlightModel flight) {
//		flightService.addFlight(flight);
//		return "add";
//	}
//	
//	//controller submit semua data flight yg ditambah
//	@RequestMapping(value="/flight/add/{licenseNumber}", params={"add"})
//	public String addRow(@ModelAttribute PilotModel pilot, BindingResult bindingResult, Model model) {
//		pilot.getPilotFlight().add(new FlightModel());
//	    model.addAttribute("pilot", pilot);
//	    return "addFlight";
//	}
//	
//	@RequestMapping(value = "/flight/add/{licenseNumber}", params={"submit"}, method = RequestMethod.POST)
//	private String addFlightSubmit(@ModelAttribute PilotModel pilot) {
//		PilotModel thisPilot = pilotService.getPilotDetailByLicenseNumber(pilot.getLicenseNumber()).get();
//		for (FlightModel flight:pilot.getPilotFlight()) {
//			flight.setPilot(thisPilot);
//			flightService.addFlight(flight);
//		}
//		return "add";
//	}
//	
//	
//	
//	//batas edit 
//	@RequestMapping(value = "/flight/view")
//	private String viewFlight(@RequestParam (value = "flightNumber" )String flightNumber, Model model) {
//		
//		List<FlightModel> archive = flightService.getFlightList();
//		List<FlightModel> archiveView = new ArrayList();
//		
//		for(FlightModel looping : archive) {
//			if(looping.getFlightNumber().equals(flightNumber)) {
//				archiveView.add(looping);
//			}
//		}
//		if(archiveView.size() == 0) {
//			return "error";
//		}
//		
//		model.addAttribute("fnum", flightNumber);
//		model.addAttribute("flights", archiveView);
//		return "view-flight";
//		
//	}
//	
//	@RequestMapping(value="/flight/delete", method = RequestMethod.POST)
//	private String delPilot(@ModelAttribute PilotModel pilot, Model model) {
//		for(FlightModel flight : pilot.getPilotFlight()){
//			flightService.deleteFlight(flight.getId());
//		}
//		return "hapusFlight";
//	}
//	
//	@RequestMapping(value="/flight/update/{id}", method = RequestMethod.GET)
//	private String updtFlight(@PathVariable(value = "id")Long id, Model model) {
//		FlightModel flight = flightService.getFlight(id);
//		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(flight.getPilot().getLicenseNumber()).get();
//		flight.setPilot(pilot);
//		model.addAttribute("updFlight", flight);
//		return "updFlight";
//	}
//	
//	@RequestMapping(value = "flight/update", method = RequestMethod.POST)
//	private String flightSubmitUpdate(@ModelAttribute FlightModel flight) {
//		flightService.updateFlight(flight, flight.getId());
//		return "updateDataFlight";
//	}
//	
//
//}
