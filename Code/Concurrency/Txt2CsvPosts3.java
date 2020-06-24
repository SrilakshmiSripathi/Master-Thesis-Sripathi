import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.jsoup.Jsoup;
import org.jsoup.examples.HtmlToPlainText;

public class Txt2CsvPosts {
	
	
	public static void main(String[] args) throws IOException {
		try {
			// Creating CSV file from Text
			File cp = new File("C://Users//s//Desktop//RA//DataDumps//concurrency-posts.txt");
			BufferedReader cpData = new BufferedReader(new FileReader(cp));
			
			String CommaDel = ",";
			String NewLineDel = "\n";
			
			Pattern number = Pattern.compile("\\d+");
			Pattern word = Pattern.compile("\\w+");
			
			//location of new file
			File cp_csv= new File("C://Users//s//Desktop//RA//DataDumps//Senti//concurrency-posts.csv");
			BufferedWriter cpCSV = new BufferedWriter(new FileWriter(cp_csv));
			
			int MissingAnsPosts = 0;
			int MissingQPosts = 0;	
			File missed = new File("C://Users//s//Desktop//RA//DataDumps//Senti//IgnoredPostsCount.txt");
            BufferedWriter missedData = new BufferedWriter(new FileWriter(missed));
			
			String line = cpData.readLine();
			while (line != null) {
				if (line.startsWith("Question #: ")){
					// add QID
					Matcher num = number.matcher(line);
					if (num.find( )) {
						cpCSV.write("Q");
						cpCSV.write(CommaDel);
						cpCSV.write(num.group(0));
						cpCSV.write(CommaDel);
						}
					else {
						System.out.println("NO MATCH");
						}
					while (line != null) {
						if (line.startsWith("Body: ")) {
							String body = line;
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
							body = body.trim();
							
							if (body != null && !body.isEmpty()) {
								cpCSV.write(body);
								cpCSV.write(CommaDel);								
								}
							else {
								MissingQPosts ++; 
								// body here is empty
	                			// not writing null or empty as Senti4SD marks this data negative
	                			// In consistency with SentimentData
								cpCSV.write(body);
								cpCSV.write(CommaDel);								
								}
							line = cpData.readLine();
							}
						if (line.startsWith("Accepted Answer #:")) {
							Matcher digit = number.matcher(line);
							Matcher letter = word.matcher(line);
							
							if (digit.find( )) {
								// Has accepted answer
								cpCSV.write("1");
								cpCSV.write(CommaDel);
								cpCSV.write(NewLineDel);
								}
							else if (letter.find( )) {
								// Doesn't have accepted answer
								cpCSV.write("0");
								cpCSV.write(CommaDel);
								cpCSV.write(NewLineDel);
								} 
							else {
								System.out.println("NO MATCH");
								}
							line = cpData.readLine(); 
							break ;
							}
						else {
							line = cpData.readLine();
							}
						}
					}
				if (line.startsWith("Answer #: ")){
					Matcher num = number.matcher(line);
					
					if (num.find( )) {
						cpCSV.write("A");
						cpCSV.write(CommaDel);
						cpCSV.write(num.group(0));
						cpCSV.write(CommaDel);
						}
					else {
						System.out.println("NO MATCH");
						}
					
					while (line != null) {
						if (line.startsWith("Body: ")) {
							String body = line;
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
							body = body.trim();
							
							if (body != null && !body.isEmpty()) {
								cpCSV.write(body);
								cpCSV.write(CommaDel);
								cpCSV.write(NewLineDel);
								}
							else {
								MissingAnsPosts ++; 
								// body here is empty
	                			// not writing null or empty as Senti4SD marks this data negative
	                			// In consistency with SentimentData
								cpCSV.write(body);
								cpCSV.write(CommaDel);
								cpCSV.write(NewLineDel);
								}
							break ;
							}
						else {
							line = cpData.readLine();
							}
						}
				}
				else {
					line = cpData.readLine();
					}
				}
			missedData.write("Records ignored in pre-processing step are AnswersPosts=" +MissingAnsPosts +" QuestionPosts=" + MissingQPosts+ " of Total records 245576");
            System.out.println("AnswersPosts=" +MissingAnsPosts +"QuestionPosts=" + MissingQPosts);
            missedData.close();
			cpData.close();
			cpCSV.close();
			System.out.println("Terminated");
		}
		catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			}
		}
	}