import java.util.ArrayList;

public class ShortPathFinder {

    public void dijkstra(FlightGraph graph, String startCityName, String endCityName, Boolean costPath) {
        if (graph.adjList == null)
            return;
        CityData start = null;
        CityData end = null;
        ArrayList<CityData> notFoundList = new ArrayList<>();
        for (Node<CityData> cur : graph.adjList) {
            if (cur.data.cityName.compareTo(startCityName) == 0)
                start = cur.data;
            if (cur.data.cityName.compareTo(endCityName) == 0)
                end = cur.data;
            cur.data.cost = Integer.MAX_VALUE;
            notFoundList.add(cur.data);
        }
        start.cost = 0;
        start.minuteCost = 0;
        start.pennyCost = 0;
        while (!notFoundList.isEmpty()) {
            int minCost = Integer.MAX_VALUE;
            CityData minCostCity = null;
            for (CityData cur : notFoundList) {
                if (cur.cost < minCost) {
                    minCostCity = cur;
                }
            }
            if (minCostCity.cost == Integer.MAX_VALUE)
                break; // only paths left do not lead to start node, break loop
            minCostCity.known = true;
            notFoundList.remove(minCostCity);
            for (Node<ConnectionData> cur : minCostCity.connections) {
                for (CityData city : notFoundList)
                    if (cur.data.cityName.compareTo(city.cityName) == 0) // if city unknown
                        if (minCostCity.cost + weight(cur, costPath) < city.cost) {
                            city.cost = minCostCity.cost + weight(cur, costPath);
                            city.pennyCost = minCostCity.pennyCost + cur.data.pennyCost;
                            city.minuteCost = minCostCity.minuteCost + cur.data.minuteCost;
                            city.path = minCostCity;

                        }
            }
        }
        printPath(graph, start, end);
    }

    public int weight(Node<ConnectionData> connection, Boolean costPath) {
        return costPath == true ? connection.data.minuteCost : connection.data.pennyCost;
    }


    public void printPath(FlightGraph graph, CityData start, CityData destination) {
        String str = destination.cityName;
        int minuteCost = destination.minuteCost;
        int pennyCost = destination.pennyCost;
        while (destination.cityName.compareTo(start.cityName) != 0 && destination.path != null) {
            destination = destination.path;
            str = destination.cityName + " -> " + str;
        }
        System.out.println("" + str + ". Time: " + minuteCost + " Cost: " + pennyCost / 100);
    }
}
