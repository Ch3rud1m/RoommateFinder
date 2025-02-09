public class Student
{
    private String name;
    private int year; //2025-2028
    private int gender; // 0-female, 1-male, 2-other
    private int gender_pref; // 0-female, 1-male, 2-other
    private int quad;

    private double[] responses;
    
    /**
     * 0 Craven
     * 1 Crowell
     * 2 Edens
     * 3 Few
     * 4 Hollows
     * 5 Keohane
     * 6 Kilgo
     * 7 Swift
     * 8 Wannamaker
     */

    public Student(String name, int year, int gender, int gender_pref, int quad)
    {
        this.name = name;
        this.year = year;
        this.gender = gender;
        this.gender_pref = gender_pref;
        this.quad = quad;
        responses = new double[Match.NUM_QUESTIONS];
    }

    public Student(String data_input)
    {
        String[] values = data_input.split(" ");
        
        this.name = values[0];
        this.year = Integer.parseInt(values[1]);
        this.gender = Integer.parseInt(values[2]);
        this.gender_pref = Integer.parseInt(values[3]);
        this.quad = Integer.parseInt(values[4]);
        responses = new double[Match.NUM_QUESTIONS];

        //populate responses instance variable
        for(int i = 0; i < responses.length; i++)
        {
            responses[i] = Double.parseDouble(values[i+5]);
        }
    }

    public String getName()
    {
        return name;
    }
    
    public void addResponses(double[] responses)
    {
        this.responses = responses;
    }

    /**
     * MATCHING ALGORITHM (higher score is better)
     */
    public double matchScore(Student other)
    {
        double score = 0;
        
        boolean absolutes_met = this.year == other.year && this.gender == other.gender_pref && this.gender_pref == other.gender && this.quad == other.quad;
        if(!absolutes_met)
        {
            return 0;
        }

        //Match.TOTAL_WEIGHT
        
        for(int i = 0; i < responses.length; i++)
        {
            if(Match.questionnaire[i].quant) //Quantitative Question (0 -> 1)
            {
                double diff = Math.abs(this.responses[i] - other.responses[i]);
                score += Match.questionnaire[i].weight * (diff/Match.questionnaire[i].max_diff);
            }
            else //Categorical Question (0 or 1)
            {
                if(this.responses[i] == other.responses[i])
                {
                    score += Match.questionnaire[i].weight;
                }
                //Else, if not matching, this weight is not added
            }
        }

        return (score/Match.TOTAL_WEIGHT);
    }

    @Override
    public String toString()
    {
        String ret = name + " " + year + " " + gender + " " + gender_pref + " " + quad;

        for(double d : responses)
        {
            ret += " " + d;
        }
        return ret;
    }
}