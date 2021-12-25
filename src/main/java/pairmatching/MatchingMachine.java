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
			if(mainView.isExit()){
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
		if(mainView.isInitializing()){
			initializing();
			return;
		}
	}

	private void matching() {
		Matching matching = new Matching();
		Mission mission = missionInput();
		Set<Set> pairCrews = matching.matching(mission, crews);
		int errorCountLimit = 3;

		while (errorCountLimit > 0) {
			if (matching.checkDuplicatePair(pairCrews, missions, mission)) {
				mission.updatePairCrews(pairCrews);
				OutputView.printPairCrews(pairCrews);
				System.out.println();
				return;
			}
			errorCountLimit--;
		}
		//여기까지 왔다면 에러 던지기
	}

	private void searching() {
		searchingInput();
		System.out.println();
	}

	private void initializing(){
		List<Mission> allMissions = missions.getMissions();
		for(Mission mission : allMissions){
			mission.updatePairCrews(null);
		}
	}

	private Mission missionInput() {
		List<String> matchInformation = Arrays.asList((InputView.askWantedMatchingInformation().split(", ")));
		Mission mission = missions.getMission(Course.getCourse(matchInformation.get(0)),
				Level.getLevel(matchInformation.get(1)), matchInformation.get(2));

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

	private void searchingInput() {
		List<String> matchInformation = Arrays.asList((InputView.askWantedMatchingInformation().split(", ")));
		Mission mission = missions.getMission(Course.getCourse(matchInformation.get(0)),
				Level.getLevel(matchInformation.get(1)), matchInformation.get(2));
		if(mission.getPairCrews() == null){
			OutputView.printNonSearchingResult();
			return;
		}
		OutputView.printPairCrews(mission.getPairCrews());
	}

}

//백엔드, 레벨1, 자동차경주