import java.util.*;
import java.io.*;

public class Main {
    static int n; static int[][] arr; static double ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        arr = new int[n][2];
        for (int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        ans = 0;
        for (int i=1;i<n-1;i++) {
            ans += getSize(i);
        }
        System.out.printf("%.1f%n",Math.abs(ans/2));

    }
    static double getSize(int i) {
        double a = arr[0][0] - arr[i][0];
        double b = arr[0][0] - arr[i+1][0];
        double c = arr[0][1] - arr[i][1];
        double d = arr[0][1] - arr[i+1][1];
        return b*c-a*d;
    }
}
