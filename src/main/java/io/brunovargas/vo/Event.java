package io.brunovargas.vo;

import java.util.Date;

import io.brunovargas.config.Config;
import io.brunovargas.enumeration.EventEnum;
import io.brunovargas.util.TimeUtil;

public class Event {

	private String title;

	private Integer duration;

	private Date startTime = new Date();

	public Event(final String title, final Integer duration) {
		this.title = title;
		this.duration = duration;
	}

	public Event(final EventEnum eventEnum) {
		this.title = eventEnum.getEventName();
		this.startTime = eventEnum.getStartDate();
		this.duration = -1;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(final Integer duration) {
		this.duration = duration;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
	}

	public String toString() {
		String result;
		if (duration == -1) {
			result = String.format(Config.FIXED_SESSION_LABEL_FORMAT, TimeUtil.formatTime(startTime), title);
		} else {
			result = String.format(Config.TALK_LABEL_FORMAT, TimeUtil.formatTime(startTime), title,
					duration == Config.LIGHTINING_DURATION ? Config.LIGHTINING_LABEL
							: String.format(Config.DURATION_LABEL_FORMAT, duration));
		}
		return result;
	}

}
