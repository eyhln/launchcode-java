package transit.dao;

import java.time.LocalDateTime;
import java.time.LocalTime;

public interface DaoInputOutputFormatter {
	
	public String removeMetrolinkStationFromName(String stopName);
	public String formatTimeToMatchDatabase(LocalDateTime dateTime);
	public String formatDateToMatchDatabase(LocalDateTime dateTime);
	public LocalTime formatReturnStringToLocalTime(String result);

}
