package io.brunovargas;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import io.brunovargas.conference.ConferenceTrackGenerator;
import io.brunovargas.vo.Track;

public class Main {
	
	private static final String DEFAULT_FILE = "input.txt";

	public static void main(String[] args) {
		
		Path filePath = Paths.get(DEFAULT_FILE);
		List<Track> tracks = ConferenceTrackGenerator.getInstance().generateTracksFromFile(filePath);
		
		for(Track track : tracks){
			System.out.println(track.toString());
		}

	}

}
