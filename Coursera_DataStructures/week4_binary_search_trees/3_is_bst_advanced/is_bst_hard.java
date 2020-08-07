import java.util.*;
import java.io.*;

public class is_bst_hard {
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

    public class IsBST {
        class Node {
            int key;
            int left;
            int right;
            
            Node(int key, int left, int right) {
                this.left = left;
                this.right = right;
                this.key = key;
            }
        }

        int nodes;
        Node[] tree;
        Integer previousNode;
        boolean startUpdatingPreviousNode;
        boolean isLeft;
        
        void read() throws IOException {
            FastScanner in = new FastScanner();
            nodes = in.nextInt();
            tree = new Node[nodes];
            for (int i = 0; i < nodes; i++) {
                tree[i] = new Node(in.nextInt(), in.nextInt(), in.nextInt());
            }
        }

        boolean isBinarySearchTree() {
        	previousNode = -1;
        	startUpdatingPreviousNode = false;
        	if(nodes == 0)
        		return true;
        	return isBSTusingFind();
        }
        
        
        
        private boolean isBST_usingInOrderTraversalWithFind(int idx, int parent) {
        	if(idx != -1) {
        		if(!isBST_usingInOrderTraversalWithFind(tree[idx].left,idx))
					return false;
				if(previousNode != -1 && tree[idx].key < tree[previousNode].key)
					return false;
				if(previousNode != -1 && tree[idx].key == tree[previousNode].key) 
					if(tree[idx].left != -1 && find(tree[idx].left, tree[idx].key)!= -1)
						return false;
			
				previousNode = idx;
				return isBST_usingInOrderTraversalWithFind(tree[idx].right,  idx);
				
			}
			return true;
		}
        
        private boolean isBSTusingFind() {
        	
        	return isBST_usingInOrderTraversalWithFind(0, -1);
        	
        }
        
        private int find(int idx, int value) {
        	
        	if(tree[idx].key == value)
        		return idx;
        	if(tree[idx].key > value && tree[idx].left != -1)
        		return find(tree[idx].left, value);
        	if(tree[idx].key < value && tree[idx].right != -1)
        		return find(tree[idx].right, value);
        	
        	return -1;
        	
        	
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new is_bst_hard().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }
    public void run() throws IOException {
        IsBST tree = new IsBST();
        tree.read();
        if (tree.isBinarySearchTree()) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT");
        }
    }
}
