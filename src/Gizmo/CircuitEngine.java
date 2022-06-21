package Gizmo;

import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JComponent;

/*
 * Author: Momin Naeem
 * Date: Jan 7, 2019
 * Course: ICS4U1
 * Topic: Gizmo
 * Title: CircuitEngine
 * Description: This class is the engine of the program, when the circuit runs, it will 
 * run through the engine and check which column and row is powered and checks each power 
 * line where it updates components ends as well.
 */

public class CircuitEngine {

	// Fields of the placements of the component
	ArrayList<JComponent> components;
	ArrayList<Integer> componentID;
	int numOfLoops;
	boolean topRow[];
	boolean bottomRow[];
	boolean groundTopRow[];
	boolean groundBottomRow[];
	boolean resistorGroundTop[];
	boolean resistorGroundBottom[];

	// Primary Constructor
	// Lists the ArrayList which contains all the components placed by the user and
	// the ID
	public CircuitEngine(ArrayList<JComponent> list, ArrayList<Integer> list2) {

		// Loads all components
		components = list;
		componentID = list2;
		numOfLoops = components.size() * 2;
		topRow = new boolean[800];
		bottomRow = new boolean[800];
		groundTopRow = new boolean[800];
		groundBottomRow = new boolean[800];
		resistorGroundTop = new boolean[800];
		resistorGroundBottom = new boolean[800];
		// Fills arrays to aviod null pointer execptions
		fillArrays();
		// Simulates the circuit
		simulateCircuit();

	}

	// Simulates the circuit
	final public void simulateCircuit() {

		checkResistors();
		for (int i = 0; i < numOfLoops; i++) {

			checkWires();
			checkChips();
			checkLEDs();

		}
	}

	// Checks and updates the power status of wires
	public void checkWires() {

		// Component array
		for (int i = 0; i < components.size(); i++) {

			if (componentID.get(i) == 2) {

				checkPowerLine(i);
			}
		}

		// ComponentID array
		for (int i = 0; i < componentID.size(); i++) {

			for (int j = 0; j < components.size(); j++) {

				if (componentID.get(j) == 2) {

					updateWireBoard(j);
				}
			}
		}
	}

	// Checks and updates the power status and output status of logical chip
	public void checkChips() {

		for (int i = 0; i < 5; i++) {

			for (int j = 0; j < components.size(); j++) {

				if (componentID.get(j) <= 8 && componentID.get(j) >= 3) {

					checkChipPower(j);
					checkChipGround(j);
					checkChipInput(j);
					checkChipOutput(j, componentID.get(j));
				}
			}
		}
	}

	// Checks the power status of the LEDs
	public void checkLEDs() {
		for (int i = 0; i < components.size(); i++) {

			if (componentID.get(i) == 1) {

				Point LEDPoint = ((LED) components.get(i)).getPosition();
				// If the wire is connected to the top part of the breadboard
				if (LEDPoint.getY() < 280 && LEDPoint.getY() > 100) {

					if (groundTopRow[(int) LEDPoint.getX() + 24] == true) {

						groundTopRow[(int) LEDPoint.getX()] = true;
					}
					if (resistorGroundTop[((int) LEDPoint.getX()) + 24] == true && topRow[(int) LEDPoint.getX()] == true
							&& groundTopRow[(int) LEDPoint.getX()] == false
							&& groundTopRow[(int) LEDPoint.getX() + 24] == false) {

						((LED) components.get(i)).setState(true);
						topRow[(int) LEDPoint.getX() + 24] = true;
					} else {
						((LED) components.get(i)).setState(false);
					}
					// If the wire is connected to the bottom part of the breadboard
				} else {
					if (groundBottomRow[(int) LEDPoint.getX() + 24] == true) {

						groundBottomRow[(int) LEDPoint.getX()] = true;
					}
					if (resistorGroundBottom[((int) LEDPoint.getX()) + 24] == true
							&& bottomRow[(int) LEDPoint.getX()] == true
							&& groundBottomRow[(int) LEDPoint.getX()] == false
							&& groundBottomRow[(int) LEDPoint.getX() + 24] == false) {

						((LED) components.get(i)).setState(true);
						bottomRow[(int) LEDPoint.getX() + 24] = true;
					} else {

						((LED) components.get(i)).setState(true);
					}
				}
			}
		}
	}

	// Fills each array so there are no null ppinter errors
	final public void fillArrays() {
		for (int i = 0; i < 800; i++) {
			topRow[i] = false;
		}
		for (int i = 0; i < 800; i++) {
			bottomRow[i] = false;
		}
		for (int i = 0; i < 800; i++) {
			groundBottomRow[i] = false;
		}
		for (int i = 0; i < 800; i++) {
			groundTopRow[i] = false;
		}
		for (int i = 0; i < 800; i++) {
			resistorGroundBottom[i] = false;
		}
		for (int i = 0; i < 800; i++) {
			resistorGroundTop[i] = false;
		}
	}

	// Checks if the wire is connected to the powerLine, and if so it sets the line
	// to the other end its connected to, to powered
	// variable is is the location of the wire in the arrayList
	public void checkPowerLine(int i) {

		// If the power pin is connected to the top power line
		if (((Wire) components.get(i)).getPowerPin().getY() == 50) {

			// Sets the wire's state to powered
			((Wire) components.get(i)).setState(true);
			if (((Wire) components.get(i)).getGroundPin().getY() < 280

					// If the ground pin is in the top part of the breadboard
					&& ((Wire) components.get(i)).getGroundPin().getY() > 110) {

				// Sets the row the wire is connected to, to powered
				topRow[(int) (((Wire) components.get(i)).getGroundPin().getX())] = true;
			} else if (((Wire) components.get(i)).getGroundPin().getY() > 250

					// If the ground is in the bottom part of the breadboard
					&& ((Wire) components.get(i)).getGroundPin().getY() < 410) {

				// Sets the row the wire is connected to, to powered
				topRow[(int) (((Wire) components.get(i)).getGroundPin().getX())] = true;
			}

			// If the ground pin is connected to the top power line
		} else if (((Wire) components.get(i)).getGroundPin().getY() == 50) {

			// Sets the wire's state to powered
			((Wire) components.get(i)).setState(true);
			if (((Wire) components.get(i)).getPowerPin().getY() < 280

					// If the power pin is in the top part of the breadboard
					&& ((Wire) components.get(i)).getPowerPin().getY() > 110) {

				// Sets the row the wire is connected to, to powered
				topRow[(int) (((Wire) components.get(i)).getPowerPin().getX())] = true;
			} else if (((Wire) components.get(i)).getPowerPin().getY() > 250

					// If the power in the bottom part of the breadboard
					&& ((Wire) components.get(i)).getPowerPin().getY() < 410) {

				// Sets the row the wire is connected to, to powered
				topRow[(int) (((Wire) components.get(i)).getPowerPin().getX())] = true;
			}

			// If the power pin is connected to the bottom power line
		} else if (((Wire) components.get(i)).getPowerPin().getY() == 482) {

			// Sets the wire's state to powered
			((Wire) components.get(i)).setState(true);
			if (((Wire) components.get(i)).getGroundPin().getY() < 280

					// If the ground pin is in the top part of the breadboard
					&& ((Wire) components.get(i)).getGroundPin().getY() > 110) {

				// Sets the row the wire is connected to, to powered
				topRow[(int) (((Wire) components.get(i)).getGroundPin().getX())] = true;
			} else if (((Wire) components.get(i)).getGroundPin().getY() > 250

					// If the ground pin in the bottom part of the breadboard
					&& ((Wire) components.get(i)).getGroundPin().getY() < 410) {

				// Sets the row the wire is connected to, to powered
				topRow[(int) (((Wire) components.get(i)).getGroundPin().getX())] = true;
			}

			// If the ground pin is connected to the bottom power line
		} else if (((Wire) components.get(i)).getGroundPin().getY() == 482) {

			// Sets the wire's state to powered
			((Wire) components.get(i)).setState(true);
			if (((Wire) components.get(i)).getPowerPin().getY() < 280

					// If the power pin is in the top part of the breadboard
					&& ((Wire) components.get(i)).getPowerPin().getY() > 110) {

				// Sets the row the wire is connected to, to powered
				topRow[(int) (((Wire) components.get(i)).getPowerPin().getX())] = true;
			} else if (((Wire) components.get(i)).getPowerPin().getY() > 250

					// If the power in the bottom part of the breadboard
					&& ((Wire) components.get(i)).getPowerPin().getY() < 410) {

				// Sets the row the wire is connected to, to powered
				topRow[(int) (((Wire) components.get(i)).getPowerPin().getX())] = true;
			}
		}
	}

	// Updates board's state based on the wire
	public void updateWireBoard(int i) {

		// If the wire is connected to the power row
		if (((Wire) components.get(i)).isPowerWire()) {

			updateWireEnds(i);
		}

		// If the wire is not connected to the ground or power runs
		if (!((Wire) components.get(i)).isGroundWire() && !((Wire) components.get(i)).isPowerWire()) {

			checkWirePoint1(i);
			checkWirePoint2(i);

			// If the wire is connected to the ground rows
		} else {

			updateGround(i);
		}
	}

	// Updates the boards state to be powered since the wire is powered
	public void updateWireEnds(int i) {

		if (((Wire) components.get(i)).getGroundPin().getY() < 280

				// If the ground pin is in the top part of the breadboard
				&& ((Wire) components.get(i)).getGroundPin().getY() > 110) {

			// Sets the row the wire is connected to, to powered
			topRow[(int) (((Wire) components.get(i)).getGroundPin().getX())] = true;
		} else if (((Wire) components.get(i)).getGroundPin().getY() > 250

				// If the ground in in the bottom part of the breadboard
				&& ((Wire) components.get(i)).getGroundPin().getY() < 410) {

			// Sets the row the wire is connected to, to powered
			bottomRow[(int) (((Wire) components.get(i)).getGroundPin().getX())] = true;
		}
		if (((Wire) components.get(i)).getPowerPin().getY() < 280

				// If the power pin is in the top part of the breadboard
				&& ((Wire) components.get(i)).getPowerPin().getY() > 110) {

			// Sets the row the wire is connected to, to powered
			topRow[(int) (((Wire) components.get(i)).getPowerPin().getX())] = true;
		} else if (((Wire) components.get(i)).getPowerPin().getY() > 250

				// If the power in the bottom part of the breadboard
				&& ((Wire) components.get(i)).getPowerPin().getY() < 410) {

			// Sets the row the wire is connected to, to powered
			bottomRow[(int) (((Wire) components.get(i)).getPowerPin().getX())] = true;
		}
	}

	// Checks if the wire gets powered, and if so powers the other end of the wire
	public void checkWirePoint1(int i) {

		Point wirePoint = ((Wire) components.get(i)).getPowerPin();

		// If the wire is connected to the top part of the breadboard
		if (wirePoint.getY() < 280 && wirePoint.getY() > 100) {

			// If the row the wire is connected to is powered
			if (topRow[(int) wirePoint.getX()] == true) {

				// The wire is powered
				((Wire) components.get(i)).setState(true);

				// The ends of the wire updates
				updateWireEnds(i);
			}

			// If the wire is connected to the bottom part of the breadboard
		} else if (wirePoint.getY() > 250 && wirePoint.getY() < 410) {

			// If the row the wire is connected to is powered
			if (bottomRow[(int) wirePoint.getX()] == true) {

				// The wire gets powered and updated
				((Wire) components.get(i)).setState(true);
				updateWireEnds(i);
			}
		}

		// If the wire is connected to the top part of the breadboard
		if (wirePoint.getY() < 280 && wirePoint.getY() > 100) {

			// If the row the wire is connected to is grounded
			if (groundTopRow[(int) wirePoint.getX()] == true) {

				// If the ground pin is in the top part of the breadboard
				if (((Wire) components.get(i)).getGroundPin().getY() < 280
						&& ((Wire) components.get(i)).getGroundPin().getY() > 110) {

					// Sets the row the wire is connected to, to grounded
					groundTopRow[(int) (((Wire) components.get(i)).getGroundPin().getX())] = true;
				} else if (((Wire) components.get(i)).getGroundPin().getY() > 250

						// If the ground in the bottom part of the breadboard
						&& ((Wire) components.get(i)).getGroundPin().getY() < 410) {

					// sets the row the wire is connected to, to grounded
					groundBottomRow[(int) (((Wire) components.get(i)).getGroundPin().getX())] = true;
				}
			}

			// If the wire is connected to the bottom part of the breadboard
		} else if (wirePoint.getY() > 250 && wirePoint.getY() < 410) {

			// If the row the wire is connected to is grounded
			if (groundBottomRow[(int) wirePoint.getX()] == true) {
				if (((Wire) components.get(i)).getGroundPin().getY() < 280

						// If the ground pin is in the top part of the breadboard
						&& ((Wire) components.get(i)).getGroundPin().getY() > 110) {

					// Sets the row the wire is connected to, to grounded
					groundTopRow[(int) (((Wire) components.get(i)).getGroundPin().getX())] = true;
				} else if (((Wire) components.get(i)).getGroundPin().getY() > 250

						// If the ground in the bottom part of the breadboard
						&& ((Wire) components.get(i)).getGroundPin().getY() < 410) {

					// Sets the row the wire is connected to, to grounded
					groundBottomRow[(int) (((Wire) components.get(i)).getGroundPin().getX())] = true;
				}
			}
		}
	}

	// Checks if the wire gets powered, and if so powers the other end of the wire
	public void checkWirePoint2(int i) {

		Point wirePoint = ((Wire) components.get(i)).getGroundPin();

		// If the wire is connected to the top part of the breadboard
		if (wirePoint.getY() < 280 && wirePoint.getY() > 100) {

			// If the row the wire is connected to is powered
			if (topRow[(int) wirePoint.getX()] == true) {

				// The wire is powered and updated
				((Wire) components.get(i)).setState(true);
				updateWireEnds(i);
			}

			// If the wire is connected to the bottom part of the breadboard
		} else if (wirePoint.getY() > 250 && wirePoint.getY() < 410) {

			// If the row the wire is connected to is powered
			if (bottomRow[(int) wirePoint.getX()] == true) {

				// The wire is powered and updated
				((Wire) components.get(i)).setState(true);
				updateWireEnds(i);
			}
		}

		// If the wire is connected to the top part of the breadboard
		if (wirePoint.getY() < 280 && wirePoint.getY() > 100) {

			// If the row the wire is connected to is grounded
			if (groundTopRow[(int) wirePoint.getX()] == true) {

				// If the ground pin is in the top part of the breadboard
				if (((Wire) components.get(i)).getPowerPin().getY() < 280
						&& ((Wire) components.get(i)).getPowerPin().getY() > 110) {

					// Sets the row the wire is connected to, to grounded
					groundTopRow[(int) (((Wire) components.get(i)).getPowerPin().getX())] = true;
				} else if (((Wire) components.get(i)).getPowerPin().getY() > 250

						// If the ground in the bottom part of the breadboard is powered
						&& ((Wire) components.get(i)).getPowerPin().getY() < 410) {

					// Sets the row the wire is connected to, to grounded
					groundBottomRow[(int) (((Wire) components.get(i)).getPowerPin().getX())] = true;
				}
			}

			// If the wire is connected to the bottom part of the breadboard
		} else if (wirePoint.getY() > 250 && wirePoint.getY() < 410) {

			// If the row the wire is connected to is grounded
			if (groundBottomRow[(int) wirePoint.getX()] == true) {

				// If the ground pin is in the top part of the breadboard
				if (((Wire) components.get(i)).getPowerPin().getY() < 280
						&& ((Wire) components.get(i)).getPowerPin().getY() > 110) {

					// Sets the row the wire is connected to, to grounded
					groundTopRow[(int) (((Wire) components.get(i)).getPowerPin().getX())] = true;
				} else if (((Wire) components.get(i)).getPowerPin().getY() > 250

						// If the ground in the bottom part of the breadboard is powered
						&& ((Wire) components.get(i)).getPowerPin().getY() < 410) {

					// Sets the row the wire is connected to, to grounded
					groundBottomRow[(int) (((Wire) components.get(i)).getPowerPin().getX())] = true;
				}
			}
		}
	}

	// Updates the grounded array
	public void updateGround(int i) {

		Point wirePoint = ((Wire) components.get(i)).getPowerPin();

		// If the wire is connected to the top part of the breadboard
		if (wirePoint.getY() < 280 && wirePoint.getY() > 100) {

			// Grounds the row the wire is connected to
			groundTopRow[(int) wirePoint.getX()] = true;

			// If the wire is connected to the bottom part of the breadboard
		} else if (wirePoint.getY() > 250 && wirePoint.getY() < 410) {

			// Grounds the row the wire is connected to
			groundBottomRow[(int) wirePoint.getX()] = true;
		}
	}

	// Checks to see which rows are grounded by a resistor, and updates the boolean
	// array of resistors grounded rows
	public void checkResistors() {

		for (int i = 0; i < components.size(); i++) {
			if (componentID.get(i) == 9) {

				Point wirePoint = ((Resistor) components.get(i)).getGroundPin();

				// If the wire is connected to the top part of the breadboard
				if (wirePoint.getY() < 280 && wirePoint.getY() > 100) {

					resistorGroundTop[(int) wirePoint.getX()] = true;

					// Else if the wire is connected to the bottom part of the breadboard
				} else {

					resistorGroundBottom[(int) wirePoint.getX()] = true;
				}
			}
		}
	}

	// Checks if the chip is powered
	public void checkChipPower(int i) {

		// If the row the logical chip's power pin is in, is powered
		if (topRow[((Chip) components.get(i)).getPoisiton()]) {

			((Chip) components.get(i)).setPowered(true);

			// Else set powered to false
		} else {

			((Chip) components.get(i)).setPowered(false);
		}
	}

	// Checks if the chip is grounded
	public void checkChipGround(int i) {

		// If the row the logical chip's ground pin is in, is grouneded
		if (groundBottomRow[((Chip) components.get(i)).getPoisiton() + (24 * 6)]) {

			((Chip) components.get(i)).setGrounded(true);

			// Else set to false
		} else {

			((Chip) components.get(i)).setGrounded(false);
		}
	}

	// Checks the input pins of the logical chip
	public void checkChipInput(int i) {

		if (componentID.get(i) != 6) {

			regularChipInput(i);
		} else {

			NOTChipInput(i);
		}
	}

	// Sets the input states for all logical chips except the NOT chip, where its
	// different
	public void regularChipInput(int i) {

		// Variable for counting
		int counter = 0;
		int x = ((Chip) components.get(i)).getPoisiton() + 24;

		// Sets the input pin state for the pins on the top row
		for (int j = 0; j < 2; j++) {

			((Chip) components.get(i)).setInputPinState(topRow[x], counter, 0);
			((Chip) components.get(i)).setInputPinState(topRow[x + 24], counter, 1);

			// Moves the input pins to the next pair
			x = +24 * 3;
			counter++;
		}
		x = ((Chip) components.get(i)).getPoisiton();

		// Sets the input pin state for the pins on the bottom row
		for (int j = 0; j < 2; j++) {

			((Chip) components.get(i)).setInputPinState(bottomRow[x], counter, 0);
			((Chip) components.get(i)).setInputPinState(bottomRow[x + 24], counter, 1);

			// Moves the input pins to the next pair
			x = +24 * 3;
			counter++;
		}
	}

	// Sets the input pin states for the NOT Chip
	public void NOTChipInput(int i) {

		// Variable for counting
		int counter = 0;
		int x = ((Chip) components.get(i)).getPoisiton() + 24;

		// Sets the input pin state for the pins on the top row
		for (int j = 0; j < 3; j++) {

			((NOTChip) components.get(i)).setNOTInputPinState(topRow[x], counter);

			// Moves the input pin location to the pin
			x = +24;
			counter++;
		}
		x = ((Chip) components.get(i)).getPoisiton();

		// Sets the input pin state for the pins on the bottom row
		for (int j = 0; j < 3; j++) {

			((NOTChip) components.get(i)).setNOTInputPinState(bottomRow[x], counter);
			((NOTChip) components.get(i)).setNOTInputPinState(bottomRow[x + 24], counter);

			// Moves the input pin location to the pin
			x = +24;
			counter++;
		}
	}

	// Checks all the outputs for all logical chips, and updates the power boolean
	// arrays
	public void checkChipOutput(int i, int componentID) {

		if (((Chip) components.get(i)).isPowered() && ((Chip) components.get(i)).isGrounded()) {

			int x = ((Chip) components.get(i)).getPoisiton();

			// Checks all the outputs for an AND CHIP
			if (componentID == 3) {

				System.out.println(((ANDChip) components.get(i)).getOutput(0));
				topRow[x + (24 * 3)] = ((ANDChip) components.get(i)).getOutput(0);
				topRow[x + (24 * 6)] = ((ANDChip) components.get(i)).getOutput(1);
				bottomRow[x + (24 * 2)] = ((ANDChip) components.get(i)).getOutput(2);
				bottomRow[x + (24 * 5)] = ((ANDChip) components.get(i)).getOutput(3);

				// Checks all the outputs for a NAND CHIP
			} else if (componentID == 4) {

				topRow[x + 24 * 3] = ((NANDChip) components.get(i)).getOutput(0);
				topRow[x + 24 * 6] = ((NANDChip) components.get(i)).getOutput(1);
				bottomRow[x + 24 * 2] = ((NANDChip) components.get(i)).getOutput(2);
				bottomRow[x + 24 * 5] = ((NANDChip) components.get(i)).getOutput(3);

				// Checks all the outputs for a NOR CHIP
			} else if (componentID == 5) {

				topRow[x + 24 * 3] = ((NORChip) components.get(i)).getOutput(0);
				topRow[x + 24 * 6] = ((NORChip) components.get(i)).getOutput(1);
				bottomRow[x + 24 * 2] = ((NORChip) components.get(i)).getOutput(2);
				bottomRow[x + 24 * 5] = ((NORChip) components.get(i)).getOutput(3);

				// Checks all the outputs for a NOT CHIP
			} else if (componentID == 6) {

				topRow[x + 24 * 2] = ((NOTChip) components.get(i)).getOutput(0);
				topRow[x + 24 * 4] = ((NOTChip) components.get(i)).getOutput(1);
				topRow[x + 24 * 6] = ((NOTChip) components.get(i)).getOutput(2);
				bottomRow[x + 24 * 1] = ((NOTChip) components.get(i)).getOutput(3);
				bottomRow[x + 24 * 3] = ((NOTChip) components.get(i)).getOutput(4);
				bottomRow[x + 24 * 5] = ((NOTChip) components.get(i)).getOutput(5);

				// Checks all the outputs for a OR CHIP
			} else if (componentID == 7) {

				topRow[x + 24 * 3] = ((ORChip) components.get(i)).getOutput(0);
				topRow[x + 24 * 6] = ((ORChip) components.get(i)).getOutput(1);
				bottomRow[x + 24 * 2] = ((ORChip) components.get(i)).getOutput(2);
				bottomRow[x + 24 * 5] = ((ORChip) components.get(i)).getOutput(3);

				// Checks all the outputs for a XOP CHIP
			} else {

				topRow[x + 24 * 3] = ((XORChip) components.get(i)).getOutput(0);
				topRow[x + 24 * 6] = ((XORChip) components.get(i)).getOutput(1);
				bottomRow[x + 24 * 2] = ((XORChip) components.get(i)).getOutput(2);
				bottomRow[x + 24 * 5] = ((XORChip) components.get(i)).getOutput(3);
			}
		}
	}
}
