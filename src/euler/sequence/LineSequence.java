package euler.sequence;

import java.util.Arrays;

import euler.numberic.Number;

public class LineSequence {
	private final byte[] line;

	public LineSequence(byte[] startLine) {
		line = startLine;
	}

	public byte[] current() {
		return line;
	}

	public boolean next() {
		for (int i = line.length - 1; i > 0; i--) {
			if (line[i] > line[i - 1]) {
				int min = Integer.MAX_VALUE;
				int minIndex = i;
				for (int j = i; j < line.length; j++) {
					if (line[j] < min && line[j] > line[i - 1]) {
						min = line[j];
						minIndex = j;
					}
				}

				{
					byte temp = line[minIndex];
					line[minIndex] = line[i - 1];
					line[i - 1] = temp;
				}
				Arrays.sort(line, i, line.length);
				return true;
			}
		}
		return false;
	}

	public boolean prev() {
		for (int i = line.length - 1; i > 0; i--) {
			if (line[i] < line[i - 1]) {
				int max = Integer.MIN_VALUE;
				int maxIndex = i;
				for (int j = i; j < line.length; j++) {
					if (line[j] > max && line[j] < line[i - 1]) {
						max = line[j];
						maxIndex = j;
					}
				}

				{
					byte temp = line[maxIndex];
					line[maxIndex] = line[i - 1];
					line[i - 1] = temp;
				}

				Arrays.sort(line, i, line.length);
				for (int j = 0; i + j < (line.length - 1 - j); j++) {
					byte temp = line[i + j];
					line[i + j] = line[line.length - 1 - j];
					line[line.length - 1 - j] = temp;
				}
				return true;
			}
		}
		return false;
	}

	public Number toNumber() {
		return new Number(line);
	}
}
