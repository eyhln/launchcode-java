package transit.dao;

import java.time.*;
import java.time.format.*;

public class SqliteDaoDateTimeFormatter implements DaoDateTimeFormatter {
	
	private final int NUMBER_CHARS_IN_DB_TIME_FORMAT = 8;
	private String MIN_ARRIVAL_TIME_FOR_ALL_STOPS = "03:15:00";
	private String MAX_ARRIVAL_TIME_FOR_ALL_STOPS = "26:24:00";
	
	public String formatTimeToMatchDatabase(LocalDateTime dateTime) {
  	LocalTime time = dateTime.toLocalTime();
  	DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
  	String timeString = time.format(timeFormatter);
  	String formattedTime = removeNanoSeconds(timeString);
  	if (formattedTime.compareTo(MIN_ARRIVAL_TIME_FOR_ALL_STOPS) <= 0) {
  		String lateNightTime = formattedTime;
  		formattedTime = adjustLateNightTime(lateNightTime);
  	}
  	return formattedTime;
  }
	
	private String removeNanoSeconds(String timeString) {
		timeString.substring(0,NUMBER_CHARS_IN_DB_TIME_FORMAT);
		return timeString;
	}
	
	private String adjustLateNightTime(String time) {
		String hour = time.substring(0,2);
		String nonHour = time.substring(2,time.length());
		int hourField = Integer.parseInt(hour);
		int adjustedHourField = hourField + 24;
		time = adjustedHourField + nonHour;
		return time;
	}
	
	 public String formatDateToMatchDatabase(LocalDateTime dateTime) {
   	LocalDate date = dateTime.toLocalDate();
   	DateTimeFormatter dateFormatter = DateTimeFormatter.BASIC_ISO_DATE;
   	String formattedDate = date.format(dateFormatter);
   	return formattedDate;
   }
	 
	 public LocalTime formatReturnStringToLocalTime(String result) {
		String toReturn;
   	if (result.compareTo("23:59:59.999") > 0) {
   		String lateNightReturnTime = adjustLateNightReturnTime(result);
   		toReturn = lateNightReturnTime;
   	}
   	else
   		toReturn = result;
     DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;
     LocalTime nextTrainArrivalTime = formatter.parse(toReturn, LocalTime::from);
     return nextTrainArrivalTime;
   }
	 
	 private String adjustLateNightReturnTime(String result) {
  		String hour = result.substring(0,2);
  		String minutesSeconds = result.substring(2,result.length());
  		int hourField = Integer.parseInt(hour);
  		int adjustedHourField = hourField - 24;
  		result = "0" + adjustedHourField + minutesSeconds;
  		return result;
	 }

}
