package pairmatching.domain;

import java.util.List;
import java.util.Set;

public class Mission {
	private Course course;
	private Level level;
	private String name;

	private Set<Set> pairCrews;

	public Mission(Course course, Level level, String name) {
		pairCrews = null;
		this.course = course;
		this.level = level;
		this.name = name;
	}

	public Course getCourse() {
		return course;
	}

	public Level getLevel() {
		return level;
	}

	public String getName() {
		return name;
	}

	public Set<Set> getPairCrews() {
		return pairCrews;
	}

	public void updatePairCrews(Set<Set> updatedPairCrews) {
		this.pairCrews = updatedPairCrews;
	}
}
