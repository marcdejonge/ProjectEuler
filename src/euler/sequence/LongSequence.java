package euler.sequence;

import euler.sequence.AbstractSequence.Test;

public interface LongSequence {

    long current();

    void dropWhile(Test test);

    long[] head(long until);

    long next();

    long position();

    void reset();

    String toString(int nr);

}