package org.security_awareness.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="notification_status", uniqueConstraints={@UniqueConstraint(columnNames={"user_id", "notification_id"}, 
		name="UK_user_notification")})
public class NotificationStatus {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	// Deleted (0), Read(1), Not read(2)
	private int status;
	
	@ManyToOne
	@NotNull
	private Notification notification;
	
	@ManyToOne
	@NotNull
	private User user;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getId() {
		return id;
	}
	
}
