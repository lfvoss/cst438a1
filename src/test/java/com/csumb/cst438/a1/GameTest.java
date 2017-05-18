package com.csumb.cst438.a1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;


/**
 * Test of Game class
 * @author david wisneski
 * @veraion 1.0
 */
public class GameTest {
    
    public GameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    //helper method to generate random char that's not in game's random word
    private char randCharNotInWord(Game instance) {
        String temp = instance.getWord();
        char c = 'a';
        boolean flag = true;
        while (flag) {
            Random r = new Random();
            c = (char)(r.nextInt(26) + 'a');
            if (temp.indexOf(c) < 0) {  // if char c is not present in temp
                flag = false;
            }
        }
        return c;
    }
    
    /**
     * Test of getState method, of class Game.
     * at start of game, state should be 1.
     * a correct guess will not change the state
     * an incorrect guess will increase state by 1
     */
    @org.junit.Test
    public void testGetState() {
        System.out.println("getState");
        Game instance = new Game();
        int expResult = 1;
        int result = instance.getState();
        assertEquals(expResult, result);
        String temp = instance.getWord();
        Random r = new Random();
        int i = r.nextInt(temp.length()-1);   //generate rand int !> length word
        instance.playGame(temp.charAt(i));    //test letter known w/in the word
        result = instance.getState();
        assertEquals(expResult, result);
        char rand = randCharNotInWord(instance); //get rand letter not in word
        instance.playGame(rand);                 //test letter known not in word
        result = instance.getState();
        assertEquals(expResult+1, result);
    }

    /**
     * Test of getWord method, of class Game.
     */
    @org.junit.Test
    public void testGetWord() {
        System.out.println("getWord");
        Game instance = new Game();
        String expResult = instance.getWord();
        String result = instance.getWord();     
        assertEquals(expResult, result);
    }

    /**
     * Test of getDisplayWord method, of class Game.
     */
    @org.junit.Test
    public void testGetDisplayWord() {
        System.out.println("getDisplayWord");
        Game instance = new Game();
        String expResult = instance.getDisplayWord();
        String result = instance.getDisplayWord();
        assertEquals(expResult, result);
        String temp = instance.getWord();
        instance.playGame(temp.charAt(0));
        result = instance.getDisplayWord();
        assertEquals(temp.charAt(0), result.charAt(0));

    }

    /**
     * Test of startNewGame method, of class Game.
     */
    @org.junit.Test
    public void testStartNewGame() {
        System.out.println("startNewGame");
        Game instance = new Game();
        instance.startNewGame();
        char temp = instance.getWord().charAt(0);  //get first letter from word
        instance.playGame(temp);
        char rand = randCharNotInWord(instance); //get rand letter not in word
        instance.playGame(rand);
        instance.startNewGame();
        int result = instance.getState();
        assertEquals(1,result);
    }

    /**
     * Test of playGame method, of class Game.
     *   correct guess should return 0 , or 1 when game is won
     *   incorrect guess should return 2, or 3 when game is lost
     */
    @org.junit.Test
    public void testPlayGame() {
        
        System.out.println("playGame");

        Game instance = new Game();
        String word = instance.getWord();
        char guess = word.charAt(0);

        int expResult = 0;
        int result = instance.playGame(guess);
        assertEquals(expResult, result);
        
        char temp = randCharNotInWord(instance);
        result = instance.playGame(temp);
        assertEquals(2, result);
        temp = randCharNotInWord(instance);
        result = instance.playGame(temp);
        assertEquals(2, result);
        temp = randCharNotInWord(instance);
        result = instance.playGame(temp);
        assertEquals(2, result);
        temp = randCharNotInWord(instance);
        result = instance.playGame(temp);        
        assertEquals(2,result);
        temp = randCharNotInWord(instance);
        result = instance.playGame(temp);
        assertEquals(2,result);
        temp = randCharNotInWord(instance);
        result = instance.playGame(temp);
        assertEquals(3,result);
 
        instance.startNewGame();
        word = instance.getWord();
        int x = word.length();
        for (int i = 0; i < x; i++) { 
            if (i == x - 1) {
                result = instance.playGame(word.charAt(i));
                assertEquals(1,result);
            } else {
                result = instance.playGame(word.charAt(i));
                assertEquals(0,result); 
            }
        }
    }
    
}