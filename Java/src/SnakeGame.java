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

    // Received this method from a fellow student //
    public void SnakeHead(boolean[][] A, int x, int y) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                game[i][j] = A[i][j];
            }
        }

        headPosition[0] = x;
        headPosition[1] = y;
    }

    public int[] findTailExhaustive() {
        resetCounters();
        int[] snakeTail = {-1,-1,-1};
        int n;
        int size = 0;

        for (int i = 0; i < game.length; i++) {
            for (int j = 0; i < game[0].length; j++) {
                exhaustiveChecks += 1;
                n = neighbors(i,j);
                if ((n == 1) && ((i != headPosition[0]) && (j != headPosition[1]))) {
                    snakeTail[0] = i;
                    snakeTail[1] = j;
                   break;
                }
            }
            if ((snakeTail[0] != -1) && (snakeTail[1] != -1)) {
                break;
            }
        }
        size = TotalLength();
        snakeTail[2] = size;
        return snakeTail;
    }

    public int[] findTailRecursivly() {
        resetCounters();
        int[] snakeTail = findTailRecursivly(headPosition, headPosition);
        return snakeTail;
    }

    public int[] findTailRecursivly(int[] currentPosition, int[] previousPosition) {
        recursiveChecks += 1;
        int[] snakeTail = {0,0,0};
        int[] infoStockade = neighborsNext(currentPosition[0], currentPosition[1]);
        if ((infoStockade[4] == 1) && ((currentPosition[0] != headPosition[0]) && (currentPosition[1] != headPosition[1]))) {
            snakeTail[0] = currentPosition[0];
            snakeTail[1] = currentPosition[1];
            snakeTail[2] = TotalLength();
        } else {
            if ((infoStockade[0] != previousPosition[0]) && (infoStockade[1] != previousPosition[1])) {
                previousPosition[0] = currentPosition[0];
                previousPosition[1] = currentPosition[1];
                currentPosition[0] = infoStockade[0];
                currentPosition[1] = infoStockade[1];
                findTailRecursivly(currentPosition, previousPosition);
            } else {
                previousPosition[0] = currentPosition[0];
                previousPosition[1] = currentPosition[1];
                currentPosition[0] = infoStockade[2];
                currentPosition[1] = infoStockade[3];
                findTailRecursivly(currentPosition, previousPosition);
            }
        }
        return snakeTail;
    }

    private void resetCounters() {
        exhaustiveChecks = 0;
        recursiveChecks = 0;
    }

    private static int getRecursiveChecks() {
        return recursiveChecks;
    }

    private static int getExhaustiveChecks() {
        return exhaustiveChecks;
    }

    public int neighbors(int a, int b) {
        int neigh = 0;
        int rPlus = a + 1;
        int rMinus = a - 1;
        int cPlus = b + 1;
        int cMinus = b - 1;


        if ((-1 < rPlus) && (rPlus < game.length) && (-1 < b) && (b < game.length)) {
            if (game[rPlus][b] == true) {
                neigh += 1;
            }
        }


        if ((-1 < a) && (a < game.length) && (-1 < cPlus) && (cPlus < game.length)) {
            if (game[a][cPlus] == true) {
                neigh += 1;
            }
        }

        if ((-1 < a) && (a < game.length) && (-1 < cMinus) && (cMinus < game.length)) {
            if (game[a][cMinus] == true) {
                neigh += 1;
            }
        }


        if ((-1 < rMinus) && (rMinus < game.length) && (-1 < b) && (b < game.length)) {
            if (game[rMinus][b] == true) {
                neigh += 1;
            }
        }

        return neigh;
    }

    public int[] neighborsNext (int a, int b) {
        int parthers = neighbors(a,b);
        int[] neigh = {0,0,0,0,0};
        int rPlus = a + 1;
        int rMinus = a - 1;
        int cPlus = b + 1;
        int cMinus = b - 1;


        if ((-1 < rPlus) && (rPlus < game.length) && (-1 < b) && (b < game.length)) {
            if (game[rPlus][b] == true) {
                if ((neigh[0] == 0) && (neigh[1] == 0)) {
                    neigh[0] = rPlus;
                    neigh[1] = b;
                } else {
                    neigh[2] = rPlus;
                    neigh[3] = b;
                }
            }
        }


        if ((-1 < a) && (a < game.length) && (-1 < cPlus) && (cPlus < game.length)) {
            if (game[a][cPlus] == true) {
                if ((neigh[0] == 0) && (neigh[1] == 0)) {
                    neigh[0] = a;
                    neigh[1] = cPlus;
                } else {
                    neigh[2] = a;
                    neigh[3] = cPlus;
                }
            }
        }

        if ((-1 < a) && (a < game.length) && (-1 < cMinus) && (cMinus < game.length)) {
            if (game[a][cMinus] == true) {
                if ((neigh[0] == 0) && (neigh[1] == 0)) {
                    neigh[0] = a;
                    neigh[1] = cMinus;
                } else {
                    neigh[2] = a;
                    neigh[3] = cMinus;
                }
            }
        }


        if ((-1 < rMinus) && (rMinus < game.length) && (-1 < b) && (b < game.length)) {
            if (game[rMinus][b] == true) {
                if ((neigh[0] == 0) && (neigh[1] == 0)) {
                    neigh[0] = rMinus;
                    neigh[1] = b;
                } else {
                    neigh[2] = rMinus;
                    neigh[3] = b;
                }
            }
        }
        neigh[4] = parthers;

        return neigh;
    }

    public int TotalLength() {
        int length = 0;
        for (int i = 0; i < game.length; i++) {
            for (int j = 0; j < game[0].length; j++) {
                if (game[i][j] == true) {
                    length += 1;
                }
            }
        }
        return length;
    }


}

