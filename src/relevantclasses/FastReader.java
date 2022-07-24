package relevantclasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class FastReader {

	BufferedReader br = null;

	StringTokenizer str = null;

	protected FastReader() {
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	String next() {
		if (str == null || !str.hasMoreTokens()) {
			try {
				str = new StringTokenizer(br.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return str.nextToken();
	}
}