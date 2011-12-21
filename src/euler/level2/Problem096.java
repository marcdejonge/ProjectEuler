package euler.level2;

import java.util.Arrays;

import euler.Problem;
import euler.search.DepthFirstSearch;
import euler.search.Model;
import euler.search.SearchAlgorithmListener;
import euler.search.Transition;

public class Problem096 extends Problem<Integer> {

    class SudokuBoard implements Model<int[], SudokuTransition> {
        private final int[] board, possibleNumbers;

        public SudokuBoard() {
            board = new int[81];
            possibleNumbers = new int[81];
            Arrays.fill(possibleNumbers, 1022);
        }

        SudokuBoard(SudokuBoard board) {
            this.board = Arrays.copyOf(board.board, board.board.length);
            possibleNumbers = Arrays.copyOf(board.possibleNumbers, board.possibleNumbers.length);
        }

        boolean canPlace(int x, int y, int value) {
            // Reseting is always allowed
            if (value == 0) {
                return true;
            } else {
                return (possibleNumbers[x + y * 9] >>> value & 1) == 1;
            }
        }

        @Override
        public SudokuTransition getTransition(SudokuTransition last) {
            if (last == null) {
                if (!isFeasable()) {
                    return null;
                }
                for (int x = 0; x < 9; x++) {
                    for (int y = 0; y < 9; y++) {
                        if (board[x + y * 9] == 0) {
                            // Found a place to put it, now find the first value
                            for (int value = 1; value <= 9; value++) {
                                if (canPlace(x, y, value)) {
                                    return new SudokuTransition(this, x, y, value);
                                }
                            }
                        }
                    }
                }
            } else {
                for (int value = last.getValue() + 1; value <= 9; value++) {
                    if (canPlace(last.getX(), last.getY(), value)) {
                        return new SudokuTransition(last, value);
                    }
                }
            }
            return null;
        }

        public boolean isFeasable() {
            for (int ix = 0; ix < board.length; ix++) {
                if (board[ix] == 0 && possibleNumbers[ix] == 0) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public boolean isGoalState() {
            for (int value : board) {
                if (value == 0) {
                    return false;
                }
            }
            return true;
        }

        public void place(int x, int y, int value) {
            // Basic argument testing
            if (x < 0 || x > 8) {
                throw new IllegalArgumentException("x should be in range [0,8]");
            } else if (y < 0 || y > 8) {
                throw new IllegalArgumentException("y should be in range [0,8]");
            } else if (value < 1 || value > 9) {
                throw new IllegalArgumentException("value should be in range [1,9]");
            } else if (!canPlace(x, y, value)) {
                throw new IllegalArgumentException("Invalid placing on board");
            }

            board[x + y * 9] = value;

            int mask = -1 ^ 1 << value;

            // Remove the value from all the possible numbers
            for (int ix = y * 9; ix < (y + 1) * 9; ix++) {
                possibleNumbers[ix] &= mask;
            }
            for (int ix = x; ix < board.length; ix += 9) {
                possibleNumbers[ix] &= mask;
            }
            for (int xx = x / 3 * 3; xx < (x + 3) / 3 * 3; xx++) {
                for (int yy = y / 3 * 3; yy < (y + 3) / 3 * 3; yy++) {
                    possibleNumbers[xx + yy * 9] &= mask;
                }
            }

            // System.out.printf("Placement made:%n%s", this);
        }

        @Override
        public void restoreState(int[] state) {
            System.arraycopy(state, 0, board, 0, board.length);
            System.arraycopy(state, board.length, possibleNumbers, 0, possibleNumbers.length);
        }

        @Override
        public int[] storeState() {
            int[] backup = Arrays.copyOf(board, board.length + possibleNumbers.length);
            System.arraycopy(possibleNumbers, 0, backup, board.length, possibleNumbers.length);
            return backup;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int y = 0; y < 9; y++) {
                if (y % 3 == 0) {
                    sb.append("|===|===|===| |=========|=========|=========|").append(System.lineSeparator());
                }
                for (int x = 0; x < 9; x++) {
                    if (x % 3 == 0) {
                        sb.append('|');
                    }
                    if (board[x + y * 9] == 0) {
                        sb.append(' ');
                    } else {
                        sb.append(board[x + y * 9]);
                    }
                }
                sb.append("| ");
                for (int x = 0; x < 9; x++) {
                    if (x % 3 == 0) {
                        sb.append('|');
                    }
                    sb.append(String.format("%03d", possibleNumbers[x + y * 9] / 2));
                }
                sb.append('|').append(System.lineSeparator());
            }
            sb.append("|===|===|===| |=========|=========|=========|").append(System.lineSeparator());
            return sb.toString();
        }
    }

    class SudokuTransition implements Transition<SudokuTransition> {
        private final SudokuBoard board;
        private final int x, y, value;
        private int[] backup;

        public SudokuTransition(SudokuBoard board, int x, int y, int value) {
            this.board = board;
            this.x = x;
            this.y = y;
            this.value = value;
        }

        public SudokuTransition(SudokuTransition orig, int value) {
            board = orig.board;
            x = orig.x;
            y = orig.y;
            this.value = value;
        }

        @Override
        public void execute() {
            backup = board.storeState();
            board.place(x, y, value);
            // System.out.printf("Executed to:%n%s", board);
        }

        public SudokuBoard getBoard() {
            return board;
        }

        public int getValue() {
            return value;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public void undo() {
            board.restoreState(backup);
            backup = null;
            // System.out.printf("Undo to:%n%s", board);
        }
    }

    @Override
    public Integer solve() {
        SudokuBoard board = new SudokuBoard();
        board.place(2, 0, 3);
        board.place(4, 0, 2);
        board.place(6, 0, 6);
        board.place(0, 1, 9);
        board.place(3, 1, 3);
        board.place(5, 1, 5);
        board.place(8, 1, 1);
        board.place(2, 2, 1);
        board.place(3, 2, 8);
        board.place(5, 2, 6);
        board.place(6, 2, 4);
        board.place(2, 3, 8);
        board.place(3, 3, 1);
        board.place(5, 3, 2);
        board.place(6, 3, 9);
        board.place(0, 4, 7);
        board.place(8, 4, 8);
        board.place(2, 5, 6);
        board.place(3, 5, 7);
        board.place(5, 5, 8);
        board.place(6, 5, 2);
        board.place(2, 6, 2);
        board.place(3, 6, 6);
        board.place(5, 6, 9);
        board.place(6, 6, 5);
        board.place(0, 7, 8);
        board.place(3, 7, 2);
        board.place(5, 7, 3);
        board.place(8, 7, 9);
        board.place(2, 8, 5);
        board.place(4, 8, 1);
        board.place(6, 8, 3);

        DepthFirstSearch<int[], SudokuTransition, SudokuBoard> dfs = DepthFirstSearch.create(SudokuBoard.class);
        dfs.setSearchAlgorithmListener(new SearchAlgorithmListener<int[]>() {
            @Override
            public void goalStateFound(int[] state) {
                System.out.println("Result: " + (state[0] * 100 + state[1] * 10 + state[2]));
            }

            @Override
            public void searchFinished() {
                System.out.println("Finished!");
            }
        });
        dfs.search(board);
        return null;
    }

}
