Files content and logic:

ProcessingQuestions - 
Input: 
	Reads bigdata-questions.xml which contains raw questions
	stackoverflow data
Target Logic:
	1. splits files to make sure senti4Sd is able to process it, 
	2. Process Body tag to remove html tags etc
Output: Creates 2 files, 
	1. Qid, bodytext -files names data(x).csv
	2. Bodytext only for senti4SD output file names - senti(x).csv

ProcessingAnswers - 
Input: 
	Reads bigdata-accepted-answers.xml which contains raw answers 
	stackoverflow data
Target Logic:
	1. splits files to make sure senti4Sd is able to process it, 
	2. Process Body tag to remove html tags etc
Output: Creates 2 files, 
	1. Aid, bodytext -files names input(x).csv
	2. Bodytext only for senti4SD output file names - sInput(x).c

matchqsentiment - 

Input: 
	Extracts question sentiment from Senti4SD output files( about 11 or 	25files).
Logic:
	Combines all the predicted sentiment data to original data.
Output: 
	Creates a file with answer Id - QID, and sentiment respectively.
	File name - questions.csv

matchasentiment - 

Input:
	Extracts answer sentiment from Senti4SD output files( about 11 or
	25files).
Logic:
	Combines all the predicted sentiment data to original data.

Output: 
	Creates a file with answer Id - AID, and sentiment respectively.
	File name - answers.csv

TopicSentimentPooling - 
Input: 
	Reads dominant topics file - BIGDATA-FINAL-dominant-topic
Target logic: 	
	for easy traceback, this logic only deals topic classification and 
	associated qid and sentiment. {topicClassification,Q/AId,
	Sentiment}. Next step logic could be compressed to this, but this 
	way we can check individual sentiment if needed.
Output: 
	TopicClassification.csv file which contains topicId, question/answerid,
	sentiment respectively. 


	
TopicSentimentPerc - 
Input: 
	Reads TopicSentiment.csv file {topicId, Q/AId, Senti}
Logic: 
	1. Calculates percentages for each topic sentiment according to
 	topics pool, made sure duplicate topic values are calculated
 	accordingly
	2. Makes dataset ready for SPSS, 
	3. (later done manually - to add difficulty and popularity
	values.) 
Output:
	Addes calcualted sentiment percentages to BigDataSentiPerc.csv {Topic, Positive, negative, neutral}