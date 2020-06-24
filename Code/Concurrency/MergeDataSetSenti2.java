import java.io.*;
import java.util.HashMap;
import java.lang.String;

public class MergeDataSetSenti {

	public static void main(String[] args) throws IOException {
		// 1. Read 25 prediction files of format {t_rowid, sentiment} and creat hashmap DataDumps//Senti//
        // 2. Read 25 post id's of format {body} and use it as a array
        // 3. Create new 25k output Sentiment file of format {body, sentiment}
        
        int TotalFiles = 25;
        int currentFile = 1; 

        // create new file to merge data from 2 sources
        String BodySentiment = "C://Users//s//Desktop//RA//DataDumps//Senti//SentimentData.csv";
        File Senti = new File(BodySentiment);
        BufferedWriter sentiWriter = new BufferedWriter(new FileWriter(Senti));
        int totalLines = 0;
        
        // Merge files, by matching sentiments
        // Match Rule: Row id of Posts Files is the {t_rowid} in Prediction file   
        while(currentFile <= TotalFiles) {

            String postFileName = "C://Users//s//Desktop//RA//DataDumps//So//posts" + currentFile + ".csv";
            File postFile = new File(postFileName);
            BufferedReader postFileData = new BufferedReader(new FileReader(postFile));

            String predFileName = "C://Users//s//Desktop//RA//DataDumps//So//prediction" + currentFile +".csv";
            File predFile = new File(predFileName);
            BufferedReader predFileData = new BufferedReader(new FileReader(predFile));

            // Create Hash map for each prediction files
            HashMap<String, String> hmap = new HashMap<String, String>();

            String currentLine;
            while ((currentLine = predFileData.readLine()) != null) {
                String[] lm = currentLine.split(",");
                hmap.put(lm[0], lm[1]);
            }    
            // Starting from index 0, matching each question and answer w.r.t sentiment
                
            String CommaDel = ",";
            String NewLineDel = "\n";

            int filemarker= 0;
            String currLine = postFileData.readLine();
            
            while ((currLine) != null) {
                totalLines ++;
                String pos = "t" + filemarker;
                String value = hmap.get(pos);
                
                sentiWriter.write(currLine);

                sentiWriter.write(value);
                sentiWriter.write(CommaDel);
                sentiWriter.write(NewLineDel);  

                hmap.remove(pos);   
                filemarker ++;      
                currLine = postFileData.readLine();
            }
            postFileData.close();
            predFileData.close();

            currentFile++; 
            }
        sentiWriter.close();
        System.out.print("validate number of lines "+ totalLines);
        System.out.println("Terminated");
        }
    }
