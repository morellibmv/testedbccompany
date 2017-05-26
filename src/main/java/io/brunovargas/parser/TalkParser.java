package io.brunovargas.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.brunovargas.config.Config;
import io.brunovargas.vo.Event;

public class TalkParser {

	public static List<Event> parseFile(final Path inputFilePath) {
		List<Event> result = new ArrayList<Event>();
		Stream<String> stream;
		try {
			stream = Files.lines(inputFilePath);
			result = stream.map(String::trim).map(inputLine -> parseLine(inputLine)).filter(line -> line != null).collect(Collectors.toList());
			stream.close();
		} catch (IOException e) {
			System.out.println("ERROR: " + e.getMessage());
		} 
		
		return result;
	}

	private static Event parseLine(final String input) {
		Event result = null;

		final Pattern pattern = Pattern.compile(Config.LINE_INPUT_REGEX);
		final Matcher matcher = pattern.matcher(input);

		if (matcher.find()) {
			final String title = matcher.group(Config.TITLE);
			final String type = matcher.group(Config.TYPE);
			final String timeString = matcher.group(Config.TIME);

			if (timeString != null || type.equalsIgnoreCase(Config.LIGHTINING_LABEL)) {
				result = new Event(title,
						timeString == null ? Config.LIGHTINING_DURATION : Integer.parseInt(timeString));
			}
		}

		return result;
	}

}
