package edu.gatech.oad.antlab.person;

import java.util.Arrays;
import java.util.Random;

/**
 * A simple class for person 2 returns their name and a modified string
 *
 * @author Bob
 * @version 1.1
 */
public class Person2 {
    /** Holds the persons real name */
    private String name;

    /**
     * The constructor, takes in the persons name
     * 
     * @param pname
     *            the person's real name
     */
    public Person2(String pname) {
        name = pname;
    }

    /**
     * This method should take the string input and return its characters in
     * random order. given "gtg123b" it should return something like "g3tb1g2".
     *
     * @param input
     *            the string to be modified
     * @return the modified string
     */
    private static String calc(String input) {
        // Person 2 put your implementation here
        char[] arr = input.toCharArray();
        Random rand = new Random();

        for (int i = 0; i < input.length(); i++) {
            int swap = rand.nextInt(input.length());
            char temp = arr[i];
            arr[swap] = temp;
            arr[i] = arr[swap];
        }
        StringBuilder build = new StringBuilder();
        for (int j = 0; j < input.length(); j++) {
            build.append(arr[j]);
        }
        return build.toString();
    }

    /**
     * Return a string rep of this object that varies with an input string
     *
     * @param input
     *            the varying string
     * @return the string representing the object
     */
    public String toString(String input) {
        return name + calc(input);
    }
}
