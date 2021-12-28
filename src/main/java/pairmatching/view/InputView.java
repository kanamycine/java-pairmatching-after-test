package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

	private static final String ASK_FEATURE_INPUT = "기능을 선택하세요. "
			+ "\n1. 페어 매칭 "
			+ "\n2. 페어 조회 "
			+ "\n3. 페어 초기화"
			+ "\nQ. 종료";

	private static final String ASK_MISSION_INFORMATION = "############################################"
			+ "\n과정: 백엔드 | 프론트엔드"
			+ "\n미션:"
			+ "\n- 레벨1: 자동차경주 | 로또 | 숫자야구게임"
			+ "\n- 레벨2: 장바구니 | 결제 | 지하철노선도"
			+ "\n- 레벨3:"
			+ "\n- 레벨4: 성능개선 | 배포"
			+ "\n- 레벨5:"
			+ "\n############################################";

	private static final String ASK_KEEP_MATCHING = "매칭 정보가 있습니다. 다시 매칭하시겠습니까?";
	private static final String YES_OR_NO_INPUT = "네 | 아니오";

	public static int askFeature() {
		System.out.println(ASK_FEATURE_INPUT);
		String input = Console.readLine();
		if (input.equals("Q")) {
			return input.charAt(0);
		}
		if (input.length() > 1) {
			return 0;
		}
		if (!(input.charAt(0) >= 49 && input.charAt(0) <= 59)) {
			return 0;
		}
		return Integer.parseInt(input);
	}

	public static String askWantedMatchingInformation() {
		System.out.println(ASK_MISSION_INFORMATION);
		return Console.readLine();
	}

	public static String askKeepMatching() {
		System.out.println(ASK_KEEP_MATCHING);
		System.out.println(YES_OR_NO_INPUT);
		return Console.readLine();
	}

}
