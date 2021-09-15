import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Application {

	public static void main(String[] args) throws IOException {
		
		final String INPUT_FILE_NAME = "transcript.txt";
		final String OUTPUT_FILE_NAME = "subtitle.srt";

		File inputFile = new File(INPUT_FILE_NAME);
		Scanner sc = new Scanner(inputFile);
		
		Integer counter = 1;
		String lastTimestamp = null;
		String lastText = null;
		BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
	    
		while (sc.hasNextLine()) {
			String str = sc.nextLine();
			System.out.println(str);
			
			if(str.isEmpty()) {
				continue;
			}
						
			if(str.startsWith("00:")) {
				if(lastTimestamp == null) {
					lastTimestamp = str;
				} else {
					writer.append(counter.toString());
					writer.newLine();
					writer.append(lastTimestamp.concat(" --> ").concat(str));
					writer.newLine();
					writer.append(lastText);
					writer.newLine();
					writer.append("");
					writer.newLine();
					
					lastTimestamp = str;
					counter++;
				}
			} else {
				lastText = str;
			}
		}

		sc.close();
		writer.close();

	}

}