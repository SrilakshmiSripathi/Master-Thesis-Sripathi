import java.io.*;
import java.util.HashMap;
import java.lang.String;

public class MatchaSentiment {
	public static void main(String[] args) throws IOException {
		try {
			int totalfiles = 5;
			
			// read Senti4SD output data
			String n = "C://Users//sleno//Desktop//Big Data Dataset//answers.csv";
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(n)));
			
			//Open files
			int kFiles = 1;
			while (kFiles <= totalfiles) {
				// read question id and body data
				String d = "C://Users//sleno//Desktop//Big Data Dataset//ans id and body//data"+ kFiles + ".csv";
				BufferedReader data = new BufferedReader(new FileReader(d));
				
				/*
				// read body tags for cross verification of the matching 
				String x = "C://Users//sleno//Desktop//Big Data Dataset//Senti4SD a//input//senti"+ kFiles + ".csv";
				BufferedReader text = new BufferedReader(new FileReader(x)); */
				
				// read Senti4SD output data
				String y = "C://Users//sleno//Desktop//Big Data Dataset//Senti4SD a//output//sa"+ kFiles + ".csv";
				BufferedReader sentiment = new BufferedReader(new FileReader(y));
				
				// Create Hash map of the prediction file
	            HashMap<String, String> hmap = new HashMap<String, String>();
	            
	            String currentLine;
                while ((currentLine = sentiment.readLine()) != null) {
                    String[] lm = currentLine.split(",");
                    hmap.put(lm[0], lm[1]);
                }
                System.out.print(hmap);
                
	           
                // CSV delimiters 
    			String CommaDel = "," , NewLineDel = "\n";
    			
    			// write data to the file
    			String a_id;    			    			
    			int position = 0;
    			String line = data.readLine();;
    			 while (line != null) {
    				// qid's 
    				 String[] aid_data = line.split(",");
    				 a_id = aid_data[0];
    				 System.out.print(a_id);
    				 String pos = "t" + position;
    				 
    				 writer.write(a_id);
                     writer.write(CommaDel);
                     writer.write(hmap.get(pos));
                     writer.write(NewLineDel);
    				 
    				 position += 1;				 
    				 line = data.readLine();                     
                 }
    			sentiment.close();
 				data.close();
    			kFiles ++;
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
