public class Question
{
    private String question;
    private String[] answers;
    public double weight;

    public boolean quant; //0-quantitative scale, 1-categorical scale
    public int max_diff;
    

    public Question(String question, String[] answers, double weight, boolean quant)
    {
        this.question = question;
        this.answers = answers;
        this.weight = weight;
        
        max_diff = answers.length-1;
        this.quant = quant;
    }

    //Cat: 4589

    @Override
    public String toString()
    {
        String ret = this.question;

        for(int i = 1; i <= answers.length; i++)
        {
            ret += "\n ("+i+") " + answers[i-1];
        }

        return ret + "\n";
    }

}