package energy.consumption.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The ConsumptionPerHourModel entity contains all village counter values 
 * updated on hourly basis
 * 
 * @author shamini.shankar
 */
@Entity
@Table(name="ConsumptionPerHourModel")
public class ConsumptionPerHourModel {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	int counter_id;
	
	double amount;
	
	Timestamp updatedTime;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}

	public int getCounter_id() {
		return counter_id;
	}

	public void setCounter_id(int counter_id) {
		this.counter_id = counter_id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString(){
		return "id="+id+",counter_id="+counter_id+", updatedTime="+updatedTime+", amount="+amount;
	}


}
