package io.brunovargas.vo;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import io.brunovargas.config.Config;

public class Track {

	private String name;
	private List<Session> sessions;

	public Track(final String name) {
		this.name = name;
		sessions = new LinkedList<Session>();
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(final List<Session> sessions) {
		this.sessions = sessions;
	}

	public String toString() {
		return String.format(Config.TRACK_LABEL_FORMAT, name,
				sessions.stream().map(Session::toString).collect(Collectors.joining("\n")));
	}

	public void addSession(final Session session) {
		sessions.add(session);
	}

}
