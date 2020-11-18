import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class FileOperations {
	String directory;//creating field named "directory", used later in code
	String[][] words = new String[4][]; //creating two dimensional String array, its first dimension cannot be hard coded, but i 
	Scanner myFile = null;//creating scanner and initializing it									//think this is memory waste
	
	public void openFile() { //method to open a file
		Scanner scan = new Scanner(System.in); //creating a scanner to open the file
		directory=scan.nextLine(); //user inputs path to the file and it's stored in field "directory"
		try {
			myFile = new Scanner(new File(directory)); //here we are trying if file is loaded successfully 
			System.out.println("----------File loaded succesfully----------");
		}catch(FileNotFoundException e) {
			System.err.println("File not found"); //if it's not loaded, it's thrown an exception, calling us to input valid path
			System.out.print("Please input valid path:");
			openFile();
		}
	}
	public void readFile() { //method to read from file
		int i=0; //just a simple line counter
		while(this.myFile.hasNextLine()) {
			this.words[i] = this.myFile.nextLine().split("\\s+", 0); //here we are storing every line from file into 
			i++;              //2 dimensional String array, thanks to ".split()" method we are storing every single word 
			                  // as other String, and with regex "\\s+" we are ignoring multiple spaces&TABs
		}
	}
	public void printData() { //printing method, it makes job easier for user to make changes
		StringBuilder output = new StringBuilder(); //I am using StringBuilder, because it's wiser to use it, instead of 
		System.out.println();                       //using String(immutable field), I am trying to use less memory
		for(String[] str: this.words) {             //simple forEach loop, to iterate trough words[][]
			output.append(Arrays                   
					.toString(str).replaceAll("[\\[\\],]", ""))//adding every word into StringBuilder, ignoring all "[;];," symbols
					.append(System.lineSeparator()); // adding line separators (new rows) 
		}
		System.out.println(output.toString());  //printing the StringBuilder
	}
	public int inputIndex() { //name talks by itself, this method is used many times, for inputing indexes 
		int a = 0;
		Scanner scan = new Scanner(System.in);
		boolean validInteger = false; //this boolean is created to help do-while loop
	    do{
	        if(scan.hasNextInt()){  //checking if next scanned value is an integer
	            a = scan.nextInt();
	            validInteger = true; //if it's an integer , boolean is changed to true(ending the loop few lines later)
	        }else{ //if it's not, on console is printed error message, telling the user to input an integer value
	            scan.nextLine();
	            System.err.println("Enter a valid Integer value:");
	        }
	    }while(!validInteger); //loop will make user input value, till it's correctly (integer)
	    return a-1;//returning the value to one of following methods, and its subtracted by 1, because indexes are starting from 0
	}																//but we are starting to count from 1
	public void switchWords() { //method helping us for switching words
		try{
			System.out.print("Input the lane of the first word:"); //few prints, to tell the user what to do
			int line1 = inputIndex();                              //creating some fields, to store indexes inputed by user via 
			System.out.print("Input the number of first word:");										//inputIndex() method
 			int index1 = inputIndex();
			System.out.print("Input the lane of the second word:");
			int line2 = inputIndex();
			System.out.print("Input the number of second word:");
			int index2 = inputIndex();
			String temp = this.words[line1][index1];               //creating third field, to store the value of first word
			this.words[line1][index1] = this.words[line2][index2];  //equaling first word to second
			this.words[line2][index2] = temp;             //equaling second word to first, by using temporary field
		}catch(ArrayIndexOutOfBoundsException e) { //try-catch block is used to check if user is trying to access not existing word/line
			System.err.printf("%nArrayIndexOutOfBoundsException found!%n"
					+ "Nothing was changed. %n");
			switchWords();//calling method again, until all inputed indexes are correct
		}
	}
	public void switchLines() {  //idea in this method is same like in the switchWords() method...
		try{
			System.out.println("Which lines you wanth to switch?");  //prints to help the user
			System.out.print("First lane:");
			int line1 = inputIndex();                                //inputing lines
			System.out.print("Second line:");
			int line2 = inputIndex();
			String[] temp = words[line1];
			this.words[line1] = words[line2];
			this.words[line2] = temp;
		}catch(ArrayIndexOutOfBoundsException e) {
			System.err.printf("%nArrayIndexOutOfBoundsException found!%n"
					+ "Nothing was changed. %n");
			switchLines();
		}
	}
	public void beginChange() throws InterruptedException {  //this method is, where everything happen
		Scanner scan = new Scanner(System.in); //creating scanner for user inputing
		String decision = ""; //creating field decision and initializing it, because while is checking, if it's "exit" 
		while(!decision.equals("exit")) {
			System.out.println("What do you want to do?");//telling user what to do
			System.out.print("Choose words or lines, or type \"exit\" to end application:");//here I've used "\" to escape double
			decision = scan.nextLine();	//here letting the user to input his decision									//quotes 
			if(decision.equals("lines")) {//checking if it's lines
				switchLines();//calling method "switchLines()" //wanted to achieve code reusability
				printData();//used this method here, to let the user see what he have changed
			}
			else if(decision.equals("words")){//or words
				switchWords();
				printData();
			}else if(!decision.equals("exit"))//here checking for all other inputs, except exit, to print error message
				System.err.println("Invalid input!");
		}System.out.println("Application is closed succesfuly");
		scan.close();
		System.out.println("Few seconds are required to save the file...");
		TimeUnit.SECONDS.sleep(3);//imitating waiting for saving :D 
		fileWriter();//when user decide to end changes and exit the loop, changes are saved into the file
		System.out.println("Changes saves succesfully!!!");
	}
	public void fileWriter() {//file, created to rewrite the changes
		try {
			FileWriter writer = new FileWriter(directory);//using field "directory" from above
			int line = 0;//field to count lanes, additioned in the end of loop
			for(String[] str: words) {// for each loop to iterate trough lines of String array
				int counter = 0;		//counter for words, used a few lines down
				for(String str2: str) {//loop to iterate trough every single word
					if(counter<words[line].length-1) { //checking if counter is smaller than the length of the line subtracted by 1
						writer.write(str2.toString() + " ");//if it is true, then word is wrote, followed by space " "
					}else if(counter == words[line].length-1)//if counter is equaled to size of line-1, then this means it is
						writer.write(str2.toString());//the last word of this row, and there is no need of space after it
				counter++;
				}
				if(line!=3)//same like the word counter, here I am checking if it's not the last line
					writer.write(System.lineSeparator());//if it's not, then following words are writen on new line
				counter=0;//equaling word counter to zero, to start again checking on upper rows
				line++;//enlarging line field by one, because new row is started to writing
			}writer.close();//closing the writer
		}catch (IOException e) {
		      System.err.println("An error occurred.");
		}
	}
}
