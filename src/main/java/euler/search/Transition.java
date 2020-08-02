package euler.search;

public interface Transition<T extends Transition<T>> {
    void execute();

    void undo();
}
