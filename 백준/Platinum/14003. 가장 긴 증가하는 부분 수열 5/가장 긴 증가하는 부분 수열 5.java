import java.io.*;
import java.util.*;

public class Main {
    static int N, len; static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N+10];
        int[] locs = new int[N];
        int[] now = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            int cur = Integer.parseInt(st.nextToken());
            int loc = binSearch(0,len,cur);
            arr[loc] = cur;
            locs[i] = loc;
            now[i] = cur;
            if (loc==len) {
                len++;
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        int tmp = len-1;
        for (int i=N-1;i>=0;i--) {
            if (locs[i]==tmp) {
                list.add(now[i]);
                tmp--;
            }
        }
        System.out.println(len);
        for (int i=len-1;i>=0;i--) {
            System.out.print(list.get(i)+" ");
        }

    }
    static int binSearch(int sta, int end, int value) {
        int mid = (sta + end)/2;
        if (sta>=end) {
            return mid;
        }
        if (arr[mid]==value) {
            return mid;
        }
        if (arr[mid]>value) {
            return binSearch(sta,mid,value);
        }
        return binSearch(mid+1,end,value);
    }
}
