
import java.io.*;
import java.util.*;

/**
 * Adds entries to the Roommate+ database
 */
public class Match
{
    public static final int NUM_QUESTIONS = 10;
    public static final File f = new File("./resources/questions.txt");
    public static final Question[] questionnaire = loadQuestions(f);
    public static final double TOTAL_WEIGHT = calcTotalWeight(questionnaire);
    
    public static final ArrayList<String> GENDERS = new ArrayList<>(); 
    
    public static void main(String[] args) 
    {
        GENDERS.add("F");
        GENDERS.add("M");
        GENDERS.add("NB");

        System.out.println("Welcome to Roommate+! \n");
        
        Scanner in = new Scanner(System.in);

        Student student = getStudentInfo(in);

        if(student != null)
        {
            double[] responses = askQuestions(in);
            student.addResponses(responses);

            store(student);
        }
        in.close();
    }


    private static void store(Student s)
    {
        File data = new File("./resources/data.txt");
        try 
        {
            FileWriter fw = new FileWriter(data, true);
            fw.append(s.toString() + "\n");
            fw.close();
        } 
        catch (Exception e) 
        {
            System.out.println("File data.txt not found");
        }

    }

    //Debugging method
    private static void printArray(double[] arr)
    {
        for(double d : arr)
        {
            System.out.print(d + " ");
        }
        System.out.println();
    }

    private static Student getStudentInfo(Scanner in)
    {
        //Ask general information
        System.out.println("Enter your Duke NET ID: ");
        String name = in.nextLine();

        System.out.println("Enter your graduation year: ");
        int year = Integer.parseInt(in.nextLine());
        if(year < 2025)
        {
            System.out.println("Roommate+ is not for UNCS!");
            return null;
        }
        else if(year > 2028)
        {
            System.out.println("Too young for Roommate+");
            return null;
        }
        
        System.out.println("What is your gender? (F, M, NB)");
        String gender = in.nextLine();
        while((gender.equals("F") || gender.equals("M") || gender.equals("NB")) == false)
        {
            System.out.println("Try again: ");
            gender = in.nextLine();
        }

        System.out.println("What gender roommate do you prefer? (F, M, NB)");
        String gender_pref = in.nextLine();
        while((gender_pref.equals("F") || gender_pref.equals("M") || gender_pref.equals("NB")) == false)
        {
            System.out.println("Try again: ");
            gender_pref = in.nextLine();
        }

        System.out.println("What quad do you plan to live in? \n (1) Craven \n (2) Crowell \n (3) Edens \n (4) Few \n (5) Hollows \n (6) Keohane \n (7) Kilgo \n (8) Swift \n (9) Wannamaker");

        int quad = Integer.parseInt(in.nextLine());
        while(quad < 1 || quad > 9)
        {
            System.out.println("Try again: ");
            quad = Integer.parseInt(in.nextLine());
        }

        return new Student(name, year, GENDERS.indexOf(gender), GENDERS.indexOf(gender_pref), quad-1);
    }

    private static double[] askQuestions(Scanner in)
    {
        double[] responses = new double[NUM_QUESTIONS];

        for(int i = 0; i < questionnaire.length; i++)
        {
            System.out.println(questionnaire[i]);
            int input = Integer.parseInt(in.nextLine());
            while(input < 1 || input > (questionnaire[i].max_diff+1))
            {
                System.out.println("Try again: ");
                input = Integer.parseInt(in.nextLine());
            }
            responses[i] = input;
        }
        return responses;
    }

    private static Question[] loadQuestions(File qlist)
    {
        ArrayList<Question> list = new ArrayList<>();

        try 
        {
            Scanner scanner = new Scanner(qlist);
            
            while(scanner.hasNextLine())
            {
                String weight_quant = scanner.nextLine();
                double weight = Double.parseDouble(weight_quant.substring(0,weight_quant.length()-1));
                boolean quant = weight_quant.charAt(weight_quant.length()-1) == 'q';
                
                String q = scanner.nextLine();
                ArrayList<String> answers = new ArrayList<>();
                
                while(true)
                {
                    String line = scanner.nextLine();
                    if(line.equals(" "))
                    {
                        break;
                    }
                    answers.add(line);
                }
                list.add(new Question(q, answers.toArray(String[]::new), weight, quant));
            }
            scanner.close();
            return list.toArray(Question[]::new);

        }
        catch (FileNotFoundException f) 
        {
            System.out.println(f);
            return null;
        }

    }

    private static double calcTotalWeight(Question[] questionnaire)
    {
        double sum = 0;
        for(Question q : questionnaire)
        {
            sum += q.weight;
        }
        return sum;
    }

}