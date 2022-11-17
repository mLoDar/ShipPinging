package shipPinging.Scripts;

import shipPinging.Resources.*;



public class ShipPinger {

	// Class for accessing all the needed values
    ValueCollection spValues = new ValueCollection();


    String[][] field;
    int x, y;
    
    public int Ping(String[][] gamefield, int xCord, int yCord)
    {
        // Assign global variables
        field = gamefield;
        x = xCord; y = yCord;


        int shipsNearby = 0;

        // Check all 8 possible directions from coordinate position
        shipsNearby += North();
        shipsNearby += NorthEast();
        shipsNearby += East();
        shipsNearby += SouthEast();
        shipsNearby += South();
        shipsNearby += SouthWest();
        shipsNearby += West();
        shipsNearby += NorthWest();

        return shipsNearby;
    }
    
    
    
    private int North() {
        for (int i = y; i >= 0; i--) {
            if (spValues.symbolFoundShip.equals(field[i][x]) || spValues.symbolHiddenShip.equals(field[i][x]))
                return 1;
        }
        return 0;
    }

    private int NorthEast() {
        int a = x, b = y;

        while (a < 8 && b > 0) {
            if (spValues.symbolFoundShip.equals(field[b][a]) || spValues.symbolHiddenShip.equals(field[b][a]))
                return 1;
            a++; b--;
        }
        return 0;
    }

    private int East() {
        for (int i = x; i < 8; i++) {
            if (spValues.symbolFoundShip.equals(field[y][i]) || spValues.symbolHiddenShip.equals(field[y][i]))
                return 1;
        }
        return 0;
    }

    private int SouthEast() {
        int a = x, b = y;

        while (a < 8 && b < 6) {
            if (spValues.symbolFoundShip.equals(field[b][a]) || spValues.symbolHiddenShip.equals(field[b][a]))
                return 1;
            a++; b++;
        }
        return 0;
    }

    private int South() {
        for (int i = y; i < 6; i++) {
        	if (spValues.symbolFoundShip.equals(field[i][x]) || spValues.symbolHiddenShip.equals(field[i][x]))
                return 1;
        }
        return 0;
    }

    private int SouthWest() {
        int a = x, b = y;

        while (a > 0 && b < 6) {
        	if (spValues.symbolFoundShip.equals(field[b][a]) || spValues.symbolHiddenShip.equals(field[b][a]))
                return 1;
            a--; b++;
        }
        return 0;
    }

    private int West() {
        for (int i = x; i >= 0; i--) {
        	if (spValues.symbolFoundShip.equals(field[y][i]) || spValues.symbolHiddenShip.equals(field[y][i]))
                return 1;
        }
        return 0;
    }

    private int NorthWest() {
        int a = x, b = y;

        while (a >= 0 && b >= 0) {
        	if (spValues.symbolFoundShip.equals(field[b][a]) || spValues.symbolHiddenShip.equals(field[b][a]))
                return 1;
            a--; b--;
        }
        return 0;
    }
}
