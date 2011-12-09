package euler.search;

public interface SearchAlgorithm {
    void search(State startState);

    void setSearchAlgorithmListener(SearchAlgorithmListener listener);
}
