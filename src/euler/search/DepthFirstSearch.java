package euler.search;

import java.util.Stack;

public class DepthFirstSearch<S, T extends Transition<T>, M extends Model<S, T>> implements SearchAlgorithm<S, T, M> {
    public static <S, T extends Transition<T>, M extends Model<S, T>> DepthFirstSearch<S, T, M> create(Class<M> clazz) {
        return new DepthFirstSearch<S, T, M>();
    }

    private SearchAlgorithmListener<M> listener;

    private final Stack<T> next;

    public DepthFirstSearch() {
        next = new Stack<T>();
    }

    protected boolean goalStateFound(M model) {
        if (listener != null) {
            return listener.goalStateFound(model);
        } else {
            return true;
        }
    }

    @Override
    public void search(M model) {
        next.clear();

        while (true) {
            if (model.isGoalState()) {
                if (!goalStateFound(model)) {
                    break;
                }
            }

            T transition = model.getTransition(null);
            if (transition != null) {
                transition.execute();
                next.add(transition);
            } else {
                while (transition == null && !next.isEmpty()) {
                    transition = next.pop();
                    transition.undo();
                    transition = model.getTransition(transition);
                }
                if (transition == null) { // and thus the stack is empty
                    break;
                } else {
                    transition.execute();
                    next.add(transition);
                }
            }
        }

        searchFinished();
    }

    protected void searchFinished() {
        if (listener != null) {
            listener.searchFinished();
        }
    }

    @Override
    public void setSearchAlgorithmListener(SearchAlgorithmListener<M> listener) {
        this.listener = listener;
    }
}
