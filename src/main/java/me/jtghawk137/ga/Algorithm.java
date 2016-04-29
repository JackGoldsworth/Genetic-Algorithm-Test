package me.jtghawk137.ga;

public class Algorithm
{

    private double mutationRate;
    private double changeRate;
    private int tournamentSize;
    private boolean chooseFittest;
    private boolean keepFittest;

    public Algorithm(double mutationRate, double changeRate, int tournamentSize, boolean chooseFittest, boolean keepFittest)
    {
        this.mutationRate = mutationRate;
        this.changeRate = changeRate;
        this.tournamentSize = tournamentSize;
        this.chooseFittest = chooseFittest;
        this.keepFittest = keepFittest;
    }

    public Algorithm()
    {
        mutationRate = 0.015;
        changeRate = 0.5;
        tournamentSize = 5;
        chooseFittest = false;
        keepFittest = true;
    }

    /**
     * Evolves to population into a new generation.
     *
     * @param population the population we want to evolve.
     * @return the new population.
     */
    public Population evolve(Population population)
    {
        Population newPopulation = new Population(population.getSize(), false);

        // Allow the for loops to skip over the fittest individual.
        int fittestOffset;

        if (keepFittest)
        {
            newPopulation.addIndividual(0, population.getFittest());
            fittestOffset = 1;
        } else
            fittestOffset = 0;

        // Getting two individuals to mate (Randomly depending on the chooseFittest variable),
        // then adding their child to the new population (The next generation).
        for (int i = fittestOffset; i < population.getSize(); i++)
        {
            Individual individual1 = selectIndividual(population);
            Individual individual2 = selectIndividual(population);
            Individual newIndividual = crossover(individual1, individual2);
            newPopulation.addIndividual(i, newIndividual);
        }

        // Finally we mutate the new population.
        for (int i = fittestOffset; i < newPopulation.getSize(); i++)
        {
            mutate(newPopulation.getIndividual(i));
        }

        return newPopulation;
    }

    /**
     * Changes an individual's genes randomly so we can see some advancements.
     *
     * @param individual
     */
    public void mutate(Individual individual)
    {
        for (int i = 0; i < individual.getSize(); i++)
        {
            if (Math.random() <= mutationRate)
            {
                byte gene = (byte) Math.round(Math.random());
                individual.setGene(i, gene);
            }
        }
    }

    /**
     * Crossing over two individual's genes.
     *
     * @param individual1 first individual you want to crossover.
     * @param individual2 second individual you want to crossover.
     */
    public Individual crossover(Individual individual1, Individual individual2)
    {
        Individual newIndividual = new Individual(64); // The new individual we are creating by crossing the other individuals genes
        for (int i = 0; i < newIndividual.getSize(); i++)
        {
            if (Math.random() <= changeRate)
            {
                newIndividual.setGene(i, individual1.getGene(i));
            } else
            {
                newIndividual.setGene(i, individual2.getGene(i));
            }
        }
        return newIndividual;
    }

    /**
     * Selecting the individual for crossing over. If chooseFittest is true,
     * it will choose the fittest individual, but if false it will choose a random individual.
     *
     * @param population the population from which to select from
     * @return the selected individual
     */
    public Individual selectIndividual(Population population)
    {
        Population tournament = new Population(tournamentSize, false);
        for (int i = 0; i < tournamentSize; i++)
        {
            int randomId = (int) (Math.random() * population.getSize());
            tournament.addIndividual(i, population.getIndividual(randomId));
        }
        Individual individual;
        if (chooseFittest)
        {
            individual = tournament.getFittest();
        } else
        {
            individual = population.getIndividual((int) (Math.random() * population.getSize()));
        }
        return individual;
    }
}