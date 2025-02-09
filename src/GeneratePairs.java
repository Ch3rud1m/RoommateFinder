import java.io.*;
import java.util.*;

public class GeneratePairs
{

    private static ArrayList<Student> students = new ArrayList<>();
    private static ArrayList<Student[]> matches = new ArrayList<>();

    //private static ArrayList<Double> comp_scores = new ArrayList<>(); //DEBUGGING

    public static void main(String[] args)
    {
        File data = new File("./resources/data.txt");
        
        try
        {
            Scanner data_reader = new Scanner(data);
            loadStudents(data_reader);
            data_reader.close();

            double[][] scores = new double[students.size()][students.size()]; //upper triangular matrix

            getMatchScores(scores); //stores compatibility scores in matrix, i < j
            
            while(!empty(scores)) //O(N)
            {
                Student[] match = getTopMatch(scores); //O(N^2)
                if(match == null)
                {
                    System.out.println("WARNING: Some students may not be matched");
                    break;
                }
                matches.add(match); //O(1)
            }

            printMatches();
        }
        catch (Exception e) 
        {
            System.out.println(e);
        }
    }

    /**
     * Load students from data.txt to Java
     */
    private static void loadStudents(Scanner data_reader)
    {
        while(data_reader.hasNextLine())
        {
            Student s = new Student(data_reader.nextLine());
            students.add(s);
        }
    }

    private static void getMatchScores(double[][] scores)
    {
        for(int i = 0; i < students.size()-1; i++)
        {
            for(int j = i+1; j < students.size(); j++)
            {
                scores[i][j] = students.get(i).matchScore(students.get(j));
            }
        }
    }

    private static boolean empty(double[][] scores)
    {
        for(int i = 0; i < students.size()-1; i++)
        {
            for(int j = i+1; j < students.size(); j++)
            {
                if(scores[i][j] != 0) return false;
            }
        }
        return true;
    }

    /**
     * Finds the most compatible match and removes it
     */
    private static Student[] getTopMatch(double[][] scores)
    {
        int i_max = -1;
        int j_max = -1;
        double score_max = 0;
        
        for(int i = 0; i < scores.length-1; i++)
        {
            for(int j = i+1; j < scores.length; j++)
            {
                if(scores[i][j] > score_max)
                {
                    score_max = scores[i][j];
                    i_max = i;
                    j_max = j;
                }
            }
        }
        if(i_max == -1 || j_max == -1) return null;

        Student[] top_match = new Student[2];
        top_match[0] = students.get(i_max);
        top_match[1] = students.get(j_max);
        //comp_scores.add(score_max); //DEBUGGING
        removeRC(scores, i_max);
        removeRC(scores, j_max);
        return top_match;
    }

    private static void removeRC(double[][] scores, int index)
    {
        if(index >= scores.length) return;

        for(int i = 0; i < scores.length; i++)
        {
            scores[i][index] = 0;
            scores[index][i] = 0;
        }
    }

    private static void printMatches()
    {
        //int count = 0; //DEBUGGING
        for(Student[] pair : matches)
        {
            System.out.println(pair[0].getName() + " matches with " + pair[1].getName());
            //System.out.println(pair[0].getName() + " matches with " + pair[1].getName() + " (score: " + comp_scores.get(count) + ")"); //DEBUGGING
            //count++;//DEBUGGING
        }
    }
}