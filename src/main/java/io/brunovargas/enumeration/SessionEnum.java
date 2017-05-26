package io.brunovargas.enumeration;

import java.util.Date;

import io.brunovargas.config.Config;

public enum SessionEnum {
	   MORNING(Config.MORNING_SESSION_NAME, Config.MORNING_SESSION_START, Config.MORNING_MAXIMUM_DURATION, Config.MORNING_MINIMAL_DURATION), 
	     LUNCH( Config.LUNCH_SESSION_NAME, Config.LUNCH_SESSION_START, Config.LUNCH_MAXIMUM_DURATION, Config.LUNCH_MINIMAL_DURATION),
	 AFTERNOON(Config.AFTERNOON_SESSION_NAME, Config.AFTERNOON_SESSION_START, Config.AFTERNOON_MAXIMUM_DURATION, Config.AFTERNOON_MINIMAL_DURATION), 
	NETWORKING( Config.NETWORKING_SESSION_NAME, Config.NETWORKING_SESSION_START, Config.NETWORKING_MAXIMUM_DURATION, Config.NETWORKING_MINIMAL_DURATION);
	
	private String name;
	private Date startTime;
	private Integer maximumDuration;
	private Integer minimalDuration;

	private SessionEnum(final String name, final Date startTime, final Integer maximumDuration,
			final Integer minimalDuration) {
		this.name = name;
		this.startTime = startTime;
		this.maximumDuration = maximumDuration;
		this.minimalDuration = minimalDuration;
	}

	public String getName() {
		return name;
	}

	public Date getStartTime() {
		return startTime;
	}

	public Integer getMaximumDuration() {
		return maximumDuration;
	}

	public Integer getMinimalDuration() {
		return minimalDuration;
	}
	
	
}
