package pairmatching.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import camp.nextstep.edu.missionutils.Randoms;

public class Matching {

	private Set<List> pairCrews;

	public Matching(){
		this.pairCrews = new HashSet<>();
	}

	public Set<List> matching(Mission mission, Crews crews) {
		Set<List> pairsCrew = new HashSet<>();
		List<String> crewNames = new ArrayList<>();
		crewNames = crews.getCrews(mission.getCourse().getName());
		//중복 검사 로직

		pairsCrew = pairMatching(crewNames, mission);
		return pairsCrew;
	}



	public Set<List> pairMatching(List<String> crewNames, Mission mission) {
		Randoms.shuffle(crewNames);

		if (crewNames.size() % 2 == 0) {
			makeEvenNumberCrews(crewNames);
		}
		if (crewNames.size() % 2 != 0) {
			makeOddNumberCrews(crewNames);
		}
		return pairCrews;
	}

	private void makeEvenNumberCrews(List<String> crewNames) {
		for (int i = 0; i < crewNames.size(); i += 2) {
			List<String> pair = new ArrayList<>();
			pair.add(crewNames.get(i));
			pair.add(crewNames.get(i + 1));
			pairCrews.add(pair);
		}
	}

	private void makeOddNumberCrews(List<String> crewNames) {
		for (int i = 0; i < (crewNames.size() / 2) - 1; i += 2) {
			List<String> pair = new ArrayList<>();
			pair.add(crewNames.get(i));
			pair.add(crewNames.get(i + 1));
			pairCrews.add(pair);
		}
		List<String> pair = new ArrayList<>();
		pair.add(crewNames.get(crewNames.size() - 1));
		pair.add(crewNames.get(crewNames.size() - 2));
		pair.add(crewNames.get(crewNames.size() - 3));
	}

	public boolean checkDuplicatePair(Set<List> pairCrews, Missions missions, Mission mission){

		//레벨만 비교하고 있는데, 코스 비교도 필요함.
		Set<List> sameLevelPairCrews = new HashSet<>();
		int totalPairCrewsCount = pairCrews.size();
		for(List pair : pairCrews){
			sameLevelPairCrews.add(pair);
		}
		Level sameLevel = mission.getLevel();
		List<Mission> sameLevelMissions = missions.getSameLevelMission(sameLevel);
		for(Mission sameLevelMission : sameLevelMissions){
			Set<List> eachPairCrews = new HashSet<>();
			eachPairCrews = sameLevelMission.getPairCrews();
			System.out.println(eachPairCrews.size());
			totalPairCrewsCount += eachPairCrews.size();
			for (List pair : eachPairCrews){
				sameLevelPairCrews.add(pair);
			}
		}
		return totalPairCrewsCount == sameLevelPairCrews.size();
	}
}
