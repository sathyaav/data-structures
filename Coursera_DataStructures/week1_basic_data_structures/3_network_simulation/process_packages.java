import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

class Request {
    public Request(int arrival_time, int process_time) {
        this.arrival_time = arrival_time;
        this.process_time = process_time;
    }

    public int arrival_time;
    public int process_time;
}

class Response {
    public Response(boolean dropped, int start_time) {
        this.dropped = dropped;
        this.start_time = start_time;
    }

    public boolean dropped;
    public int start_time;
}

class Buffer {
    public Buffer(int size) {
        this.size_ = size;
        this.finish_time_ = new ArrayList<Integer>();
    }

    public Response Process(Request request) {
   	Iterator<Integer> it = finish_time_.iterator();
	int lastJobEndTime = 0;
	int current_jobs_in_queue = finish_time_.size();
	if(current_jobs_in_queue > 0)
	      lastJobEndTime = finish_time_.get(current_jobs_in_queue - 1);
       
	int i=0;;
	for( i = current_jobs_in_queue-1; i>0 ; i--)
		if( finish_time_[i] <= request.arrival_time)
			break;
	finish_time_.removeRange(0,i);
	/*while(it.hasNext()){
		if(it.next() <= request.arrival_time){
			it.remove();
		}
	}*/
	current_jobs_in_queue = finish_time_.size();
	if(size_ > current_jobs_in_queue){
		finish_time_.add(lastJobEndTime+request.process_time);
		//lastJobEndTime += request.process_time;
		if(lastJobEndTime > request.arrival_time )
			return new Response(false, lastJobEndTime );
		else
			return new Response(false, request.arrival_time);
	}
	else{
		return new Response(true, -1);
	}
    }
    private int lastJobEndTime;
    private int size_;
    private ArrayList<Integer> finish_time_;
}

class process_packages {
    private static ArrayList<Request> ReadQueries(Scanner scanner) throws IOException {
        int requests_count = scanner.nextInt();
        ArrayList<Request> requests = new ArrayList<Request>();
        for (int i = 0; i < requests_count; ++i) {
            int arrival_time = scanner.nextInt();
            int process_time = scanner.nextInt();
            requests.add(new Request(arrival_time, process_time));
        }
        return requests;
    }

    private static ArrayList<Response> ProcessRequests(ArrayList<Request> requests, Buffer buffer) {
        ArrayList<Response> responses = new ArrayList<Response>();
        for (int i = 0; i < requests.size(); ++i) {
            responses.add(buffer.Process(requests.get(i)));
        }
        return responses;
    }

    private static void PrintResponses(ArrayList<Response> responses) {
        for (int i = 0; i < responses.size(); ++i) {
            Response response = responses.get(i);
            if (response.dropped) {
                System.out.println(-1);
            } else {
                System.out.println(response.start_time);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int buffer_max_size = scanner.nextInt();
        Buffer buffer = new Buffer(buffer_max_size);

        ArrayList<Request> requests = ReadQueries(scanner);
        ArrayList<Response> responses = ProcessRequests(requests, buffer);
        PrintResponses(responses);
    }
}
