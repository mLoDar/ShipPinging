package shipPinging;

import java.util.Scanner;

import shipPinging.Resources.*;
import shipPinging.Scripts.*;



public class ShipPinging {
	
	// Create a new gamefield
	static String[][] gamefield = new String[7][9];
	static int amountMoves = 0;
	static int amountShipsHit = 0;
	static boolean allShipsFound = false;
	static Scanner in = new Scanner(System.in);
	
	
	
	// Class for accessing all the needed values
	static ValueCollection spValues = new ValueCollection();
	static FunctionManager callFunc = new FunctionManager();
	
	
		
	public static void main(String args[])
	{
		// Fill the field with water
		gamefield = callFunc.FillField(gamefield);


			
		// Hide new ships in the new gamefield
		gamefield = callFunc.HideShips(gamefield);	
		
		
		
		// Main game part
		while (allShipsFound == false)
		{
		    // Print gamefield to the console
		    callFunc.PrintField(gamefield);

		    System.out.println();

		    // Variables for handling user input
		    String userInput = "n/a";
		    boolean inputIsCorrect = false;
		    		    
		    while (inputIsCorrect == false)
		    {
		        // Ask for user input
		        System.out.print("Enter coordinates: ");
		        
		        userInput = in.nextLine();
		        
		        inputIsCorrect = callFunc.CheckInput(userInput);
		    }

		    

		    // Converting user input to coordinates for field
		    int xCord = spValues.allowedInputLetters.indexOf(userInput.substring(0, 1).toUpperCase());
		    int yCord = Integer.parseInt(userInput.substring(1, 2)) - 1;



		    // Determine if player hit a ship
		    boolean shipHit = callFunc.ShipWasHit(gamefield, xCord, yCord);
		    boolean foundShipHit = callFunc.FoundShipWasHit(gamefield, xCord, yCord);
		    
		    if (shipHit == true) {

		        amountShipsHit++;
		        gamefield[yCord][xCord] = spValues.symbolFoundShip;

		        if (amountShipsHit >= spValues.amountHiddenShips) {
		            allShipsFound = true;
		            break;
		        }
		    } else if(foundShipHit == false){

		        // Search for ships nearby
		        int shipsNearby = callFunc.PingForShips(gamefield, xCord, yCord);

		        gamefield[yCord][xCord] = Integer.toString(shipsNearby);

		    }



		    amountMoves++;
		}

		// Print gamefield to the console
		callFunc.PrintField(gamefield);

		// Show "Endscreen"
		System.out.println();
		System.out.println("Congrats! You found all '" + spValues.amountHiddenShips + "' ships");
		System.out.println("You were able to do that all with '" + amountMoves + "' moves");
		
		in.close();
	}
}
