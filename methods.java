import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class methods {
	
	public static int[] deckIndex = new int[52];
	public static String[] deckString = new String[52];
	public static File[] visualLocation = new File[52];
	public static File infoCard;
	
	public static ArrayList<String> generateRow(int number){
		ArrayList<String> row = new ArrayList<String>();
		while(row.size() < number){
			int value = (int) ((52-number)*Math.random()+1);
			String name = convertIntToString(value);
			
			if(row.contains(name) == false){
				row.add(name);
			}
		}
		return row;
	}

	
	public static String convertIntToString(int value){ //Value bör vara arrayKorrigerad redan!
		return deckString[value];
	}
	
	
	public static int convertStringToInt(String name){
		int current = 0;
		for(String x : deckString){
			if(x.equals(name)){
				return current;
			}
			current++;
		}
		return -1;
	}
	
	
	public static void fillDeckIndex(){
		for(int i = 0; i <52;i++){
			deckIndex[i] = i+1;
		}
	}
	
	
	public static void fillDeckString() throws FileNotFoundException{
		File deck = new File("C:\\cardGame\\table.txt");
		Scanner inputFromFile = new Scanner(deck);
		for(int i = 0; inputFromFile.hasNextLine(); i++){
			String x = inputFromFile.nextLine();
			deckString[i] = x;
		}
	}
	
	
	public static void fillInfoCardFile(){
		infoCard = new File("C:\\cardGame\\info.png");
	}
	
	
	public static File returnInfoLocation(){
		return infoCard;
	}
	
	
	public static void fillVisualLocation(){
		String[] prefix = new String[13];
		prefix[0] = "2";
		prefix[1] = "3";
		prefix[2] = "4";
		prefix[3] = "5";
		prefix[4] = "6";
		prefix[5] = "7";
		prefix[6] = "8";
		prefix[7] = "9";
		prefix[8] = "10";
		prefix[9] = "jack";
		prefix[10] = "queen";
		prefix[11] = "king";
		prefix[12] = "ace";
		
		String[] suffix = new String[4];
		suffix[0] = "hearts";
		suffix[1] = "diamonds";
		suffix[2] = "spades";
		suffix[3] = "clubs";
		int currentPosition = 0;
		for(int i = 0; i < 4; i++){
			for(int x = 0; x < 13; x++){
				visualLocation[currentPosition] = new File("C:\\cardGame\\" + prefix[x] + "_of_" + suffix[i] + ".png");
				System.out.println("added to index: "+  currentPosition + " new file: " + visualLocation[currentPosition].getPath());
				currentPosition++;
			}
		}	
	}
	
	
	public static void printDeckArrayList(){
		for(int i = 0; i < deckIndex.length; i++){
			System.out.println(deckIndex[i] + "   " + deckString[i]);
		}
	}
	
	
	public static ArrayList<String> calculateFreeCards (ArrayList<String> cardsToShow){
		ArrayList<String> substractedCards = new ArrayList<String>(Arrays.asList(deckString));
		for(String x : cardsToShow){
			substractedCards.remove(x);
		}
		return substractedCards;
	}
	
	
	public static File returnVisualLocation(int index){
		return visualLocation[index];
	}
}
