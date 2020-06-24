import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class MatchData {

	public static void main(String[] args) throws IOException {
		try {
			//Read Concurrency-posts
			File cp_csv= new File("C://Users//s//Desktop//RA//DataDumps//Senti//concurrency-posts.csv");
			BufferedReader cp_CSV = new BufferedReader(new FileReader(cp_csv));
			
			// Read respective Sentiments
			File senti = new File("C://Users//s//Desktop//RA//DataDumps//Senti//SentimentData.csv");
			BufferedReader DataSenti = new BufferedReader(new FileReader(senti));
			
			//write 
			File out = new File("C://Users//s//Desktop//RA//DataDumps//Senti//SPSS-Data.csv");
			BufferedWriter Finaloutput = new BufferedWriter(new FileWriter(out));

			String CommaDel = ",";
            String NewLineDel = "\n";

            // Header
            Finaloutput.write("QuestionIds");
            Finaloutput.write(CommaDel);
			Finaloutput.write("AcceptedAnswers");
			Finaloutput.write(CommaDel);
			Finaloutput.write("SentimentValue");
			Finaloutput.write(CommaDel);
			Finaloutput.write(NewLineDel);
			
			// Merge two files extracting required data
			int i = 0;
			int ignoredCount = 0;
			
			String Posts = cp_CSV.readLine();
			String SentiLines = DataSenti.readLine();
			
			while ((Posts != null) && (SentiLines != null)) {
				
				String[] p = Posts.split(",");
				String[] s = SentiLines.split(",");
				
				// check if posts is a question and check if the respective sentiment match 
				// if yes, add {qid, accAnswervalue, sentiment}
				
				/* p[0] = {Q,A}
				 * p[1] = {id}
				 * p[2] = {postBody}
				 * p[3] = {AccAnswer}
				 * s[0] = {PostBody}
				 * s[1] = {Sentiment}
				 * Do SQL join operation*/ 
				
				if (p[0].equals("Q")) {
					if (p[2].equals("")){
						ignoredCount ++;
						Posts = cp_CSV.readLine(); 
						SentiLines = DataSenti.readLine();
						i ++;
						}
					else if (p[2].equals(s[0])) {
						//Question ID
						Finaloutput.write(p[1]);
						Finaloutput.write(CommaDel);
						// Accepted Answer
						Finaloutput.write(p[3]);
						Finaloutput.write(CommaDel);
						// Sentiment value 
						if (s[1].equals("negative")) {
							Finaloutput.write("-1");
							}
						if (s[1].equals("positive")) {
							Finaloutput.write("1");
							}
						if (s[1].equals("neutral")) {
							Finaloutput.write("0");
							}				
						Finaloutput.write(NewLineDel);
						Posts = cp_CSV.readLine(); 
						SentiLines = DataSenti.readLine();
						i ++;
						}
					else if (!p[2].equals(s[0])){
						System.out.print("Posts Body didnot match check again rowid " +i);
						Posts = cp_CSV.readLine(); 
						SentiLines = DataSenti.readLine();
						i ++;
						}					
					}
				else {
					Posts = cp_CSV.readLine(); 
					SentiLines = DataSenti.readLine();
					i ++;
				}
			}
			Finaloutput.close();
			DataSenti.close();
			cp_CSV.close();
			System.out.println("Terminating");
			System.out.println(+ignoredCount);
			} catch (FileNotFoundException e) {
		        e.printStackTrace();
		    } catch (IOException e) {
		    	System.err.println(e.getMessage());
		    	e.printStackTrace();
			} 
		}
	}
