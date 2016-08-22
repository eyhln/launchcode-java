package transit.dao;

import java.time.LocalDateTime;
import java.time.LocalTime;

public interface DaoDateTimeFormatter {
	
	public String formatTimeToMatchDatabase(LocalDateTime dateTime);
	public String formatDateToMatchDatabase(LocalDateTime dateTime);
	public LocalTime formatReturnStringToLocalTime(String result);

}
