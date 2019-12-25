package energy.consumption.requests;

/**
 * The Class EnergyConsumptionResponse forms the response with the given attributes
 *
 * @author shamini.shankar
 */
public class EnergyConsumptionResponse {
	
	String village_name;
	double consumption;
	
	public String getVillage_name() {
		return village_name;
	}
	public void setVillage_name(String village_name) {
		this.village_name = village_name;
	}
	public double getConsumption() {
		return consumption;
	}
	public void setConsumption(double consumption) {
		this.consumption = consumption;
	}
	

}
