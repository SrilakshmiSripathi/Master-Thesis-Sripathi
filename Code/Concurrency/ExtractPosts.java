import java.io.*;

public class ExtractPosts {

	public static void main(String[] args) throws IOException{
		try {
			// File containing {Q,A | Id | Body | AccAnsw}
			 String openP = "D:\\RA\\DataDumps\\Senti\\concurrency-posts.csv";
			 File postsFile = new File(openP);
			 BufferedReader posts = new BufferedReader(new FileReader(postsFile));

	         // File containing {Q,A posts | Senti}
	         String openS = "D:\\RA\\DataDumps\\Senti\\SentimentData.csv";
	         File sentiFile = new File(openS);
	         BufferedReader senti = new BufferedReader(new FileReader(sentiFile));
	            
	         //create new file to combine sentiment w.r.t the questions {Q, Senti}
	         String openQ = "D:\\RA\\DataDumps\\Senti\\Questions.txt";
	         File QSenti = new File(openQ);
	         BufferedWriter qs = new BufferedWriter(new FileWriter(QSenti));
	            
	         //create new file to combine sentiment w.r.t the questions {A, Senti}
	         String openA = "D:\\RA\\DataDumps\\Senti\\Answers.txt";
	         File ASenti = new File(openA);
	         BufferedWriter as = new BufferedWriter(new FileWriter(ASenti));
	            
	         String currPosts = posts.readLine();
	         String currSenti = senti.readLine();         

	         int i = 0; 
	         int j = 0;
	         while ((currPosts != null) && (currSenti != null)) {      	 
	        	 
	        	 String[] pValues = currPosts.split(",");
	        	 String[] sValues = currSenti.split(",");
	        	 
	        	 /* pValues[0] = {Q,A}
					 * pValues[1] = {id}
					 * pValues[2] = {postBody, Empty}
					 * pValues[3] = {AccAnswer}
					 * sValues[0] = {PostBody, Empty}
					 * sValues[1] = {Sentiment} */
	        	 
	        	 if (pValues[0].equals("Q")) {
	        		 i ++;
	        		 qs.write(pValues[1]);
	        		 qs.write("\t");
	        		 qs.write(sValues[1]);
		        	 qs.write("\n");	        			 	        			 
	        		 
	        		 currPosts = posts.readLine();
	        		 currSenti = senti.readLine();
	        		 }
	        	 if (pValues[0].equals("A")) {	  
	        		 j ++;
	        		 as.write(pValues[1]);
	        		 as.write("\t");
	        		 as.write(sValues[1]);
		        	 as.write("\n");        			 	 
	        		 
	        		 currPosts = posts.readLine();
	        		 currSenti = senti.readLine();
	        	 }
	         }	         
	         posts.close();
	         senti.close();
	         qs.close();
	         as.close();
	         System.out.println("Total Question Posts" +i + " Total Answer Posts" + j);
		}
		catch (Exception e) {
		    e.printStackTrace();
		}

	}

}
