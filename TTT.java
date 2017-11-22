package tickTackToe;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TTT extends JFrame implements ActionListener {

	public static final int BOARD_SIZE = 3;

	private static enum gameStatus {
		Xwins, Zwins, Incomplete, Tie;
	}

	private JButton[][] buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
	private boolean crossTurn = true;

	TTT() {
		super.setSize(800, 800);
		super.setTitle("Tic_Tac-Toe");
		GridLayout grid = new GridLayout(BOARD_SIZE, BOARD_SIZE);
		super.setLayout(grid);
		Font font = new Font("Comic sans", 1, 50); // 1 means bold, 150 is the
													// text size
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				JButton button = new JButton("");
				buttons[i][j] = button;
				button.setFont(font);
				button.addActionListener(this);
				super.add(button);
			}
		}
		super.setResizable(false);
		super.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton ClickedButton = (JButton) e.getSource();
		makeMove(ClickedButton);
		gameStatus gs = getgameStatus();
		if (gs == gameStatus.Incomplete) {
			return;
		}
		declareWinner(gs);
		Font font = new Font("Comic sans", 1, 50);
		int choice = JOptionPane.showConfirmDialog(this, "Do you want to continue playing?");
		if (choice == JOptionPane.YES_OPTION) {
			for (int i = 0; i < BOARD_SIZE; i++) {
				for (int j = 0; j < BOARD_SIZE; j++) {
					JButton button = new JButton("");
					buttons[i][j] = button;
					button.setFont(font);
					button.addActionListener(this);
					super.add(button);
				}
			
			}
			crossTurn = true;
		} else {
			super.dispose();
		}

	}

	private void makeMove(JButton clickedButton) {
		String ButtonText = clickedButton.getText();
		if (ButtonText.length() > 0) {
			JOptionPane.showMessageDialog(this, "Invalid move");
		}
		if (crossTurn) {
			clickedButton.setText("X");
		} else {
			clickedButton.setText("0");
		}
		crossTurn = !crossTurn;

	}

	 private gameStatus getgameStatus() {
		 for(int i=0;i<BOARD_SIZE;i++){
			 String s1=buttons[i][0].getText();
			 String s2=buttons[0][i].getText();
			 boolean bool1=true;
			 boolean bool2=true;
			 if(s1.equals("")){
				 bool1=false;
			 }
			 if(s2.equals("")){
				 bool2=false;
			 }
			 for(int j=0;j<BOARD_SIZE;j++){
				 if(bool1){
					 if(!s1.equals(buttons[i][j].getText())){
						 bool1=false;
					 }
					 
				 }
				 if(bool2){
					 if(!s2.equals(buttons[j][i].getText())){
						 bool2=false;
					 }
				 }
			 }
			 if(bool1){
				 if(s1.equals("X")){
					 return gameStatus.Xwins;
				 }
				 else return gameStatus.Zwins;
			 }
			 if(bool2){
				 if(s2.equals("0")){
					 return gameStatus.Zwins;
				 }
				 else return gameStatus.Xwins;
			 }
		 }
		 return gameStatus.Incomplete;
	 }

	private void declareWinner(gameStatus gs) {
		if (gs == gameStatus.Xwins) {
			JOptionPane.showMessageDialog(this, "X has won");
		}
		if (gs == gameStatus.Zwins) {
			JOptionPane.showMessageDialog(this, "Z has won");
		}
		if(gs==gameStatus.Tie){
			JOptionPane.showMessageDialog(this, "Game has been tied");
		}

	}

}
