import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class lab1{
	public static void main(String[] args){
		if(args.length != 1){
			System.err.println("Usage: java lab1 <filename>");
		}
		String filename = args[0];
		String[][] data = readCSV(filename);
		for(String[] row : data){
			for(String col : row){
				System.out.print(col + ",");
			}
		}
		double[] values = extractNumericColumn(data,2);
		displayStatistics(values,data[0][1]);


		

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

			String arrayLine;

			while((arrayLine = arrayReader.readLine()) != null){ // goes through data to figure out how big to make 2d array.
				row++;
				String[] lineContents = arrayLine.split(",");
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

	public static double[] extractNumericColumn(String[][] data, int columnIndex){
		int realValues =0;
		for(int i = 0; i<data.length;i++){ // checks for real values in column
			try{
				Double.parseDouble(data[i][columnIndex-1]);
				realValues++;
			}catch(NumberFormatException e){
				System.out.println("Skipping column: " + i);
				continue;
			}
		}

		double[] values = new double[realValues];
		int index = 0;
		for(int i = 0; i<data.length;i++){ // enters real values into values
			try{
				values[index] = Double.parseDouble(data[i][columnIndex-1]);
				index++;
			}catch(NumberFormatException e){

			}
		}
		return values;
	}

	public static void displayStatistics(double[] values, String columnName){
		double total = 0.0;
		double average = 0.0;
		for(double value : values){
			total += value;
			System.out.println(value);
		}
		average = total/values.length;
		System.out.println(columnName);
		System.out.printf("%5.1f\n",average);

	}
	
}