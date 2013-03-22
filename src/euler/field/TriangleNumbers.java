package euler.field;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TriangleNumbers {
    private Node[][] numbers;

    public TriangleNumbers(File file) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new FileReader(file))) {
            numbers = new Node[10][];
            int lineNr = 0;
            String line = null;
            while ((line = reader.readLine()) != null) {
                final String[] sNrs = line.split(" ");
                lineNr++;
                if (sNrs.length != lineNr) {
                    throw new IOException("Illegal format: should be " + lineNr + "numbers on line " + lineNr);
                }
                if (numbers.length <= lineNr) {
                    final Node[][] temp = new Node[lineNr * 2][];
                    System.arraycopy(numbers, 0, temp, 0, numbers.length);
                    numbers = temp;
                }
                numbers[lineNr - 1] = new Node[lineNr];
                for (int i = 0; i < lineNr; i++) {
                    try {
                        numbers[lineNr - 1][i] = new Node(lineNr > 1 && i > 0 ? numbers[lineNr - 2][i - 1] : null,
                                                          lineNr > 1 && i < lineNr - 1 ? numbers[lineNr - 2][i] : null,
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

    public Node[] findHeaviestRoute() {
        Node max = numbers[numbers.length - 1][0];
        for (final Node n : numbers[numbers.length - 1]) {
            if (n.getMaxPath() > max.getMaxPath()) {
                max = n;
            }
        }
        final Node[] route = new Node[numbers.length];
        for (int i = numbers.length - 1; i >= 0; i--) {
            route[i] = max;
            max = max.getMaxPathNode();
        }
        return route;
    }

    public int getNr(int x, int y) {
        return numbers[x][y].getNr();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        final int lineLength = (Node.maxSize + 1) * numbers.length - 1;
        for (int y = 0; y < numbers.length; y++) {
            final int nrSpaces = lineLength - ((Node.maxSize + 1) * (y + 1) - 1);
            int s = 0;
            for (; s < nrSpaces / 2; s++) {
                sb.append(' ');
            }
            for (int x = 0; x <= y; x++) {
                sb.append(numbers[y][x].toString());
                if (x < y) {
                    sb.append(' ');
                }
            }
            for (; s < nrSpaces; s++) {
                sb.append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
