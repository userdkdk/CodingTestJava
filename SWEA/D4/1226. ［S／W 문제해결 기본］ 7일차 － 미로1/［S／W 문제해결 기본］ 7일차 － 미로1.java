
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	static int[][] arr; static int[] sta, las; static boolean[][] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc=1;tc<=10;tc++) {
			String tmp = br.readLine();
			arr = new int[16][16];
			visit = new boolean[16][16];
			sta = new int[2];
			las = new int[2];
			for (int i=0;i<16;i++) {
				char[] tmp_arr = br.readLine().toCharArray();
				for (int j=0;j<16;j++) {
					arr[i][j] = Character.getNumericValue(tmp_arr[j]);
					if (arr[i][j]==2) {
						sta[0] = i;
						sta[1] = j;
					} else if (arr[i][j]==3) {
						las[0] = i;
						las[1] = j;
					}
				}				
			}
			if (bfs()) {
				System.out.println("#"+tc+" 1");
			} else {
				System.out.println("#"+tc+" 0");
			}
		}
	}
	static int[] dr = {1,-1,0,0}; static int[] dc = {0,0,1,-1};
	static boolean bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.add(sta);
		visit[sta[0]][sta[1]] = true;
		while (!q.isEmpty()) {
			int[] now = q.poll();
			if (now[0] == las[0] && now[1] == las[1]) {
				return true;
			}
			
			for (int d=0;d<4;d++) {
				int new_r = now[0] + dr[d];
				int new_c = now[1] + dc[d];
				if (visitcheck(new_r,new_c)) continue;
				q.add(new int[] {new_r,new_c});
				visit[new_r][new_c] = true;
			}
		}
		
		return false;
	}
	static boolean visitcheck(int i, int j) {
		if (i<0 || i>=16 || j<0 || j>=16 || visit[i][j] || arr[i][j]==1) {
			return true;
		}
		return false;
	}
}