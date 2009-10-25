package euler.level2;

import java.io.File;
import java.io.IOException;

import euler.FileUtil;
import euler.Problem;

public class Problem059 extends Problem<Integer> {

	@Override
	public Integer solve() {
		try {
			char[] enc = FileUtil.readChars(new File("Problem59.txt"));
			char[] dec = new char[enc.length];

			char[] key = new char[] {
			                         0, 0, 0
			};
			int needed = ((enc.length / key.length) * 85) / 100;
			for (int k = 0; k < key.length; k++) {
				for (int e = 'a'; e <= 'z'; e++) {
					for (int i = k; i < enc.length; i += key.length) {
						dec[i] = (char) (enc[i] ^ e);
					}

					int normal = 0;
					for (int i = k; i < enc.length; i += key.length) {
						char c = dec[i];
						if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c == ' ') {
							normal++;
						}
					}

					if (normal > needed) {
						key[k] = (char) e;
					}
				}
			}

			int sum = 0;
			for (int i = 0; i < enc.length; i++) {
				dec[i] = (char) (enc[i] ^ key[i % 3]);
				sum += dec[i];
			}

			// System.out.println(new String(dec));
			// System.out.println(new String(key));
			return sum;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
