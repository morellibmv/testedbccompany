package io.brunovargas.vo;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import io.brunovargas.enumeration.SessionEnum;
import io.brunovargas.util.TimeUtil;

public class Session {

	private String name;

	private Date startTime;

	private Integer maximumDuration;

	private Integer minimalDuration;

	private final List<Event> events;

	public Session(final SessionEnum type) {
		this.name = type.getName();
		this.startTime = type.getStartTime();
		this.maximumDuration = type.getMaximumDuration();
		this.minimalDuration = type.getMinimalDuration();
		events = new LinkedList<Event>();
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
	}

	public Integer getMaximumDuration() {
		return maximumDuration;
	}

	public void setMaximumDuration(final Integer maximumDuration) {
		this.maximumDuration = maximumDuration;
	}

	public Integer getMinimalDuration() {
		return minimalDuration;
	}

	public void setMinimalDuration(final Integer minimalDuration) {
		this.minimalDuration = minimalDuration;
	}

	public Integer getTotalDurationInMinutes() {
		Integer result = 0;
		if (events != null) {
			result = events.stream().mapToInt(Event::getDuration).sum();
		}
		return result;
	}

	public Boolean addEvent(final Event event) {
		Boolean result = false;

		if (event.getDuration() == -1
				|| events.stream().mapToInt(Event::getDuration).sum() + event.getDuration() <= maximumDuration) {
			events.add(event);
			result = true;
		}
		return result;
	}

	public Long defineTimeframes() {
		int sessionTimeSpent = 0;
		for (final Event event : events) {
			event.setStartTime(new Date(startTime.getTime() + TimeUtil.minutesToMillis(sessionTimeSpent)));
			sessionTimeSpent += event.getDuration();
		}

		return TimeUtil.minutesToMillis(sessionTimeSpent);
	}

	public String toString() {
		return events.stream().map(Event::toString).collect(Collectors.joining("\n"));
	}

	public List<Event> getEvents() {
		return events;
	}

}
