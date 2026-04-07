// Chase Batchelor
// April 6, 2026
// CPSC-39-12106

// test github push

import javax.swing.*;
import java.awt.Image;
import java.awt.event.ActionListener;


public class AdventureGameGUI {
	private JFrame frame;
	private JTextArea txtArea;
	private JButton btnChoice1;
	private JButton btnChoice2;
    private JButton btnPickup;
    private JLabel lblImage;
	
	private AdventureGame game;
	
	public AdventureGameGUI() {
		initialize();
		game = new AdventureGame();
		updateDisplay();
	}
	
	public void initialize() {
		frame = new JFrame("Escape Room Adventure");
		frame.setSize(700, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		
		txtArea = new JTextArea();
		txtArea.setBounds(20, 20, 300, 250);
		txtArea.setEditable(false);
		txtArea.setLineWrap(true);
		txtArea.setWrapStyleWord(true);
		frame.add(txtArea);
		
		lblImage = new JLabel();
		lblImage.setBounds(350, 20, 300, 300);
		frame.add(lblImage);
		
		btnChoice1 = new JButton("Choice 1");
		btnChoice1.setBounds(50, 350, 250, 50);
		frame.add(btnChoice1);
		
		btnChoice1.addActionListener(e -> {
			game.makeChoice(0);
			updateDisplay();
		});
		
		btnChoice2 = new JButton("Choice 2");
		btnChoice2.setBounds(350, 350, 250, 50);
		frame.add(btnChoice2);
		
		btnChoice2.addActionListener(e -> {
			game.makeChoice(1);
			updateDisplay();
		});
		
		btnPickup = new JButton("Pick Up Item");
		btnPickup.setBounds(200, 430, 250, 50);
		frame.add(btnPickup);
		
		btnPickup.addActionListener(e -> {
			game.pickUpItem();
			updateDisplay();
		});
		
		frame.setVisible(true);
	}
	
	public void updateDisplay() {
		Scene scene = game.getCurrentScene();
		Player player = game.getPlayer();
		
		String imagePath = scene.getImagePath();
		
		if (imagePath != null && !imagePath.equalsIgnoreCase("none") ) {
			ImageIcon icon = new ImageIcon(imagePath);
			Image scaledImage = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
			lblImage.setIcon(new ImageIcon(scaledImage));
		} else {
			lblImage.setIcon(null);
		}
		
		if(scene.getSceneId() == 5) {
			txtArea.setText(game.getFinalRoomMessage());
			btnChoice1.setEnabled(false);
			btnChoice2.setEnabled(false);
			btnPickup.setEnabled(false);
			return;
		}
		
		String text = "";
		text += "==========================\n";
		text += scene.getTitle() + "\n";
		text += "==========================\n";
		text += scene.getDescription() + "\n\n";
		text += player.getInventoryText() + "\n\n";
		
		txtArea.setText(text);
		
		if (scene.getChoices().size() > 0) {
			btnChoice1.setText(scene.getChoices().get(0).getText());
			btnChoice1.setEnabled(true);
		} else {
			btnChoice1.setEnabled(false);
		}
		
		if (scene.getChoices().size() > 1) {
			btnChoice2.setText(scene.getChoices().get(1).getText());
			btnChoice2.setEnabled(true);
		} else {
			btnChoice2.setEnabled(false);
		}
		
		if (scene.getItem() != null) {
			btnPickup.setEnabled(true);
			btnPickup.setText("Pick Up " + scene.getItem().getName());
		} else {
			btnPickup.setEnabled(false);
			btnPickup.setText("No Item Here");
		}
	}
	
	public static void main(String[] args) {
		new AdventureGameGUI();
	}
}