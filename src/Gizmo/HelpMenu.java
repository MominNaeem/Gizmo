package Gizmo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

/*
 * Author: Momin Naeem
 * Date: Jan 15, 2019
 * Course: ICS4U1
 * Topic: Gizmo
 * Title: HelpMenu
 * Description: This is the class where it contains the HelpMenu properties and the labels and 
 * images.
 */

public class HelpMenu extends JFrame {

	// Fields
	ApplicationTest main;
	JPanel area;
	JButton back;
	JLabel help1;
	JLabel help2;
	JLabel help3;
	JLabel help4;
	JLabel help5;
	JLabel GateChips;

	// Primary Constructor
	public HelpMenu(ApplicationTest main) {
		this.main = main;
		// Sets JFrame's size, background color, and exit operation.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1300, 800);
		setBackground(Color.DARK_GRAY);
		// Instanitates all necessary components
		area = new JPanel();
		back = new JButton("Back");
		help1 = new JLabel("");
		help2 = new JLabel("");
		help5 = new JLabel("");
		// Sets the JLabels' texts, sizes, and locations
		Font serifFont = new Font("Serif", Font.BOLD, 14);
		help1.setFont(serifFont);
		help1.setText(
				"<html>This program helps engineers with building circuits and testing out new components outside of the school environment. With many gate chips, such as an ANDChip, NANDChip, ORChip, NORChip, XORChip, and a NOTChip. Logic gate chips are truly versatile chips. They do not have to be used just for pure logic circuits. In fact, they have a wide range of uses. They can be used as inverters, as active low inputs, active low outputs, active high inputs, active high outputs. One of the most used chips is the NAND gate. The differences from these chips are there pins and where they lead to in the chip itself.<html>");
		help1.setSize(1300, 150);
		help1.setLocation(10, -30);

		help2.setFont(serifFont);
		help2.setText(
				"<html>In this breadboard screen, there is a breadboard on the left with two power sources (positive and negative) on the top and bottom of the breadboard. With rows and columns in the middle where resistors and wires as well as LEDs can be placed. From the left side, there are chips with images of its symbol and buttons for wires and resistors. There are also other features such as running the circuit, stopping it, going back to the main menu as well as undoing a mistake you made.<html>");
		help2.setSize(440, 300);
		help2.setLocation(20, 250);

		help5.setFont(serifFont);
		help5.setText(
				"<html>In this schematic screen, there is a schematic drawing on the left with lines indicating where the rows and columns are for the breadboard. On the left, there are buttons for each component you can place down. When placed down on the drawing, there will be a drawing of the component instead of a image. Other features in this screen are undoing a mistake, going back to the main menu, and saving the drawing with a in-built screenshot system. These screenshots will be saved in the Program's file.<html>");
		help5.setSize(400, 300);
		help5.setLocation(900, 260);

		help3 = new JLabel(new ImageIcon("Images/Help1.png"));
		help3.setBounds(75, 200, 300, 130);

		help4 = new JLabel(new ImageIcon("Images/Help2.png"));
		help4.setBounds(935, 200, 300, 129);

		GateChips = new JLabel(new ImageIcon("Images/GateChips.gif"));
		GateChips.setBounds(485, 100, 380, 595);

		// Sets JPanel layout
		area.setLayout(null);
		// Sets the buttons locations
		back.setBounds(610, 705, 120, 40);
		// Adds MouseListeners to buttons
		back.addMouseListener(backL);
		// Adds components to the JPanel
		add(GateChips);
		add(help2);
		add(help3);
		add(help4);
		add(help5);
		area.add(back);
		area.add(help1);
		// area.add(help2);
		// area.add(help3);
		// area.add(help4);
		// Adds JPanel area to this class
		add(area);
	}

	// MouseListener used to check if the user clicked the back button
	MouseListener backL = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (back.contains(e.getPoint())) {
				main.switchFrame(main.MAIN_MENU);
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
