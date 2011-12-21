package euler.search;

public interface SearchAlgorithmListener<S> {
    void goalStateFound(S state);

    void searchFinished();
}
