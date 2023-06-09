// Joy Schwarting
// CS 145
// Assignment 1 Word Search Generator
// This java file prints user menu and allows user input for the word search generator
// to run appropriately. It contains the main method in which the introduction, printed
// word search, and solution to word search are displayed in the output window.
// This file and the other one I turned in, 'WordSearch.java' must be in
// the same folder to run correctly, although they are not packaged together.

import java.util.*;
import java.io.*;

public class WordSearchMain{
      public static void main(String[] args) throws FileNotFoundException {
            boolean generated = false; // default
            Scanner console = new Scanner(System.in);
            String choice;
            WordSearch search = new WordSearch();
            
            do {
                  printIntro(); // display user menu
                  choice = console.next();// User picks from options
                  if(choice.equals("g")) {// begins new word search creation
                        System.out.println("Type a word, press enter to type next word, and repeat.");
                        System.out.println("When you are finished, type a single \"q\" and press enter.");
                        String listOfWords = console.next();
                        ArrayList<String> wordGrid = new ArrayList<String>();
                        do {
                              wordGrid.add(listOfWords); // adds words to WS after user input
                              listOfWords = console.next();
                        } while(!listOfWords.equals("q")); // ends users ability to input words and generates WS
                              String[] words = new String[wordGrid.size()];
                              wordGrid.toArray(words);
                              search.generate(words);
                              generated = true;
                         } else if(choice.equals("p")) { // print word search
                              if(generated){
                                    print(search);
                              }
                         } else if(choice.equals("s")) { // show word search solution
                              if(generated){
                                    showSolution(search);
                              }
                         }
                  } while(!choice.equals("q")); // quit program
      } // end main
                  
      // Prints an intro to the program which explains and lists options.
      public static void printIntro() {
            System.out.println("Welcome to the Word Search Generator!");
            System.out.println("With this program, you can create your own wordsearch puzzle!");
            System.out.println("Please select from the options below:");
            System.out.println("[g] Generate a new word search.");
            System.out.println("[p] display your word search.");
            System.out.println("[s] Show the solution to your word search.");
            System.out.println("[q] Quit the program.");
      } // end printIntro

      // Prints the Word Search
      public static void print(WordSearch ws){
            System.out.println(ws);
      } // end Print

      // Prints Word Search solution
      public static void showSolution(WordSearch ws){
            System.out.println(ws.toSolution());
      } // end showSolution
      
} // end WordSearchMain class
