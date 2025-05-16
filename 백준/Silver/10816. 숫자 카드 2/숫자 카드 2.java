import javax.swing.*;
import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

class Main
{
    static ArrayList<Integer> arr; static int n, min_num, max_num; static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        arr = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(arr);
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<m; i++) {
            count = 0;
            min_num = 0;
            max_num = 0;
            int num = Integer.parseInt(st.nextToken());
            findLowerbound(0,n-1,num);
            findUpperbound(0,n-1,num);
            count = max_num-min_num+1;
            if (num != arr.get(max_num)) count--;
            bw.write(count + " ");
        }
        bw.flush();
        bw.close();
    }
    static void findLowerbound(int sta, int las, int num) {
        int mid = (sta+las)/2;
        if (sta >= las) {
            min_num = las;
            return;
        }
        if (num > arr.get(mid)) {
            findLowerbound(mid+1, las, num);
        } else {
            findLowerbound(sta, mid, num);
        }
    }
    static void findUpperbound(int sta, int las, int num) {
        int mid = (sta+las)/2;
        if (sta >= las) {
            max_num = sta;
            return;
        }
        if (num >= arr.get(mid)) {
            findUpperbound(mid+1, las, num);
        } else {
            findUpperbound(sta, mid, num);
        }
    }
}