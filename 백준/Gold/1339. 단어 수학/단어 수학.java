import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        char[] charArr = new char[10];
        for (int i=0;i<10;i++) {
            charArr[i] = '-';
        }
        int[] intArr = new int[10];
        for (int t=0;t<T;t++) {
            char[] tmpArr = br.readLine().toCharArray();
            for (int i=0;i<tmpArr.length;i++) {
                for (int j=0;j<10;j++) {
                    if (charArr[j]=='-') {
                        charArr[j] = tmpArr[i];
                        intArr[j] += (int) Math.pow(10,tmpArr.length-i-1);
                        break;
                    }
                    else if (charArr[j]==tmpArr[i]) {
                        intArr[j] += (int) Math.pow(10,tmpArr.length-i-1);
                        break;
                    }
                }
            }
        }
        Arrays.sort(intArr);
        int ans = 0;
        for (int i=9;i>=0;i--) {
            ans += intArr[i] * i;
        }
        System.out.println(ans);

    }
}