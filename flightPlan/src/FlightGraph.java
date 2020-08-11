public class FlightGraph {
    int numNodes;
    int numEdges;
    LinkedList<CityData> adjList;

    void addEdge(String[] inputLine) {
        addEdge(inputLine[0], inputLine[1], Double.parseDouble(inputLine[2]), Integer.parseInt(inputLine[3]));
        numNodes++;
        numEdges++;
    }

    void addEdge(String cityA, String cityB, double dollarCost, int minuteCost) {
        if (adjList == null) {
            adjList = new LinkedList<>(new CityData(cityA, new ConnectionData(cityB, dollarCost, minuteCost)));
            adjList.add(new CityData(cityB, new ConnectionData(cityA, dollarCost, minuteCost)));
            numNodes += 2;
            numEdges++;
            return;
        }
        Node<CityData> nodeCityA = null;
        Node<CityData> nodeCityB = null;
        for (Node<CityData> cur : adjList) {
            if (cur.data.cityName.compareTo(cityA) == 0) nodeCityA = cur;
            if (cur.data.cityName.compareTo(cityB) == 0) nodeCityB = cur;
            if (nodeCityA != null && nodeCityB != null) break;
        }
        if (nodeCityA != null && nodeCityB != null) {
            nodeCityA.data.connections.add(new ConnectionData(cityB, dollarCost, minuteCost));
            nodeCityB.data.connections.add(new ConnectionData(cityA, dollarCost, minuteCost));
            numEdges++;
        }
        if (nodeCityA != null && nodeCityB == null) {
            nodeCityA.data.connections.add(new ConnectionData(cityB, dollarCost, minuteCost));
            adjList.add(new CityData(cityB, new ConnectionData(cityA, dollarCost, minuteCost)));
            numNodes++;
            numEdges++;
        }
        if (nodeCityA == null && nodeCityB != null) {
            nodeCityB.data.connections.add(new ConnectionData(cityA, dollarCost, minuteCost));
            adjList.add(new CityData(cityA, new ConnectionData(cityB, dollarCost, minuteCost)));
            numNodes++;
            numEdges++;
        }
        if (nodeCityA == null && nodeCityB == null) {
            adjList.add(new CityData(cityA, new ConnectionData(cityB, dollarCost, minuteCost)));
            adjList.add(new CityData(cityB, new ConnectionData(cityA, dollarCost, minuteCost)));
            numNodes += 2;
            numEdges++;
        }
    }

    void print() {
        for (Node<CityData> cur : adjList) {
            System.out.print(cur.data.cityName + ": ");
            System.out.println(cur.data.connections);
        }
    }
}
