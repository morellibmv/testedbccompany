package io.brunovargas.conference;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import io.brunovargas.enumeration.SessionEnum;
import io.brunovargas.vo.Track;

public class ConferenceTrackGeneratorTest {

	Path filePath = Paths.get("test_input.txt");
	List<Track> tracks;

	@Before
	public void setup() {
		tracks = ConferenceTrackGenerator.getInstance().generateTracksFromFile(filePath);
	}

	@Test
	public void checkIfAllTalksAreScheduled() {
		try {
			String fileContent = new String(Files.readAllBytes(filePath));

			// First will check if all the scheduled events are within input
			tracks.stream()
					.forEach(track -> track.getSessions().stream()
							.filter(session -> session.getName().equals(SessionEnum.MORNING.getName())
									|| session.getName().equals(SessionEnum.AFTERNOON.getName()))
							.forEach(session -> session.getEvents().stream()
									.forEach(event -> assertThat(fileContent, containsString(event.getTitle())))));

			// Now that we know that all of the events scheduled originated from
			// the input we validate the amount of events from the input and
			// output
			Long numberOfEvents = new Long(fileContent.split("\n").length);
			Long numberOfScheduledEvents = tracks.stream()
					.mapToLong(track -> track.getSessions().stream()
							.filter(session -> session.getName().equals(SessionEnum.MORNING.getName())
									|| session.getName().equals(SessionEnum.AFTERNOON.getName()))
							.mapToLong(session -> session.getEvents().stream().count()).sum())
					.sum();
			
			assertEquals(numberOfEvents, numberOfScheduledEvents);

		} catch (IOException ioe) {
			fail("Error reading test_input.txt file. Maybe it's deleted, moved or renamed");
		}
	}
}
