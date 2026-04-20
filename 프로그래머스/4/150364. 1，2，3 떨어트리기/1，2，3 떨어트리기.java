import java.io.*;
import java.util.*;

class Solution {
    static int N; static int[] dirs; static ArrayList<Integer>[] adjs;
    static ArrayList<Integer> orders;
    public int[] solution(int[][] edges, int[] target) {
        int[] answer = {};
        init(edges);
        getOrders();
        answer = getAnswers(target);
        System.out.println(orders);
        
        return answer;
    }
    static int[] getAnswers(int[] target) {
        int count = 0;
        int[] visited = new int[N+1];
        boolean[] checked = new boolean[N+1];
        for (int i=0;i<target.length;i++) {
            if (target[i]>0) count++;
        }
        int idx = 0;
        while (count>0) {
            int preIdx = idx;
            idx = (idx+1)%orders.size();
            int cur = orders.get(preIdx);
            visited[cur]++;
            int c = visited[cur];
            if (c>target[cur-1]) {
                return new int[]{-1};
            }
            if (c*3>=target[cur-1] && !checked[cur]) {
                checked[cur] = true;
                count--;
            }
        }
        
        return getResult(visited,target);
    }
    
    static int[] getResult(int[] visited, int[] target) {
        ArrayList<Integer> arr = new ArrayList<>();
        int count = 0;
        for (int i=0;i<=N;i++) {
            if (visited[i]>0) count++;
        }
        int idx = 0;
        while (count>0) {
            int preIdx = idx;
            idx = (idx+1)%orders.size();
            int cur = orders.get(preIdx);
            if (target[cur-1] <= (visited[cur]-1)*3) {
                target[cur-1]--;
                arr.add(1);
            } else {
                int q = target[cur-1] - (visited[cur]-1)*3;
                target[cur-1] -= q;
                arr.add(q);
            }
            visited[cur]--;
            if (visited[cur]==0) count--;
        }
        return arr.stream().mapToInt(i->i).toArray();
    }
    
    // 방문 순서 구하기
    static void getOrders() {
        orders = new ArrayList<>();
        dirs = new int[N+1];
        while (true) {
            int next = findOrder(1);
            orders.add(next);
            
            // escape
            if (isInit()) {
                break;
            }
        }
    }
    static int findOrder(int cur) {
        if (adjs[cur].size()==0) {
            return cur;
        }
        int nowLoc = dirs[cur];
        dirs[cur] = (dirs[cur]+1) % (adjs[cur].size());
        return findOrder(adjs[cur].get(nowLoc));
    }
    
    static boolean isInit() {
        for (int i=1;i<=N;i++) {
            if (dirs[i]!=0) {
                return false;
            }
        }
        return true;
    }
    
    static void init(int[][] edges) {
        N = edges.length + 1;
        adjs = new ArrayList[N+1];
        for (int i=1;i<=N;i++) {
            adjs[i] = new ArrayList<>();
        }
        for (int i=0;i<edges.length;i++) {
            int[] cur = edges[i];
            adjs[cur[0]].add(cur[1]);
        }
        for (int i=1;i<=N;i++) {
            Collections.sort(adjs[i]);
        }
    }
}