public class CityData {
    String cityName;
    LinkedList<ConnectionData> connections;

    CityData(String cityName, ConnectionData connections) {
        this.cityName = cityName;
        this.connections = new LinkedList<>(connections);
    }

    @Override
    public String toString() {
        return "CityData{" + this.cityName + ", " + this.connections + "}";
    }
}
