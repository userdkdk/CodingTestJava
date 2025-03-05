import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main
{
    static int[][] check = new int[8][3];
    public static void main(String[] args) throws IOException {
        check[0] = new int[] {0, 1, 2};
        check[1] = new int[] {3, 4, 5};
        check[2] = new int[] {6, 7, 8};
        check[3] = new int[] {0, 3, 6};
        check[4] = new int[] {1, 4, 7};
        check[5] = new int[] {2, 5, 8};
        check[6] = new int[] {0, 4, 8};
        check[7] = new int[] {2, 4, 6};
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr = br.readLine().toCharArray();
        while (arr[0]!='e') {
            int[] x = new int[4];
            int[] y = new int[4];
            for (int i=0;i<8;i++) {
                if (arr[check[i][0]]=='X' && arr[check[i][1]]=='X'
                        && arr[check[i][2]]=='X') {
                    x[i/3]++;
                }
                if (arr[check[i][0]]=='O' && arr[check[i][1]]=='O'
                        && arr[check[i][2]]=='O') {
                    y[i/3]++;
                }
            }
            for (int i=0;i<9;i++) {
                if (arr[i]=='X') x[3]++;
                if (arr[i]=='O') y[3]++;
            }
            int sum_x = 0;
            int sum_y = 0;
            for (int i=0;i<3;i++) {
                sum_x += x[i];
                sum_y += y[i];
            }
            if (sum_x+sum_y>=3) {
                System.out.println("invalid");
            } else if (x[3]-y[3]<0 || x[3]-y[3]>=2) {
                System.out.println("invalid");
            } else if (sum_x == 1 && sum_y==1) {
                System.out.println("invalid");
            } else if (sum_y==1) {
                if (x[3]-y[3]==1) {
                    System.out.println("invalid");
                } else {
                    System.out.println("valid");
                }
            } else if (sum_x==1) {
                if (x[3]-y[3]==0) {
                    System.out.println("invalid");
                } else {
                    System.out.println("valid");
                }
            }else if (sum_x+sum_y==0) {
                if (x[3]==5 && y[3]==4) {
                    System.out.println("valid");
                } else {
                    System.out.println("invalid");
                }
            } else if (sum_x==2) {
                boolean flag = false;
                for (int i=0;i<2;i++) {
                    if (x[i]>=2) flag=true;
                }
                if (flag) {
                    System.out.println("invalid");
                } else {
                    System.out.println("valid");
                }
            } else {
                System.out.println("valid");
            }
//            System.out.println(Arrays.toString(x));
//            System.out.println(Arrays.toString(y));
            arr = br.readLine().toCharArray();
        }
    }
}