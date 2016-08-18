package transit;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface MetrolinkDao {

  public List<Stop> getStopsAllStops();
  public LocalTime getTimeOfNextTrain(Stop stop, LocalDateTime currentDayTime);

}
