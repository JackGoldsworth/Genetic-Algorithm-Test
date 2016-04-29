package me.jtghawk137.ga;

public class Population
{
    private Individual[] individuals;
    private int size;
    private boolean shouldInit;

    public Population(int size, boolean shouldInit)
    {
        individuals = new Individual[size];
        this.size = size;
        this.shouldInit = shouldInit;
        if (shouldInit)
            init();
    }

    public void init()
    {
        for (int i = 0; i < individuals.length; i++)
        {
            Individual individual = new Individual(64);
            individual.generate();
            addIndividual(i, individual);
        }
    }

    public Individual getIndividual(int index)
    {
        return individuals[index];
    }

    /**
     * Getting the fittest individual in the population.
     *
     * @return individual
     */
    public Individual getFittest()
    {
        Individual fittest = individuals[0];
        for (int i = 0; i < individuals.length; i++)
        {
            if (individuals[i].getFitness() >= fittest.getFitness())
            {
                fittest = individuals[i];
            }
        }
        return fittest;
    }

    public void addIndividual(int index, Individual individual)
    {
        individuals[index] = individual;
    }

    public int getSize()
    {
        return size;
    }
}