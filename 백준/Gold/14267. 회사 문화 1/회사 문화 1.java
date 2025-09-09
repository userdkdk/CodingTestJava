import java.util.*;
import java.io.*;

public class Main {
    static int N, M; static ArrayList<Integer>[] adj; static int[] praise, persons;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        persons = new int[N+1];
        praise = new int[N+1];
        adj = new ArrayList[N+1];
        for (int i=1;i<=N;i++) adj[i] = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        int tmp = Integer.parseInt(st.nextToken());
        for (int i=2;i<=N;i++) {
            int p = Integer.parseInt(st.nextToken());
            adj[p].add(i);
        }
        for (int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int now = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            praise[now] +=value;
        }
        getPersons(1,0);
        StringBuilder sb = new StringBuilder();
        for (int i=1;i<=N;i++) {
            sb.append(persons[i]).append(" ");
        }
        System.out.println(sb);
    }
    static void getPersons(int loc, int value) {
        int nowValue = value + praise[loc];
        persons[loc] = nowValue;
        for (int next : adj[loc]) {
            getPersons(next,nowValue);
        }
    }
}
