/*
PROG2 VT2024, Inlämningsuppgift, del 1
Grupp 59
Erik Hubendick erhu5251
Mariia Loskutova malo6383
 */

public class Edge<T> {

    private T destination;
    private String name;
    private int weight;

    public Edge(T destination, String name, int weight) {
        this.destination = destination;
        this.name = name;
        this.weight = weight;
    }

    public T getDestination() {
        return destination;
    }

    public void setWeight(int weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("Edge weight cannot be < 0");
        } else {
            this.weight = weight;
        }
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return " till " + destination + " med " + name + " tar " + weight;
    }
}
