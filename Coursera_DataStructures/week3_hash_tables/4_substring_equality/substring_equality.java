import java.util.*;
import java.io.*;

public class substring_equality {
	public class Solver {
		private String s;
		private SubStringFinder finder;
		
		public Solver(String s) {
			this.s = s;
			this.finder = new SubStringFinder(s);
		}
		
		public boolean ask(int a, int b, int l) {
		 	return finder.isSubStringEqual(a,b,l);
		}
	}

	public void run() throws IOException {
		FastScanner in = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		String s = in.next();
		int q = in.nextInt();
		Solver solver = new Solver(s);
		for (int i = 0; i < q; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int l = in.nextInt();
			out.println(solver.ask(a, b, l) ? "Yes" : "No");
		}
		out.close();
	}

	static public void main(String[] args) throws IOException {
	    new substring_equality().run();
	}
	
	static class SubStringFinder{
		private String text;
		private int textLen;
		private long prime1;
		private long prime2;
		private long[] hashes_p1;
		private long[] powers_p1;
		private long[] hashes_p2;
		private long[] powers_p2;
		private int randomNumber;
		
		public SubStringFinder(String s) {
			this.text    = s;
			this.prime1  = 1000000007;
			this.prime2  = 1000000009;
			this.randomNumber = 13;
			
			this.textLen = s.length();
			this.hashes_p1  = new long[textLen+1];
			this.powers_p1  = new long[textLen+1];
			this.hashes_p2  = new long[textLen+1];
			this.powers_p2  = new long[textLen+1];
			calculate_powers(this.randomNumber,this.textLen,  this.prime1, this.powers_p1);
			calculate_powers(this.randomNumber,this.textLen,  this.prime2, this.powers_p2);
			
			preComputeHashes(this.text);
		}
		
		
		
		public boolean isSubStringEqual(int a, int b, int l) {
			if ( a+l > textLen || b+l > textLen)
				return false;
			
			long hash1 = getSubStringHash(hashes_p1, a,l, prime1, powers_p1); 
			
			long hash2 = getSubStringHash(hashes_p1, b,l, prime1, powers_p1);
			
			// if both hash is same, then do find hash with second hash function (different prime) and check for collision.
			if(hash1 == hash2) {
				
				hash1 = getSubStringHash(hashes_p2, a,l, prime2, powers_p2); 
				
				hash2 = getSubStringHash(hashes_p2, b,l, prime2, powers_p2); 
				
				if(hash1 == hash2)
					return true;
				
			}
			
			return false;
			
		}
		
		private long getSubStringHash(long[] hashes, int from, int len, long prime, long[] powers) {
			long hash = (hashes[from+len] - (hashes[from])%prime) % prime;
			if(hash <0)    // if hash is negative, convert to positive
				hash = (((hash % prime) + prime) % prime) ;
			
			// convert all hashes to the same degree
			hash = (hash * powers[textLen-from]) % prime;
			return hash;
			
		}
		
		
		private void  preComputeHashes(String text) {
			long hash_p1 = 0;
			long hash_p2 = 0;
		
			hashes_p1[0] = 0;
			hashes_p2[0] = 0;
			
			long a, b;
			
			for(int i=0; i< textLen; i++) {
				a = (long) ((text.charAt(i)-'a'+1) * (powers_p1[i]));
				if(a >= 0)
					hash_p1 = ((hash_p1 % prime1) + (a % prime1)) % prime1 ;
				else
					hash_p1 = ((hash_p1 % prime1) + ((a % prime1) + prime1) % prime1) % prime1 ;
				
				hashes_p1[i+1] = hash_p1;
				
				
				
				b = (long) ((text.charAt(i)-'a'+1) * (powers_p2[i]));
				if(b >= 0)
					hash_p2 = ((hash_p2 % prime2) + (b % prime2)) % prime2 ;
				else
					hash_p2 = ((hash_p2 % prime2) + ((b % prime2) + prime2) % prime2) % prime2 ;
				
				hashes_p2[i+1] = hash_p2;
				
			}
			
		}
		
		private void calculate_powers( long x, long pow, long mod, long[] powers )
		{
			long val=1;
			powers[0] = 1;
			for(int j =1 ; j <= pow; j++) {
				val = (long) (powers[j-1] * x) % mod;
				if(val <0);
					val = (mod + val) % mod;
				powers[j] = val;
			}
		}
		
		
	}

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
}
