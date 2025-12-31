import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K; static int[] version = new int[1000010];
    static int[] max; static ArrayDeque<Node> dq;
    static int[] powers;
    static class Node {
        int num, v;
        public Node(int num, int v) {
            this.num = num;
            this.v = v;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.toString(N).length();
        max = new int[K+1];
        max[0] = N;
        if (M==1) {
            System.out.println(-1);
            return;
        }
        powers = new int[12];
        for (int i=0;i<12;i++) {
            powers[i] = (int) Math.pow(10,i);
        }
        dq = new ArrayDeque<>();
        dq.add(new Node(N,0));
        while (!dq.isEmpty()) {
            Node node = dq.poll();
            if (node.v==K) {
                break;
            }
            getNext(node.num, node.v+1);
        }
        System.out.println(max[K]==0 ? -1:max[K]);
    }
    static void getNext(int num, int v) {
        for (int i=0;i<M-1;i++) {
            for (int j=i+1;j<M;j++) {
                int next = swipeNum(num,i,j);
                if (next==-1) continue;
                if (version[next]==v) continue;
                if (v>=2) {
                    if (next<max[v-2]) continue;
                }
                version[next] = v;
                max[v] = Math.max(max[v],next);
                dq.add(new Node(next,v));
            }
        }
    }
    static int getIndex(int num, int loc) {
        return (num/powers[M-loc-1])%10;
    }
    static int getPow(int n, int pow) {
        return n * powers[M-pow-1];
    }
    static int swipeNum(int num, int i, int j) {
        int l = getIndex(num,i);
        int r = getIndex(num,j);
        if (i==0 && r==0) {
            return -1;
        }
        num = num - getPow(l,i) - getPow(r,j) + getPow(l,j) + getPow(r,i);
        return num;
    }
}
