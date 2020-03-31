import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.ImageIcon;

public class gameEasy {

	public JFrame frame;
	public JLabel lbl1;
	
	public static ArrayList<String> cardsToShow = new ArrayList<String>();
	public static ArrayList<String> freeCards = new ArrayList<String>();
	public static boolean shownInfoOnce = false;
	public static int timeWait = 0;
	public static int currentRound = 0;
	
	public void initiate(int numberCards, int time) throws IOException, InterruptedException{
		System.out.println("Welcome to Have You Seen Me Before!");
		System.out.println("Initializing game...");
		timeWait = time;
		methods.fillDeckIndex();
		methods.fillDeckString();
		methods.fillVisualLocation();
		methods.fillInfoCardFile();
		//methods.printDeckArrayList();
		cardsToShow = methods.generateRow(numberCards);
		setupGui();
		displayCards();
		
	}
	
	public void displayCards() throws InterruptedException, IOException{
		System.out.println("Card will be shown in: 2 seconds");
		Thread.sleep(2000);
		for(int i = 0; i < cardsToShow.size(); i++){
			System.out.println("Card number " + (i+1) + ":   " + cardsToShow.get(i));
			int index = methods.convertStringToInt(cardsToShow.get(i));
			System.out.println(methods.returnVisualLocation(index).getPath());
			lbl1.setIcon(new ImageIcon(methods.returnVisualLocation(index).getPath()));
			frame.repaint();
			Thread.sleep(timeWait);
		}
		
		if(shownInfoOnce == false){
		lbl1.setIcon(new ImageIcon(methods.returnInfoLocation().getPath()));
		shownInfoOnce = true;
		}
		guess();
	}
	
	public void guess() throws IOException, InterruptedException{
		
		
		currentRound++;
		int correct = ((int) (10*Math.random()+1));
		System.out.println("RÄTT: " + correct);
		System.out.println("1 = yes, 2 = no");
		freeCards = methods.calculateFreeCards(cardsToShow);
		String correctCard = cardsToShow.get((int) (cardsToShow.size()*Math.random()));
		for(int i = 0; i < 10; i++ ){
			if(i+1 == correct){
				int index = methods.convertStringToInt(correctCard);
				lbl1.setIcon(new ImageIcon(methods.returnVisualLocation(index).getPath()));
				System.out.println("Have you seen me before?   :::   " + correctCard);
				
				int answer = 0;
				int reply = JOptionPane.showConfirmDialog(null, "Have you seen this card before?", "?", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					answer = 1;
				}else{
					answer = 2;
				}
	
				if(answer == 1){
					System.out.println("GRATTIS, DU VAN!");
					JOptionPane.showMessageDialog(null, "Grattis, du klarade denna nivå!");
					
					//CONTINUE LEVEL 2
					if(cardsToShow.size() == 9){
						System.out.println("Du klarade ut EASY!!!");
						JOptionPane.showMessageDialog(null, "Du klarade ut hela nivån! Grattis!");
						System.exit(1);
					}else{
					System.out.println("\n\n\nNext level, one more card. Restarting..." );
					JOptionPane.showMessageDialog(null, "Du klarade runda" + currentRound +"! Nästa nivå, ett kort extra!");
					frame.setVisible(false);
					initiate((52-freeCards.size()) +1, timeWait);
					}
				}else{
					System.out.println("Sorry, den har du sett :(");
					JOptionPane.showMessageDialog(null, "Tyvärr, denna har du sett :(");
					JOptionPane.showMessageDialog(null, "Du dog på runda: " + currentRound);
					System.exit(1);
		}
		}else{
			String card = freeCards.get((int)((51-cardsToShow.size())*Math.random() +1));
			int index = methods.convertStringToInt(card);
			lbl1.setIcon(new ImageIcon(methods.returnVisualLocation(index).getPath()));
			System.out.println("Have you seen me before?   :::   " + card);
			int answer = 0;
			int reply = JOptionPane.showConfirmDialog(null, "Have you seen this card before?", "?", JOptionPane.YES_NO_OPTION);
			if(reply == JOptionPane.YES_OPTION){
				answer = 1;
			}else{
				answer = 2;
			}
		
			if(answer == 2){
				System.out.println("So far so good!");
			}else{
				System.out.println("Nähä du, idiot!");
				JOptionPane.showMessageDialog(null, "Sorry, du förlorade på runda: " + currentRound);
				System.exit(1);
			}
		}
		}
	}
	
	public void setupGui(){
		frame = new JFrame();
		frame.setSize(570,770);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		lbl1 = new JLabel();
		frame.setLayout(new FlowLayout());
		frame.add(lbl1);
		
	}
	

	
	
	
}
