import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.SortedMap;

public class Exercise4 {
    private Graph<Node> graph = new ListGraph<>();

    public void loadLocationGraph(String fileName) throws IOException {

//        try{
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);

        String line1 = br.readLine();
        String[] locations = line1.split(";");
        for (int i = 0; i < locations.length; i = i + 3) {
            String location = locations[i];
            double x = Double.parseDouble(locations[i + 1]);
            double y = Double.parseDouble(locations[i + 2]);
            Location l = new Location(location, x, y);
            graph.add(l);
        }
        String lines;
        while ((lines = br.readLine()) != null) {
            String[] connection = lines.split(";");
            String from = connection[0];
            String destination = connection[1];
            String edgeName = connection[2];
            int weight = Integer.parseInt(connection[3]);
            Node node1 = null;
            Node node2 = null;
            for (Node n : graph.getNodes()) {
                if (n.getName().equals(from)) {
                    node1 = n;
                }
                if (n.getName().equals(destination)) {
                    node2 = n;
                }
            }
            graph.connect(node1, node2, edgeName, weight);
        }
        System.out.println(graph.toString());
        fr.close();
        br.close();
//    }
//    catch (FileNotFoundException e){
//        System.err.println("file not found" + e.getMessage());
//    }

    }

    public SortedMap<Integer, SortedSet<Record>> getAlsoLiked(Record item) {
        return null;
    }

    public int getPopularity(Record item) {
        return -1;
    }

    public SortedMap<Integer, Set<Record>> getTop5() {
        return null;
    }

    public void loadRecommendationGraph(String fileName) {
    }

}
