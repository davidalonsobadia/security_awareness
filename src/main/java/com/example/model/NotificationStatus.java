package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="notification_status", uniqueConstraints={@UniqueConstraint(columnNames={"recipient_id", "notification_id"}, 
		name="UK_user_notification")})
public class NotificationStatus {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private int status;
	
	@ManyToOne
	private Notification notification;
	
	@ManyToOne
	private User recipient;

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

	public User getRecipient() {
		return recipient;
	}

	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}

	public long getId() {
		return id;
	}
	
}
