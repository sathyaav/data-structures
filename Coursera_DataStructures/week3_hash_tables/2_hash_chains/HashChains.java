import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class HashChains {

	private FastScanner in;
	private PrintWriter out;
	// store all strings in one list
	// private List<String> elems;
	private ArrayList<ArrayList<String>> elems;
	// for hash function
	private int bucketCount;
	private int prime = 1000000007;
	private int multiplier = 263;

	public static void main(String[] args) throws IOException {
		new HashChains().processQueries();
	}

	private long findPowerModPrime(int pow) {
		long val = 1;
		for(int i=0; i< pow; i++) {
			val = (val * multiplier) % prime;
		}
		return val;
	}
	private int hashFunc(String s) {
		long hash = 0;
		long a = 0;
		for (int i = 0; i < s.length() ; i++) {
			a = (long) (s.charAt(i) * (findPowerModPrime(i)));
			if(a >= 0)
				hash = ((hash % prime) + (a % prime)) % prime ;
			else
				hash = ((hash % prime) + ((a % prime) + prime) % prime) % prime ; // to avoid mod of negative numbers (a%p) -> ((a %p
																	// )+p )%p
			
		}
		return (int) hash % bucketCount;
	}

	private Query readQuery() throws IOException {
		String type = in.next();
		if (!type.equals("check")) {
			String s = in.next();
			return new Query(type, s);
		} else {
			int ind = in.nextInt();
			return new Query(type, ind);
		}
	}

	private void writeSearchResult(boolean wasFound) {
		out.println(wasFound ? "yes" : "no");
	}

	private void processQuery(Query query) {
		ArrayList<String> existingWordList = null;
		int idx;
		switch (query.type) {
		case "add":
			idx = hashFunc(query.s);
			existingWordList = elems.get(idx);
			if (existingWordList == null) {
				existingWordList = new ArrayList<String>();
				existingWordList.add(0, query.s);
				elems.set(idx, existingWordList);

			} else {
				if (!existingWordList.contains(query.s))
					existingWordList.add(0, query.s);
			}
			break;
		case "del":

			idx = hashFunc(query.s);
			if (elems.get(idx) != null) {
				existingWordList = elems.get(idx);
				if (existingWordList.contains(query.s))
					existingWordList.remove(query.s);
			}

			break;
		case "find":
			idx = hashFunc(query.s);
			if (elems.get(idx) != null) {
				existingWordList = elems.get(idx);
				writeSearchResult(existingWordList.contains(query.s));
			} else
				writeSearchResult(false);

			break;
		case "check":

			idx = query.ind;
			if (elems.get(idx) != null) {
				ArrayList<String> list = elems.get(idx);
				for (int i = 0; i < list.size(); i++)
					out.print(list.get(i) + " ");
			}

			out.println();
			break;
		default:
			throw new RuntimeException("Unknown query: " + query.type);
		}
	}

	public void processQueries() throws IOException {
		// elems = new ArrayList<ArrayList<String>>();
		in = new FastScanner();
		out = new PrintWriter(new BufferedOutputStream(System.out));
		bucketCount = in.nextInt();
		elems = new ArrayList<ArrayList<String>>(bucketCount);
		for (int i = 0; i < bucketCount; i++)
			elems.add(i, null);
		int queryCount = in.nextInt();
		for (int i = 0; i < queryCount; ++i) {
			processQuery(readQuery());
		}
		out.close();
	}

	static class Query {
		String type;
		String s;
		int ind;

		public Query(String type, String s) {
			this.type = type;
			this.s = s;
		}

		public Query(String type, int ind) {
			this.type = type;
			this.ind = ind;
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
