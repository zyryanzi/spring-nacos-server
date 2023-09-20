package cloud.spring.my.study.algorithm;

public class ValidSudoku {

    private static final int[][] rows = new int[9][9];
    private static final int[][] cols = new int[9][9];
    private static final int[][] subBoards = new int[9][9];

    public static boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int boardVal = Integer.parseInt(String.valueOf(board[i][j])) - 1;
                    if (rows[i][boardVal] == 1 || cols[boardVal][j] == 1 || subBoards[i / 3 * 3 + j / 3][boardVal] == 1) {
                        return false;
                    }
                    rows[i][boardVal] = 1;
                    cols[boardVal][j] = 1;
                    subBoards[i / 3 * 3 + j / 3][boardVal] = 1;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        char[][] board = {{'.','8','7','6','5','4','3','2','1'}, {'2','.','.','.','.','.','.','.','.'}, {'3','.','.','.','.','.','.','.','.'}, {'4','.','.','.','.','.','.','.','.'}, {'5','.','.','.','.','.','.','.','.'}, {'6','.','.','.','.','.','.','.','.'}, {'7','.','.','.','.','.','.','.','.'}, {'8','.','.','.','.','.','.','.','.'}, {'9','.','.','.','.','.','.','.','.'}};

        System.out.println(isValidSudoku(board));
    }

}
