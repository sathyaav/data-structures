import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class BuildHeap {
    private int[] data;
    private List<Swap> swaps;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new BuildHeap().solve();
    }

    private void readData() throws IOException {
        int n = in.nextInt();
        data = new int[n];
        for (int i = 0; i < n; ++i) {
          data[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        out.println(swaps.size());
        for (Swap swap : swaps) {
          out.println(swap.index1 + " " + swap.index2);
        }
    }

    private void generateSwaps() {
      swaps = new ArrayList<Swap>();
      int n = data.length;
      int startIdx = (int) Math.floor((double)n/2) ;
      for(int i=startIdx; i>= 1 ; i--){  
        shiftDown(i,n);
      }
      //testSolutionIsMinHeap();
    }

    private void shiftUp(int index){
	int parent = index/2;

	while( index > 1 && data[parent-1] > data[index-1] ){
		swapInt(parent-1 , index-1);
	        index = parent;
	        parent = index/2;
        }	
    }
    



    private void swapInt(int a ,int b){
	swaps.add(new Swap(a,b));
	int tmp = data[a];
	data[a] = data[b];
	data[b] = tmp;
    }

    // Finding the least of both children , swap it, shift down
    private void shiftDown(int index, int size){
       if(index <=0 )
	      return; 
       int least = index;
       int leftChild = 2*(index) ;
       int rightChild = 2*(index) + 1;
       

       if( leftChild <= size  && data[index-1] > data[leftChild-1] ) 
	  least = leftChild;
       
       if( rightChild <= size && data[index-1] > data[rightChild-1] && data[leftChild -1] > data[rightChild -1])
          least = rightChild;  

       if(least != index){
	  swapInt(index-1, least-1);
          shiftDown(least, size);
       }

    }
    
    private boolean testSolutionIsMinHeap(){
        int n= data.length;
	System.out.println("Min Heap: ");
	for(int i=0; i<n ; i++){
            System.out.print(data[i]+" ");
	}	    
        System.out.println();	
	for(int i=1; i<=n/2; i++){
	    int l = 2*i;
	    int r = 2*i + 1;
	    if(l<n && data[i-1] > data[l-1]){
               System.out.println("E: "+ (i-1) +" left: "+ (l-1));
	       return false;
	    }
	    if(r<n && data[i-1] > data[r-1]){
	 	System.out.println("E: "+(i-1)+ " right: " + (r-1));
		return false;
            }
        }
	return true;
    }
	      


    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        generateSwaps();
        writeResponse();
        out.close();
    }

    static class Swap {
        int index1;
        int index2;

        public Swap(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
