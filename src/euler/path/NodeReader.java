package euler.path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class NodeReader {
    public static final int CONNECT_UP = 1, CONNECT_DOWN = 2, CONNECT_RIGHT = 4, CONNECT_LEFT = 8;

    private final BufferedReader reader;

    private final int connecting;

    public NodeReader(Reader reader, int connecting) {
        if (reader instanceof BufferedReader) {
            this.reader = (BufferedReader) reader;
        } else {
            this.reader = new BufferedReader(reader);
        }
        this.connecting = connecting;
    }

    public List<List<Node>> readNodes() throws IOException {
        List<List<Node>> matrix = new ArrayList<List<Node>>();

        String line = null;
        for (int y = 0; (line = reader.readLine()) != null; y++) {
            List<Node> current = new ArrayList<Node>();
            String[] nrs = line.split(",");
            for (int x = 0; x < nrs.length; x++) {
                Node node = null;
                try {
                    int nr = Integer.parseInt(nrs[x]);
                    node = new Node(nr, x, y);
                } catch (NumberFormatException ex) {
                    node = new Node(0, x, y);
                }
                current.add(node);
            }
            matrix.add(current);
        }

        for (int y = 0; y < matrix.size(); y++) {
            final List<Node> row = matrix.get(y);
            for (int x = 0; x < row.size(); x++) {
                Node node = row.get(x);
                if (y > 0 && (connecting & CONNECT_UP) != 0) {
                    node.addNeighbor(matrix.get(y - 1).get(x));
                }
                if (x < row.size() - 1 && (connecting & CONNECT_RIGHT) != 0) {
                    node.addNeighbor(matrix.get(y).get(x + 1));
                }
                if (y < matrix.size() - 1 && (connecting & CONNECT_DOWN) != 0) {
                    node.addNeighbor(matrix.get(y + 1).get(x));
                }
                if (x > 0 && (connecting & CONNECT_LEFT) != 0) {
                    node.addNeighbor(matrix.get(y).get(x - 1));
                }
            }
        }

        reader.close();

        return matrix;
    }
}
