package energy.consumption.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The EnergyConsumptionCounter entity contains unique counter values with the recently updated
 * consumption charges.
 *
 * @author shamini.shankar
 */
@Entity
@Table(name="EnergyConsumptionCounter")
public class EnergyConsumptionCounter {
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	int villageId;
	
	double consumption;
	
	String villageName;
	
	public int getVillageId() {
		return villageId;
	}

	public void setVillageId(int villageId) {
		this.villageId = villageId;
	}


	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	

	public double getConsumption() {
		return consumption;
	}

	public void setConsumption(double consumption) {
		this.consumption = consumption;
	}
	
	@Override
	public String toString(){
		return "id="+id+", villageId="+villageId+", villageName="+villageName+", consumption="+consumption;
	}

}
