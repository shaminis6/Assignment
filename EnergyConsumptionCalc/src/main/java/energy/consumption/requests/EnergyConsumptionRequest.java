package energy.consumption.requests;

/**
 * The Class EnergyConsumptionRequest forms the request with the mentioned attributes
 *
 * @author shamini.shankar
 */
public class EnergyConsumptionRequest {
	
	int villageId;
	
	String villageName;
	
	double amount;
	
	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public int getVillageId() {
		return villageId;
	}

	public void setVillageId(int villageId) {
		this.villageId = villageId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
