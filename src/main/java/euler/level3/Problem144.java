package euler.level3;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Locale;

import euler.IntegerProblem;

public class Problem144 extends IntegerProblem {
    private PrintStream svg;

    private void printAngle(String name, double angle) {
        print("%s = %5.2f°%n", name, angle * 180 / Math.PI);
    }

    private void printSvg(String format, Object... params) {
        if (debug) {
            try {
                if (svg == null) {
                    svg = new PrintStream(new FileOutputStream("problem144.svg"));
                }
                svg.print(String.format(Locale.US, format, params));
            } catch (IOException ex) {
                // Ignore
            }
        }
    }

    @Override
    public long solve() {
        printSvg("<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"500\" height=\"750\" viewBox=\"-10 -15 20 30\">%n");
        printSvg("\t<ellipse rx=\"5\" ry=\"10\" stroke=\"black\" stroke-width=\"0.1\" fill=\"none\" />%n");

        long count = 0;
        double x1 = 0, y1 = 10.1;
        double x2 = 1.4, y2 = -9.6;

        printSvg("\t<path stroke=\"red\" stroke-width=\"0.1\" stroke-linejoin=\"bevel\" fill=\"none\""
                 + " d=\"M %1.3f %1.3f L %1.3f %1.3f ", x1, -y1, x2, -y2);
        while (y2 < 0 || x2 < -0.01 || x2 > 0.01) {
            double a_in = Math.atan((y2 - y1) / (x2 - x1));
            printAngle("α_in", a_in);
            double t = Math.atan(-4 * x2 / y2);
            printAngle("t", t);
            double a_out = 2 * t - a_in;
            printAngle("α_out", a_out);
            double outSlope = Math.tan(a_out);
            print("Slope: %1.3f%n", outSlope);

            // 4 * (x + dx)^2 + (y + s * dx)^2 = 100
            // 4*x^2 + 8*x*dx + 4*dx^2 + y^2 + 2 * y * s * dx + s^2 * dx^2 - 100 = 0
            // (4 + s^2) dx^2 + (8*x + 2 * y * s) dx + (4*x^2 + y^2 - 100) = 0
            // dx = (-b +- sqrt(b^2 - 4*a*c)) / (2 * a)
            // a = 4 * s^2
            // b = 8 * x + 2 * y * s
            // c = 4 * x^2 + y^2 - 100 = 0
            // dx = -b / a
            // dy = dx * s

            double a = 4 + outSlope * outSlope;
            double b = 8 * x2 + 2 * y2 * outSlope;

            double dx = -b / a;
            double dy = dx * outSlope;

            x1 = x2;
            y1 = y2;
            x2 = x1 + dx;
            y2 = y1 + dy;

            printSvg("L %1.3f %1.3f ", x2, -y2);
            count++;
        }
        printSvg("\" />%n");
        printSvg("</svg>%n");

        if (svg != null) {
            svg.close();
        }

        return count;
    }

}
