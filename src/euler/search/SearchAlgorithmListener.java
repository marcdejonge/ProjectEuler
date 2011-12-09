package euler.search;

public interface SearchAlgorithmListener {
    void goalStateFound(State state);

    void searchFinished();
}
