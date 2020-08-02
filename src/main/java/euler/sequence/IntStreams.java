package euler.sequence;

import java.util.Spliterator;
import java.util.Spliterator.OfInt;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public class IntStreams {
    public static class Builder {
        private int from = 1;
        private int step = 1;
        private int until = Integer.MAX_VALUE;

        Builder() {
        }

        public Builder from(int from) {
            this.from = from;
            return this;
        }

        public Builder step(int step) {
            this.step = step;
            return this;
        }

        public IntStream stream() {
            final int from = this.from;
            final int until = this.until;

            return StreamSupport.intStream(new IntGenerator(from, step, until), true);
        }

        public Builder to(int to) {
            until = to - 1;
            return this;
        }

        public Builder until(int until) {
            this.until = until;
            return this;
        }
    }

    private static class IntGenerator implements OfInt {
        private int curr;
        private int incr;
        private final int until;

        IntGenerator(int curr, int incr, int until) {
            this.curr = curr;
            this.incr = incr;
            this.until = until;
        }

        @Override
        public int characteristics() {
            return Spliterator.SIZED | Spliterator.SUBSIZED;
        }

        @Override
        public long estimateSize() {
            if (incr == 0) {
                return Long.MAX_VALUE;
            }
            long size = (until - curr) / incr;
            if (size < 0) {
                return Long.MAX_VALUE;
            }
            return size + 1;
        }

        @Override
        public boolean tryAdvance(IntConsumer action) {
            if (curr > until) {
                return false;
            } else {
                action.accept(curr);
                curr += incr;
                return true;
            }
        }

        @Override
        public java.util.Spliterator.OfInt trySplit() {
            if (incr > 1 << 16) {
                return null;
            }

            IntGenerator copy = new IntGenerator(curr + incr, incr * 2, until);
            incr *= 2;
            return copy;
        }
    }

    public static Builder from(int from) {
        return new Builder().from(from);
    }
}
