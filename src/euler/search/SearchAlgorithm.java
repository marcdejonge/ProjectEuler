package euler.search;

public interface SearchAlgorithm<S, T extends Transition<T>, M extends Model<S, T>> {
    void search(M model);

    void setSearchAlgorithmListener(SearchAlgorithmListener<M> listener);
}
