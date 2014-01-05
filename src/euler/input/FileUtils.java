package euler.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import euler.Problem;
import euler.graph.WeightedDirectedGraphImpl;

public abstract class FileUtils {
    public static File findFile(Problem<?> problem) throws IOException {
        String filename = "input" + File.separator + problem.getClass().getSimpleName() + ".txt";
        File file = new File(filename);
        if (file.exists() && file.canRead()) {
            return file;
        } else {
            throw new IOException("Can not read file [" + filename + "]");
        }
    }

    public static char[] readChars(Problem<?> problem) throws IOException {
        File file = findFile(problem);
        try (final Scanner scanner = new Scanner(file)) {
            scanner.useDelimiter(",|\n");
            final char[] chars = new char[(int) (file.length() / 2)];
            int i = 0;
            while (scanner.hasNextInt()) {
                chars[i] = (char) scanner.nextInt();
                i++;
            }
            final char[] temp = new char[i];
            System.arraycopy(chars, 0, temp, 0, i);
            return temp;
        }
    }

    public static BufferedReader readInput(Problem<?> problem) throws IOException {
        return new BufferedReader(new FileReader(findFile(problem)));
    }

    public static List<String> readNames(Problem<?> problem) throws IOException {
        final List<String> names = new ArrayList<String>();
        try (final Scanner scanner = scanInput(problem)) {
            scanner.useDelimiter("\"(,\")*");
            String name = null;
            while (scanner.hasNext()) {
                name = scanner.next();
                if (name.length() > 0) {
                    names.add(name.toLowerCase());
                }
            }
        }
        return names;
    }

    public static WeightedDirectedGraphImpl readWeightedGraph(Problem<?> problem) throws IOException {
        WeightedDirectedGraphImpl graph = new WeightedDirectedGraphImpl();
        int from = 0;
        try (BufferedReader reader = FileUtils.readInput(problem)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                for (int to = 0; to < parts.length; to++) {
                    if (!"-".equals(parts[to])) {
                        int weight = Integer.parseInt(parts[to]);
                        graph.addEdge(from, to, weight);
                    }
                }
                from++;
            }
        }
        return graph;
    }

    public static Scanner scanInput(Problem<?> problem) throws IOException {
        return new Scanner(findFile(problem));
    }
}
