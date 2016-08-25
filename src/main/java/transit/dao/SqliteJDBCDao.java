package transit.dao;

import java.sql.*;
import java.time.*;
import java.util.*;

import transit.MetrolinkDao;
import transit.Stop;


public class SqliteJDBCDao implements MetrolinkDao {

    public static final String JDBC_SQLITE_METROLINK_DB = "jdbc:sqlite:src/main/resources/metrolink.db";
    public static final String ORG_SQLITE_JDBC = "org.sqlite.JDBC";
    
  	private DaoInputOutputFormatter formatter;

  	
    public List<Stop> getStopsAllStops() {
        try (Connection connection = getConnection();) {
        	PreparedStatement preparedStatement = prepareGetAllStopsStatement(connection);
          ResultSet resultSet = preparedStatement.executeQuery();
          List<Stop> stops = createListOfStops(resultSet);
          return stops;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving stops");
        }
    }
    		
    		private PreparedStatement prepareGetAllStopsStatement(Connection connection) throws SQLException {
    			PreparedStatement preparedStatement = connection.prepareStatement(
          				"SELECT DISTINCT stop_name FROM stops " +
          				"WHERE stop_name LIKE '%METROLINK%STATION';");
    			return preparedStatement;
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
		      String truncatedStopName = formatter.removeMetrolinkStationFromName(stopName);
		      stop.setStopName(truncatedStopName);
		      return stop;
		    }
		    
    public LocalTime getTimeOfNextTrain(Stop stop, LocalDateTime dateTime) {
      try (Connection connection = getConnection();) {	
  			String statement = constructQuery(stop, dateTime);
      	PreparedStatement preparedStatement = connection.prepareStatement(statement);
        ResultSet resultSet = preparedStatement.executeQuery();
        return retrieveArrivalTime(resultSet);
      } catch (SQLException e) {
          throw new RuntimeException("Error retrieving next train time");
    	}
    }
    
    		private String constructQuery(Stop stop, LocalDateTime dateTime) {
        	String stopName = stop.getStopName();
        	String time = formatter.formatTimeToMatchDatabase(dateTime);
        	String date = formatter.formatDateToMatchDatabase(dateTime);
        	String dayName = dateTime.getDayOfWeek().toString();
          String statement = 
        				"SELECT min(arrival_time) AS next_train \n" +
          			"FROM stops \n" +
        				"INNER JOIN stop_times ON stops.stop_id = stop_times.stop_id \n" +
        				"INNER JOIN trips ON stop_times.trip_id = trips.trip_id \n" +
        				"INNER JOIN calendar ON trips.service_id = calendar.service_id \n" +
        				"INNER JOIN calendar_dates ON calendar.service_id = calendar_dates.service_id \n" +
        				"WHERE stop_name LIKE '" + stopName + " METROLINK%STATION' \n" +
        				"AND ((date = " + date + " AND exception_type = 1) \n" +
        				"OR ((date != " + date + ") AND " + dayName + ")) \n" +
        				"AND (arrival_time > '" + time + "' \n" +
        				"OR ('" + time + "' >= (" +
        				    "\tSELECT max(arrival_time) \n" + 
        				    "\tFROM stops \n" + 
        				    "\tINNER JOIN stop_times ON stops.stop_id = stop_times.stop_id \n" +
        				    "\tWHERE stop_name LIKE '" + stopName + " METROLINK%STATION') \n" +
        				    "\tAND arrival_time > '00:00:00'));\n"; 
    			return statement;
    		}
    		
    		private LocalTime retrieveArrivalTime(ResultSet resultSet) throws SQLException {
    			String nextTrainTimeString = resultSet.getString("next_train");
          LocalTime nextTrainArrivalTime = 
          		formatter.formatReturnStringToLocalTime(nextTrainTimeString);
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

    public void setDaoFormatter(DaoInputOutputFormatter formatter) {
    	this.formatter = formatter;
    }
}
