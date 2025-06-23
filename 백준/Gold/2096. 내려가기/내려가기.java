import java.util.*;
import java.io.*;

public class Main {
    static int[][] max, min; static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        max = new int[n][3];
        min = new int[n][3];
        st =  new StringTokenizer(br.readLine());

        int n1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());
        int n3 = Integer.parseInt(st.nextToken());

        max[0][0] = n1;
        max[0][1] = n2;
        max[0][2] = n3;
        min[0][0] = n1;
        min[0][1] = n2;
        min[0][2] = n3;


        for (int i=1;i<n;i++) {
            max[i][0] = Math.max(max[i-1][0],max[i-1][1]);
            max[i][2] = Math.max(max[i-1][1],max[i-1][2]);
            max[i][1] = Math.max(max[i][0],max[i][2]);

            min[i][0] = Math.min(min[i-1][0],min[i-1][1]);
            min[i][2] = Math.min(min[i-1][1],min[i-1][2]);
            min[i][1] = Math.min(min[i][0],min[i][2]);

            st = new StringTokenizer(br.readLine());
            n1 = Integer.parseInt(st.nextToken());
            n2 = Integer.parseInt(st.nextToken());
            n3 = Integer.parseInt(st.nextToken());

            max[i][0] += n1;
            max[i][1] += n2;
            max[i][2] += n3;

            min[i][0] += n1;
            min[i][1] += n2;
            min[i][2] += n3;
        }
        int max_ans = Math.max(Math.max(max[n-1][0],max[n-1][1]),max[n-1][2]);
        int min_ans = Math.min(Math.min(min[n-1][0],min[n-1][1]),min[n-1][2]);
        System.out.println(max_ans + " " + min_ans);
    }
}
