package transit.dao;

import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import transit.AppOutput;
import transit.MetrolinkDao;
import transit.Stop;


public class SqliteJDBCDao implements MetrolinkDao {

	//TODO resolve relative classpath issue
    public static final String JDBC_SQLITE_METROLINK_DB = "jdbc:sqlite:/home/marie/git/metrolink-stl-data/metrolink.db";
    public static final String ORG_SQLITE_JDBC = "org.sqlite.JDBC";

    private AppOutput appOutput;
    private final int WEEKDAY = 1;
    private final int SATURDAY = 2;
    private final int SUNDAY = 3;

    public List<Stop> getStopsAllStops() {
        appOutput.print("Fetching metrolink stations...");
        try (Connection connection = getConnection();) {
        	PreparedStatement preparedStatement = 
          		connection.prepareStatement(
          				"SELECT DISTINCT stop_name, stop_desc FROM stops " +
          				"WHERE stop_name LIKE '%METROLINK STATION';");
          ResultSet resultSet = preparedStatement.executeQuery();
          List<Stop> stops = new ArrayList<Stop>();
          while (resultSet.next()) {
              Stop stop = new Stop();
              stop.setStopName(resultSet.getString("stop_name"));
              stop.setStopDescription(resultSet.getString("stop_desc"));
              stops.add(stop);
          }
          stops = removeMetrolinkStationFromNames(stops);
          return stops;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving stops");
        }
    }
    
    List<Stop> removeMetrolinkStationFromNames(List<Stop> stops) {
    	for (Stop stop : stops) {
    		//TODO create regular expression to format text
    	}
    	return null;
    }
    
    public LocalTime getTimeOfNextTrain(Stop stop, LocalDateTime currentDayTime) {
    	appOutput.print("Fetching next train arrival time...");
    	String stopName = stop.getStopName();
    	String currentTime = formatCurrentTime(currentDayTime);
    	int trainScheduleCode = getDayOfWeekCode(currentDayTime);
      try (Connection connection = getConnection();) {
      	PreparedStatement preparedStatement = 
        		connection.prepareStatement(
        				"SELECT min(arrival_time) AS next_train " +
        				"FROM stops INNER JOIN stop_times " +
        				"ON stops.stop_id = stop_times.stop_id " +
        				"INNER JOIN trips " +
        				"ON stop_times.trip_id = trips.trip_id " +
        				"WHERE stop_name LIKE '" + stopName + "' " +
        				"AND arrival_time > '" + currentTime + "' " +
        				"AND service_id like '" + trainScheduleCode + "%';");
        ResultSet resultSet = preparedStatement.executeQuery();
        String nextTrainTimeString = resultSet.getString("next_train");
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;
        LocalTime nextTrainTime = formatter.parse(nextTrainTimeString, LocalTime::from);
        return nextTrainTime;

      } catch (SQLException e) {
          throw new RuntimeException("Error retrieving next train time");
      }
    }
    
    String formatCurrentTime(LocalDateTime currentDayTime) {
    	LocalTime currentTime = currentDayTime.toLocalTime();
    	DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;
    	String formattedTime = currentTime.format(formatter);
    	return formattedTime;
    }
    
    int getDayOfWeekCode(LocalDateTime currentDayTime) {
    	LocalDate currentDate = currentDayTime.toLocalDate();
    	DayOfWeek today = currentDate.getDayOfWeek();
    	int trainScheduleCode = 0;
    	switch (today) {
    		case SATURDAY:
    			trainScheduleCode = SATURDAY;
    			break;
    		case SUNDAY:
    			trainScheduleCode = SUNDAY;
    			break;
    		default:
    			trainScheduleCode = WEEKDAY;
    	}
    	return trainScheduleCode;
    }
    
    static Connection getConnection() throws SQLException {
        try {
            Class.forName(ORG_SQLITE_JDBC);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to find class for loading the database", e);
        }
        return DriverManager.getConnection(JDBC_SQLITE_METROLINK_DB);
    }

    public void setAppOutput(AppOutput appOutput) {
        this.appOutput = appOutput;
    }
}
