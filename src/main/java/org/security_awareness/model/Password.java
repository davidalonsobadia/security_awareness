package org.security_awareness.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.security_awareness.utils.BCryptPasswordDeserializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
@Table(name="passwords")
public class Password {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String password;
	
	public Password(){
	}
	
	public Password(String password){
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
	
	public String getString() {
		return password;
	}

	@JsonDeserialize(using = BCryptPasswordDeserializer.class )
	public void setPassword(String password) {
		this.password = password;
	}

	public long getId() {
		return id;
	}
}
