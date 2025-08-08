import java.sql.SQLOutput;
import java.util.*;
import java.io.*;

public class Main {
    static int N, K; static Node[] jewel;
    static Map<Integer,Integer> map; static int[][] packs; static ArrayList<Integer>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        jewel = new Node[N];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            jewel[i] = new Node(weight,value);
        }
        map = new HashMap<>();
        for (int i=0;i<K;i++) {
            int weight = Integer.parseInt(br.readLine());
            if (map.get(weight)==null) {
                map.put(weight,1);
            } else {
                map.put(weight,map.get(weight)+1);
            }
        }
        packs = new int[map.keySet().size()+1][2];
        List<Integer> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);
        int loc = 0;
        for (int key : keys) {
            packs[loc][0] = key;
            packs[loc][1] = map.get(key);
            loc++;
        }
        packs[packs.length-1][0] = Integer.MAX_VALUE;
        list = new ArrayList[packs.length];
        for (int i=0;i<list.length;i++) {
            list[i] = new ArrayList<>();
        }
        for (int i=0;i<N;i++) {
            int now = binarySearch(0,packs.length,jewel[i].weight);
            list[now].add(jewel[i].value);
        }

        long ans = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        loc = 0;
        int count = 0;
//        System.out.println(Arrays.deepToString(packs));
//        System.out.println(Arrays.deepToString(list));
        while (loc<packs.length) {
            for (int i : list[loc]) {
                pq.add(i);
            }
//            System.out.println(pq);
            int len = packs[loc][1] + count;
            if (len>=pq.size()) {
                while (!pq.isEmpty()) {
                    int tmp = pq.poll();
                    ans += tmp;
//                    System.out.println(tmp);
                }
                count = len-packs[loc][1];
            } else {
                for (int i=0;i<packs[loc][1];i++) {
                    int tmp = pq.poll();
                    ans += tmp;
//                    System.out.println(tmp);
                }
            }
            loc++;
        }
        System.out.println(ans);

    }
    static int binarySearch(int sta, int las, int value) {
        int mid = (sta + las)/2;
//        System.out.println(value+", "+sta+", "+las+", "+mid+", "+packs[mid][0]);
        if (sta>=las) {
//            System.out.println(value+", "+sta+", "+las);
            return las;
        }
        if (value > packs[mid][0]) {
            return binarySearch(mid+1,las,value);
        } else if (value == packs[mid][0]) {
            return mid;
        } else {
            return binarySearch(sta,mid,value);
        }
    }

    static class Node implements Comparable<Node> {
        int weight, value;

        public Node() {}

        public Node(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        public int compareTo(Node o) {
            return o.value - this.value;
        }
    }
}
