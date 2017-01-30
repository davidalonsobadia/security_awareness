package com.example.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="notifications")
@Embeddable
public class Notification {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String title;
	
	private Date date;
	
	private String description;
	
	@ManyToOne
	private User sender;
	
	@OneToMany(mappedBy="notification")
	private List<NotificationStatus> notificationStatus;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public long getId() {
		return id;
	}

	public List<NotificationStatus> getNotificationStatus() {
		return notificationStatus;
	}

	public void setNotificationStatus(List<NotificationStatus> notificationStatus) {
		this.notificationStatus = notificationStatus;
	}
}
