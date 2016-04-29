package me.jtghawk137.ga;

/**
 * @author JTGhawk137
 *         Made while following (http://www.theprojectspot.com/tutorial-post/creating-a-genetic-algorithm-for-beginners/3)
 */
public class GeneticAlgorithm
{

    public static void main(String[] args)
    {
        Fitness.setSolution("111100000000000000000000000000000000000000000000000000000000000000000001111");
        Population population = new Population(50, false);
        Algorithm algorithm = new Algorithm();
        int generation = 0;
        while (population.getFittest().getFitness() < Fitness.getMaxFitness())
        {
            generation++;
            System.out.println("Generation: " + generation + " Fittest: " + population.getFittest().getFitness());
            population = algorithm.evolve(population);
        }
        System.out.println("Solution found!");
        System.out.println("Generation: " + generation);
        System.out.println("Genes:");
        System.out.println(population.getFittest());
    }
}
