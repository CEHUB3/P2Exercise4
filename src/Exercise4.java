import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Exercise4 {
    private Graph<Node> graph = new ListGraph<>();

    public void loadLocationGraph(String fileName) throws IOException {
        try {
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
                if (graph.getEdgeBetween(node1, node2) == null) {
                    graph.connect(node1, node2, edgeName, weight);
                }
            }
            fr.close();
            br.close();
        } catch (FileNotFoundException e) {
            System.err.println("file not found" + e.getMessage());
        }
    }

    public SortedMap<Integer, SortedSet<Record>> getAlsoLiked(Record item) {
        SortedMap<Integer, SortedSet<Record>> recordsByPopularity = new TreeMap<>(Collections.reverseOrder());
        Collection<Edge<Node>> firstEdges = graph.getEdgesFrom(item);
        Set<Person> persons = new HashSet<>();
        for (Edge<Node> edge : firstEdges) {
            persons.add((Person) edge.getDestination());
        }
        for (Person p : persons) {
            Collection<Edge<Node>> secondEdges = graph.getEdgesFrom(p);
            for (Edge<Node> edge : secondEdges) {
                Record record = (Record) edge.getDestination();
                Integer popularity = getPopularity(record);
                SortedSet<Record> sortedRecords = recordsByPopularity.get(popularity);
                if (sortedRecords == null) {
                    sortedRecords = new TreeSet<>(Comparator.comparing(Record::getName));
                    sortedRecords.add(record);
                    recordsByPopularity.put(popularity, sortedRecords);
                }
                sortedRecords.add(record);
                recordsByPopularity.put(popularity, sortedRecords);
            }
        }
        return recordsByPopularity;
    }

    public int getPopularity(Record item) {
        Collection<Edge<Node>> outDegree = graph.getEdgesFrom(item);
        return outDegree.size();
    }

    public SortedMap<Integer, Set<Record>> getTop5() {
        SortedMap<Integer, Set<Record>> recordsByPopularity = new TreeMap<>(Comparator.reverseOrder());
        Set<Node> records = graph.getNodes();
        for (Node node : records) {
            if (node instanceof Record record) {
                int popularity = getPopularity(record);
                Set<Record> sortedRecords = recordsByPopularity.get(popularity);
                if (sortedRecords == null) {
                    sortedRecords = new TreeSet<>(Comparator.comparing(Record::getName));
                    sortedRecords.add(record);
                    recordsByPopularity.put(popularity, sortedRecords);
                }
                sortedRecords.add(record);
                recordsByPopularity.put(popularity, sortedRecords);
            }
        }
        int count = 0;
        SortedMap<Integer, Set<Record>> top5ByPopularity = new TreeMap<>(Comparator.reverseOrder());
        for (Map.Entry<Integer, Set<Record>> entryset : recordsByPopularity.entrySet()) {
            if (count >= 5) {
                break;
            }
            top5ByPopularity.put(entryset.getKey(), entryset.getValue());
            count++;
        }
        return top5ByPopularity;
    }

    public void loadRecommendationGraph(String fileName) {
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            Set<Record> records = new HashSet<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] ownership = line.split(";");
                for (int i = 0; i < ownership.length; i++) {
                    Person person = new Person(ownership[0]);
                    Record record = new Record(ownership[1], ownership[2]);
                    records.add(record);
                    graph.add(person);
                    graph.add(record);
                    if (graph.getEdgeBetween(person, record) == null) {
                        graph.connect(person, record, "", 0);
                    }
                }
            }
            fr.close();
            br.close();
        } catch (FileNotFoundException e) {
            System.err.println(fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOfel");
        }
    }
}
