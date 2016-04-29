package me.jtghawk137.ga;

public class Individual
{
    private int geneLength;
    private byte[] genes;
    private int fitness;

    public Individual(int geneLength)
    {
        this.geneLength = geneLength;
        genes = new byte[geneLength];
        fitness = 0;
    }

    public void generate()
    {
        for (int i = 0; i < getSize(); i++)
        {
            byte randGene = (byte) Math.round(Math.random());
            setGene(i, randGene);
        }
    }

    public byte getGene(int index)
    {
        return genes[index];
    }

    public void setGene(int index, byte value)
    {
        genes[index] = value;
    }

    public int getSize()
    {
        return genes.length;
    }

    public int getFitness()
    {
        if (fitness == 0)
            fitness = Fitness.getFitness(this);
        return fitness;
    }

    @Override
    public String toString()
    {
        String genesString = "";
        for (int i = 0; i < getSize(); i++)
        {
            genesString += genes[i];
        }
        return genesString;
    }
}