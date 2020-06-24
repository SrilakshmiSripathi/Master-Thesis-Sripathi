import java.io.*;
import java.util.HashMap;


public class TopicSentimentPerc {
	public static void main(String[] args) throws IOException {
		/* 
	        * Step1: Read topic category and sentiment, and be ready 
	        */
		//HashMap<Integer, ArrayList<Integer>> hmap = new HashMap<Integer, ArrayList<Integer>>();
		HashMap<Integer, HashMap<String, Integer>> hmap = new HashMap<Integer, HashMap<String, Integer>>();
		try {
			String q = "C://Users//sleno//Desktop//Big Data Dataset//topicsentiment.csv";
			BufferedReader sdata = new BufferedReader(new FileReader(q));
			
			String w = "C://Users//sleno//Desktop//Big Data Dataset//BigDataSentiPerc.csv";
			BufferedWriter writer = new BufferedWriter(new FileWriter(w));
			
			// CSV delimiters 
			String CommaDel = "," , NewLineDel = "\n";
			
			// keep everything in the hash maps
			String currentLine = sdata.readLine();	
			Integer totalRecords = 0;
	        while ((currentLine) != null) {
	        	totalRecords ++;
	        	String[] lineValues = currentLine.split(",");
	        	// as we stored question id's as strings, changing to int
	        	Integer topicKey = Integer.parseInt(lineValues[0]);
	        	
	        	String sentiType1 = "positive", sentiType2 = "negative",  sentiType3 = "neutral";
        		
	        	if (!hmap.containsKey(topicKey)) { 
	        		hmap.put(topicKey, new HashMap<String, Integer>());
	        		Integer InitialSenti = 0;
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
	        	currentLine = sdata.readLine();	
	        		}
	       //System.out.println(hmap);
	       

	        /* 
	        * Step 2: Calculating sentiment Percentages to finding correlations
	        */
	     
	       
	        String[] topicArray = new String[30];
	    	topicArray[0] = "Memory Management";
	    	topicArray[1] = "Pig";
	    	topicArray[2] = "Connection Management";
	    	topicArray[3] = "Dataset Load&Store";
	    	topicArray[4] = "Data Organization";
	    	topicArray[5] = "Scala Spark";
	    	topicArray[6] = "PySpark";
	    	topicArray[7] = "Dataframe";
	    	topicArray[8] = "File Distribution";
	    	topicArray[9] = "General Programming";
	    	topicArray[10] = "Debugging"; 			//merged with 24        
	    	topicArray[11] = "Hbase";
	    	topicArray[12] = "Job Management";
	    	topicArray[13] = "File Format";
	    	topicArray[14] = "File Management";
	    	topicArray[15] = "String";
	    	topicArray[16] = "Database Import&Export";
	    	topicArray[17] = "Stream Processing";
	    	topicArray[18] = "Basic Concepts";
	    	topicArray[19] = "Date&Time";
	    	topicArray[20] = "Log Analysis";
	    	topicArray[21] = "Performance";
	    	topicArray[22] = "Text Search";
	    	topicArray[23] = "Dependency Management";
	    	topicArray[24] = "Debugging";			//merged with 10                  
	    	topicArray[25] = "Machine Learning";
	    	topicArray[26] = "Hive";
	    	topicArray[27] = "Mapreduce Model";  //merged into 28
	    	topicArray[28] = "Mapreduce Model";  //merged into 27
	    	topicArray[29] = "RDD";
	    	
	    	
	    	
	       // header
	       writer.write("Topics");
	       writer.write(CommaDel);
	       writer.write("Positive Sentiment");
	       writer.write(CommaDel);
	       writer.write("Negative Sentiment");
	       writer.write(CommaDel);
	       writer.write("Neutral Sentiment");
	       writer.write(NewLineDel);
	       
	       Integer VerifyRecords = 0;
	       
	       // as we know they are 0-29 topics
	       Integer i = 0;
	       while ( i <= 29) {
	    	   Integer pos = hmap.get(i).get("positive");
	    	   Integer neg = hmap.get(i).get("negative");
	    	   Integer neu =  hmap.get(i).get("neutral");	    	  
	    	   
	    	   Integer totalTopicSenti = pos + neg + neu;
	    	   // Calculate the sentiment percentages for merged topics
	    	   if (i == 10) {
	    		   Integer other_pos = hmap.get(24).get("positive");
		    	   Integer other_neg = hmap.get(24).get("negative");
		    	   Integer other_neu =  hmap.get(24).get("neutral");	
		    	   
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
		    	   
		    	   writer.write(topicArray[i]);
		    	   writer.write(CommaDel);
		    	   writer.write(ps1);
		    	   writer.write(CommaDel);
		    	   writer.write(ngs1);
		    	   writer.write(CommaDel);
		    	   writer.write(nus1);
		    	   writer.newLine();
		    	   i++;
	    	   }
	    	   else if(i == 27) {
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
		    	   
		    	   writer.write(topicArray[i]);
		    	   writer.write(CommaDel);
		    	   writer.write(ps1);
		    	   writer.write(CommaDel);
		    	   writer.write(ngs1);
		    	   writer.write(CommaDel);
		    	   writer.write(nus1);
		    	   writer.newLine();
		    	   i++;
	    	   }
	    	   else if( i == 24 || i == 28) {
	    		   i++;
	    	   }
	    	   else {
	    		 //Check for total sentiment values in each category
		    	   VerifyRecords = VerifyRecords + totalTopicSenti;
		    	   
		    	   Float ps = (float) pos/totalTopicSenti *100;
		    	   Float neg_s = (float) neg / totalTopicSenti*100;
		    	   Float nue_s = (float) neu / totalTopicSenti*100;
		    	   
		    	   // Converting to String for writing to file
		    	   String ps1 = Float.toString(ps);
		    	   String ngs1 = Float.toString(neg_s);
		    	   String nus1 = Float.toString(nue_s);
		    	   
		    	   writer.write(topicArray[i]);
		    	   writer.write(CommaDel);
		    	   writer.write(ps1);
		    	   writer.write(CommaDel);
		    	   writer.write(ngs1);
		    	   writer.write(CommaDel);
		    	   writer.write(nus1);
		    	   writer.newLine();
		    	   i++;
	    		   
	    	   }

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