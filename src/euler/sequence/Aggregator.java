package euler.sequence;

public interface Aggregator<T extends java.lang.Number> {
	T aggregate(Sequence<T> seq);
}
