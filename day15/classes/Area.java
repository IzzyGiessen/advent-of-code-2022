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

  public Point notPresentCount() {
    int count = 0;
    int min = 0;
    int max = 4000000;
    for (int y = min; y <= max; y++) {
      for (int x = min; x <= max; x++) {
        int add = isPresent(new Point(x, y));
        if (add == -1) {
          return new Point(x, y);
        }
        x += add;
      }
    }
    return null;
  }

  public int isPresent(Point p) {
    for (Sensor s : sensors) {
      int dis = p.distance(s);
      if (dis <= s.beaconDistance()) {
        if (p.x - s.x > 0) {
          return (p.x - s.x) * 2;
        } else {
          return (s.beaconDistance() - Math.abs(p.y - s.y)) - p.x + s.x;
        }
      }
    }
    return -1;
  }

}
