package energy.consumption.calc.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import energy.consumption.calc.services.EnergyConsumptionService;
import energy.consumption.requests.EnergyConsumptionRequest;
import energy.consumption.requests.EnergyConsumptionResponse;

/**
 * This class computes energy consumption charges, based on the 
 * information received by the electricity counters
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	public static final String SERVER_URI = "http://localhost:9002/counter?id= {id}";
	
	private EnergyConsumptionService energyService;
	
	@Autowired(required=true)
	@Qualifier(value="energyService")
	public void setPersonService(EnergyConsumptionService service){
		this.energyService = service;
	}

	
	/**
	 * Gets energy consumption charges on hour basis
	 */
	@RequestMapping(value = "/counter_callback", method = RequestMethod.POST , consumes =
		{ MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public HttpStatus getConsumptionVal(@RequestBody EnergyConsumptionRequest request) {
		
		energyService.addConsumptionValues(request);
		
		// Calls the external api to fetch village name
		int id = request.getVillageId();
		RestTemplate template = new RestTemplate();
		EnergyConsumptionRequest externalRequest = template.getForObject(SERVER_URI, EnergyConsumptionRequest.class,"id");
		externalRequest.setVillageName(externalRequest.getVillageName());
		this.energyService.updateVillageName(externalRequest);
		return HttpStatus.CREATED;
	}
	
	/**
	 * Generates energy consumption report, per village for the given duration
	 */
	@RequestMapping(value = "/consumption_report", method = RequestMethod.GET)
	@ResponseBody
	public List<EnergyConsumptionResponse> getConsumptionReport(@RequestParam String duration) {
		
		List<EnergyConsumptionResponse> counterList = new ArrayList<EnergyConsumptionResponse>();
		// Get time value
		String time[]=duration.split("h");
		int reportDuration = Integer.parseInt(time[0]);		
		counterList = this.energyService.getConsumptionReport(reportDuration);				
		return counterList;
	}
	
	
	
	
}
