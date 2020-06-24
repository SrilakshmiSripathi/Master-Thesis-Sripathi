import java.io.*;

import org.jsoup.Jsoup;
import org.jsoup.examples.HtmlToPlainText;


public class SplitDataSet {

	public static void main(String[] args) throws IOException {
		
		int totalLines = 0;
        try {
            // 1. Read file to memory
            BufferedReader out = new BufferedReader(new FileReader("C://Users//s//Desktop//RA//DataDump//concurrency-posts.txt"));
            String x = out.readLine();
            while ( ( x = out.readLine() ) != null ) { 
                if (x.startsWith("Body: ")) {
                    totalLines += 1; 
                } } out.close();
        } catch (IOException e) { System.err.println(e.getMessage()); e.printStackTrace(); }

        try{
            BufferedReader data = new BufferedReader(new FileReader("C://Users//s//Desktop//RA//DataDump//concurrency-posts.txt"));
            
            int SentiLimit = 10000;
            int Splits = totalLines/SentiLimit + 1;
            
            String CommaDel = ",";
            String NewLineDel = "\n";

            String line = data.readLine();
            
            int MissedDataCounter = 0;
            File missed = new File("C://Users//s//Desktop//RA//SentimentData//IgnoredDataCount.txt");
            BufferedWriter missedData = new BufferedWriter(new FileWriter(missed));
            
            int kFiles = 1;
            while (kFiles <= Splits) {
            System.out.println(" new split no: " +kFiles);
            	
                //location of new file
                File New_Csv = new File("C://Users//s//Desktop//RA//SentimentData//posts" + kFiles + ".csv");
                BufferedWriter write2csv = new BufferedWriter(new FileWriter(New_Csv));

                int flag = 0;
                
                while (line != null && flag <= SentiLimit-1){
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
                			write2csv.write(body);
                			write2csv.write(CommaDel);
                			write2csv.write(NewLineDel);
                			}
                		else {
                			write2csv.write(body);
                			write2csv.write(CommaDel);
                			write2csv.write(NewLineDel);
                			MissedDataCounter ++;
                			}
                		flag += 1;
                		line = data.readLine();
                		}
                	else {
                		line = data.readLine();
                		}
                	}
                write2csv.close(); 
                kFiles ++;
            }
            missedData.write("Records ignored in pre-processing step are " +MissedDataCounter +" of Total records = " +totalLines);
            System.out.println(+MissedDataCounter);
            missedData.close();
            data.close();
            System.out.println("Terminating");
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}