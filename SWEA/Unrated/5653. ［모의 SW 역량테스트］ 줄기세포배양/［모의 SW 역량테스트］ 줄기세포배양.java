import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;


public class Solution {
	static int n, m, k; static int[][][] arr;
	static int[] dr = {1,-1,0,0}; static int[] dc = {0,0,1,-1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			arr = new int[500][500][2];
			for (int i=0;i<500;i++) {
				for (int j=0;j<500;j++) {
					arr[i][j][1] = -1;
				}
			}
			Queue<Node> q = new LinkedList<>();
			for (int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0;j<m;j++) {
					int tmp = Integer.parseInt(st.nextToken());
					if (tmp != 0) {
						arr[i+200][j+200][0] = tmp;
						arr[i+200][j+200][1] = 0;
						Node node = new Node(i+200,j+200,tmp,2*tmp);
						q.add(node);
					}
				}
			}
			for (int time=1;time<=k;time++) {
				int tmp_size = q.size();
				for (int co=0;co<tmp_size;co++) {
					Node node = q.poll();
					node.nowLife -=1;
					if (node.nowLife > node.life) {
						q.add(node);
						continue;
					}
					int x = node.x;
					int y = node.y;
					node.isActivate = true;
					if (node.life !=arr[x][y][0]) continue;
					if (node.nowLife == node.life-1) {
						for (int d=0;d<4;d++) {
							int new_x = x+dr[d];
							int new_y = y+dc[d];
							if (arr[new_x][new_y][1]==-1 || arr[new_x][new_y][1]==time) {
								if (arr[new_x][new_y][0]>=node.life) continue;
								arr[new_x][new_y][0] = node.life;
								arr[new_x][new_y][1] = time;
								Node new_node = new Node(new_x, new_y, node.life, 2*node.life);
								q.add(new_node);
							}
						}
					}
					if (node.nowLife > 0) {
						q.add(node);
					}
				}
//				System.out.println("#"+time+" "+q.size());
			}
			int tmp_size = q.size();
			boolean[][] check = new boolean[500][500];
			int ans = 0;
			for (int ts=0;ts<tmp_size;ts++) {
				Node node = q.poll();
				int x = node.x;
				int y = node.y;
				if (!check[x][y]) {
					check[x][y] = true;
					ans++;
				}
			}
			System.out.println("#"+tc+" "+ans);
		}
	}
	static class Node {
		int x,y,life,nowLife;
		boolean isActivate;
		
		Node() {}
		
		public Node(int x, int y, int life, int nowLife) {
			this.x = x;
			this.y = y;
			this.life = life;
			this.nowLife = nowLife;
			this.isActivate = false;
		}

		@Override
		public String toString() {
			return "x=" + (x-200) + ", y=" + (y-200) + ", life=" + life+"/ ";
		}
		
	}
}