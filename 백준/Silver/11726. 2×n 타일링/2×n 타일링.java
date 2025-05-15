import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.*;

class Main
{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n+1];
        arr[1] = 1;
        if (n>1) {
            arr[2] = 2;
            for (int i=3;i<=n;i++) {
                arr[i] = arr[i-1] + arr[i-2];
                if (arr[i] > 10007) arr[i] = arr[i]%10007;
            }

        }
        System.out.println(arr[n]);

    }
}