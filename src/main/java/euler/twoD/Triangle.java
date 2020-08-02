package euler.twoD;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Triangle {
    public static Triangle decode(String line) throws IOException {
        final String[] xs = line.split(",");
        if (xs.length != 6) {
            throw new IOException("The line should contain 6 numbers, seperated by a comma");
        }

        try {
            return new Triangle(new Vector(Double.parseDouble(xs[0]), Double.parseDouble(xs[1])),
                                new Vector(Double.parseDouble(xs[2]), Double.parseDouble(xs[3])),
                                new Vector(Double.parseDouble(xs[4]), Double.parseDouble(xs[5])));
        } catch (final NumberFormatException e) {
            throw new IOException(e);
        }
    }

    public static List<Triangle> read(File file) throws IOException {
        final List<Triangle> res = new ArrayList<Triangle>();
        try (final BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                res.add(decode(line));
            }
        }
        return res;
    }

    private final Vector a, b, c;

    public Triangle(Vector a, Vector b, Vector c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public boolean containsPoint(Vector p) {
        final Vector v0 = c.minus(a);
        final Vector v1 = b.minus(a);
        final Vector v2 = p.minus(a);

        final double dot00 = v0.dot(v0);
        final double dot01 = v0.dot(v1);
        final double dot02 = v0.dot(v2);
        final double dot11 = v1.dot(v1);
        final double dot12 = v1.dot(v2);

        final double invDenom = 1.0 / (dot00 * dot11 - dot01 * dot01);
        final double u = (dot11 * dot02 - dot01 * dot12) * invDenom;
        final double v = (dot00 * dot12 - dot01 * dot02) * invDenom;

        return u > 0 && v > 0 && u + v < 1;
    }
}
