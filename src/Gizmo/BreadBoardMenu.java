package Gizmo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * Author: Momin Naeem
 * Date: Jan 11, 2019
 * Course: ICS4U1
 * Topic: Gizmo
 * Title: BreadBoardMenu
 * Description: This class contains all the GUI and button related parts of the breadboard 
 * screen. It constructs all the buttons on the side with its images and labels. As well as 
 * all their Mouselistener actions if clicked. It also controls what the user wants to put on 
 * the board when clicked on a component.
 */

public class BreadBoardMenu extends JFrame {

	// Fields for this class
	JPanel area;
	Graphics2D drawer;
	// Buttons
	JButton run;
	JButton back;
	JButton wireButton;
	JButton resistorButton;
	JButton undoButton;
	JButton deleteButton;
	JButton stopRun;
	// LABELS
	JLabel leds = new JLabel("LEDs");
	JLabel andchip = new JLabel("ANDChip");
	JLabel nandchip = new JLabel("NANDChip");
	JLabel norchip = new JLabel("NORChip");
	JLabel notchip = new JLabel("NOTChip");
	JLabel orchip = new JLabel("ORChip");
	JLabel xorchip = new JLabel("XORChip");
	// LEDS
	LED ledGreen;
	LED ledBlue;
	LED ledRed;
	// Chips
	ANDChip and;
	NANDChip nand;
	NORChip nor;
	NOTChip not;
	ORChip or;
	XORChip xor;
	Board board;
	Snapper snapper;
	// Boolean fields for components
	boolean resistorP = false;
	boolean resistorStep2 = false;
	boolean ledP = false;
	boolean wireP = false;
	boolean wireStep2 = false;
	boolean andP = false;
	boolean nandP = false;
	boolean norP = false;
	boolean notP = false;
	boolean orP = false;
	boolean xorP = false;
	boolean simulating;
	// ArrayLists for components
	ArrayList<JComponent> componentList = new ArrayList();
	ArrayList<Integer> componentListID = new ArrayList();
	// X and Y values for the board
	int x1 = 0;
	int y1 = 0;
	int x2 = 0;
	int y2 = 0;
	int ledID;
	ApplicationTest main;

	// Primary Constructor
	public BreadBoardMenu(ApplicationTest main) {
		this.main = main;
		// Sets JFrame's size, background color, and exit operation.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board = new Board(false);
		setSize(1300, 562);
		setBackground(Color.DARK_GRAY);
		// Instantiates all necessary components
		simulating = false;
		// LEDS
		ledID = 1;
		ledGreen = new LED(1, 0, 0);
		ledGreen.setState(true);
		ledRed = new LED(2, 0, 0);
		ledRed.setState(true);
		ledBlue = new LED(3, 0, 0);
		ledBlue.setState(true);
		// Chips
		and = new ANDChip(0, 0);
		nand = new NANDChip(0, 0);
		nor = new NORChip(0, 0);
		or = new ORChip(0, 0);
		xor = new XORChip(0, 0);
		not = new NOTChip(0, 0);
		area = new JPanel();
		// Buttons
		run = new JButton("Run");
		back = new JButton("Back");
		wireButton = new JButton("Wire");
		resistorButton = new JButton("Resistor");
		undoButton = new JButton("Undo");
		deleteButton = new JButton("Delete");
		stopRun = new JButton("Stop Simulation");
		snapper = new Snapper();
		// Sets JPanel layout
		area.setLayout(null);
		// FONT
		Font serifFont = new Font("Serif", Font.BOLD, 12);
		andchip.setFont(serifFont);
		andchip.setBounds(906, 124, 100, 30);

		nandchip.setFont(serifFont);
		nandchip.setBounds(906, 224, 100, 30);

		norchip.setFont(serifFont);
		norchip.setBounds(906, 424, 100, 30);

		orchip.setFont(serifFont);
		orchip.setBounds(906, 324, 100, 30);

		xorchip.setFont(serifFont);
		xorchip.setBounds(1106, 124, 100, 30);

		notchip.setFont(serifFont);
		notchip.setBounds(1106, 224, 100, 30);

		leds.setFont(serifFont);
		leds.setBounds(930, 0, 100, 30);
		add(andchip);
		add(nandchip);
		add(norchip);
		add(orchip);
		add(xorchip);
		add(notchip);
		add(leds);
		// Sets the JComponent's locations and size
		wireButton.setBounds(1050, 250, 100, 30);
		undoButton.setBounds(1050, 300, 100, 30);
		deleteButton.setBounds(1050, 350, 100, 30);
		resistorButton.setBounds(1150, 250, 100, 30);
		stopRun.setBounds(1050, 400, 200, 30);
		run.setBounds(1150, 300, 100, 30);
		back.setBounds(1150, 350, 100, 30);
		ledGreen.setBounds(850, 0, ledGreen.getWidth(), ledGreen.getHeight());
		ledRed.setBounds(875, 0, ledGreen.getWidth(), ledGreen.getHeight());
		ledBlue.setBounds(900, 0, ledGreen.getWidth(), ledGreen.getHeight());
		and.setLocation(850, 50);
		nand.setLocation(850, 150);
		nor.setLocation(850, 350);
		or.setLocation(850, 250);
		xor.setLocation(1050, 50);
		not.setLocation(1050, 150);
		// Adds MouseListeners to all components
		stopRun.addMouseListener(stopRunL);
		undoButton.addMouseListener(undoL);
		deleteButton.addMouseListener(deleteL);
		resistorButton.addMouseListener(resistorL);
		run.addMouseListener(runL);
		back.addMouseListener(backL);
		wireButton.addMouseListener(wireL);
		ledGreen.addMouseListener(ledLGreen);
		ledRed.addMouseListener(ledLRed);
		ledBlue.addMouseListener(ledLBlue);
		board.addMouseListener(boardL);
		and.addMouseListener(andL);
		nand.addMouseListener(nandL);
		nor.addMouseListener(norL);
		or.addMouseListener(orL);
		xor.addMouseListener(xorL);
		not.addMouseListener(notL);
		// Adds components to the JPanel
		setup();
		// Adds JPanel area to this class
		add(area);

	}

	// Checks for when the user wants to stop the simulation to add more components
	// on
	MouseListener stopRunL = new MouseListener() {

		// MouseClicked method
		@Override
		public void mouseClicked(MouseEvent e) {
			if (stopRun.contains(e.getPoint())) {
				// Resets all the components states
				resetAllStates();
				// Sets simulating to false so the user can place things again
				simulating = false;
				// Redraws the frame
				repaint();
			}
		}

		// MouseListener required methods
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

	// Mouselistener used to check if the user clicked on the run button
	MouseListener runL = new MouseListener() {

		// MouseClicked method
		@Override
		public void mouseClicked(MouseEvent e) {
			if (run.contains(e.getPoint())) {
				simulating = true;
				CircuitEngine engine = new CircuitEngine(componentList, componentListID);
			}
			repaint();
		}

		// MouseListener required methods
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

	// Mouselistener used to check if the user clicked on the back button
	MouseListener backL = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (run.contains(e.getPoint())) {
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
	// Mouselistener used to check if the user clicked on the green LED button
	MouseListener ledLGreen = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (ledGreen.contains(e.getPoint())) {
				resetPlacer();
				ledID = 1;
				ledP = true;
			}

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

	// Mouselistener used to check if the user clicked on the red LED button
	MouseListener ledLRed = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (ledRed.contains(e.getPoint())) {
				resetPlacer();
				ledP = true;
				ledID = 2;
			}
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

	// Mouselistener used to check if the user clicked on the blue LED button
	MouseListener ledLBlue = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (ledBlue.contains(e.getPoint())) {
				resetPlacer();
				ledP = true;
				ledID = 3;
			}
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

	// Mouselistener used to check if the user clicked on the board button
	MouseListener boardL = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (board.contains(e.getPoint()) && !simulating) {

				if (ledP == true) { // If the user choose to place an LED before hand
					// Snaps to the nearest point that the user clicked
					x1 = snapper.snapToX((int) e.getPoint().getX());
					y1 = snapper.snapToY((int) e.getPoint().getY());

					// Makes sure you can't place an LED off the edge of the breadboard
					if (x1 >= 783 - 60) {
						// Error message
						JOptionPane.showMessageDialog(null, "You cannot place an LED here");

					} else if (!snapper.ledPinUsed(x1, y1)) { // If the pin is not already being used

						componentList.add(0, new LED(ledID, x1, y1)); // Adds an led to the component list
						componentListID.add(0, 1); // Adds the led's ID to the componentlistID array
						// Sets the visual location of the LED
						((LED) componentList.get(0)).setLocation(snapper.snapToX(x1) + 1, snapper.snapToY(y1) - 11);
						area.removeAll(); // Removes all the components from the JPanel
						repaint();
						redrawAll(); // Adds all components to JPanel in proper order
						repaint();

					} else {

						// Error message
						JOptionPane.showMessageDialog(null, "This pin is already being used");

					}

				} else if (wireP == true) { // If the user choose to place a wire before hand

					if (!wireStep2) { // If the user has not chosen the first point for the wire yet

						x1 = snapper.wSnapToX(e.getX(), e.getY());
						y1 = snapper.wSnapToY(e.getY());

						if (!snapper.pinUsed(x1, y1)) { // If the pin the user choose is not used

							wireStep2 = true;

						} else {

							// Error Message
							JOptionPane.showMessageDialog(null, "This pin is already being used");

						}

					} else {

						x2 = snapper.wSnapToX(e.getX(), e.getY());
						y2 = snapper.wSnapToY(e.getY());

						// Checks if the pin for the second point is being used and if the wire connects
						// a logical chip output to its input
						if (!snapper.pinUsed(x2, y2)
								&& (!snapper.connectsInOut(x1, y1, x2, y2) || (new Wire(x1, y1, x2, y2)).isGroundWire()
										|| (new Wire(x1, y1, x2, y2)).isPowerWire())) {

							wireStep2 = false;
							componentListID.add(0, 2);
							componentList.add(0, new Wire(x1, y1, x2, y2));
							((Wire) componentList.get(0)).setLocation(0, 0);
							area.removeAll();
							repaint();
							redrawAll();
							repaint();

						} else {

							// Error Message
							JOptionPane.showMessageDialog(null,
									"This pin is already being used or you tried to connect a chip's out to it's input");
							// Makes the pin originally taken up by the first point of the wire free
							snapper.resetPin(x1, y1);
							// Makes the pin originally taken up by the second point of the wire free
							snapper.resetPin(x2, y2);
							wireStep2 = false;
						}
					}

				} else if (andP == true) { // If the user choose to place an AND chip

					x1 = snapper.cSnapToX((int) e.getPoint().getX());
					y1 = snapper.cSnapToY();
					if (!snapper.cPinUsed(x1, y1, 3)) { // Checks that none of the pins the chip would need are used

						componentList.add(0, new ANDChip(x1, y1));
						componentListID.add(0, 3);
						((ANDChip) componentList.get(0)).setLocation(x1 - 10, y1);
						area.removeAll();
						repaint();
						redrawAll();
						repaint();
					}

				} else if (nandP == true) { // If the user choose to place a NAND chip

					x1 = snapper.cSnapToX((int) e.getPoint().getX());
					y1 = snapper.cSnapToY();
					if (!snapper.cPinUsed(x1, y1, 4)) { // Checks that none of the pins the chip would need are used

						componentListID.add(0, 4);
						componentList.add(0, new NANDChip(x1, y1));
						((NANDChip) componentList.get(0)).setLocation(x1 - 10, y1);
						area.removeAll();
						repaint();
						redrawAll();
						repaint();
					}

				} else if (norP == true) { // If the user choose to place a NOR chip

					x1 = snapper.cSnapToX((int) e.getPoint().getX());
					y1 = snapper.cSnapToY();
					if (!snapper.cPinUsed(x1, y1, 5)) { // Checks that none of the pins the chip would need are used

						componentList.add(0, new NORChip(x1, y1));
						componentListID.add(0, 5);
						((NORChip) componentList.get(0)).setLocation(x1 - 10, y1);
						area.removeAll();
						repaint();
						redrawAll();
						repaint();
					}

				} else if (notP == true) { // If the user choose to place a NOT chip

					x1 = snapper.cSnapToX((int) e.getPoint().getX());
					y1 = snapper.cSnapToY();
					if (!snapper.cPinUsed(x1, y1, 6)) { // Checks that none of the pins the chip would need are used

						componentList.add(0, new NOTChip(x1, y1));
						componentListID.add(0, 6);
						((NOTChip) componentList.get(0)).setLocation(x1 - 10, y1);
						area.removeAll();
						repaint();
						redrawAll();
						repaint();
					}

				} else if (orP == true) { // If the user choose to place an OR chip

					x1 = snapper.cSnapToX((int) e.getPoint().getX());
					y1 = snapper.cSnapToY();
					if (!snapper.cPinUsed(x1, y1, 7)) { // Checks that none of the pins the chip would need are used

						componentList.add(0, new ORChip(x1, y1));
						componentListID.add(0, 7);
						((ORChip) componentList.get(0)).setLocation(x1 - 10, y1);
						area.removeAll();
						repaint();
						redrawAll();
						repaint();
					}

				} else if (xorP == true) { // If the user choose to place a XOR chip

					x1 = snapper.cSnapToX((int) e.getPoint().getX());
					y1 = snapper.cSnapToY();
					if (!snapper.cPinUsed(x1, y1, 8)) { // Checks that none of the pins the chip would need are used

						componentList.add(0, new XORChip(x1, y1));
						componentListID.add(0, 8);
						((XORChip) componentList.get(0)).setLocation(x1 - 10, y1);
						area.removeAll();
						repaint();
						redrawAll();
						repaint();
					}

				} else if (resistorP == true) { // If the user choose to place a resistor

					if (!resistorStep2) {

						// Snaps to the resistor's first point to a ground pin
						x1 = snapper.resistorSnapToX(e.getX(), e.getY());
						y1 = snapper.resistorSnapToY(e.getY());
						if (!snapper.pinUsed(x1, y1)) {

							resistorStep2 = true;

						} else {

							// Error Message
							JOptionPane.showMessageDialog(null, "This pin is already being used");

						}

					} else {

						// Snaps the resistor's second point to a pin on the middle rows of the
						// breadboard
						x2 = snapper.resistorSnapToX2(e.getX());
						y2 = snapper.resistorSnapToY2(e.getX(), e.getY());
						if (!snapper.pinUsed(x2, y2)) {

							resistorStep2 = false;
							componentList.add(0, new Resistor(x1, y1, x2, y2));
							componentListID.add(0, 9);
							((Resistor) componentList.get(0)).setLocation(0, 0);
							area.removeAll();
							repaint();
							redrawAll();
							repaint();

						} else {

							// Error Message
							JOptionPane.showMessageDialog(null, "This pin is already being used");
							resistorStep2 = false;
							// Makes the pin originally taken up by the first point of the resistor free
							snapper.resetPin(x1, y1);
							// Makes the pin originally taken up by the second point of the resistor free
							snapper.resetPin(x2, y2);
						}
					}
				}
				// Resets the placing boolean for all components
				resetPlacer();
			}
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

	// Checks to see if the user clicked the button to add a wire
	// wire
	MouseListener wireL = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (wireButton.contains(e.getPoint())) {
				if (wireP == false) {
					resetPlacer();
					wireP = true;
				} else {
					wireP = false;
				}
			}
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

	// The mouse listener which checks if the user clicked to add an AND gate on the
	// board
	MouseListener andL = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (and.contains(e.getPoint())) {
				if (andP == false) {
					resetPlacer();
					andP = true;
				} else {
					andP = false;
				}
			}
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

	// The mouse listener which checks if the user clicked to add an NAND gate on
	// the board
	MouseListener nandL = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (nand.contains(e.getPoint())) {
				if (nandP == false) {
					resetPlacer();
					nandP = true;
				} else {
					nandP = false;
				}
			}
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

	// The mouse listener which checks if the user clicked to add an NOR gate on the
	// board
	MouseListener norL = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (nor.contains(e.getPoint())) {
				if (norP == false) {
					resetPlacer();
					norP = true;
				} else {
					norP = false;
				}
			}
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

	// The mouse listener which checks if the user clicked to add an NOT gate on the
	// board
	MouseListener notL = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (not.contains(e.getPoint())) {
				if (notP == false) {
					resetPlacer();
					notP = true;
				} else {
					notP = false;
				}
			}
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

	// The mouse listener which checks if the user clicked to add an OR gate on the
	// board
	MouseListener orL = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (or.contains(e.getPoint())) {
				if (orP == false) {
					resetPlacer();
					orP = true;
				} else {
					orP = false;
				}
			}
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

	// The mouse listener which checks if the user clicked to add an XOR gate on the
	// board
	MouseListener xorL = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (xor.contains(e.getPoint())) {
				if (xorP == false) {
					resetPlacer();
					xorP = true;
				} else {
					xorP = false;
				}
			}
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

	// The mouse listener which checks if the user clicked to add a resistor to the
	// board
	MouseListener resistorL = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (resistorButton.contains(e.getPoint())) {// If the user clicked on the resistor button
				if (resistorP == false) {
					resetPlacer();
					resistorP = true;
				} else {
					resistorP = false;
				}

			}
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

	// The mouse listener which checks if the user clicked the undo button
	MouseListener undoL = new MouseListener() {

		// If button clicked
		@Override
		public void mouseClicked(MouseEvent e) {

			if (undoButton.contains(e.getPoint()) && !simulating) {

				if (componentList.size() > 0) {

					// Resets the pins that the component once used so it can be used again
					if (componentListID.get(0) == 1) { // If the component is an led

						// Resets the pins based on an LED Properties
						snapper.resetLEDPin(((LED) componentList.get(0)).getPosition());

					} else if (componentListID.get(0) == 2) { // If the components is a wire

						// Resets the pins based on an Wire's properties
						snapper.resetWirePin(((Wire) componentList.get(0)).getPowerPin(),
								((Wire) componentList.get(0)).getGroundPin());

						// If the components is a logical chip
					} else if (componentListID.get(0) >= 3 && componentListID.get(0) <= 8) {

						// Resets the pins based on a logical chip's properties
						snapper.resetChipPin(new Point(((Chip) componentList.get(0)).getPoisiton(), 217));

					} else if (componentListID.get(0) == 9) { // Otherwise the components is a resistor

						// Resets the pins based on a resistors properties
						snapper.resetWirePin(((Resistor) componentList.get(0)).getPowerPin(),
								((Resistor) componentList.get(0)).getGroundPin());

					}

					componentList.remove(0);// Removes the latest added component
					componentListID.remove(0);// Removes the latest added component ID
					// Redraws the frame
					area.removeAll();
					repaint();
					redrawAll();
					repaint();

				} else {

					// Error/Test Message
					System.out.println("Empty array");

				}
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

	// The mouse listener which checks if the user clicked the delete button
	MouseListener deleteL = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {

			// If the user clicked on the delete button and the program is not simulating
			if (deleteButton.contains(e.getPoint()) && !simulating) {

				componentList.removeAll(componentList);// Removes all components from the component list
				componentListID.removeAll(componentListID);// Removes all component IDs from the componentID list
				snapper.clearAllPins();// Clears all the pins so the user can reuse them
				// Redraws the panel
				area.removeAll();
				repaint();
				redrawAll();
				repaint();

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

	// Adds all the base components to the screen
	private void setup() {

		area.add(run);
		area.add(back);
		area.add(wireButton);
		area.add(ledGreen);
		area.add(ledRed);
		area.add(ledBlue);
		area.add(board);
		area.add(and);
		area.add(nand);
		area.add(nor);
		area.add(not);
		area.add(or);
		area.add(xor);
		area.add(resistorButton);
		area.add(undoButton);
		area.add(deleteButton);
		area.add(stopRun);
		area.add(and);
		area.add(nandchip);
		area.add(norchip);
		area.add(orchip);
		area.add(xorchip);
		area.add(notchip);
		area.add(leds);

	}

	// Resets all the booleans which tell the program which component to place to
	// false
	private void resetPlacer() {

		nandP = false;
		norP = false;
		notP = false;
		orP = false;
		xorP = false;
		ledP = false;
		andP = false;
		xorP = false;
		if (wireStep2 == false) {
			wireP = false;
		}
		if (resistorStep2 == false) {
			resistorP = false;
		}
	}

	// Adds all components added by the user back into the JPanel and redraws it
	private void redrawAll() {

		for (int i = 0; i < componentList.size(); i++) {

			area.add(componentList.get(i));
		}

		setup();
	}

	// Resets the state of all components to their basic un-powered state
	public void resetAllStates() {

		for (int i = 0; i < componentList.size(); i++) {

			if (componentListID.get(i) == 1) { // If the components is an LED
				((LED) componentList.get(i)).resetState();
			} else if (componentListID.get(i) == 3) { // If the component is an AND chip
				((ANDChip) componentList.get(i)).resetState();
			} else if (componentListID.get(i) == 4) { // If the component is a NAND chip
				((NANDChip) componentList.get(i)).resetState();
			} else if (componentListID.get(i) == 5) { // If the component is a NOR chip
				((NORChip) componentList.get(i)).resetState();
			} else if (componentListID.get(i) == 6) { // If the component is a NOT chip
				((NOTChip) componentList.get(i)).resetState();
			} else if (componentListID.get(i) == 7) { // If the component is a OR chip
				((ORChip) componentList.get(i)).resetState();
			} else if (componentListID.get(i) == 8) { // If the component is a XOR chip
				((XORChip) componentList.get(i)).resetState();
			}
		}
	}
}
