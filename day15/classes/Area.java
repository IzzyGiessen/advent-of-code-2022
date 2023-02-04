package aoc;

import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Area {
  private List<Sensor> sensors;

  public Area() {
    sensors = new ArrayList();
  }

  public void addSensor(Sensor sensor) {
    sensors.add(sensor);
  }

  public int notPresentCount(int row) {
    int count = 0;
    int minX = 0;
    int maxX = 0;
    for (Sensor s : sensors) {
      minX = Math.min(minX, s.x - s.beaconDistance());
      maxX = Math.max(maxX, s.x + s.beaconDistance());
    }
    for (int x = minX; x <= maxX; x++) {
      count += isPresent(new Point(x, row)) ? 1 : 0;
    }
    return count;
  }

  public boolean isPresent(Point p) {
    for (Sensor s : sensors) {
      int dis = p.distance(s);
      if (dis <= s.beaconDistance() && !p.equals(s.beacon)) {
        return true;
      }
    }
    return false;
  }

}
