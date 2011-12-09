package euler.search;

public interface Transition {
    State execute();

    State getFromState();

    Transition nextTransition();
}
