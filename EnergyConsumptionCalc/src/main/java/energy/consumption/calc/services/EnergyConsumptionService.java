package energy.consumption.calc.services;

import java.util.List;
import energy.consumption.requests.EnergyConsumptionRequest;
import energy.consumption.requests.EnergyConsumptionResponse;

/**
 * The interface EnergyConsumptionService contains interface methods to 
 * compute energy consumption charges
 *
 * @author shamini.shankar
 */
public interface EnergyConsumptionService {
	
	/**
	 * Calls DAO methods to add consumption values, in database tables
	 *
	 * @param request
	 *           the request
	 * @return null
	 */
	void addConsumptionValues(EnergyConsumptionRequest request);
	
	/**
	 * Generates consumption report for the given duration
	 *
	 * @param duration
	 *           the duration
	 * @return list of EnergyConsumptionResponse model
	 */
	List<EnergyConsumptionResponse> getConsumptionReport(int duration);
	
	/**
	 * Updates village name with the given value
	 *
	 * @param request
	 *           the request
	 * @return null
	 */
	void updateVillageName(EnergyConsumptionRequest request);

}
