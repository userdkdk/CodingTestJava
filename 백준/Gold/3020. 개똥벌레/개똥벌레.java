import java.io.*;
import java.util.*;

public class Main {
    static int n; static int h;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        int[] arrDown = new int[h];
        int[] arrUp = new int[h];
        for (int i=1;i<=n/2;i++) {
            int a = Integer.parseInt(br.readLine());
            int b = Integer.parseInt(br.readLine());
            arrDown[a]++;
            arrUp[b]++;
        }
        int[] ans = new int[h+1];
        for (int i=h-2;i>=1;i--) {
            arrDown[i] = arrDown[i+1] + arrDown[i];
            arrUp[i] = arrUp[i+1] + arrUp[i];
        }
        for (int i=1;i<h;i++) {
            ans[i] += arrDown[i];
            ans[h-i+1] += arrUp[i];
        }
        int min = Integer.MAX_VALUE;
        int count = 0;
        for (int i=1;i<h+1;i++) {
            if (ans[i] < min) {
                min = ans[i];
                count = 1;
            } else if (ans[i] == min) {
                count++;
            }
        }
        System.out.println(min + " "+count);
    }
}
