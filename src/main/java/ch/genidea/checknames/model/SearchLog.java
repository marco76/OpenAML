package ch.genidea.checknames.model;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class SearchLog {
	@Id
	@GeneratedValue
	private Long ID;
	 @Temporal(TemporalType.DATE)
	private Date timestamp;
	 
	private String ipRequester;
	 
	public Long getID() {
		return ID;
	}
	public void setID(Long id) {
		ID = id;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getStringInserted() {
		return stringInserted;
	}
	public void setStringInserted(String stringInserted) {
		this.stringInserted = stringInserted;
	}
	public Integer getTimeToAnswer() {
		return timeToAnswer;
	}
	public void setTimeToAnswer(Integer timeToAnswer) {
		this.timeToAnswer = timeToAnswer;
	}
	public void setIpRequester(String ipRequester) {
		this.ipRequester = ipRequester;
	}
	public String getIpRequester() {
		return ipRequester;
	}
	private String stringInserted;
	private Integer timeToAnswer; 
	
}
