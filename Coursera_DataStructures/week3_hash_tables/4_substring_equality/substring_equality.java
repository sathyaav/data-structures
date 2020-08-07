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
			
			
			//return s.substring(a, a + l).equals(s.substring(b, b + l));
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
		private long[] hashes;
		private int randomNumber;
		
		public SubStringFinder(String s) {
			this.text    = s;
			this.prime1  = 1000000007;
			this.prime2  = 1000000009;
			this.randomNumber = 256;
			
			this.textLen = s.length();
			this.hashes  = new long[textLen+1];
			hashes = preComputeHashes(this.text, this.prime1);
		}
		
		public boolean isSubStringEqual(int a, int b, int l) {
			if ( a+l > textLen || b+l > textLen)
				return false;
			long xl = (NoPowMod(randomNumber, l, prime1));
			long hash1 = (hashes[a+l-1] - (xl*hashes[a])%prime1) % prime1;
			if(hash1 <0)
				hash1 = (((hash1 % prime1) + prime1) % prime1) ;
			long hash2 = (hashes[b+l-1] - (xl*hashes[b])%prime1) % prime1;
			if(hash2 <0)
				hash2 =  (((hash2 % prime1) + prime1) % prime1) ;
			if(hash1 == hash2)
				return true;
			
			return false;
			
		}
		
		private long[] preComputeHashes(String text, long prime) {
			long hash = 0;
			hashes[0] = 0;
			long a;
			
			for(int i=0; i< textLen; i++) {
				a = (long) (text.charAt(i) * (NoPowMod(randomNumber, textLen-i-1, prime)));
				if(a >= 0)
					hash = ((hash % prime) + (a % prime)) % prime ;
				else
					hash = ((hash % prime) + ((a % prime) + prime) % prime) % prime ;
				
				hashes[i+1] = hash;
			}
			
			return hashes;
			
		}
		
		private long hashFunc(String s, long prime, int randomNum) {
			long hash = 0;
			long a = 0;
			for (int i = 0; i < s.length() ; i++) {
				a = (long) (s.charAt(i) * (NoPowMod(randomNum, i, prime)));
				if(a >= 0)
					hash = ((hash % prime) + (a % prime)) % prime ;
				else
					hash = ((hash % prime) + ((a % prime) + prime) % prime) % prime ; // to avoid mod of negative numbers (a%p) -> ((a %p
																		// )+p )%p
				
			}
			return hash ;
		}
		
		
		private long NoPowMod( long x, long pow, long mod )
		{
			long a = x % mod;
			long t = 1;
			while( pow > 0 ){
				if( (pow & 1)==1 )
					t = (t * a) % mod;
				
				pow >>= 1;
				a = (a * a) % mod;
			}
			return(t);
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
