import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class lab1{
	public static void main(String[] args){
		if(args == null){
			System.out.println("Usage: java WeatherAnalyzer \"filename\"");
		}
		String filename = args[0];
		String[][] data = readCSV(filename);
		for(String[] row : data){
			for(String col : row){
				System.out.print(col + ",");
			}
		}

		

	}

	public static String[][] readCSV(String filename){
		BufferedReader reader = null;
		BufferedReader arrayReader = null;
		int row = 0;
		int col = 0;
		String[][] lines = null;

		try{
			reader = new BufferedReader(new FileReader(filename));
			arrayReader = new BufferedReader(new FileReader(filename));
			String line;

			String testLine;

			while((testLine = arrayReader.readLine()) != null){ // goes through data to figure out how big to make 2d array.
				row++;
				String[] lineContents = testLine.split(",");
				col = lineContents.length; 
			}
			
			int insert = 0;
			lines = new String[row][col];
			while((line = reader.readLine()) != null){ // goes through data and splits each line by the comma and inserts into each row of 2d array.
				String[] lineContents = line.split(",");
				lines[insert] = lineContents;
				insert++; 
			}
		}catch(IOException e){
			System.err.println("Error reading file: " + e.getMessage());
		}finally{
			if(reader != null || arrayReader != null){
				try{
					reader.close();
					arrayReader.close();
				}catch(IOException e){
					System.err.println("Error closing file: " + e.getMessage());
				}
			}
		}

		return lines;
	}

	public static float extractNumericColumn(String[][] data, int columnIndex){
		return -1;

	}

	public static void displayStatistics(double[] values, String columnName){

	}
	
}