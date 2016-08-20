package transit.dao;

import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.*;

import transit.MetrolinkDao;
import transit.Stop;


public class SqliteJDBCDao implements MetrolinkDao {

	//TODO resolve relative classpath issue
    public static final String JDBC_SQLITE_METROLINK_DB = "jdbc:sqlite:/home/marie/metrolink.db";
    public static final String ORG_SQLITE_JDBC = "org.sqlite.JDBC";
  	Pattern textToRemove = Pattern.compile(" METROLINK STATION");

    public List<Stop> getStopsAllStops() {
        try (Connection connection = getConnection();) {
        	PreparedStatement preparedStatement = 
          		connection.prepareStatement(
          				"SELECT DISTINCT stop_name, stop_desc FROM stops " +
          				"WHERE stop_name LIKE '%METROLINK STATION';");
          ResultSet resultSet = preparedStatement.executeQuery();
          List<Stop> stops = createListOfStops(resultSet);
          return stops;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving stops");
        }
    }
    
    private List<Stop> createListOfStops(ResultSet resultSet) throws SQLException {
    	List<Stop> stops = new ArrayList<Stop>();
      while (resultSet.next()) {
      	Stop stop = createNewStop(resultSet);
      	stops.add(stop);
      }
    	return stops;
    }
    
    private Stop createNewStop(ResultSet resultSet) throws SQLException {
      Stop stop = new Stop();
      String stopName = resultSet.getString("stop_name");
      String truncatedStopName = removeMetrolinkStationFromName(stopName);
      stop.setStopName(truncatedStopName);
      stop.setStopDescription(resultSet.getString("stop_desc"));
      return stop;
    }
    
    private String removeMetrolinkStationFromName(String stopName) {
    	Matcher matcher = textToRemove.matcher(stopName);
    	return matcher.replaceAll("");
    }
    
    //TODO troubleshoot integrating holiday schedule changes
    public LocalTime getTimeOfNextTrain(Stop stop, LocalDateTime fromDayTime) {
    	String stopName = stop.getStopName();
    	String currentTime = formatCurrentTimeToMatchDatabase(fromDayTime);
    	String today = fromDayTime.getDayOfWeek().toString();
      try (Connection connection = getConnection();) {
      	PreparedStatement preparedStatement = 
        		connection.prepareStatement(
        				"SELECT min(arrival_time) AS next_train " +
        				"FROM stops " +
        				"INNER JOIN stop_times ON stops.stop_id = stop_times.stop_id " +
        				"INNER JOIN trips ON stop_times.trip_id = trips.trip_id " +
        				"INNER JOIN calendar ON trips.service_id = calendar.service_id " +
        				"WHERE stop_name LIKE '" + stopName + "%' " +
        				"AND arrival_time > '" + currentTime + "' " +
        				"AND " + today + ";");
        ResultSet resultSet = preparedStatement.executeQuery();
        LocalTime nextTrainArrivalTime = retrieveNextArrivalTime(resultSet);
        return nextTrainArrivalTime;
      } catch (SQLException e) {
          throw new RuntimeException("Error retrieving next train time");
    	}
    }
    
    String formatCurrentTimeToMatchDatabase(LocalDateTime currentDayTime) {
    	LocalTime currentTime = currentDayTime.toLocalTime();
    	DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;
    	String formattedTime = currentTime.format(formatter);
    	return formattedTime;
    }
    
    private LocalTime retrieveNextArrivalTime(ResultSet resultSet) throws SQLException {
    	String nextTrainTimeString = resultSet.getString("next_train");
      DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;
      LocalTime nextTrainArrivalTime = formatter.parse(nextTrainTimeString, LocalTime::from);
      return nextTrainArrivalTime;
    }
    
    static Connection getConnection() throws SQLException {
        try {
            Class.forName(ORG_SQLITE_JDBC);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to find class for loading the database", e);
        }
        return DriverManager.getConnection(JDBC_SQLITE_METROLINK_DB);
    }

}
