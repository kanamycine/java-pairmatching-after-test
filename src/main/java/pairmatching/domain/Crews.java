package pairmatching.domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Crews {

	private static final String backEndFilePath = "src/main/resources/backend-crew.md";
	private static final String frontEndFilePath = "src/main/resources/frontend-crew.md";

	private List<String> backEndCrews;
	private List<String> FrontEndCrews;

	public Crews() {
		backEndCrews = new ArrayList<>();
		FrontEndCrews = new ArrayList<>();
		makeBackCrews();
		makeFrontCrews();
	}

	public void makeBackCrews() {
		String readLine = null;
		try {
			FileReader fileReader = new FileReader(backEndFilePath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((readLine = bufferedReader.readLine()) != null) {
				backEndCrews.add(readLine);
			}
			fileReader.close();
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void makeFrontCrews() {
		String readLine = null;
		try {
			FileReader fileReader = new FileReader(frontEndFilePath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((readLine = bufferedReader.readLine()) != null) {
				FrontEndCrews.add(readLine);
			}
			fileReader.close();
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<String> getCrews(String courseName) {
		if (Course.BACKEND.getName().equals(courseName)) {
			return backEndCrews;
		}
		if (Course.FRONTEND.getName().equals(courseName)) {
			return FrontEndCrews;
		}
		return null;
	}
}
