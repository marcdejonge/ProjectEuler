package euler.level2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

import euler.Problem;
import euler.search.DepthFirstSearch;
import euler.search.Model;
import euler.search.SearchAlgorithmListener;
import euler.search.Transition;

public class Problem096 extends Problem<Integer> {

    class SudokuBoard implements Model<SudokuBoard, SudokuTransition> {
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
                // Now find a location where only one option is available
                for (int x = 0; x < 9; x++) {
                    for (int y = 0; y < 9; y++) {
                        if (board[x + y * 9] == 0) {
                            int pn = possibleNumbers[x + y * 9];
                            if ((pn & pn - 1) == 0) {
                                return new SudokuTransition(this, x, y, Integer.numberOfTrailingZeros(pn));
                            }
                        }
                    }
                }
                // Then just locate the first place where we can place something
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
        public void restoreState(SudokuBoard state) {
            System.arraycopy(state.board, 0, board, 0, board.length);
            System.arraycopy(state.possibleNumbers, 0, possibleNumbers, 0, possibleNumbers.length);
        }

        @Override
        public SudokuBoard storeState() {
            return new SudokuBoard(this);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int y = 0; y < 9; y++) {
                if (y % 3 == 0) {
                    sb.append("|===|===|===|").append(System.lineSeparator());
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
                sb.append('|').append(System.lineSeparator());
            }
            sb.append("|===|===|===|").append(System.lineSeparator());
            return sb.toString();
        }
    }

    class SudokuTransition implements Transition<SudokuTransition> {
        private final SudokuBoard board;
        private final int x, y, value;
        private SudokuBoard backup;

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
        }
    }

    public Map<String, SudokuBoard> readBoards() {
        try (final BufferedReader reader = new BufferedReader(new FileReader("Problem096.txt"))) {
            int y = 0;
            String line = null;
            SudokuBoard board = null;
            Map<String, SudokuBoard> result = new TreeMap<String, SudokuBoard>();
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Grid")) {
                    board = new SudokuBoard();
                    result.put(line, board);
                    y = 0;
                } else if (line.length() == 9) {
                    for (int x = 0; x < 9; x++) {
                        char c = line.charAt(x);
                        if (c != '0') {
                            board.place(x, y, c - '0');
                        }
                    }
                    y++;
                }
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyMap();
        }
    }

    @Override
    public Integer solve() {
        DepthFirstSearch<SudokuBoard, SudokuTransition, SudokuBoard> dfs = DepthFirstSearch.create(SudokuBoard.class);
        final AtomicInteger sum = new AtomicInteger();
        SearchAlgorithmListener<SudokuBoard> resultAggregator = new SearchAlgorithmListener<SudokuBoard>() {
            @Override
            public boolean goalStateFound(SudokuBoard board) {
                SudokuBoard state = board.storeState();
                sum.addAndGet(state.board[0] * 100 + state.board[1] * 10 + state.board[2]);
                return false;
            }

            @Override
            public void searchFinished() {
            }
        };
        dfs.setSearchAlgorithmListener(resultAggregator);

        for (Entry<String, SudokuBoard> board : readBoards().entrySet()) {
            // long start = System.nanoTime();
            dfs.search(board.getValue());
            // System.out.printf("%s solved in %1.3f milliseconds%n", board.getKey(), (System.nanoTime() - start) /
            // 1e6);
        }
        return sum.get();
    }
}
