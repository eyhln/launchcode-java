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
  	String formattedTime = timeString.substring(0,NUMBER_CHARS_IN_DB_TIME_FORMAT);
  	if (formattedTime.compareTo(MIN_ARRIVAL_TIME_FOR_ALL_STOPS) <= 0) {
  		String hour = formattedTime.substring(0,2);
  		String nonHour = formattedTime.substring(2,formattedTime.length());
  		int hourField = Integer.parseInt(hour);
  		int adjustedHourField = hourField + 24;
  		formattedTime = adjustedHourField + nonHour;
  	}
  	return formattedTime;
  }
	
	 public String formatDateToMatchDatabase(LocalDateTime dateTime) {
   	LocalDate date = dateTime.toLocalDate();
   	DateTimeFormatter dateFormatter = DateTimeFormatter.BASIC_ISO_DATE;
   	String formattedDate = date.format(dateFormatter);
   	return formattedDate;
   }
	 
	 public LocalTime formatReturnStringToLocalTime(String result) {
   	if (result.compareTo("23:59:59.999") > 0) {
   		String hour = result.substring(0,2);
   		String minutesSeconds = result.substring(2,result.length());
   		int hourField = Integer.parseInt(hour);
   		int adjustedHourField = hourField - 24;
   		result = "0" + adjustedHourField + minutesSeconds;
   	}
     DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;
     LocalTime nextTrainArrivalTime = formatter.parse(result, LocalTime::from);
     return nextTrainArrivalTime;
   }

}
