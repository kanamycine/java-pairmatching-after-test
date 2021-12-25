package pairmatching.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import camp.nextstep.edu.missionutils.Randoms;

public class Matching {

	private Set<Set> pairCrews;

	public Matching(){
		this.pairCrews = new LinkedHashSet<>();
	}

	public Set<Set> matching(Mission mission, Crews crews) {
		Set<Set> pairsCrew = new HashSet<>();
		List<String> crewNames = new ArrayList<>();
		crewNames = crews.getCrews(mission.getCourse().getName());
		//중복 검사 로직

		pairsCrew = pairMatching(crewNames, mission);
		return pairsCrew;
	}



	public Set<Set> pairMatching(List<String> crewNames, Mission mission) {
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
			Set<String> pair = new HashSet<>();
			pair.add(crewNames.get(i));
			pair.add(crewNames.get(i + 1));
			pairCrews.add(pair);
		}
	}

	private void makeOddNumberCrews(List<String> crewNames) {

		for (int i = 0; i < crewNames.size()-3; i += 2) {
			Set<String> pair = new HashSet<>();
			pair.add(crewNames.get(i));
			pair.add(crewNames.get(i + 1));
			pairCrews.add(pair);
		}
		Set<String> pair = new HashSet<>();
		pair.add(crewNames.get(crewNames.size() - 1));
		pair.add(crewNames.get(crewNames.size() - 2));
		pair.add(crewNames.get(crewNames.size() - 3));
		pairCrews.add(pair);
	}

	public boolean checkDuplicatePair(Set<Set> pairCrews, Missions missions, Mission mission){

		//레벨만 비교하고 있는데, 코스 비교도 필요함.
		//
		Set<Set> sameLevelPairCrews = new HashSet<>();
		int totalPairCrewsCount = pairCrews.size();
		Level sameLevel = mission.getLevel();
		Course sameCourse = mission.getCourse();
		String sameName = mission.getName();
		for(Set pair : pairCrews){
			sameLevelPairCrews.add(pair);
		}


		List<Mission> sameLevelMissions = missions.getSameLevelCourseMission(sameLevel, sameCourse, sameName);
		for(Mission sameLevelMission : sameLevelMissions){
			Set<Set> eachPairCrews = new HashSet<>();
			eachPairCrews = sameLevelMission.getPairCrews();
			if(eachPairCrews == null) {
				continue;
			}
			if(eachPairCrews != null) {
				totalPairCrewsCount += eachPairCrews.size();
			}
			for (Set pair : eachPairCrews){
				sameLevelPairCrews.add(pair);
			}
		}
		return totalPairCrewsCount == sameLevelPairCrews.size();
	}
}
