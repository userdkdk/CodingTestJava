import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        boolean[][] arr = new boolean[101][101];
        
        for (int tc = 0; tc < T; tc++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            
            for (int i = n; i < n + 10; i++) {
                for (int j = m; j < m + 10; j++) {
                    arr[i][j] = true;
                }
            }
        }
        
        int perimeter = 0;
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        
        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < 101; j++) {
                if (arr[i][j]) {
                    for (int k = 0; k < 4; k++) {
                        int new_x = i + dr[k];
                        int new_y = j + dc[k];
                        if (new_x < 0 || new_x > 100 || new_y < 0 || new_y > 100 || !arr[new_x][new_y]) {
                            perimeter++;
                        }
                    }
                }
            }
        }
        
        System.out.println(perimeter);
    }
}
