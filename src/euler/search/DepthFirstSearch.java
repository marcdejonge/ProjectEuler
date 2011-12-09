package euler.search;

import java.util.Stack;

public class DepthFirstSearch implements SearchAlgorithm {

    private SearchAlgorithmListener listener;

    private final Stack<Transition> next;

    public DepthFirstSearch() {
        next = new Stack<Transition>();
    }

    private void add(Transition transition) {
        if (transition != null) {
            next.add(transition);
        }
    }

    protected void goalStateFound(State state) {
        if (listener != null) {
            listener.goalStateFound(state);
        }
    }

    @Override
    public void search(State startState) {
        next.clear();
        next.add(startState.getFirstTransitions());

        while (!next.isEmpty()) {
            Transition trans = next.pop();

            State nextState = trans.execute();
            if (nextState.isGoalState()) {
                goalStateFound(nextState);
            }

            add(trans.nextTransition());
            add(nextState.getFirstTransitions());
        }

        searchFinished();
    }

    protected void searchFinished() {
        if (listener != null) {
            listener.searchFinished();
        }
    }

    @Override
    public void setSearchAlgorithmListener(SearchAlgorithmListener listener) {
        this.listener = listener;
    }
}
