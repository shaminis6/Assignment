package energy.consumption.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import energy.consumption.dao.EnergyConsumptionDAO;
import energy.consumption.model.ConsumptionPerHourModel;
import energy.consumption.model.EnergyConsumptionCounter;

/**
 * The Class EnergyConsumptionDAOImpl contains database queries to 
 * compute energy consumption charges
 *
 * @author shamini.shankar
 */
@Repository
public class EnergyConsumptionDAOImpl implements EnergyConsumptionDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(EnergyConsumptionDAOImpl.class);
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	/**
	 * Adds values in EnergyConsumptionCounter Model.This tables contains 
	 * unique values of village counters, with the recently updated
	 * consumption charges
	 *
	 * @param model
	 *           the model
	 * @return null
	 */
	@Override
	public void addConsumptionValue(EnergyConsumptionCounter model) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.save(model);
		logger.info("Entry saved successfully");		
	}

	/**
	 * Adds values in ConsumptionPerHourModel.This table contains all counter values, 
	 * on hour basis 
	 *
	 * @param model
	 *           the model
	 * @return null
	 */
	@Override
	public void addConsumptionPerHourValues(ConsumptionPerHourModel model) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.save(model);
		logger.info("Entry saved successfully");
		
	}

	/**
	 * Gets EnergyConsumptionCounter record, for the given counter id
	 *
	 * @param id
	 *           the id
	 * @return EnergyConsumptionCounter
	 */
	@Override
	public EnergyConsumptionCounter getConsumptionById(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();		
		EnergyConsumptionCounter p = (EnergyConsumptionCounter) session.load(EnergyConsumptionCounter.class, new Integer(id));
		return p;
	}

	/**
	 * Updates the record
	 *
	 * @param model
	 *          the model
	 * @return null
	 */
	@Override
	public void updateConsumptionValue(EnergyConsumptionCounter model) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(model);
		logger.info("updated successfully");
	}
	
	/**
	 * Gets all the unique village counters
	 *
	 * @param 
	 * @return list of EnergyConsumptionCounter
	 */
	@Override
	public List<EnergyConsumptionCounter> getAllCounters() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<EnergyConsumptionCounter> counterList = session.createQuery("from EnergyConsumptionCounter").list();
		return counterList;
	}

	/**
	 * Gets the counter for the given duration
	 *
	 * @param id
	 * 		the id
	 * @param duration
	 * 		the duration
	 * @return ConsumptionPerHourModel
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ConsumptionPerHourModel getConsumptionPerHourVal(int id,int duration) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		// Query the record, with diff of given duration and current time
		SQLQuery query = session.createSQLQuery("select * from ConsumptionPerHourModel where "
				+ "TIMESTAMPDIFF(HOUR, updatedTime, NOW()) > ? "
				+ "and counter_id = ?");
		query.setMaxResults(1);
		query.setParameter(0,duration);
		query.setParameter(1, id);
		List<Object[]> rows = query.list();
		ConsumptionPerHourModel model = null;
		for(Object[] row : rows){
			model = new ConsumptionPerHourModel();
			model.setId(Integer.parseInt(row[0].toString()));
			model.setCounter_id(Integer.parseInt(row[1].toString()));
			model.setAmount(Double.parseDouble(row[2].toString()));
		}
		return model;
	}

	/**
	 * Check if the record with the given counter id exists
	 *
	 * @param id
	 * 		the id
	 * @return boolean
	 */
	@Override
	public boolean checkIfRecordExists(int id) {
		// TODO Auto-generated method stub
		Query query = this.sessionFactory.getCurrentSession().createQuery("select 1 from EnergyConsumptionCounter t where t.villageId = :key");
		query.setInteger("key", id);
		return (query.uniqueResult() != null);
	}

	/**
	 * Check if the record with the given counter id exists, with empty village name value
	 *
	 * @param id
	 * 		the id
	 * @return boolean
	 */
	@Override
	public boolean checkIfVillageNameExists(int id) {
		// TODO Auto-generated method stub
		Query query = this.sessionFactory.getCurrentSession().createQuery("select 1 from EnergyConsumptionCounter t where t.villageId = :key and t.villageName is NULL");
		query.setInteger("key", id);
		return (query.uniqueResult() != null);
	}
	
	

}
