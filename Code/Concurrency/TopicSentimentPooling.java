import java.io.*;
import java.util.HashMap;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
import java.lang.String;

public class TopicSentimentPooling {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// Create Hash map of the prediction file
		HashMap<String, String> hmap = new HashMap<String, String>();
		try {
			/*
			 * questions.txt file has {Question_id, Sentiment} columns
			 * AidSentiment.csv file has {Answer_id, Sentiment} columns
			 * */
			
			String q = "C://Users//sleno//Desktop//Conc Dataset//Questions.txt";
			BufferedReader qdata = new BufferedReader(new FileReader(q));
			
			String a = "C://Users//sleno//Desktop//Conc Dataset//Answers.txt";
			BufferedReader adata = new BufferedReader(new FileReader(a));
			
			// read questions sentiment data
			String currentLine;	
	        while ((currentLine = qdata.readLine()) != null) {
	            String[] lm = currentLine.split("\t");
	            hmap.put(lm[0], lm[1]);
	            //currentLine = qdata.readLine();
	        }
	        qdata.close(); 
	        
	        
	        // read answer sentiment data
	        String currLine = adata.readLine();
	        while ((currLine) != null) {
	            String[] am = currLine.split("\t");
	            hmap.put(am[0], am[1]);
	            currLine = adata.readLine();
	            //currLine= adata.readLine();
	        }
	        adata.close();	 
	        //System.out.println("hash map"+hmap.size());
	        
		}
		catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			}
		
		
		// Step 2: Match each question sentiment with respect to its topics
		
		/*
		 * id-topic-map-concurrency.txt file has many fields, 
		 * here we are interested in {[0]- TopicCategory number, [2] - question/AnswerID}
		 * */
		try {
			String d = "C://Users//sleno//Desktop//Conc Dataset//id-topic-map-concurrency.txt";
			BufferedReader tData = new BufferedReader(new FileReader(d));
			
			String w = "C://Users//sleno//Desktop//Conc Dataset//TopicClassification.csv";
			BufferedWriter writer = new BufferedWriter(new FileWriter(w));
			
			// CSV delimiters 
			String CommaDel = "," , NewLineDel = "\n";
			
			String topicdata = tData.readLine();
			Integer i = 0;
			while ((topicdata) != null) {
				//System.out.println(topicdata);
				
				// Here y = {y[0] = Qid/Aid, y[2] = TopicID, y[1] = "\t"}
				String[] y = topicdata.split("\t");
				
				
				//System.out.println("topic reading check \t " +y[0] + "\t" + y[2]);
				
				if (hmap.containsKey(y[0])) {
					// System.out.println("No contains key "+y[0]);
					i++;

		
				}
				writer.write(y[2]); // topic classification number
	            writer.write(CommaDel);
	            writer.write(y[0]); // writing question or answer id
	            writer.write(CommaDel);
	           // System.out.print("\n\n");
	           // System.out.print(id);
	            writer.write(hmap.get(y[0])); // matching exact sentiment wrt id
	            writer.write(NewLineDel);
	            
				topicdata = tData.readLine();

			 }
			//System.out.println("total answer missing "+i);
				
		tData.close();
		writer.close();
		hmap.clear();
		}
		catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			}

}
}
