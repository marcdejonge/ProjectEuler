package euler.sequence;

import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.stream.LongStream;

public interface LongSequence extends LongSupplier {

    long current();

    LongSequence dropWhile(LongPredicate predicate);

    LongSequence reset();

    long[] head(long until);

    long next();

    long position();

    String toString(int nr);

    LongStream stream();
}