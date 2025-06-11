import java.util.*;
import java.io.*;

public class Main {
    static int n, m, k; static int[][] arr; static int[][] oriSticker;
    static int[][] modifiedSticker;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        for (int tc=1;tc<=k;tc++) {
            st = new StringTokenizer(br.readLine());
            int tc_n = Integer.parseInt(st.nextToken());
            int tc_m = Integer.parseInt(st.nextToken());
            oriSticker = new int[tc_n][tc_m];
            for (int i=0;i<tc_n;i++) {
                st = new StringTokenizer(br.readLine());
                for (int j=0;j<tc_m;j++) {
                    oriSticker[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            out:
            for (int d=0;d<4;d++) {
                // 회전시킨 도형 구하기
                rotation(tc_n,tc_m,d);
                if (d%2==0) {
                    for (int i=0;i<=n-tc_n;i++) {
                        for (int j=0;j<=m-tc_m;j++) {
                            if (checkChecked(tc_n, tc_m, i, j)) continue;
                            fillArr(tc_n, tc_m, i, j);
                            break out;
                        }
                    }
                } else {
                    for (int i=0;i<=n-tc_m;i++) {
                        for (int j=0;j<=m-tc_n;j++) {
                            if (checkChecked(tc_m, tc_n, i, j)) continue;
                            fillArr(tc_m, tc_n, i, j);


                            break out;
                        }
                    }
                }
            }

        }
        int answer = 0;
        for (int i=0;i<n;i++) {
            for (int j=0;j<m;j++) {
                if (arr[i][j]==1) answer+=1;
            }
        }
        System.out.println(answer);
    }
    static void fillArr(int tc_n, int tc_m, int a, int b) {
        for (int i=0;i<tc_n;i++) {
            for (int j=0;j<tc_m;j++) {
                if (modifiedSticker[i][j]==1) {
                    arr[a+i][b+j] = 1;
                }
            }
        }
    }

    static boolean checkChecked(int tc_n, int tc_m, int a, int b) {
        for (int i=0;i<tc_n;i++) {
            for (int j=0;j<tc_m;j++) {
                if (arr[a+i][b+j]==1 && modifiedSticker[i][j]==1) return true;
            }
        }
        return false;
    }

    static void rotation(int tc_n, int tc_m, int d) {
        switch (d) {
            case 0:
                modifiedSticker = new int[tc_n][tc_m];
                for (int i=0;i<tc_n;i++) {
                    modifiedSticker[i] = oriSticker[i].clone();
                }
                break;
            case 1:
                modifiedSticker = new int[tc_m][tc_n];
                for (int i=0;i<tc_m;i++) {
                    for (int j=0;j<tc_n;j++) {
                        modifiedSticker[i][j] = oriSticker[tc_n-j-1][i];
                    }
                }
                break;
            case 2:
                modifiedSticker = new int[tc_n][tc_m];
                for (int i=0;i<tc_n;i++) {
                    for (int j=0;j<tc_m;j++) {
                        modifiedSticker[i][j] = oriSticker[tc_n-i-1][tc_m-j-1];
                    }
                }
                break;
            case 3:
                modifiedSticker = new int[tc_m][tc_n];
                for (int i=0;i<tc_m;i++) {
                    for (int j=0;j<tc_n;j++) {
                        modifiedSticker[i][j] = oriSticker[j][tc_m-i-1];
                    }
                }
                break;
        }
    }
}
