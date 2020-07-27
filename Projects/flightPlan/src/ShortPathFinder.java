import java.util.PriorityQueue;

public class ShortPathFinder {

    public void dijkstra(FlightGraph graph, Node<CityData> start, Boolean costPath) {    // costPath = true ? minuteCost : pennyCost
        if (graph.adjList == null) return;
        start.data.cost = 0;
        PriorityQueue<Node<CityData>> notFoundHeap = new PriorityQueue<>(graph.numNodes, new PathComparator());
        for (Node<CityData> cur : graph.adjList)
            notFoundHeap.add(cur);
        while (!notFoundHeap.isEmpty()) {
            Node<CityData> minCostNode = notFoundHeap.peek();
            if (minCostNode.data.cost == Integer.MAX_VALUE)
                break;    // only paths left do not lead to start node, break loop
            minCostNode.data.known = true;
            notFoundHeap.remove();
            for (Node<ConnectionData> cur : minCostNode.data.connections) {
                for (Node<CityData> cityA : notFoundHeap) {
                    if (cityA.data.cityName.compareTo(cur.data.cityName) == 0) { // if cityA is unknown
                        if (minCostNode.data.cost + weight(cur, costPath) < cityA.data.cost) {
                            cityA.data.cost = minCostNode.data.cost + weight(cur, costPath);
                            cityA.data.path = minCostNode;
                        }
                    }
                }
            }

        }
    }

    public int weight(Node<ConnectionData> connection, Boolean costPath) {
        if (costPath) {
            return connection.data.minuteCost;
        } else
            return connection.data.pennyCost;
    }

    public void printPath(FlightGraph graph, Node<CityData> start, Node<CityData> destination) {
        String str = destination.data.cityName;
        while (destination.data.cityName.compareTo(start.data.cityName) != 0) {
            destination = destination.data.path;
            str = destination.data.cityName + " -> " + str;
        }
        System.out.println("Shortest Path: " + str);
    }
}
