package io.brunovargas.conference;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import io.brunovargas.factory.TrackFactory;
import io.brunovargas.parser.TalkParser;
import io.brunovargas.vo.Event;
import io.brunovargas.vo.Track;

public final class ConferenceTrackGenerator {

	public static ConferenceTrackGenerator instance;

	private ConferenceTrackGenerator() {
	}

	public static synchronized ConferenceTrackGenerator getInstance() {
		if (instance == null) {
			instance = new ConferenceTrackGenerator();
		}

		return instance;
	}

	public List<Track> generateTracksFromFile(final Path inputFilePath) {
		final List<Track> result = new LinkedList<Track>();

		final List<Event> talks = TalkParser.parseFile(inputFilePath);

		Integer trackId = 1;

		while (!talks.isEmpty()) {
			Track track = TrackFactory.createFullDayTrack(trackId.toString(), talks);
			trackId++;
			result.add(track);
		}

		return result;
	}
}
