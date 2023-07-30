/*
Name: Tommy
Date: 23/11/2022
This program takes a phone number, searches through a randomly-generated list of 300 phone numbers, and outputs whether the
phone number can be found. If it can be found, it also outputs the index of the phone number.
 */
import javax.swing.JOptionPane;       //imports JOptionPane class
import java.util.Random;            //imports Random class

public class PhoneFinder    //creating a PhoneFinder class
{
    public static String input()    //defining input method
    {
        return JOptionPane.showInputDialog("Please input a phone number."); //asks user for a phone number and returns user input
    }   //end of input method

    static String[] toStringArr(int[] arr, String str)  //defining toStringArr method with integer array and string parameters
    {
        char c = str.charAt(3); //checks the char at index 3 for ' ' and '-'
        String[] strArr = new String[arr.length];   //declaring and initializing a String array
        for (int i = 0; i < arr.length; i++)    //for loop from 0 to array length
        {
            String intToString = String.valueOf((10000000 + arr[i]) * 10);  //converts integer at i to String
            strArr[i] = "416" + c + intToString.substring(1, 4) + c + intToString.substring(4, 8);  //formats String to Phone Number Format
        }
        return strArr;  //returns the String array
    }   //end of toStringArr method

    public static int linearSearch(String[] strArr, String str) //defining linearSearch method with string array and string parameters
    {
        for (int i = 0; i < strArr.length; i++) //for loop from 0 to array length - 1
        {
            if (strArr[i].equals(str))  //searches the String array for the input
            {
                return i;   //returns the index if found
            }
        }
        return -1;  //returns -1 if not found
    }   //end of linearSearch method

    public static int[] phoneNums() //defining phoneNums method
    {
        Random random = new Random();   //creates a Random object
        int[] Numbers = new int[300];   //declaring and initializing an array
        boolean found;  //declaring a boolean
        for (int i = 0; i < Numbers.length; i++)    //for loop from 0 to array length -1
        {
            Numbers[i] = random.nextInt(0, 10000000);   //populates the integer at the index with a random number
            do  //do loop to check for duplicate
            {
                found = false;  //initializes boolean as false
                for (int j = 0; j < i && !found; j++)   //for loop from 0 to i -1 to check each integer of the array
                {
                    if (Numbers[i] == Numbers[j])   //if the number at is equal to one of the other numbers of the array
                    {
                        Numbers[i] = random.nextInt(0, 10000000);   //reassigns the number at the index with a random number
                        found = true;   //reassigns boolean to true
                    }   //end of if
                }   //end of for
            } while (found);    //loops again if found is true; end of do while loop
        }   //end of for loop
        return Numbers; //returns the array of integers
    }   //end of phoneNums method

    public static void main(String[] args)  //defining main method
    {
        String numPhone = input();  //declares and initializes a String variable from input method
        int[] phoneList = phoneNums();  //declares and initializes an array from phoneNums method
        String[] stringPhone = toStringArr(phoneList, numPhone);    //declares and initializes a String array from toStringArr method
        int pos = linearSearch(stringPhone, numPhone);  //declares and initializes an integer from linearSearch method
        if (pos != -1)  //if the number is found
        {
            JOptionPane.showMessageDialog(null, "Phone Number Found! \n At index " + pos + "   " + numPhone);
            //prints found message with index number in a JOptionPane
        }   //end of if
        else    //if the number is not found
        {
            JOptionPane.showMessageDialog(null, "Phone Number Not Found \n" + pos);
            //prints not found message
        }   //end of else
    }   //end of main method
}   //end of PhoneFinder class
