package energy.consumption.dao;

import java.util.List;

import energy.consumption.model.ConsumptionPerHourModel;
import energy.consumption.model.EnergyConsumptionCounter;

/**
 * The Interface EnergyConsumptionDAO contains database queries to 
 * compute energy consumption charges
 *
 * @author shamini.shankar
 */
public interface EnergyConsumptionDAO {
	
	/**
	 * Adds values in ConsumptionPerHourModel.This table contains all counter values, 
	 * on hour basis 
	 *
	 * @param model
	 *           the model
	 * @return null
	 */
	void addConsumptionPerHourValues(ConsumptionPerHourModel model);
	
	/**
	 * Adds values in EnergyConsumptionCounter Model.This tables contains 
	 * unique values of village counters, with the recently updated
	 * consumption charges
	 *
	 * @param model
	 *           the model
	 * @return null
	 */
	void addConsumptionValue(EnergyConsumptionCounter model);
	
	/**
	 * Gets EnergyConsumptionCounter record, for the given counter id
	 *
	 * @param id
	 *           the id
	 * @return EnergyConsumptionCounter
	 */
	EnergyConsumptionCounter getConsumptionById(int id);
	
	/**
	 * Updates the record
	 *
	 * @param model
	 *          the model
	 * @return null
	 */
	void updateConsumptionValue(EnergyConsumptionCounter model);
	
	/**
	 * Gets all the unique village counters
	 *
	 * @param 
	 * @return list of EnergyConsumptionCounter
	 */
	List<EnergyConsumptionCounter> getAllCounters();
	
	/**
	 * Gets the counter for the given duration
	 *
	 * @param id
	 * 		the id
	 * @param duration
	 * 		the duration
	 * @return ConsumptionPerHourModel
	 */
	ConsumptionPerHourModel getConsumptionPerHourVal(int id,int duration);
	
	/**
	 * Check if the record with the given counter id exists
	 *
	 * @param id
	 * 		the id
	 * @return boolean
	 */
	boolean checkIfRecordExists(int id);
	
	/**
	 * Check if the record with the given counter id exists, with empty village name value
	 *
	 * @param id
	 * 		the id
	 * @return boolean
	 */
	boolean checkIfVillageNameExists(int id);
}
