package energy.consumption.calc.services.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import energy.consumption.calc.services.EnergyConsumptionService;
import energy.consumption.dao.EnergyConsumptionDAO;
import energy.consumption.model.ConsumptionPerHourModel;
import energy.consumption.model.EnergyConsumptionCounter;
import energy.consumption.requests.EnergyConsumptionRequest;
import energy.consumption.requests.EnergyConsumptionResponse;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class EnergyConsumptionSeviceImpl contains service methods to 
 * compute energy consumption charges
 *
 * @author shamini.shankar
 */
@Service
public class EnergyConsumptionSeviceImpl implements EnergyConsumptionService{
	
	private EnergyConsumptionDAO energyConsumptionDao;
	
	public EnergyConsumptionDAO getEnergyConsumptionDao() {
		return energyConsumptionDao;
	}

	public void setEnergyConsumptionDao(EnergyConsumptionDAO energyConsumptionDao) {
		this.energyConsumptionDao = energyConsumptionDao;
	}
	
	/**
	 * Calls DAO methods to add consumption values, in database tables
	 *
	 * @param request
	 *           the request
	 * @return null
	 */
	@Override
	@Transactional
	public void addConsumptionValues(EnergyConsumptionRequest request) {
		
		// Adding consumption values in ConsumptionPerHourModel model		
		ConsumptionPerHourModel consumptionModel = new ConsumptionPerHourModel();
		consumptionModel.setAmount(request.getAmount());
		consumptionModel.setCounter_id(request.getVillageId());
		
		this.energyConsumptionDao.addConsumptionPerHourValues(consumptionModel);
		
		/* Updating the consumption charge value in EnergyConsumptionCounter model.
		 * If the record doesn't exists, add a new record, with the consumption charges.
		*/
		if(this.energyConsumptionDao.checkIfRecordExists(request.getVillageId()))
		{
			EnergyConsumptionCounter model = this.energyConsumptionDao.getConsumptionById(request.getVillageId());
			model.setConsumption(request.getAmount());
			this.energyConsumptionDao.updateConsumptionValue(model);
		}
		else
		{
			EnergyConsumptionCounter model = new EnergyConsumptionCounter();
			model.setVillageId(request.getVillageId());
			model.setConsumption(request.getAmount());
			this.energyConsumptionDao.addConsumptionValue(model);
		}
						
	}

	/**
	 * Generates consumption report for the given duration
	 *
	 * @param duration
	 *           the duration
	 * @return list of EnergyConsumptionResponse model
	 */
	@Override
	@Transactional
	public List<EnergyConsumptionResponse> getConsumptionReport(int duration) {
		// TODO Auto-generated method stub
		List<EnergyConsumptionResponse> resultantList = new ArrayList<EnergyConsumptionResponse>();
		// Get all the unique set of village counters
		List<EnergyConsumptionCounter> counterList = this.energyConsumptionDao.getAllCounters();
		for(EnergyConsumptionCounter counter:counterList)
		{
			// Get the consumption model, for the given duration
			ConsumptionPerHourModel model = this.energyConsumptionDao.getConsumptionPerHourVal(counter.getVillageId(),duration);
			// Calculate the consumption charges
			double consumptionCharges = counter.getConsumption() - model.getAmount();
			// Generate the response
			EnergyConsumptionResponse response = new EnergyConsumptionResponse();
			response.setVillage_name(counter.getVillageName());
			response.setConsumption(consumptionCharges);
			resultantList.add(response);
		}
		return resultantList;
	}

	/**
	 * Updates village name with the given value
	 *
	 * @param request
	 *           the request
	 * @return null
	 */
	@Override
	public void updateVillageName(EnergyConsumptionRequest request) {
		// TODO Auto-generated method stub
		EnergyConsumptionCounter model = this.energyConsumptionDao.getConsumptionById(request.getVillageId());
		// If the village name is not present,then update the counter with the village name
		if(this.energyConsumptionDao.checkIfVillageNameExists(request.getVillageId()))
		{
			model.setVillageName(request.getVillageName());
			this.energyConsumptionDao.updateConsumptionValue(model);
		}
		
	}

}
