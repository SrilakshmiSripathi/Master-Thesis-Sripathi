import java.io.BufferedReader; 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.examples.HtmlToPlainText;

public class ProcessingAnswers {
	public static void main(String[] args) {
		int missingcount = 0;
		try {
			// 1. Open the file 
			String d = "C://Users//sleno//Desktop//Big Data Dataset//bigdata-accepted-answers.xml";
			BufferedReader data = new BufferedReader(new FileReader(d));
			// total input question
			int totalLines = 44577;
			// Senti4SD input limit
			int SentiLimit = 10000;
			// input files for senti4sd
			int Splits = totalLines/SentiLimit + 1;
			// current file tracker
			int kFiles = 1;
			
			//pattern matching
			Pattern qbody = Pattern.compile("Body=\"([^\"]*)\"");
	        Pattern qid = Pattern.compile("Id=\"([^\"]*)\"");
	        
			String line = data.readLine();
			
			// CSV delimiters 
			String CommaDel = "," , NewLineDel = "\n";
			
			  
			while (kFiles <= Splits) {
				// 2. Split files ready for the Senti4SD
				String w = "C://Users//sleno//Desktop//Big Data Dataset//split-Answers//data" + kFiles + ".csv";
				BufferedWriter writer = new BufferedWriter(new FileWriter(new File(w)));
				
				String w1 = "C://Users//sleno//Desktop//Big Data Dataset//split-Answers2//senti" + kFiles + ".csv";
				BufferedWriter writer1 = new BufferedWriter(new FileWriter(new File(w1)));
				
				int flag = 0; 
				while (line != null && flag <= SentiLimit - 1) {
					Matcher idMatcher = qid.matcher(line);
					Matcher bodyMatcher = qbody.matcher(line);
					
					String body = "";
					String id = "";
					
					// 2. access the data which is first row Question Id
					if(idMatcher.find()) {
						id = idMatcher.group(1);								
						}
					// 3. Access the data in 4th column that has data
					if(bodyMatcher.find()) {
						body = bodyMatcher.group(1);
						}
					
					body = body.replaceAll("Body: ", "");
					body = body.replaceAll("&lt;", "<");
					body = body.replaceAll("&gt;", ">");
					body = body.replaceAll("(<code>.*?</code>)", "");
					body = body.replaceAll("(<a>.*?</a>)", "");
					body = body.replaceAll("nbsp;", " ");
                
					HtmlToPlainText plain = new HtmlToPlainText();
					body = plain.getPlainText(Jsoup.parse(body));
                
					body = body.replaceAll("\n", " ");
					body = body.replaceAll("[\\s]+", " ");
					body = body.replaceAll("[^A-Za-z0-9'\\s]", " ");
					body = body.toLowerCase();
					body = body.trim();
                
					writer.write(id);
					writer.write(CommaDel);
					writer.write(body);					
					writer.write(NewLineDel);
					
								
					writer1.write(body);	
					writer1.write(CommaDel);
					writer1.write(NewLineDel);
					
					if(body == null || body.length() == 0) {
						missingcount++;
					}							
					line = data.readLine();
					flag++;
					}
				writer.flush();
				writer.close();
				writer1.flush();
				writer1.close();
				flag = 0;
				kFiles++;
				}
			
			data.close();
			System.out.print("Total missing data");
			System.out.print(missingcount);
			
			}
		catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			}
		}
	}

