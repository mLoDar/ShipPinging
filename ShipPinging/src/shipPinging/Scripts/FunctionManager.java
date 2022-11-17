package shipPinging.Scripts;

import java.util.Random;

import shipPinging.Resources.*;





public class FunctionManager {

	// Class for accessing all the needed values
    ValueCollection spValues = new ValueCollection();





    // Fill the field with water
    public String[][] FillField(String[][] input)
    {
        String[][] output = input;

        // Loop through x-axis of the array
        for (int i = 0; i < input.length; i++)
        {
            // Loop through y-axis of the array
            for (int j = 0; j < input[0].length; j++)
            {
                // Fill the current position with water
                input[i][j] = spValues.symbolWater;
            }
        }

        return output;
    }
    
    
    
    
    
    // Insert new ships into the given field
    public String[][] HideShips(String[][] input)
    {
        String[][] output = input;

        Random rndm = new Random();

        // Hide 4 ships in total
        for (int i = 0; i < spValues.amountHiddenShips; i++)
        {
            // Generate new coordinates
            int xAxis = rndm.nextInt(1, input[1].length - 1);
            int yAxis = rndm.nextInt(1, input.length - 1);

            // Generate new coordinates if the previous ones are already occupied with a ship
            while (input[yAxis][xAxis].equals(spValues.symbolHiddenShip))
            {
            	xAxis = rndm.nextInt(1, input[1].length - 1);
                yAxis = rndm.nextInt(1, input.length - 1);
            }

            // Insert the ship symbol to the generated coordinate
            output[yAxis][xAxis] = spValues.symbolHiddenShip;
        }

        return output;
    }
    
    
    
    
    
    // Print given field to the console
    public void PrintField(String[][] input)
    {
    	System.out.println("  A B C D E F G H I");

        // Loop through y-axis
        for (int i = 0; i < input.length; i++) {
        	System.out.print((i+1) + " ");

            // Loop through x-axis
            for (int j = 0; j < input[1].length; j++) {
                // If current coordinate is a hidden ship output "water" instead
                if (input[i][j].equals(spValues.symbolHiddenShip)) {
                	System.out.print(spValues.symbolWater + " ");
                    continue;
                }
                // Output current coordinate
                System.out.print(input[i][j] + " ");
            }

            System.out.println();
        }
    }
    
    
    
    
    
    // Check if user input has correct format
    public boolean CheckInput(String input)
    {
    	int num = 0;
    	
        // Only 1 letter and 1 number allowed
        if (input.length() != 2)
            return false;
        // First position needs to be existent in predefined string
        if (spValues.allowedInputLetters.contains(input.substring(0, 1).toUpperCase()) == false)
            return false;
        
        // Second position needs to be an number
        try {
        	num = Integer.parseInt(input.substring(1, 2));
        } catch(Exception error) {
        	return false;
        }

        // Number needs to be greater than or equal 1 and lower than 8
        if (num > 7 || num < 1)
            return false;

        return true;
    }
    
    
    
    
    
    // Check coordinates to determine if a ship was hit
    public boolean ShipWasHit(String[][] input, int xCord, int yCord) {
        if (input[yCord][xCord].equals(spValues.symbolHiddenShip))
            return true;
        return false;
    }





    // Check coordinates to determine if a ship was hit
    public boolean FoundShipWasHit(String[][] input, int xCord, int yCord) {
        if (input[yCord][xCord].equals(spValues.symbolFoundShip))
            return true;
        return false;
    }
    
    
    
    
    
    // Scan for ships nearby based on the provided coordinates
    public int PingForShips(String[][] gamefield, int xCord, int yCord)
    {
        int shipsNearby = new ShipPinger().Ping(gamefield, xCord, yCord);
        return shipsNearby;
    }
}