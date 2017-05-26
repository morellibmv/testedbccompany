package io.brunovargas.factory;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import io.brunovargas.enumeration.EventEnum;
import io.brunovargas.enumeration.SessionEnum;
import io.brunovargas.util.TimeUtil;
import io.brunovargas.vo.Event;
import io.brunovargas.vo.Session;
import io.brunovargas.vo.Track;

public final class TrackFactory {

	public static Track createFullDayTrack(final String name, final List<Event> talks) {
		final Track result = new Track(name);

		Long timespent = createAndFillSessionForTrack(result, SessionEnum.MORNING, talks);
		timespent = createLunchSession(result, timespent);
		timespent = createAndFillSessionForTrack(result, SessionEnum.AFTERNOON, talks);
		createNetworkingSession(result, timespent);

		return result;
	}

	private static Long createAndFillSessionForTrack(final Track track, final SessionEnum sessionEnum,
			final List<Event> talks) {
		final Session session = new Session(sessionEnum);
		track.addSession(session);
		return fillSession(session, talks);
	}

	private static Long fillSession(final Session session, final List<Event> events) {
		// Since we are able to remove items from the collection, the safest way
		// is to iterate it by using Iterator. I thought of using some lambda
		// here but it would get unnecessarily complex
		// There could be an Else statement to break the loop when an event
		// is not added, but then it would not try all the possibilities
		// with different time frames
		for (final Iterator<Event> iterator = events.iterator(); iterator.hasNext();) {
			final Event event = iterator.next();
			if (session.addEvent(event)) {
				iterator.remove();
			}
		}
		final Long totalTime = session.defineTimeframes();

		return session.getStartTime().getTime() + totalTime;
	}

	private static Long createLunchSession(final Track track, final Long timespent) {
		return createSingleEventSession(track, SessionEnum.LUNCH, EventEnum.LUNCH, timespent);
	}

	private static Long createNetworkingSession(final Track track, final Long timespent) {
		return createSingleEventSession(track, SessionEnum.NETWORKING, EventEnum.NETWORKING, timespent);
	}

	private static Long createSingleEventSession(final Track track, final SessionEnum sessionEnum,
			final EventEnum eventEnum, final Long timespent) {
		final Event event = new Event(eventEnum);
		// In the case of all the events having spent a time that bigger than
		// the next event's start time (let's say the afternoon session goes
		// until 4:35PM and the Networking session is scheduled for 4PM, when
		// creating the networking event, originally scheduled for 4PM, will
		// have it's start time reset for 4:35PM
		if (timespent > event.getStartTime().getTime()) {
			event.setStartTime(new Date(timespent));
		}
		final Session session = new Session(sessionEnum);
		session.addEvent(event);
		track.addSession(session);
		return event.getStartTime().getTime() + TimeUtil.minutesToMillis(event.getDuration());
	}

}
