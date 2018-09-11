package mo.bioinf.bmark;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DnaOutput {

    private List<String> DNA_sequences = new ArrayList<String>();

    public void printDNA(){

        if(DNA_sequences.size() == 0)
        {
            System.out.println("sequences empty");
        }

        for(String sequence : DNA_sequences){
            System.out.println(sequence);
        }
    }

    public DnaOutput(File input) throws java.io.FileNotFoundException{

        if(input.exists() && input.canRead()) {

            System.out.println("exists and can read");

            FileReader filereader = new FileReader(input);
            BufferedReader bufferedreader = new BufferedReader(filereader);
            StringBuffer stringbuffer = new StringBuffer();

            String currentLine;
            try{
                while((currentLine = bufferedreader.readLine()) != null){
                    stringbuffer.append(currentLine);
                    System.out.println(stringbuffer.toString());
                }
                filereader.close();
            }catch(java.io.IOException e){
                System.out.println(e.getMessage());
            }







        }

    }

    public DnaOutput(){

    }



    public static String binary2dna(String input, int kmersize)
    {
        String[] split = input.split(" ");

        if(split.length != 4)
        {
            return "error";
        }

        String[] hex = {transform(split[3]), transform(split[2]), transform(split[1]), transform(split[0])};

        String[] base4 = {hex2base4(hex[0]),hex2base4(hex[1]),hex2base4(hex[2]),hex2base4(hex[3])};

        String[] extended = {extend(base4[0]),extend(base4[1]),extend(base4[2]),extend(base4[3])};

        String[] DNA = {base42dna(extended[0]),base42dna(extended[1]),base42dna(extended[2]),base42dna(extended[3])};

        String ans = "";
        for(int i = 0; i < 4; i++)
        {
            ans += DNA[i];
        }

        if(ans.length() > kmersize)
        {
            int difference = ans.length() - kmersize;
            ans = ans.substring(difference,ans.length());
        }


        return ans;
    }

    public static String transform(String input)
    {
        char[] inputChars = input.toCharArray();
        char[] old = input.toCharArray();

        inputChars[0] = old[2];
        inputChars[1] = old[3];
        inputChars[2] = old[0];
        inputChars[3] = old[1];

        return String.valueOf(inputChars);

    }

    public static String hex2base4(String input)
    {
        return Integer.toString(Integer.parseInt(input,16),4);

    }

    public static String extend(String input){
        int length = input.length();

        int difference = 8 - length;

        String substr = "";
        for(int i = 0; i < difference; i++)
        {
            substr += "0";
        }

        return substr + input;


    }

    public static String base42dna(String input)
    {
        char[] inputChars = input.toCharArray();

        for(int i = 0; i < input.length(); i++)
        {
            char currentChar = inputChars[i];

            if(currentChar == '0')
                inputChars[i] = 'A';
            else if(currentChar == '1')
                inputChars[i] = 'C';
            else if(currentChar == '2')
                inputChars[i] = 'T';
            else if(currentChar == '3')
                inputChars[i] = 'G';
            else
                return "error";
        }

        return String.valueOf(inputChars);
    }


}