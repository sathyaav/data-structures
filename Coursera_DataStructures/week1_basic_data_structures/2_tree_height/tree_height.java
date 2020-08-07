import java.util.*;
import java.io.*;

public class tree_height {
    class FastScanner {
		StringTokenizer tok = new StringTokenizer("");
		BufferedReader in;

		FastScanner() {
			in = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() throws IOException {
			while (!tok.hasMoreElements())
				tok = new StringTokenizer(in.readLine());
			return tok.nextToken();
		}
		int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	}

	public class TreeHeight {
		int n;
		int parent[];
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = in.nextInt();
			}
		}

		int computeHeight() {
			int maxHeight = 0;
			int[] memHeight = new int[n];
			for (int vertex = 0; vertex < n; vertex++) {
				memHeight[vertex] = findHeight(memHeight,vertex);
				maxHeight = Math.max(memHeight[vertex], maxHeight);
			}
			return maxHeight;
		}

		int findHeight(int[] memHeight, int vertex){
			if(parent[vertex] == -1)
				return 1;
			if(memHeight[vertex] != 0 )
				return memHeight[vertex];
			memHeight[vertex] = 1 + findHeight(memHeight, parent[vertex]);
			return memHeight[vertex];
		}
	}

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_height().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}
	public void run() throws IOException {
		TreeHeight tree = new TreeHeight();
		tree.read();
		System.out.println(tree.computeHeight());
	}
}
