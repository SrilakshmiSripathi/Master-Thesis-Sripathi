import java.io.*;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.String;

public class TopicSentimentPooling {
	private static BufferedReader data2;

	public static void main(String[] args) throws IOException {
		
	// Create Hash map of the prediction file
	HashMap<String, String> hmap = new HashMap<String, String>();
	try {
		/*
		 * QidSentiment.csv file has {Question_id, Sentiment} columns
		 * AidSentiment.csv file has {Answer_id, Sentiment} columns
		 * */
		
		String q = "C://Users//sleno//Desktop//Big Data Dataset//QidSentiment.csv";
		BufferedReader qdata = new BufferedReader(new FileReader(q));
		
		String a = "C://Users//sleno//Desktop//Big Data Dataset//AidSentiment.csv";
		BufferedReader adata = new BufferedReader(new FileReader(a));
		
		// read questions sentiment data
		String currentLine;	
        while ((currentLine = qdata.readLine()) != null) {
            String[] lm = currentLine.split(",");
            hmap.put(lm[0], lm[1]);
            //currentLine = qdata.readLine();
        }
        qdata.close();
        
        // read answer sentiment data
        String currLine;
        while ((currLine= adata.readLine()) != null) {
            String[] am = currLine.split(",");
            hmap.put(am[0], am[1]);
            //currLine= adata.readLine();
        }
        adata.close();	
	}
	catch (Exception e) {
		System.err.println("Error: " + e.getMessage());
		}
	
	// Step 2: Match each question sentiment with respect to its topics
	
	/*
	 * BIGDATA-FINAL-dominant-topic.txt file has many fields, 
	 * here we are interested in {[0]- TopicCategory number, [2] - question/AnswerID}
	 * */
	try {  
        String d = "C://Users//sleno//Desktop//Big Data Dataset//BIGDATA-FINAL-dominant-topic.txt";
		data2 = new BufferedReader(new FileReader(d));

		String w = "C://Users//sleno//Desktop//Big Data Dataset//topicsentiment.csv";
		BufferedWriter writer = new BufferedWriter(new FileWriter(w));
		
        // CSV delimiters 
		String CommaDel = "," , NewLineDel = "\n";
        String topicdata = data2.readLine();
        //System.out.print("here \n\n");
        while ((topicdata) != null) {
        	String[] y = topicdata.split("\t");
        	
        	//System.out.print("here with y3\n\n");
            Pattern qid = Pattern.compile("(\\d+)");     // qid number is the unique pattern
            Matcher idMatcher = qid.matcher(y[3]);   // file:/C:/Mallet/BIGDATA-FINAL/10001892-Q.txt = y[3]
            
            //System.out.print("\n\n");
          //  System.out.print(y[3]);
            //System.out.print("\n\n");

            String id = "";
            if(idMatcher.find()) { 
    			id = idMatcher.group(1);
    		}
            writer.write(y[0]); // topic classification number
            writer.write(CommaDel);
            writer.write(id); // writing question or answer id
            writer.write(CommaDel);
           // System.out.print("\n\n");
           // System.out.print(id);
            writer.write(hmap.get(id)); // matching exact sentiment wrt id
            writer.write(NewLineDel);
            topicdata = data2.readLine(); 
        }
		
		writer.flush();
		writer.close();
		System.out.print("done");
	}
	catch (Exception e) {
		System.err.println("Error: " + e.getMessage());
		}

}
}

