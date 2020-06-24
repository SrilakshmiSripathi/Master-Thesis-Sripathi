import java.io.*;
import java.util.HashMap;


public class TopicSentimentPerc {
	public static void main(String[] args) throws IOException {
		/*
		 * Step1: Read topic category and sentiment, and be ready
		 * */
		//HashMap<Integer, ArrayList<Integer>> hmap = new HashMap<Integer, ArrayList<Integer>>();
		HashMap<Integer, HashMap<String, Integer>> hmap = new HashMap<Integer, HashMap<String, Integer>>();
		try {
			String q = "C://Users//sleno//Desktop//Conc Dataset//ConcurrenctTopicClassification.csv";
			BufferedReader sdata = new BufferedReader(new FileReader(q));
			
			String w = "C://Users//sleno//Desktop//Conc Dataset//ConcurrencyTopicSentiPerc.csv";
			BufferedWriter writer = new BufferedWriter(new FileWriter(w));
			
			// CSV delimiters
			String CommaDel = "," , NewLineDel = "\n";
			
			// header
			writer.write("Topics");
			writer.write(CommaDel);
			writer.write("Positive Sentiment");
			writer.write(CommaDel);
			writer.write("Negative Sentiment");
			writer.write(CommaDel);
			writer.write("Neutral Sentiment");
			writer.write(NewLineDel);
			
			// keep everything in the hash maps
			String currentLine = sdata.readLine();
			Integer totalRecords = 0;
			while ((currentLine) != null) {
				totalRecords ++;
				String[] lineValues = currentLine.split(",");
				// as we stored question id's as strings, changing to int
				Integer topicKey = Integer.parseInt(lineValues[0]);
				
				String sentiType1 = "positive", sentiType2 = "negative",  sentiType3 = "neutral";
				Integer j =0;
				if (!hmap.containsKey(topicKey)) { 
	        		hmap.put(topicKey, new HashMap<String, Integer>());
	        		Integer InitialSenti = 0;
	        		if (topicKey == 11) {
	        			j++;
	        		}
	        		hmap.get(topicKey).put("positive", InitialSenti);
	        		hmap.get(topicKey).put("negative", InitialSenti);
    	        	hmap.get(topicKey).put("neutral", InitialSenti);	
    	        	if( lineValues[2].compareTo(sentiType1) == 0) {
    	        		Integer sentikeyval = hmap.get(topicKey).get("positive");
	        			hmap.get(topicKey).put("positive", sentikeyval+1);
	        		}
	        		else if(  lineValues[2].compareTo(sentiType2) == 0) {
	        			Integer sentikeyval = hmap.get(topicKey).get("negative");
    	        		hmap.get(topicKey).put("negative", sentikeyval +1);
    	        	}
	        		else if(  lineValues[2].compareTo(sentiType3) == 0) {
	        			Integer sentikeyval = hmap.get(topicKey).get("neutral");
    	        		hmap.get(topicKey).put("neutral", sentikeyval+1);	
	        	}
    	        	}
	        	else {
	        		if( lineValues[2].compareTo(sentiType1) == 0) {
	        			Integer sentikeyval = hmap.get(topicKey).get("positive");
	        			hmap.get(topicKey).put("positive", sentikeyval+1);
	        		}
	        		else if(  lineValues[2].compareTo(sentiType2) == 0) {
	        			Integer sentikeyval = hmap.get(topicKey).get("negative");
    	        		hmap.get(topicKey).put("negative", sentikeyval +1);
    	        	}
	        		else if(  lineValues[2].compareTo(sentiType3) == 0) {
	        			Integer sentikeyval = hmap.get(topicKey).get("neutral");
    	        		hmap.get(topicKey).put("neutral", sentikeyval+1);	
	        	}
	        	}
				//System.out.println();
	        	currentLine = sdata.readLine();	
	        		}
			
			System.out.println(hmap);
			
			/*
			 * * Step 2: Calculating sentiment Percentages to finding correlations
			 */
		      
			
			String[] topics = new String[30];
			topics[0] = "basic concepts";  topics[15] = "basic cocentps"; //merged
	        topics[1] = "thread life cycle management"; 
	        topics[2] = "concurrent collections"; 
	        topics[3] = "entity management"; 
	        topics[4] = "object-oriented concurrency"; topics[28] = "object-oriented concurrency"; //"blocking"; 
	        topics[5] = "task parallelism"; 
	        topics[6] = "thread sharing";
	        topics[7] = "process life cycle management"; 
	        topics[8] = "thread scheduling"; 
	        topics[9] = "thread safety"; 
	        topics[10] = "runtime speedup"; 


	        topics[11] = "[removed]"; //"data synchronization"; 


	        topics[12] = "web concurrency"; 
	        topics[13] = "database management systems"; 
	        topics[14] = "locking"; //"synchronization constructs"; 
	         
	        topics[16] = "thread pool"; 
	        topics[17] = "data scraping"; 
	        topics[18] = "unexpected output"; 
	        topics[19] = "irreproducible behavior"; 
	        topics[20] = "event-based concurrency"; 
	        topics[21] = "memory consistency"; 
	        topics[22] = "producer-consumer concurrency"; 
	        topics[23] = "GUI"; 
	        topics[24] = "parallel computing"; 
	        topics[25] = "python multiprocessing"; 
	        topics[26] = "file management";
	        topics[27] = "mobile concurrency"; 

	        topics[29] = "client-server concurrency";
	        
	        Integer VerifyRecords = 0;
	        
	        // as we know they are 0-29 topics
	        Integer i = 0;
	        while ( i <= 29) {
	        	//Integer pos = hmap.get(i).get("positive");
	        	//Integer neg = hmap.get(i).get("negative");
	        	//Integer neu =  hmap.get(i).get("neutral");
	        	
	        	Integer totalTopicSenti = 0;
	        			//pos + neg + neu;
	        	
	        	// Calculate the sentiment percentages for merged topics
	        	if (i == 0) {
	        		Integer pos = hmap.get(i).get("positive");
		        	Integer neg = hmap.get(i).get("negative");
		        	Integer neu =  hmap.get(i).get("neutral");
		        	
		        	totalTopicSenti += pos + neg + neu;
		        	
	        		Integer other_pos = hmap.get(15).get("positive");	        	
	        		Integer other_neg = hmap.get(15).get("negative");
	        		Integer other_neu =  hmap.get(15).get("neutral");
	        		
	        		totalTopicSenti += other_pos + other_neg + other_neu ;
	        		
	        		//Check for total sentiment values in each category
	        		VerifyRecords = VerifyRecords + totalTopicSenti;
	        		
	        		Float ps = (float) pos/totalTopicSenti *100;
	        		Float neg_s = (float) neg / totalTopicSenti*100;
	        		Float nue_s = (float) neu / totalTopicSenti*100;
	        		
	        		// Converting to String for writing to file
	        		String ps1 = Float.toString(ps);
	        		String ngs1 = Float.toString(neg_s);
	        		String nus1 = Float.toString(nue_s);
	        		
	        		writer.write(topics[i]);
	        		writer.write(CommaDel);
	        		writer.write(ps1);
	        		writer.write(CommaDel);
	        		writer.write(ngs1);
	        		writer.write(CommaDel);
	        		writer.write(nus1);
	        		writer.newLine();
		    	   }
	        	else if(i == 4) {
	        		Integer pos = hmap.get(i).get("positive");
		        	Integer neg = hmap.get(i).get("negative");
		        	Integer neu =  hmap.get(i).get("neutral");
		        	
		        	totalTopicSenti += pos + neg + neu;
		        	
	        		Integer other_pos = hmap.get(28).get("positive");
	        		Integer other_neg = hmap.get(28).get("negative");
	        		Integer other_neu =  hmap.get(28).get("neutral");
	        		
	        		totalTopicSenti += other_pos + other_neg + other_neu ;
	        		
	        		//Check for total sentiment values in each category
	        		VerifyRecords = VerifyRecords + totalTopicSenti;
	        		
	        		Float ps = (float) pos/totalTopicSenti *100;
	        		Float neg_s = (float) neg / totalTopicSenti*100;
	        		Float nue_s = (float) neu / totalTopicSenti*100;
	        		
	        		// Converting to String for writing to file
	        		String ps1 = Float.toString(ps);
	        		String ngs1 = Float.toString(neg_s);
	        		String nus1 = Float.toString(nue_s);
	        		
	        		writer.write(topics[i]);
	        		writer.write(CommaDel);
	        		writer.write(ps1);
	        		writer.write(CommaDel);
	        		writer.write(ngs1);
	        		writer.write(CommaDel);
	        		writer.write(nus1);
	        		writer.newLine();
		    	   }
	        	else if( i == 15 || i == 28) {
	        		//15, 28 are overlapping topics,
	        		;
	        	}
	        	else if(i == 11) {
	        		//  11 is removed topic
	        		Integer other_pos = hmap.get(i).get("positive");
	        		Integer other_neg = hmap.get(i).get("negative");
	        		Integer other_neu =  hmap.get(i).get("neutral");
	        		
	        		totalTopicSenti += other_pos + other_neg + other_neu ;
	        		
	        		//Check for total sentiment values in each category
	        		VerifyRecords = VerifyRecords + totalTopicSenti;
	        		
	        		
	        		}
	        	
	        	else {
	        		Integer pos = hmap.get(i).get("positive");
		        	Integer neg = hmap.get(i).get("negative");
		        	Integer neu =  hmap.get(i).get("neutral");
		        	
		        	totalTopicSenti += pos + neg + neu;
		        	
	        		//Check for total sentiment values in each category
	        		VerifyRecords = VerifyRecords + totalTopicSenti;
	        		
	        		Float ps = (float) pos/totalTopicSenti *100;
	        		Float neg_s = (float) neg / totalTopicSenti*100;
	        		Float nue_s = (float) neu / totalTopicSenti*100;
	        		
	        		// Converting to String for writing to file
	        		String ps1 = Float.toString(ps);
	        		String ngs1 = Float.toString(neg_s);
	        		String nus1 = Float.toString(nue_s);
	        		
	        		writer.write(topics[i]);
	        		writer.write(CommaDel);
	        		writer.write(ps1);
	        		writer.write(CommaDel);
	        		writer.write(ngs1);
	        		writer.write(CommaDel);
	        		writer.write(nus1);
	        		writer.newLine();
	        		
	        	}
	        	i++;
	        	}
	        hmap.clear();
        	sdata.close();
        	writer.close();
        	System.out.println("totalRecords \t" +totalRecords);
        	System.out.println("Sentiment Records proccessed in for loop \t" +VerifyRecords);
		}
		catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			}

}
}
