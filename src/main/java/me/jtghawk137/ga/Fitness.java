package me.jtghawk137.ga;

public class Fitness
{

    private static byte[] solution = new byte[64];

    /**
     * Set the new solution using a string of bytes
     *
     * @param newSolution in 0's and 1's
     */
    public static void setSolution(String newSolution)
    {
        solution = new byte[solution.length];
        for (int i = 0; i < solution.length; i++)
        {
            String character = newSolution.substring(i, i + 1); // Singling out the character from the rest of the string
            if (character.equals("0") || character.equals("1"))
            {
                solution[i] = Byte.parseByte(character);
            } else
            {
                solution[i] = 0;
            }
        }
    }

    /**
     * This method just compares the genes of the individual to the genes that we want,
     * then for each match within the pair, the fitness level is bumped up by one.
     *
     * @param individual The individual that we are getting the fitness level for.
     * @return The fitness level
     */
    public static int getFitness(Individual individual)
    {
        int fitness = 0;
        for (int i = 0; i < solution.length && i < individual.getSize(); i++)
        {
            if (individual.getGene(i) == solution[i])
            {
                fitness++;
            }
        }
        return fitness;
    }

    /**
     * Getting the max size that the genes of an individual and a solution can share.
     *
     * @return max fitness
     */
    public static int getMaxFitness()
    {
        return solution.length;
    }
}