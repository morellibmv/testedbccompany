package io.brunovargas.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.brunovargas.config.Config;

public final class TimeUtil {

	public static String formatTime(final Date talkTime) {
		String result;
		final DateFormat formatter = new SimpleDateFormat(Config.TIME_FORMAT, Locale.getDefault());
		result = formatter.format(talkTime);
		return result;

	}

	public static Long minutesToMillis(final Integer minutes) {
		return 60L * 1000L * minutes;
	}

}
