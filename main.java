import java.io.IOException;
import java.util.Scanner;

public class main {

	public static void main(String[] args) throws IOException, InterruptedException {
		Scanner input = new Scanner(System.in);
		System.out.println("Välj svårighetsgrad, 1= enkel, 2=svårare, 3 = svårast");
		int grad = input.nextInt();
		gameEasy gm = new gameEasy();
		
		switch(grad){
		case 1:
			gm.initiate(3, 3000);
		case 2:
			gm.initiate(5, 2000);
		case 3:
			gm.initiate(6, 1000);
		
		}
		
		

	}

}
