public class SnakeGame {
    private boolean[][] game;
    private int[] headPosition;
    private static int exhaustiveChecks;
    private static int recursiveChecks;

    SnakeGame() {
        game = new boolean[1][1];
    }

    SnakeGame(boolean[][] A, int x, int y) {
        game = new boolean[A.length][A[0].length];
        headPosition = new int[]{x, y};
    }

    public int[] findTailExhaustive() {

    }

    public int[] findTailRecursivly() {

    }

    public int[] findTailRecursivly(int[] currentPosition, int[] previousPosition) {

    }

    private void resetCounters() {
        exhaustiveChecks = 0;
        recursiveChecks = 0;
    }

    private static int getRecursiveChecks() {

    }

    private static int getExhaustiveChecks() {

    }

    public int neighbors() {

    }


}

