package euler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtil {
	public static char[] readChars(File file) throws IOException {
		Scanner scanner = new Scanner(file);
		scanner.useDelimiter(",|\n");
		char[] chars = new char[(int) (file.length() / 2)];
		int i = 0;
		while (scanner.hasNextInt()) {
			chars[i] = (char) scanner.nextInt();
			i++;
		}
		char[] temp = new char[i];
		System.arraycopy(chars, 0, temp, 0, i);
		return temp;
	}

	public static List<String> readNames(File file) throws IOException {
		List<String> names = new ArrayList<String>();
		Scanner scanner = new Scanner(file);
		scanner.useDelimiter("\"(,\")*");
		String name = null;
		while (scanner.hasNext()) {
			name = scanner.next();
			if (name.length() > 0) {
				names.add(name.toLowerCase());
			}
		}
		return names;
	}
}
