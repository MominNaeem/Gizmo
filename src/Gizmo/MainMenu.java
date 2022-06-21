package Gizmo;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

/*
 * Author: Momin Naeem
 * Date: Jan 10, 2019
 * Course: ICS4U1
 * Topic: Gizmo
 * Title: MainMenu
 * Description: This class contains the MainMenu components such as the images, buttons, panels, 
 * also a music/sound effect method. This also contains all the mouse listeners for each button.
 */

public class MainMenu extends JFrame {

	// Fields
	JButton exit;
	JPanel exitArea;
	JButton breadBoardMenu;
	JButton credits;
	JButton schematicsMenu;
	JLabel background, title;
	ApplicationTest main;

	// Clip variable for the music method
	static Clip clip;

	// Panel + Button of the Game 1 Image
	JPanel gamep = new JPanel();
	JButton gameb = new JButton();

	// Panel + Button of the Game 1 Image
	JPanel game2p = new JPanel();
	JButton game2b = new JButton();

	// Panel + Button of the Game 1 Image
	JPanel game3p = new JPanel();
	JButton game3b = new JButton();

	// Panel + Button of the Game 1 Image
	JPanel game4p = new JPanel();
	JButton game4b = new JButton();

	// Primary Constructor
	public MainMenu(ApplicationTest main) {

		this.main = main;
		// Sets JFrame's size, background color, and exit operation.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1300, 725);
		setBackground(Color.DARK_GRAY);
		// instanitates all necessary components
		exitArea = new JPanel();
		exitArea.setBounds(0, 0, 1300, 725);
		exitArea.setLayout(null);
		// sets the JPanel's layout and adds all components to it
		// adds the Mouselisteners to the buttons
		// adds JPanel to the JFrame

		// Start button, containing its boundaries, image file, and action listener
		gameb.setIcon(new ImageIcon("Images/BreadBoard.gif"));
		gamep.setBounds(340, 380, 281, 84);
		gameb.setBounds(340, 380, 281, 84);
		gamep.add(gameb);
		gameb.addMouseListener(breadBoardL);
		gamep.addMouseListener(breadBoardL);

		// Start button, containing its boundaries, image file, and action listener
		game2b.setIcon(new ImageIcon("Images/Schematic.gif"));
		game2p.setBounds(650, 380, 281, 84);
		game2b.setBounds(650, 380, 281, 84);
		game2p.add(game2b);
		game2b.addMouseListener(schematicsL);
		game2p.addMouseListener(schematicsL);

		// Start button, containing its boundaries, image file, and action listener
		game3b.setIcon(new ImageIcon("Images/Exit.gif"));
		game3p.setBounds(340, 580, 281, 84);
		game3b.setBounds(340, 580, 281, 84);
		game3p.add(game3b);
		game3b.addMouseListener(exitL);
		game3p.addMouseListener(exitL);

		// Start button, containing its boundaries, image file, and action listener
		game4b.setIcon(new ImageIcon("Images/Help.gif"));
		game4p.setBounds(650, 580, 281, 84);
		game4b.setBounds(650, 580, 281, 84);
		game4p.add(game4b);
		game4b.addMouseListener(creditsL);
		game4p.addMouseListener(creditsL);

		background = new JLabel(new ImageIcon("Images/background.gif"));
		background.setBounds(0, 0, 1300, 725);

		title = new JLabel(new ImageIcon("Images/Title.png"));
		title.setBounds(400, 150, 504, 128);

		exitArea.add(gameb);
		exitArea.add(gamep);
		exitArea.add(game2b);
		exitArea.add(game2p);
		exitArea.add(game3b);
		exitArea.add(game3p);
		exitArea.add(game4b);
		exitArea.add(game4p);
		exitArea.add(title);
		exitArea.add(background);

		// Adds JPanel to the JFrame
		add(exitArea);

	}

	// This is the music method where it allows music to be played from anywhere in
	// this class.
	public static void music(String musicLocation) {

		try {
			File Sound = new File(musicLocation);
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(Sound);
			clip = AudioSystem.getClip();
			clip.open(audioInput);
			clip.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * MouseListener which is used to detect if the exit button has been pressed
	 */
	public MouseListener exitL = new MouseListener() {
		@Override
		public void mouseClicked(MouseEvent e) {
			Point x = e.getPoint();
			if (game3b.contains(x)) {
				System.exit(0);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	};
	/**
	 * MouseListener used to detect if the BreadBoardMenu button has been pressed
	 */
	public MouseListener breadBoardL = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (gameb.contains(e.getPoint())) {
				main.switchFrame(main.BREAD_BOARD_MENU);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

	};
	/**
	 * MouseListener used to detect if the credits button has been pressed
	 */
	public MouseListener creditsL = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (game2b.contains(e.getPoint())) {
				main.switchFrame(main.CREDIT_MENU);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

	};
	/**
	 * MouseListener used to detect if the SchematicsMenu button has been pressed
	 */
	public MouseListener schematicsL = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (game4b.contains(e.getPoint())) {
				main.switchFrame(main.SCHEMATICS_MENU);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

	};
}