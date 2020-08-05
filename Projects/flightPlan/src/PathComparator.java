import java.util.Comparator;

public class PathComparator implements Comparator<Node<CityData>> {
    @Override
    public int compare(Node<CityData> cityA, Node<CityData> cityB) {
        if (cityA.data.cost < cityB.data.cost) return -1;
        else if (cityA.data.cost == cityB.data.cost) return 0;
        else return 1;
    }

}
