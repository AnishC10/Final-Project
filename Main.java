import java.util.Scanner;
import java.io.*;
class Main {
  public static void main(String[] args) throws Exception {
    int [] [] puzzle = new int [9][9];
    Scanner sudoku = new Scanner(new File("Sudoku.txt"));
    int column = 0;
    int row = 0;
    while(sudoku.hasNextInt()){
      puzzle[row][column] = sudoku.nextInt();
      column++;
      if (column == 9){
        column = 0;
        row++;
      } 
    }
    for (int i = 0; i<puzzle.length; i++){
      System.out.print("|");
      for (int j = 0; j<puzzle[0].length; j++){
        System.out.print(puzzle[i][j] + "|");
      }
      System.out.println();
    }
    System.out.println("When you want to see the solution, press 1");
    Scanner keyboard = new Scanner(System.in);
    int answer = keyboard.nextInt();
    if (answer == 1){
      boolean recurse = solution(puzzle);
              
              if(recurse == true){
                printArray(puzzle);
              }
              else{System.out.println("Sudoku is unsolvable");}  
    }
  }
  public static boolean checkValue (int array[][], int row, int column, int num ){
    for (int i = 0; i<9; i++){ //row check
      if (array[row][i] == num){
        return false;
      }
    }
    for(int j = 0; j<9; j++){ //column check
      if (array[j][column] == num){
        return false;
      }
    }
    int [] boxCoord = boxCheck(row, column);
    for (int row1 = boxCoord[0]; row1<boxCoord[0]+3; row1++){
      for (int column1 = boxCoord[1]; column1<boxCoord[1]+3; column1++){
        if (array[row1][column1] == num){
          return false;
        }
      }
    }
    return true;
  }
  public static int [] boxCheck (int row, int column){// returns array with starting location of box 
    int [] startingLoc = new int [2];
    if (row >= 0 && row <3 && column >= 0 && column <3){
      startingLoc[0] = 0;
      startingLoc[1] = 0;
    }
    if (row >= 0 && row <3 && column >= 3 && column <6){
      startingLoc[0] = 0;
      startingLoc[1] = 3;
    } 
    if (row >= 0 && row <3 && column >= 6 && column <9){
      startingLoc[0] = 0;
      startingLoc[1] = 6;
    } 
    if (row >= 3 && row <6 && column >= 0 && column <3){
      startingLoc[0] = 3;
      startingLoc[1] = 0;
    }
    if (row >= 3 && row <6 && column >= 3 && column <6){
      startingLoc[0] = 3;
      startingLoc[1] = 3;
    }
    if (row >= 3 && row <6 && column >= 6 && column <9){
      startingLoc[0] = 3;
      startingLoc[1] = 6;
    }
    if (row >= 6 && row <9 && column >= 0 && column <3){
      startingLoc[0] = 6;
      startingLoc[1] = 0;
    }
    if (row >= 6 && row <9 && column >= 3 && column <6){
      startingLoc[0] = 6;
      startingLoc[1] = 3;
    }
    if (row >= 6 && row <9 && column >= 6 && column <9){
      startingLoc[0] = 6;
      startingLoc[1] = 6;
    }
    return startingLoc;
  }

  
  public static boolean solution (int [][] array) {
    int checknum = 1;
    boolean solve = false;
    int startRow=0;
    int startCol=0;
    boolean zeroFound = false;
    for (int i = 0; i<array.length; i++){
      for (int j =0; j<array[0].length; j++){
        if (array[i][j] == 0){
          startRow = i;
          startCol = j;
          zeroFound = true;
          break;
        }
      }
      if (zeroFound == true){break;}
    }
    
        if (zeroFound == true){
          while(checknum<10){
                          
            solve= checkValue(array, startRow, startCol, checknum);
            if (solve == true) {
              array[startRow][startCol] = checknum;
              if (solution(array) == true) { //recursion to check if value works
                return true;
              } else {
                array[startRow][startCol] = 0;
              }
            } 
            
            checknum++;
          }  
          return false;
        }
        return true;
      
  }
  public static void printArray (int[][] anyArray){
    for (int i = 0; i<anyArray.length; i++){
        System.out.print("|");
        for (int j = 0; j<anyArray[0].length; j++){
          System.out.print(anyArray[i][j] + "|");
        }
        System.out.println();
    }        
  }
}
