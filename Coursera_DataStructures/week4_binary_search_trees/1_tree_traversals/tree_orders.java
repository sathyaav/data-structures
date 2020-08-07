import java.util.*;
import java.io.*;

public class tree_orders {
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

	public class TreeOrders {
		int n;
		int[] key, left, right;

		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			key = new int[n];
			left = new int[n];
			right = new int[n];
			for (int i = 0; i < n; i++) {
				key[i] = in.nextInt();
				left[i] = in.nextInt();
				right[i] = in.nextInt();
			}
		}

		List<Integer> inOrder() {
			ArrayList<Integer> result = new ArrayList<Integer>();
			get_inOrder(0, result);
			return result;
		}

		List<Integer> preOrder() {
			ArrayList<Integer> result = new ArrayList<Integer>();
			get_preOrder(0, result);
			return result;
		}

		List<Integer> postOrder() {
			ArrayList<Integer> result = new ArrayList<Integer>();
			get_postOrder(0, result);
			return result;
		}

		void get_inOrder(int idx, List<Integer> result) {
			if (idx != -1) {
				get_inOrder(left[idx], result);
				result.add(key[idx]);
				get_inOrder(right[idx], result);
				
			}
		}

		void get_preOrder(int idx, List<Integer> result) {
			if (idx != -1) {
				result.add(key[idx]);
				get_preOrder(left[idx], result);
				get_preOrder(right[idx], result);
				
			}
		}

		void get_postOrder(int idx, List<Integer> result) {
			if (idx != -1) {
				get_postOrder(left[idx], result);
				get_postOrder(right[idx], result);
				result.add(key[idx]);
				
			}
		}

	}

	static public void main(String[] args) throws IOException {
		new Thread(null, new Runnable() {
			public void run() {
				try {
					new tree_orders().run();
				} catch (IOException e) {
				}
			}
		}, "1", 1 << 26).start();
	}

	public void print(List<Integer> x) {
		for (Integer a : x) {
			System.out.print(a + " ");
		}
		System.out.println();
	}

	public void run() throws IOException {
		TreeOrders tree = new TreeOrders();
		tree.read();
		print(tree.inOrder());
		print(tree.preOrder());
		print(tree.postOrder());
	}
}
