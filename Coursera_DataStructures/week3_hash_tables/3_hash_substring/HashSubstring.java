import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class HashSubstring {

	private static FastScanner in;
	private static PrintWriter out;

	public static void main(String[] args) throws IOException {
		in = new FastScanner();
		out = new PrintWriter(new BufferedOutputStream(System.out));
		printOccurrences(getOccurrences(readInput()));
		out.close();
	}

	private static Data readInput() throws IOException {
		String pattern = in.next();
		String text = in.next();
		return new Data(pattern, text);
	}

	private static void printOccurrences(List<Integer> ans) throws IOException {
		for (Integer cur : ans) {
			out.print(cur);
			out.print(" ");
		}
	}

	private static List<Integer> getOccurrences(Data input) {
		RabinKarp rk = new RabinKarp(input.pattern);
		return rk.search(input.text);
		
	}
	
	
	static class RabinKarp{
		private String pat;
		private long patHash;
		private int patLen ;  
		private long prime; // large prime number
		private int alpSize; // 256 default
		private long prePatCalc;
		
		public RabinKarp(String pattern) {
			pat = pattern;
			patLen = pat.length();
			alpSize = 256;  
			prime = 1000000007;
			patHash = hashFunc(pat);  // polyhash 
			
			// precompute prePatCalc for use in removing leading digit
			prePatCalc = 1l;
			for(int i=0; i< patLen; i++)
				prePatCalc = (prePatCalc * alpSize) % prime;
			
			
		}
		
		public ArrayList<Integer> search(String txt) {
			int txtLen = txt.length();
			if( txtLen < patLen) 
				return null;
	        
			ArrayList<Integer> result = new ArrayList<Integer>();
			long[] preComputedHashes = preComputeHashes(txt, txtLen);
			for( int i = 0 ; i <= (txtLen- patLen); i++) {
				if(patHash != preComputedHashes[i])
					continue;
				//System.out.println("i: "+i+" hashes: "+ patHash+" ");
				if(check(txt, i, txtLen))
					result.add(i);
				
				
				
			}
			
			return result;
			
			
			
		}
		
		private boolean check(String txt, int i, int txtLen) {
			String subString;
			 if(txtLen- i == patLen)
				subString = txt.substring(i);
			else
			    subString = txt.substring(i, i+patLen);
			return pat.equals(subString);
			/*
	        for (int j = 0; j < patLen; j++) 
	            if (pat.charAt(j) != txt.charAt(i + j)) 
	                return false; 
	        return true;*/
	    }
		
		private long[] preComputeHashes(String txt, int txtLen) {
			long[] hashes = new long[txtLen - patLen + 1];
			
			String subText = txt.substring(txtLen - patLen, txtLen); 
			hashes[txtLen - patLen] = hashFunc(subText);
			for(int i = (txtLen - patLen) - 1 ; i >= 0 ; i-- ) {
				long hash1 =  (( alpSize * hashes[i+1] ) % prime + txt.charAt(i)) % prime;
				long hash2 =  (prePatCalc * txt.charAt(i+ patLen)) % prime;
				hashes[i]  =  (hash1 - hash2) % prime; 
				if(hashes[i] < 0) {
					hashes[i]  =  ((hash1 - hash2) % prime + prime ) % prime;
				}
			}
			
			return hashes;
		}

		private long NoPowMod( long x, long pow, long mod )
		{
			long a = x % mod;
			long t = 1;
			while( pow > 0 )
			{
				// Y is odd
				if( (pow & 1)==1 )
				{
					t = (t * a) % mod;
				}
				pow >>= 1;
				a = (a * a) % mod;
			}
			return(t);
		}
		
		
		private long hashFunc(String s) {
			long hash = 0;
			long a = 0;
			for (int i = 0; i < s.length() ; i++) {
				a = (long) (s.charAt(i) * (NoPowMod(alpSize, i, prime)));
				if(a >= 0)
					hash = ((hash % prime) + (a % prime)) % prime ;
				else
					hash = ((hash % prime) + ((a % prime) + prime) % prime) % prime ; // to avoid mod of negative numbers (a%p) -> ((a %p
																		// )+p )%p
				
			}
			return hash ;
		}
		
	}
	
	static class Data {
		String pattern;
		String text;

		public Data(String pattern, String text) {
			this.pattern = pattern;
			this.text = text;
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
