package aoc;

public class Sensor extends Point {
  Point beacon;

  public Sensor(int x, int y) {
    super(x, y);
  }

  public Sensor(int x, int y, Point beacon) {
    super(x, y);
    this.beacon = beacon;
  }

  public void setBeacon(Point beacon) {
    this.beacon = beacon;
  }

  public int beaconDistance() {
    return this.distance(beacon);
  }

  public static Sensor readLine(String line) {
    String[] parts = line.split(":");
    String sens = parts[0].substring(10);
    Sensor sensor = new Sensor(
        Integer.parseInt(sens.split(", ")[0].substring(2)),
        Integer.parseInt(sens.split(", ")[1].substring(2)));

    String beac = parts[1].substring(22);
    Point beacon = new Point(
        Integer.parseInt(beac.split(", ")[0].substring(2)),
        Integer.parseInt(beac.split(", ")[1].substring(2)));
    sensor.setBeacon(beacon);
    return sensor;
  }
}
