
public class Main {

	public static void main(String[] args) throws NotValidChoiceException, InterruptedException{
		
		FileOperations fo = new FileOperations();//creating new object, to use its methods 
		fo.openFile(); //calling some of them, they can be in one single method, but i don't want main to be that empty
		fo.readFile();
		fo.printData();
		fo.beginChange();

	} //created by Georgi Petrov
}

