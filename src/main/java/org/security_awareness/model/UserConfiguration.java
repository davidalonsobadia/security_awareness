package org.security_awareness.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_configurations")
public class UserConfiguration {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	// Visibility: null(0), by common assisted activities(1), by common interested activities(2), by common zones(3), all(4)
	private int visibility;
	
	// Notifications received: null(0), by assisted activities(1), by interested activities(2), by my zones(3), all(4) 
	private int notificationsReceived;
	
	public UserConfiguration() {}
	
	public UserConfiguration(int visibility, int notificationsReceived){
		this.visibility = visibility;
		this.notificationsReceived = notificationsReceived;
	}

	public int getVisibility() {
		return visibility;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}

	public int getNotificationsReceived() {
		return notificationsReceived;
	}

	public void setNotificationsReceived(int notificationsReceived) {
		this.notificationsReceived = notificationsReceived;
	}
}
