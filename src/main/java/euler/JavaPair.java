package euler;

public class JavaPair<T, V> {
    public static <T, V> JavaPair<T, V> from(T first, V second) {
        return new JavaPair<T, V>(first, second);
    }

    private final T first;

    private final V second;

    public JavaPair(T first, V second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        final JavaPair<?, ?> other = (JavaPair<?, ?>) obj;

        if (first == null) {
            if (other.first != null) {
                return false;
            }
        } else if (!first.equals(other.first)) {
            return false;
        }

        if (second == null) {
            if (other.second != null) {
                return false;
            }
        } else if (!second.equals(other.second)) {
            return false;
        }

        return true;
    }

    public T getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }

    @Override
    public int hashCode() {
        return 31 * (first == null ? 0 : first.hashCode()) + 67 * (second == null ? 0 : second.hashCode());
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + ")";
    }
}
