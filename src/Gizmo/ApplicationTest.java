package Gizmo;

/*
 * Names: Momin Naeem and Jason Ou
 * 
 * Date: Jan 17, 2019
 * 
 * Course Code: ICS4U1, Mr. Fernandes
 * 
 * Title: Gizmo
 * 
 * Description: This program helps engineers with building circuits and testing out new components 
 * outside of the school environment. With many gate chips, such as an ANDChip, NANDChip, ORChip, 
 * NORChip, XORChip, and a NOTChip. Logic gate chips are truly versatile chips. They do not have 
 * to be used just for pure logic circuits. In fact, they have a wide range of uses. They can be 
 * used as inverters, as active low inputs, active low outputs, active high inputs, active high 
 * outputs. One of the most used chips is the NAND gate. The differences from these chips are 
 * there pins and where they lead to in the chip itself.
 * 
 * Features: MainMenu Screen
 * 			 Multiple of chips available to use
 * 			 Breadboard and Schematic screens
 * 			 HelpMenu Screen
 * 			 Screenshot saving feature
 * 			 Pin snapping 
 * 			 Undo option
 * 	         Delete All
 * 			 Stop and Run Simulation
 * 
 * Major Skills: Attention to Detail
 * 				 Time management
 * 				 Patience
 * 				 Brainstorming
 * 
 * Areas of Concern: Users cant save files of their circuit as planned in the beginning of the 
 * 					 project.
 * 					 Dragging and Dropping components option, instead click on a component then 
 * 					 click on the pin.
 * 
 */


/*
 * Author: Momin Naeem
 * Date: Jan 8, 2019
 * Course: ICS4U1
 * Topic: Gizmo
 * Title: ApplicationTest
 * Description: This class is where the application runs, it loads up all the screens and 
 * only sets the visible one when the user switches screens 
 */

public class ApplicationTest {

	public static void main(String[] args) {
		ApplicationTest run = new ApplicationTest();
	}

	final static int BREAD_BOARD_MENU = 1;
	final static int MAIN_MENU = 2;
	final static int CREDIT_MENU = 3;
	final static int SCHEMATICS_MENU = 4;
	MainMenu menu;
	BreadBoardMenu bMenu;
	HelpMenu cMenu;
	SchematicsMenu sMenu;

	// Primary Constructor
	public ApplicationTest() {
		// Starts all needed objects and sets the main menu visible
		menu = new MainMenu(this);
		bMenu = new BreadBoardMenu(this);
		cMenu = new HelpMenu(this);
		sMenu = new SchematicsMenu(this);
		Screenshot screenshot = new Screenshot(sMenu);
		sMenu.getScreenshot(screenshot);
		menu.setVisible(true);
	}

	// Switches between Frames and menus
	public void switchFrame(int a) {
		// Sets everything to invisible
		menu.setVisible(false);
		bMenu.setVisible(false);
		sMenu.setVisible(false);
		cMenu.setVisible(false);
		// Sets the selected frame to visible
		if (a == 1) {
			bMenu.setVisible(true);
			bMenu.repaint();
		} else if (a == 2) {
			menu.setVisible(true);
		} else if (a == 3) {
			cMenu.setVisible(true);
		} else if (a == 4) {
			sMenu.setVisible(true);
			sMenu.repaint();
		}
	}
}
