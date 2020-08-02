package euler.search;

public interface Model<S, T extends Transition<T>> {
    T getTransition(T last);

    boolean isGoalState();

    void restoreState(S state);

    S storeState();
}
