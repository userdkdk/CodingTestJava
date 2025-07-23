import java.security.spec.RSAOtherPrimeInfo;
import java.sql.SQLOutput;
import java.util.*;
import java.io.*;

public class Main {
    static int n, m, w; static Map<int[], Integer> map;
    static int max = 50000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            map = new HashMap<>();

            for (int i=0;i<m;i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int l = Integer.parseInt(st.nextToken());
                int[] pos = new int[]{a,b};
                if (map.containsKey(pos)) {
                    map.put(pos,Math.min(map.get(pos),l));
                } else {
                    map.put(pos,l);
                }
                int[] neg = new int[]{b,a};
                if (map.containsKey(neg)) {
                    map.put(neg,Math.min(map.get(neg),l));
                } else {
                    map.put(neg,l);
                }
            }

            for (int i=0;i<w;i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int l = Integer.parseInt(st.nextToken());
                int[] pos = new int[]{a,b};
                if (map.containsKey(pos)) {
                    map.put(pos,Math.min(map.get(pos),-l));
                } else {
                    map.put(pos,-l);
                }
            }
            boolean flag = check();
            if (flag) System.out.println("YES");
            else System.out.println("NO");
        }
    }
    static boolean check() {
        int[] arr = new int[n+1];
        Arrays.fill(arr,max);
        arr[1] = 0;
        for (int k=0;k<n;k++) {
            for (int[] now : map.keySet()) {
                int a = now[0];
                int b = now[1];
                int l = map.get(now);
                if (arr[b]>arr[a]+l) {
                    arr[b] = arr[a]+l;
                    if (k==n-1) {
                        return true;
                    }
                }

            }
        }
        return false;
    }
}
