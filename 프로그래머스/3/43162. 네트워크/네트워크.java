import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visit = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
		for (int i=0;i<n;i++) {
			if (visit[i]) continue;
			q.add(i);
			visit[i] = true;
			answer++;
			while (!q.isEmpty()) {
				int tmp = q.poll();
				for (int j=0;j<n;j++) {
					if (!visit[j] && computers[tmp][j]==1) {
						q.add(j);
						visit[j] = true;
					}
				}
			}
		}
        
        return answer;
    }
}