import java.io.*;
import java.util.HashMap;
import java.lang.String;

public class Matchqsentiment {
	public static void main(String[] args) throws IOException {
		try {
			int totalfiles = 12;			
			// read Senti4SD output data
			String n = "C://Users//sleno//Desktop//Big Data Dataset//questions.csv";
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(n)));
			
			//Open files
			int kFiles = 1;
			while (kFiles <= totalfiles) {
				// read question id and body data
				String d = "C://Users//sleno//Desktop//Big Data Dataset//qid and body//input"+ kFiles + ".csv";
				BufferedReader data = new BufferedReader(new FileReader(d));
				
				// read Senti4SD output data one column data {Pos/neg/Neu}
				String y = "C://Users//sleno//Desktop//Big Data Dataset//Senti4SD q//output//sq"+ kFiles + ".csv";
				BufferedReader sentiment = new BufferedReader(new FileReader(y));
				
				// Create Hash map of the prediction file
	            HashMap<String, String> hmap = new HashMap<String, String>();
	            
	            String currentLine;
                while ((currentLine = sentiment.readLine()) != null) {
                    String[] lm = currentLine.split(",");
                    hmap.put(lm[0], lm[1]);
                }
                //System.out.print(hmap);
                	           
                // CSV delimiters 
    			String CommaDel = "," , NewLineDel = "\n";
    			
    			// write data to the file
    			String q_id;    			    			
    			int position = 0;
    			String line = data.readLine();;
    			 while (line != null) {
    				// qid's {Qid, BodyText}
    				 String[] qid_data = line.split(",");
    				 q_id = qid_data[0];
    				 //System.out.print(q_id);
    				 String pos = "t" + position;
    				 
    				 writer.write(q_id);
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
