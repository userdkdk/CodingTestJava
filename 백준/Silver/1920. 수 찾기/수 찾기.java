import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.*;

class Main
{
    static ArrayList<Integer> arr; static int n; static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
            flag = false;
            int num = Integer.parseInt(st.nextToken());
            findNum(0,n-1,num);
            if (flag) System.out.println(1);
            else System.out.println(0);
        }
    }
    static void findNum(int sta, int las, int num) {
        int mid = (sta+las)/2;
        if (sta >= las) {
            if (num == arr.get(mid)) flag = true;
            return;
        }
        if (num > arr.get(mid)) {
            findNum(mid+1, las, num);
        } else {
            findNum(sta, mid, num);
        }

    }
}