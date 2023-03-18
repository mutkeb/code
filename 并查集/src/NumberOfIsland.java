import java.util.ArrayList;
import java.util.List;

public class NumberOfIsland {

    //  感染解法
    public static int numIsland3(char[][] board){
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '1'){
                    count++;
                    //  把关联的一片全部变成2
                    infect(board,i,j);
                }
            }
        }
        return count;
    }

    public static int numIsland1(char[][] board){
        Dot[][] dots = new Dot[board.length][board[0].length];
        List<Dot> dotList = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j] == '1'){
                    dots[i][j] = new Dot();
                    dotList.add(dots[i][j]);
                }
            }
        }
        UnionFind<Dot> unionFind = new UnionFind<>(dotList);
        int row = board.length;
        int col = board[0].length;
        for (int i = 1; i < row; i++) {
            if (board[i-1][0] == '1' && board[i][0] == '1'){
                unionFind.union(dots[i-1][0],dots[i][0]);
            }
        }

        for (int i = 1; i < col; i++) {
            if (board[0][col-1] == '1' && board[0][col] == '1'){
                unionFind.union(dots[0][col-1],dots[0][col]);
            }
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (board[i][j] == '1'){
                    if (board[i-1][j] == '1'){
                        unionFind.union(dots[i-1][j],dots[i][j]);
                    }
                    if (board[i][j-1] == '1'){
                        unionFind.union(dots[i][j],dots[i][j-1]);
                    }
                }
            }
        }
        return unionFind.sets();
    }

    private static void infect(char[][] board, int i, int j) {
        if (i < 0 || i == board.length || j < 0 || j == board.length || board[i][j] != '1'){
            return;
        }
        board[i][j] = '2';
        infect(board,i-1,j);
        infect(board,i+1,j);
        infect(board,i,j+1);
        infect(board,i,j-1);
    }

    public static class Dot{

    }
}
