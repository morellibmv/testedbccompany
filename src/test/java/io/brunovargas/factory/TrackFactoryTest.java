package io.brunovargas.factory;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import io.brunovargas.enumeration.SessionEnum;
import io.brunovargas.util.TimeUtil;
import io.brunovargas.vo.Event;
import io.brunovargas.vo.Track;

public class TrackFactoryTest {

	private ArrayList<Event> events;

	private Track result;

	@Before
	public void setup() {
		events = new ArrayList<Event>();
		events.add(new Event("Writing Fast Tests Against Enterprise Rails", 60));
		events.add(new Event("Overdoing it in Python", 45));
		events.add(new Event("Lua for the Masses", 30));
		events.add(new Event("Ruby Errors from Mismatched Gem Versions", 45));
		events.add(new Event("Common Ruby Errors", 45));
		events.add(new Event("Rails for Python Developers", 5));
		events.add(new Event("Communicating Over Distance", 60));
		events.add(new Event("Accounting-Driven Development", 45));
		events.add(new Event("Woah", 30));
		events.add(new Event("Sit Down and Write", 30));
		events.add(new Event("Pair Programming vs Noise", 45));
		events.add(new Event("Rails Magic", 60));
		events.add(new Event("Ruby on Rails: Why We Should Move On", 60));
		events.add(new Event("Clojure Ate Scala (on my project)", 45));
		events.add(new Event("Programming in the Boondocks of Seattle", 30));
		events.add(new Event("Ruby vs. Clojure for Back-End Development", 30));
		events.add(new Event("Ruby on Rails Legacy App Maintenance", 60));
		events.add(new Event("A World Without HackerNews", 30));
		events.add(new Event("User Interface CSS in Rails Apps", 30));

		// A Clone was created in order to keep the events list untouched
		result = TrackFactory.createFullDayTrack("1", (List) events.clone());

	}

	@Test
	public void checkTrackBasicStructure() {

		assertNotNull(result);
		assertEquals("1", result.getName());

		// Check if all sessions are in place and in order
		assertThat(result.getSessions(),
				contains(hasProperty("name", is(SessionEnum.MORNING.getName())),
						hasProperty("name", is(SessionEnum.LUNCH.getName())),
						hasProperty("name", is(SessionEnum.AFTERNOON.getName())),
						hasProperty("name", is(SessionEnum.NETWORKING.getName()))));

	}

	@Test
	public void checkUnknownEvents() {
		List<String> allScheduledEvents = result.getSessions().stream()
				.filter(session -> session.getName().equals(SessionEnum.MORNING.getName())).findFirst().get()
				.getEvents().stream().map(Event::getTitle).collect(Collectors.toList());

		allScheduledEvents.addAll(result.getSessions().stream()
				.filter(session -> session.getName().equals(SessionEnum.AFTERNOON.getName())).findFirst().get()
				.getEvents().stream().map(Event::getTitle).collect(Collectors.toList()));

		List<String> allEvents = events.stream().map(Event::getTitle).collect(Collectors.toList());

		// Check if all scheduled events originated from the events list
		for (String event : allScheduledEvents) {
			assertThat(allEvents, hasItem(event));
		}

	}

	@Test
	public void checkTimeframes() {
		List<Event> allScheduledEvents = result.getSessions().stream()
				.filter(session -> session.getName().equals(SessionEnum.MORNING.getName())).findFirst().get()
				.getEvents().stream().collect(Collectors.toList());
		allScheduledEvents.addAll(
				result.getSessions().stream().filter(session -> session.getName().equals(SessionEnum.LUNCH.getName()))
						.findFirst().get().getEvents().stream().collect(Collectors.toList()));
		allScheduledEvents.addAll(result.getSessions().stream()
				.filter(session -> session.getName().equals(SessionEnum.AFTERNOON.getName())).findFirst().get()
				.getEvents().stream().collect(Collectors.toList()));
		allScheduledEvents.addAll(result.getSessions().stream()
				.filter(session -> session.getName().equals(SessionEnum.NETWORKING.getName())).findFirst().get()
				.getEvents().stream().collect(Collectors.toList()));

		Date previousTimeframe = SessionEnum.MORNING.getStartTime();

		for (Event event : allScheduledEvents) {
			assertThat(event.getStartTime(), greaterThanOrEqualTo(previousTimeframe));
			previousTimeframe = new Date(
					event.getStartTime().getTime() + TimeUtil.minutesToMillis(event.getDuration()));
		}
	}

}
