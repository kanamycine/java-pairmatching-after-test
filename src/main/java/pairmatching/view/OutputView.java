package pairmatching.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class OutputView {
	public static void printPairCrews(Set<Set> pairCrews){

		System.out.println();
		System.out.println("페어 매칭 결과입니다.");
		Iterator iterator = pairCrews.iterator();
		for (Set set : pairCrews){
			Iterator nestedIterator = set.iterator();
			while (nestedIterator.hasNext()){
				System.out.println(nestedIterator.next() + " : " + nestedIterator.next());
			}
		}
	}
}
