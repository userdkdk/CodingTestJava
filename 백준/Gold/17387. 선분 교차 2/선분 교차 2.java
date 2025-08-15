import java.util.*;
import java.io.*;

public class Main {
    static int N; static int[] x, y; static double a,b; static int max = 987654321;
    static double min = 0.00000001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        x = new int[4];
        y = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<=1;i++) {
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i=2;i<=3;i++) {
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }
        a = (double) (y[1] - y[0]) /(x[1]-x[0]);
        b = (double) (y[3] - y[2]) /(x[3]-x[2]);
//        System.out.println(a+", "+b);

        boolean flag = true;
        if (x[0]<x[2] && x[1]<x[2] && x[0]<x[3] && x[1]<x[3]) flag = false;
        if (x[0]>x[2] && x[1]>x[2] && x[0]>x[3] && x[1]>x[3]) flag = false;
        if (y[0]<y[2] && y[1]<y[2] && y[0]<y[3] && y[1]<y[3]) flag = false;
        if (y[0]>y[2] && y[1]>y[2] && y[0]>y[3] && y[1]>y[3]) flag = false;

        if (flag) {
            int[][] tmp = {{x[0],y[0]},{x[2],y[2]}};
            if (Math.abs(a)>max && Math.abs(b)>max) {
            } else if (Math.abs(a)>max && Math.abs(b)<max) {
                double num = getF1(x[1],b,1,tmp);
                if ((y[0]-num)*(y[1]-num)>0) flag = false;
            } else if (Math.abs(a)<max && Math.abs(b)>max) {
                double num = getF1(x[2],a,0,tmp);
                if ((y[2]-num)*(y[3]-num)>0) flag = false;
            } else if (a==b) {
                double check = (double) a*x[0] - y[0] -a*x[2] + y[2];
                if (Math.abs(check)>min) {
                    flag = false;
                }
            } else {
                Arrays.sort(x);
                double left = getF1(x[1],a,0,tmp) - getF1(x[1],b,1,tmp);
                double right = getF1(x[2],a,0,tmp) - getF1(x[2],b,1,tmp);
//                System.out.println(left+ ", "+right);
                if (Math.abs(left)<min || Math.abs(right)<min) {
                } else {
                    if (left>min && right>min) flag = false;
                    if (left<min && right<min) flag = false;
                }
            }
        }

        if (flag) System.out.println(1);
        else System.out.println(0);
    }
    static double getF1(int x, double inc, int num, int[][] tmp) {
        return inc *(x-tmp[num][0]) + tmp[num][1];
    }
}
