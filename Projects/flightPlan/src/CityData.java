public class CityData {
    String cityName;
    LinkedList<ConnectionData> connections;
    Boolean known;
    Integer cost;
    CityData path;
    
    CityData(String cityName, ConnectionData connections) {
        this.cityName = cityName;
        this.connections = new LinkedList<>(connections);
        this.known = false;
        this.cost = Integer.MAX_VALUE;
        this.path = null;
    }

    @Override
    public String toString() {
        return "CityData{" + this.cityName + ", " + this.connections + "}";
    }
}
