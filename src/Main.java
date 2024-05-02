import java.io.IOException;

public class Main {

    static Exercise4 e4 = new Exercise4();

    public static void main(String[] args) throws IOException {


        System.out.println("Hello world!");

        e4.loadLocationGraph("C:\\Users\\dator\\IdeaProjects\\P2Exercise4\\src\\ex4location.graph");
        e4.loadRecommendationGraph("C:\\Users\\dator\\IdeaProjects\\P2Exercise4\\src\\ex4reco.graph");




    }
}