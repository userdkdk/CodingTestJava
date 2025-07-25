import java.util.*;
import java.io.*;

public class Main {
    static int n; static char[] arr; static Map<Character,Integer> map;
    static Stack<Character> s; static StringBuilder ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        arr = br.readLine().toCharArray();
        n = arr.length;
        initialize();
        s = new Stack<>();
        ans = new StringBuilder();

        for (int i=0;i<n;i++) {
            if (arr[i]=='(') s.push('(');
            else if (arr[i]==')') {
                while(s.peek()!='(') {
                    ans.append(s.pop());
                }
                s.pop();
            }
            else if (map.get(arr[i])==null) ans.append(arr[i]);
            else if (!s.isEmpty() && map.get(arr[i])>map.get(s.peek())) s.push(arr[i]);
            else {
                while(!s.isEmpty() && map.get(arr[i])<=map.get(s.peek())) {
                    ans.append(s.pop());
                }
                s.push(arr[i]);
            }
        }
        while(!s.isEmpty()) {
            ans.append(s.pop());
        }
        System.out.println(ans);
    }
    static void initialize() {
        map = new HashMap<>();
        map.put('(',0);
        map.put(')',0);
        map.put('+',1);
        map.put('-',1);
        map.put('*',2);
        map.put('/',2);
    }
}
