package pairmatching.view;

import java.util.Iterator;
import java.util.Set;

public class OutputView {
	public static void printPairCrews(Set<Set> pairCrews) {
		System.out.println();
		System.out.println("페어 매칭 결과입니다.");
		for (Set set : pairCrews) {
			Iterator nestedIterator = set.iterator();
			while (nestedIterator.hasNext()) {
				twoPeopleCrewPrint(set, nestedIterator);
				threePeopleCrewPrint(set, nestedIterator);
			}
		}
	}

	private static void threePeopleCrewPrint(Set set, Iterator nestedIterator) {
		if (set.size() == 3) {
			System.out.println(
					nestedIterator.next() + " : " + nestedIterator.next() + " : " + nestedIterator.next());
		}
	}

	private static void twoPeopleCrewPrint(Set set, Iterator nestedIterator) {
		if (set.size() == 2) {
			System.out.println(nestedIterator.next() + " : " + nestedIterator.next());
		}
	}

	public static void printNonSearchingResult() {
		System.out.println("[ERROR] 매칭 이력이 없습니다.");
	}
}
