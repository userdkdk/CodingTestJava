import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main
{
    static int n, m; static int[] count_arr; static int[] crain;
    static int[] crain_num;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        // 개수 세기위한 map 선언
        Map<Integer, Integer> map = new HashMap<>();
        map.put(Integer.MAX_VALUE,0);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            if (map.containsKey(tmp)) {
                map.put(tmp, map.get(tmp)+1);
            } else {
                map.put(tmp,1);
            }
        }

        // 크레인 무게와 개수 구하기
        List<Integer> keyList = new ArrayList<>(map.keySet());
        Collections.sort(keyList);
        crain = keyList.stream().mapToInt(i -> i).toArray();
        int len = crain.length;
        crain_num = new int[len];
        count_arr = new int[len];
        for (int i=0;i<len;i++) {
            crain_num[i] = map.get(crain[i]);
        }

        // 각 범위개수 구하기
        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int weight = Integer.parseInt(st.nextToken());
            int k = 0;
            while (crain[k]<weight) {
                k++;
            }
            count_arr[k]++;
        }

        // 정답 구하기
        int ans = 0;
        if (count_arr[len-1]>0) {
            ans = -1;
        } else {
            int amount = m;
            while (amount>0) {
                int stack = 0;
                for (int i=len-1;i>=0;i--) {
                    if (stack == 0) {
                        if (count_arr[i]>crain_num[i]) {
                            count_arr[i] -= crain_num[i];
                            amount -= (crain_num[i]);
                        } else if(count_arr[i]>0) {
                            stack += crain_num[i]-count_arr[i];
                            amount -= (count_arr[i]);
                            count_arr[i] = 0;
                        }else {
                            stack+=crain_num[i];
                        }
                    } else {
                        stack += crain_num[i];
                        if (count_arr[i]>stack) {
                            count_arr[i] -= stack;
                            amount -= stack;
                            stack = 0;
                        } else if (count_arr[i]>0) {
                            stack -= count_arr[i];
                            amount -= count_arr[i];
                            count_arr[i] = 0;
                        }
                    }
                }
                ans++;
            }
        }
        System.out.println(ans);
    }
}