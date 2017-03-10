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
@Table(name="activity_status", uniqueConstraints={@UniqueConstraint(columnNames={"user_id", "activity_id"}, name="UK_user_activity")})
public class ActivityStatus {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private boolean interested;
	
	private boolean assistant;
	
	@ManyToOne
	@NotNull
	private User user;
	
	@ManyToOne
	@NotNull
	private Activity activity;

	public boolean isInterested() {
		return interested;
	}

	public void setInterested(boolean interested) {
		this.interested = interested;
	}

	public boolean isAssistant() {
		return assistant;
	}

	public void setAssistant(boolean assistant) {
		this.assistant = assistant;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public long getId() {
		return id;
	}
	
}
