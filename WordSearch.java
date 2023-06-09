// Joy Schwarting
// Assignment 1 Word Search
//
// this java file needs to be in the same folder as 'WordSearchMain.java' for
// the program to work. This class contains the generate and showSolution methods
// as required by the assignment instructions as well as a placeWord, toString,
// toSolution, setUpGrid, and fillGrid methods used to create the word search
// grid, place the user input words vertically, horizontally or diagonally
// randomly, and show the solution by showing user input words in the generated
// grid and replacing all other randomized characters with an 'X' as per
// instructions.

import java.util.*;
import java.io.*;

public class WordSearch {
      private char[][] grid;
      private boolean[][] answers;
      private String[] userWords;
      
      // accepts a parameter of a string array
      // generates a new word search from array.
      public void generate(String[] w) {
            for(int i = 0 ; i < w.length ; i++) {
                  w[i] = w[i].toLowerCase();
            }
            this.userWords = w;
            char[][] wordChars = setupGrid();
            for(int i = 0 ; i < wordChars.length ; i++){
                  placeWord(wordChars, i);
            }
            fillGrid();
      }

      public String toString() {
            String result = "";
            for(int i = 0 ; i < grid.length ; i++){
                  for(int x = 0 ; x < grid[i].length ; x++) {
                        result += " " + grid[i][x] + " ";
                  }
                  result += "\r\n";
            }
            return result;
      } // end String toString

      // String representation of the word searches solution.
      // displays solution to user generated WS created in WordSearchMain
      public String toSolution() {
            String result = "";
            for(int i = 0 ; i < grid.length ; i++) {
                  for(int x = 0 ; x < grid[i].length ; x++) {
                        if(answers[i][x]) {
                              result += " " + grid[i][x] + " ";
                        } else {
                              result += " X "; // places "X" where user input words are not 
                        }
                  }
                  result += "\r\n";
            }
            return result;
      } // end String toSolution

      // Finds place to put word and valid direction of word
      private void placeWord(char[][] wordChars, int iteration) {
            Random rand = new Random();
            int direction = rand.nextInt(3);
            int[] pos = {0,0};
      
            if(direction == 0) { // horizontal words
                  boolean placed = false;
                  int attempts = 0;
                  while(!placed && attempts < 100) { // less than 100 words
                        pos[0] = rand.nextInt((grid.length-1) - wordChars[iteration].length);
                        pos[1] = rand.nextInt((grid.length-1) - wordChars[iteration].length);
                        placed = true;
                        for(int u = 0 ; u < wordChars[iteration].length ; u++) {
                              // randomly places letters where user generated words are not
                              if(grid[pos[0] + u][pos[1]] != '\u0000' && grid[pos[0] + u]
                        [pos[1]] != wordChars[iteration][u]) {
                                    placed = false;
                                    break;
                              }
                        }
                        attempts++;
                   } if(placed) {
                        for(int x = 0 ; x < wordChars[iteration].length ; x++){
                              grid[pos[0]][pos[1]] = wordChars[iteration][x];
                              answers[pos[0]][pos[1]] = true;
                              pos[0]++;
                        }
                   }
            } else if(direction == 1){ // vertical words
                     boolean placed = false;
                     int attempts = 0;
                     while(!placed && attempts < 100){
                           pos[0] = rand.nextInt((grid.length-1) - wordChars[iteration].length);
                           pos[1] = rand.nextInt((grid.length-1) - wordChars[iteration].length);
                           placed = true;
                           for(int u = 0 ; u < wordChars[iteration].length ; u++){
                                 if(grid[pos[0]][pos[1] + u] != '\u0000' && grid[pos[0]][pos[1] +
                           u] != wordChars[iteration][u]){
                                       placed = false;
                                       break;
                                 }
                           }
                           attempts++;
                     }
                     if(placed) {
                           for(int x = 0 ; x < wordChars[iteration].length ; x++){
                                 grid[pos[0]][pos[1]] = wordChars[iteration][x];
                                 answers[pos[0]][pos[1]] = true;
                                 pos[1]++;
                           }
                     }
           } else if(direction == 2) { // diagonal words
                   boolean placed = false;
                   int attempts = 0;
                   while(!placed && attempts < 100){
                            pos[0] = rand.nextInt((grid.length-1) - wordChars[iteration].length);
                            pos[1] = rand.nextInt((grid.length-1) - wordChars[iteration].length);
                            placed = true;
                            for(int u = 0 ; u < wordChars[iteration].length ; u++){
                                  if(grid[pos[0] + u][pos[1] + u] != '\u0000' && grid[pos[0] + u]
                   [pos[1] + u] != wordChars[iteration][u]){
                                        placed = false;
                                        break;
                                  }
                             }
                             attempts++;
                   }
                   if(placed) {
                        for(int x = 0 ; x < wordChars[iteration].length ; x++) {
                              grid[pos[0]][pos[1]] = wordChars[iteration][x];
                              answers[pos[0]][pos[1]] = true;
                              pos[1]++;pos[0]++;
                        }
                   }
            }
      }
   
      //Breaks up the string array into a 2d char array and adjusts the size of the
      //grid based on the length and number of the userWords.
      private char[][] setupGrid(){
            char[][] wordChars = new char[userWords.length][];
            int longest = 8;
            for(int i = 0 ; i < userWords.length ; i++){
                  wordChars[i] = userWords[i].toCharArray();
                  if(wordChars[i].length > longest){
                        longest = wordChars[i].length;
                  }
            }if(userWords.length > longest){
                  longest = userWords.length;
            }
            this.grid = new char[longest + 4][longest + 4];
            this.answers = new boolean[longest + 4][longest + 4];
            return wordChars;
       }
       
      // Fills grid w random characters
      private void fillGrid(){
            for(int i = 0 ; i < grid.length ; i++){
                  for(int x = 0 ; x < grid[i].length ; x++){
                        Random rand = new Random();
                        if(grid[i][x] == '\u0000'){
                              grid[i][x] = (char)(rand.nextInt(26)+97);
                        }
                   }
            }
      }
}