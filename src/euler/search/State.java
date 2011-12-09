package euler.search;

public interface State {
    Transition getFirstTransitions();

    boolean isGoalState();
}
