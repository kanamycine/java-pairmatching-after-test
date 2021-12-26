package pairmatching.view;

import java.util.Iterator;
import java.util.Set;

public class OutputView {
	public static void printPairCrews(Set<Set> pairCrews){
		System.out.println();
		System.out.println("페어 매칭 결과입니다.");
		Iterator iterator = pairCrews.iterator();
		for (Set set : pairCrews){
			Iterator nestedIterator = set.iterator();
			while (nestedIterator.hasNext()){
				if (set.size()==2) {
					System.out.println(nestedIterator.next() + " : " + nestedIterator.next());
				}
				if (set.size()==3){
					System.out.println(nestedIterator.next() + " : " + nestedIterator.next() + " : " + nestedIterator.next());
				}
			}
		}
	}

	public static void printNonSearchingResult(){
		System.out.println("매칭 결과가 없습니다.");
	}
}
