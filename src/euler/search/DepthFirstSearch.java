package euler.search;

import java.util.Stack;

public class DepthFirstSearch<S, T extends Transition<T>, M extends Model<S, T>> implements SearchAlgorithm<S, T, M> {
    public static <S, T extends Transition<T>, M extends Model<S, T>> DepthFirstSearch<S, T, M> create(Class<M> clazz) {
        return new DepthFirstSearch<S, T, M>();
    }

    private SearchAlgorithmListener<S> listener;

    private final Stack<T> next;

    public DepthFirstSearch() {
        next = new Stack<T>();
    }

    protected void goalStateFound(S state) {
        if (listener != null) {
            listener.goalStateFound(state);
        }
    }

    @Override
    public void search(M model) {
        next.clear();

        while (true) {
            if (model.isGoalState()) {
                goalStateFound(model.storeState());
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
    public void setSearchAlgorithmListener(SearchAlgorithmListener<S> listener) {
        this.listener = listener;
    }
}
