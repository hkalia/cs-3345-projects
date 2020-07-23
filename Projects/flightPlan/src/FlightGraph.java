public class FlightGraph {
    int numNodes;
    int numEdges;
    LinkedList<CityData> adjList;

    void addEdge(String[] inputLine) {
        addEdge(inputLine[0], inputLine[1], Double.parseDouble(inputLine[2]), Integer.parseInt(inputLine[3]));
    }

    void addEdge(String cityA, String cityB, double dollarCost, int minuteCost) {
        if (adjList == null) {
            adjList = new LinkedList<>(new CityData(cityA, new ConnectionData(cityB, dollarCost, minuteCost)));
            return;
        }
        Node<CityData> lastNode = null;
        for (Node<CityData> cur : adjList) {
            if (cur.data.cityName.compareTo(cityA) == 0) {
                cur.data.connections.add(new ConnectionData(cityB, dollarCost, minuteCost));
            }
            if (cur.data.cityName.compareTo(cityB) == 0) {
                cur.data.connections.add(new ConnectionData(cityA, dollarCost, minuteCost));
            }
        }
        adjList.add(new CityData(cityA, new ConnectionData(cityB, dollarCost, minuteCost)));
    }

    void print() {
        for (Node<CityData> cur : adjList) {
            System.out.print(cur.data.cityName + ": ");
            System.out.println(cur.data.connections);
        }
    }
}
