package euler.search;

public interface SearchAlgorithmListener<M> {
    boolean goalStateFound(M model);

    void searchFinished();
}
