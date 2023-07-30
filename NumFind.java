/*
Name: Tommy
Date: 12/12/2022
This program takes a three-digit number, searches through a randomly-generated list of 200 phone numbers, and outputs whether the
number can be found within each phone number. If it can be found, it outputs the phone number along with the total found.
 */

import javax.swing.*;
import java.util.Random;

public class NumFind    //creating a PhoneFinder class
{
    public static String input()    //defining input method
    {
        return JOptionPane.showInputDialog("Please input a three-digit number."); //asks user for a phone number and returns user input
    }   //end of input method

    public static String[] linearSearch(String[] strArr, String str) //defining linearSearch method with string array and string parameters
    {
        String[] found = new String[1]; //declaring and initializing an array
        int size = 1;   //declaring and initializing an integer
        for (int i = 0; i < strArr.length; i++) //for loop from 0 to array length - 1
        {
            if (strArr[i].contains(str))  //searches the String array if the index contains the input
            {
                String[] temp = found;  //declaring, initializing, and populating an array
                found = new String[size + 1];   //initializing the array with a new size
                for (int j = 0; j < temp.length; j++)  //for loop from 0 to array length - 1
                {
                    found[j] = temp[j]; //copies the array from the temporary array to new array
                }
                size++; //increases the size by 1
                found[size - 2] = strArr[i];    //adds the string at the index to the array
            }
        }
        found[size - 1] = (size - 1) + "";  //populates the last index as the number of indexes found
        return found;  //returns the array
    }   //end of linearSearch method

    public static String arrToString(String[] strArr) {
        String str = "";
        for (int i = 0; i < strArr.length-1; i++) {
            str += strArr[i] + "\n";
        }
        return str;
    }

    public static String[] phoneNums() //defining phoneNums method
    {
        Random random = new Random();   //creates a Random object
        String[] Numbers = new String[200];   //declaring and initializing an array
        boolean found;  //declaring a boolean
        for (int i = 0; i < Numbers.length; i++)    //for loop from 0 to array length - 1
        {
            Numbers[i] = random.nextInt(401, 999) + "-" + String.valueOf((1000 + random.nextInt(0, 1000)) * 10).substring(1, 4)
                    + "-" + String.valueOf((10000 + random.nextInt(0, 10000)) * 10).substring(1, 5);
            //populates the index with a phone number as a String
            do  //do loop to check for duplicate
            {
                found = false;  //initializes boolean as false
                for (int j = 0; j < i && !found; j++)   //for loop from 0 to i -1 to check each integer of the array
                {
                    if (Numbers[i].equals(Numbers[j]))   //if the number at is equal to one of the other numbers of the array
                    {
                        Numbers[i] = random.nextInt(401, 999) + "-" + String.valueOf((1000 + random.nextInt(0, 1000)) * 10).substring(1, 4)
                                + "-" + String.valueOf((10000 + random.nextInt(0, 10000)) * 10).substring(1, 5);
                        //reassigns the String at index with another String
                        found = true;   //reassigns boolean to true
                    }   //end of if
                }   //end of for
            } while (found);    //loops again if found is true; end of do while loop
        }   //end of for loop
        return Numbers; //returns the array of Strings
    }   //end of phoneNums method

    public static void main(String[] args)  //defining main method
    {
        String numPhone = input();  //declares and initializes a String variable from input method
        String[] phoneList = phoneNums();  //declares and initializes a String array from phoneNums method
        String[] pos = linearSearch(phoneList, numPhone);  //declares and initializes a String array from linearSearch method
        String found = arrToString(pos); //declares and initializes a String variable from arrToString method
        if (!pos[pos.length - 1].equals("0"))  //if the number is found
        {

            JOptionPane.showMessageDialog(null, numPhone+ " within List of Phone Numbers! \nFound: \n" + found + "Total found: " + pos[pos.length-1]);
            //prints found message with index number in a JOptionPane
        }   //end of if
        else    //if the number is not found
        {
            JOptionPane.showMessageDialog(null, numPhone+" within List of Phone Numbers\n-1");
            //prints not found message
        }   //end of else
    }   //end of main method
}   //end of PhoneFinder class
