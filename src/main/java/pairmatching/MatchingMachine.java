package pairmatching;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import pairmatching.domain.Course;
import pairmatching.domain.Crews;
import pairmatching.domain.Level;
import pairmatching.domain.MainView;
import pairmatching.domain.Matching;
import pairmatching.domain.Mission;
import pairmatching.domain.Missions;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class MatchingMachine {

	private Missions missions;
	private Crews crews;

	public MatchingMachine() {
		this.missions = new Missions();
		this.crews = new Crews();
	}

	public void run() {
		while (true) {
			MainView mainView = MainView.valueOf(InputView.askFeature());
			while(!(isRightSelectFeature(mainView))){
				mainView = MainView.valueOf(InputView.askFeature());
			}
			if (mainView.isExit()) {
				return;
			}
			runFeature(mainView);
		}
	}

	private void runFeature(MainView mainView) {
		if (mainView.isMatching()) {
			matching();
			return;
		}
		if (mainView.isSearching()) {
			searching();
			return;
		}
		if (mainView.isInitializing()) {
			initializing();
			return;
		}
	}

	private void matching() {
		Matching matching = new Matching();
		Mission mission = missionInput();
		Set<Set> pairCrews = matching.matching(mission, crews);
		if (checkDuplicated(matching, mission, pairCrews)) {
			OutputView.printPairCrews(pairCrews);
			System.out.println();
			return;
		}
		System.out.println("[Error] : 3회이상 재매칭 해보았으나, 경우의 수를 발견하지 못했습니다.");
	}

	private boolean checkDuplicated(Matching matching, Mission mission, Set<Set> pairCrews) {
		int errorCountLimit = 3;
		while (errorCountLimit > 0) {
			if (matching.checkDuplicatePair(pairCrews, missions, mission)) {
				mission.updatePairCrews(pairCrews);
				return true;
			}
			errorCountLimit--;
		}
		return false;
	}

	private void searching() {
		Mission mission = searchingMissionInput();
		System.out.println();
		if (mission.getPairCrews() == null) {
			return;
		}
		OutputView.printPairCrews(mission.getPairCrews());

	}

	private void initializing() {
		List<Mission> allMissions = missions.getMissions();
		for (Mission mission : allMissions) {
			mission.updatePairCrews(null);
		}
	}

	private Mission missionInput() {
		List<String> matchInformation = Arrays.asList((InputView.askWantedMatchingInformation().split(", ")));
		while (!(isExistMission(matchInformation))) {
			matchInformation = Arrays.asList((InputView.askWantedMatchingInformation().split(", ")));
		}
		Mission mission = missions.getMission(Course.getCourse(matchInformation.get(0)),
				Level.getLevel(matchInformation.get(1)), matchInformation.get(2));

		mission = checkExistPreMatching(mission);
		return mission;
	}

	private Mission checkExistPreMatching(Mission mission) {
		List<String> matchInformation;
		while (mission.getPairCrews() != null) {
			String keepMatchingAnswer = InputView.askKeepMatching();
			if (keepMatchingAnswer.equals("네")) {
				break;
			}
			matchInformation = Arrays.asList((InputView.askWantedMatchingInformation().split(", ")));
			mission = missions.getMission(Course.getCourse(matchInformation.get(0)),
					Level.getLevel(matchInformation.get(1)), matchInformation.get(2));
		}
		return mission;
	}

	private Mission searchingMissionInput() {
		List<String> matchInformation = Arrays.asList((InputView.askWantedMatchingInformation().split(", ")));
		while (!(isExistMission(matchInformation))) {
			matchInformation = Arrays.asList((InputView.askWantedMatchingInformation().split(", ")));
		}
		Mission mission = missions.getMission(Course.getCourse(matchInformation.get(0)),
				Level.getLevel(matchInformation.get(1)), matchInformation.get(2));
		if (mission.getPairCrews() == null) {
			OutputView.printNonSearchingResult();
			return null;
		}
		return mission;
	}

	private boolean isExistMission(List<String> matchInformation) {
		boolean isExistMission = true;
		try {
			checkValidateNonExistMission(matchInformation);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			isExistMission = false;
		}
		return isExistMission;
	}

	private boolean isRightSelectFeature(MainView mainView){
		boolean isRightSelectFeature = true;
		try{
			checkRightSelectFeature(mainView);
		} catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
			isRightSelectFeature = false;
		}
		return isRightSelectFeature;
	}

	private void checkRightSelectFeature(MainView mainView) {
		if (mainView == null){
			throw new IllegalArgumentException("[ERROR] 기능 선택이 잘못되었습니다.");
		}
	}

	private void checkValidateNonExistMission(List<String> matchInformation) {
		Mission mission = missions.getMission(Course.getCourse(matchInformation.get(0)),
				Level.getLevel(matchInformation.get(1)), matchInformation.get(2));
		if (mission == null) {
			throw new IllegalArgumentException("[ERROR] 존재하지 않는 미션입니다.");
		}
	}
}