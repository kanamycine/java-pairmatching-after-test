package pairmatching.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Missions {
	private List<Mission> missions;

	public Missions() {
		missions = new ArrayList<>();
		makeLevelOneMission();
		makeLevelTwoMission();
		makeLevelFourMission();
	}

	private void makeLevelOneMission() {
		missions.add(new Mission(Course.BACKEND, Level.LEVEL1, "자동차경주"));
		missions.add(new Mission(Course.FRONTEND, Level.LEVEL1, "자동차경주"));
		missions.add(new Mission(Course.BACKEND, Level.LEVEL1, "로또"));
		missions.add(new Mission(Course.FRONTEND, Level.LEVEL1, "로또"));
		missions.add(new Mission(Course.BACKEND, Level.LEVEL1, "숫자야구"));
		missions.add(new Mission(Course.FRONTEND, Level.LEVEL1, "숫자야구"));
	}

	private void makeLevelTwoMission() {
		missions.add(new Mission(Course.BACKEND, Level.LEVEL2, "장바구니"));
		missions.add(new Mission(Course.FRONTEND, Level.LEVEL2, "장바구니"));
		missions.add(new Mission(Course.BACKEND, Level.LEVEL2, "결제"));
		missions.add(new Mission(Course.FRONTEND, Level.LEVEL2, "결제"));
		missions.add(new Mission(Course.BACKEND, Level.LEVEL2, "지하철노선도"));
		missions.add(new Mission(Course.FRONTEND, Level.LEVEL2, "지하철노선도"));
	}

	private void makeLevelFourMission() {
		missions.add(new Mission(Course.BACKEND, Level.LEVEL4, "성능개선"));
		missions.add(new Mission(Course.FRONTEND, Level.LEVEL4, "성능개선"));
		missions.add(new Mission(Course.BACKEND, Level.LEVEL4, "배포"));
		missions.add(new Mission(Course.FRONTEND, Level.LEVEL4, "배포"));
	}

	public Mission getMission(Course course, Level level, String name) {
		for (int i = 0; i < missions.size(); i++) {
			if (missions.get(i).getCourse().equals(course) && missions.get(i).getLevel().equals(level) && missions.get(
					i).getName().equals(name)) {
				return missions.get(i);
			}
		}
		return null;
	}

	public List<Mission> getSameLevelCourseMission(Level level, Course course, String name) {
		List<Mission> levelMissions = new ArrayList<>();
		for (Mission mission : missions) {
			if (mission.getLevel().equals(level) && mission.getCourse().equals(course) && !(mission.getName()
					.equals(name))) {
				levelMissions.add(mission);
			}
		}
		return levelMissions;
	}

	public List<Mission> getMissions() {
		return missions;
	}
}
