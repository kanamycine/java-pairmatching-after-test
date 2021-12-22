package pairmatching.view;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class OutputView {
	public static void printPairCrews(Set<List> pairCrews){
		System.out.println();
		System.out.println("페어 매칭 결과입니다.");
		Iterator iterator = pairCrews.iterator();
		for (List list : pairCrews){
			System.out.println(list.get(0) + " : " + list.get(1));
		}
	}
}
