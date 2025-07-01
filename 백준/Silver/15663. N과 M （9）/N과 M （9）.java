import java.util.*;
import java.io.*;

public class Main {
    static int n, m; static int[] arr, ans_arr; static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        ans_arr = new int[m];
        visited = new boolean[n];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<n;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        comb(0);

    }
    static void comb(int depth) {
        if (depth==m) {
            for (int i=0;i<m;i++) {
                System.out.print(ans_arr[i]+" ");
            }
            System.out.println();
            return;
        }
        for (int i=0;i<n;i++) {
            if (visited[i]) continue;
            if (i>0 && ans_arr[depth]==arr[i]) continue;
            visited[i] = true;
            ans_arr[depth] = arr[i];
            comb(depth+1);
            visited[i] = false;
        }
        ans_arr[depth] = -1;
    }
}
