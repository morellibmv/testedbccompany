package io.brunovargas.enumeration;

import java.util.Date;

import io.brunovargas.config.Config;

public enum EventEnum {

	LUNCH(Config.LUNCH_SESSION_NAME, Config.LUNCH_SESSION_START), NETWORKING(Config.NETWORKING_SESSION_NAME,
			Config.NETWORKING_SESSION_START);

	private String eventName;
	private Date startDate;

	private EventEnum(final String eventName, final Date startDate) {
		this.eventName = eventName;
		this.startDate = startDate;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(final String eventName) {
		this.eventName = eventName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

}
