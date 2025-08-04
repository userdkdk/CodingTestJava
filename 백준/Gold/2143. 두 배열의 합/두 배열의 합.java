import java.util.*;
import java.io.*;

public class Main {
    static int N, M, T; static Map<Integer, Integer> map1, map2; static int[] arr1, arr2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());
        arr1 = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());
        arr2 = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<M;i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        map1 = new HashMap<>();
        map2 = new HashMap<>();

        putMap(N, arr1, map1);
        putMap(M, arr2, map2);
        long ans = 0;
        for (int i: map1.keySet()) {
            int num = T - i;
            if (map2.get(num)!=null) {
                ans += (long) map1.get(i) * map2.get(num);
            }
        }
        System.out.println(ans);
    }
    static void putMap(int num, int[] arr, Map<Integer,Integer> map) {
        for (int i=0;i<num;i++) {
            int sum = 0;
            for (int j=i;j<num;j++) {
                sum += arr[j];
                if (map.get(sum)==null) {
                    map.put(sum,1);
                } else {
                    map.put(sum,map.get(sum)+1);
                }
            }
        }
    }
}
