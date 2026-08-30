import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;

public class lab1{
	public static void main(String[] args){
		if(args.length != 1){
			System.err.println("Usage: java lab1 <filename>");
		}
		String filename = args[0];
		String[][] data = readCSV(filename);
		Scanner scanner = new Scanner(System.in);
		for(int i = 1; i<data[0].length; i++){
			System.out.print(data[0][i] + "|");
		}

		String columnName = "";
		int indexOfColumnName = -1;
		while(indexOfColumnName == -1){
			System.out.print("\n"+"What column would you like to select from the CSV file? ");
			columnName = scanner.nextLine();
			for(int i = 0; i<data[0].length; i++){
				if(!data[0][i].equalsIgnoreCase(columnName)){
					continue;
				}else{
					indexOfColumnName = i;
					//System.out.println(indexOfColumnName);
				}
			}
		}

		double[] values = extractNumericColumn(data,indexOfColumnName);
		Arrays.sort(values);
		displayStatistics(values,columnName);

		

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
				Double.parseDouble(data[i][columnIndex]);
				realValues++;
			}catch(NumberFormatException e){
				System.out.println("Skipping column due to invalid data: " + i);
				continue;
			}
		}

		double[] values = new double[realValues];
		int index = 0;
		for(int i = 0; i<data.length;i++){ // enters real values into values
			try{
				values[index] = Double.parseDouble(data[i][columnIndex]);
				index++;
			}catch(NumberFormatException e){

			}
		}
		return values;
	}

	public static void displayStatistics(double[] values, String columnName){
		double total = 0.0;
		double average = 0.0;
		for(double value : values){ // calculates average value
			total += value;
			//System.out.println(value);
		}
		average = total/values.length;
		System.out.println("--------------\n" + columnName + "\n--------------");
		System.out.println("Total data points processed: " + values.length);
		System.out.print("Average " + columnName + ": ");
		formatStat(columnName,average);

		double max = Double.NEGATIVE_INFINITY; // calculates max value
		for(int i = 0; i<values.length; i++){
			if(values[i]>max){
				max = values[i];
			}
		}

		double min = Double.POSITIVE_INFINITY; // calculates min value
		for(int i = 0; i<values.length; i++){
			if(values[i]<min){
				min = values[i];
			}
		}

		double median = 0.0;
		//1,2,3,4
		if(values.length%2 == 0){
			median = (values[values.length/2] + values[(values.length/2) -1])/2;
		}else{
			median = values[(values.length/2) + 1];
		}

		double stdDeviation = 0.0;
		double squaredDiff = 0.0;
		for(double value : values){
			double x = value - average;
			squaredDiff += Math.pow(x,2);
		}
		stdDeviation = Math.sqrt(squaredDiff/values.length);
	



		System.out.print("Max " + columnName + ": ");
		formatType(columnName,max);
		System.out.print("Min " + columnName + ": ");
		formatType(columnName,min);
		System.out.print("Median " + columnName + ": ");
		formatStat(columnName,median);
		System.out.print("Standard Deviation from the mean of " + columnName + ": ");
		formatStat(columnName,stdDeviation);



	}
	public static void formatType(String columnName,double value){ // checks how to format data and prints data
		if(columnName.contains("Temp")){
			if(columnName.contains("F")){
				System.out.printf("%5.1f°F\n",value);
			}else if(columnName.contains("C")){
				System.out.printf("%5.1f°C\n",value);
			}
		}else if(columnName.contains("Humidity")){
			System.out.printf("%5.1f%%\n",value);			
		}else if(columnName.contains("MPH")){
			System.out.printf("%5.1fmph\n",value);
		}else if(columnName.contains("IN")){
			System.out.printf("%5.2fin\n",value);
		}else{
			System.out.printf("%5.1f\n",value);			
		}
	}
	public static void formatStat(String columnName,double value){ // checks how to format data and prints stats
		if(columnName.contains("Temp")){
			if(columnName.contains("F")){
				System.out.printf("%5.2f°F\n",value);
			}else if(columnName.contains("C")){
				System.out.printf("%5.2f°C\n",value);
			}
		}else if(columnName.contains("Humidity")){
			System.out.printf("%5.2f%%\n",value);			
		}else if(columnName.contains("MPH")){
			System.out.printf("%5.2fmph\n",value);
		}else if(columnName.contains("IN")){
			System.out.printf("%5.3fin\n",value);
		}else{
			System.out.printf("%5.2f\n",value);			
		}
	}
	
}