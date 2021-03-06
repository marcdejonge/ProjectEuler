package euler.field;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Matrix {
    private Node[][] numbers;

    public Matrix(File file) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new FileReader(file))) {
            numbers = new Node[10][];
            int width = -1;
            int lineNr = 0;
            String line = null;
            while ((line = reader.readLine()) != null) {
                final String[] sNrs = line.split(",");
                lineNr++;
                if (width == -1) {
                    width = sNrs.length;
                } else if (sNrs.length != width) {
                    throw new IOException("Illegal format: should be " + width + " numbers on line " + lineNr);
                }
                if (numbers.length <= lineNr) {
                    final Node[][] temp = new Node[lineNr * 2][];
                    System.arraycopy(numbers, 0, temp, 0, numbers.length);
                    numbers = temp;
                }
                numbers[lineNr - 1] = new Node[width];
                for (int i = 0; i < width; i++) {
                    try {
                        numbers[lineNr - 1][i] = new Node(i > 0 ? numbers[lineNr - 1][i - 1] : null,
                                                          lineNr > 1 ? numbers[lineNr - 2][i] : null,
                                                          Integer.parseInt(sNrs[i]));
                        if (sNrs[i].length() > Node.maxSize) {
                            Node.maxSize = sNrs[i].length();
                        }
                    } catch (final NumberFormatException ex) {
                        throw new IOException("Illegal number an line " + lineNr);
                    }
                }
            }

            final Node[][] temp = new Node[lineNr][];
            System.arraycopy(numbers, 0, temp, 0, lineNr);
            numbers = temp;
        }
    }

    public Node getLast() {
        return numbers[numbers.length - 1][numbers[0].length - 1];
    }

    public int getNr(int x, int y) {
        return numbers[x][y].getNr();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (final Node[] number : numbers) {
            for (Node element : number) {
                sb.append(element).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
