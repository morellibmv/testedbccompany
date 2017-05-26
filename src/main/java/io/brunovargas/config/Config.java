package io.brunovargas.config;

import java.util.Date;
import java.util.TimeZone;

public final class Config {

	public static final String TIME_FORMAT = "hh:mmaa";
	public static final Integer LIGHTINING_DURATION = 5;

	public static final String DURATION_LABEL_FORMAT = "%smin";
	public static final String TALK_LABEL_FORMAT = "%s %s %s";
	public static final String FIXED_SESSION_LABEL_FORMAT = "%s %s";
	public static final String TRACK_LABEL_FORMAT = "Track %s\n%s\n";

	public static final Integer MORNING_MINIMAL_DURATION = -1;
	public static final Integer MORNING_MAXIMUM_DURATION = 180;
	public static final String MORNING_SESSION_NAME = "Morning";
	public static final Date MORNING_SESSION_START = new Date(32400000L - TimeZone.getDefault().getRawOffset());

	public static final Integer LUNCH_MINIMAL_DURATION = 60;
	public static final Integer LUNCH_MAXIMUM_DURATION = 60;
	public static final String LUNCH_SESSION_NAME = "Lunch";
	public static final Date LUNCH_SESSION_START = new Date(43200000L - TimeZone.getDefault().getRawOffset());

	public static final Integer AFTERNOON_MINIMAL_DURATION = -1;
	public static final Integer AFTERNOON_MAXIMUM_DURATION = 240;
	public static final String AFTERNOON_SESSION_NAME = "Afternoon";
	public static final Date AFTERNOON_SESSION_START = new Date(46800000L - TimeZone.getDefault().getRawOffset());

	public static final Integer NETWORKING_MINIMAL_DURATION = -1;
	public static final Integer NETWORKING_MAXIMUM_DURATION = -1;
	public static final String NETWORKING_SESSION_NAME = "Networking";
	public static final Date NETWORKING_SESSION_START = new Date(57600000L - TimeZone.getDefault().getRawOffset());

	public static final String LIGHTINING_LABEL = "lightning";
	public static final String TITLE = "title";
	public static final String TIME = "time";
	public static final String TYPE = "type";

	public static final String LINE_INPUT_REGEX = "^(?<" + TITLE + ">.+)\\s(?<" + TIME + ">\\d+)?\\s?(?<" + TYPE
			+ ">(min)|(" + LIGHTINING_LABEL + "))$";

}
