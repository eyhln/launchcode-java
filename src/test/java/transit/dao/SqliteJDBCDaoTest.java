package transit.dao;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import java.sql.*;
import java.time.*;
import java.util.*;
import transit.Stop;

public class SqliteJDBCDaoTest {
	
	SqliteJDBCDao dao;
	DaoInputOutputFormatter mockFormatter;
	Stop unionSta;
	
	@Before
	public void setUp() {
		dao = new SqliteJDBCDao();
		setUpMockDependency();
		setUpTestStop();
	}
	
	private void setUpMockDependency() {
		mockFormatter = mock(DaoInputOutputFormatter.class);
		dao.setDaoFormatter(mockFormatter);
	}
	
	private void setUpTestStop() {
		unionSta = new Stop();
		unionSta.setStopName("UNION STA");
	}


	@Test
	public void testEstablishesConnection() throws SQLException {
		Connection connection = SqliteJDBCDao.getConnection();
		assertNotNull(connection);
	}
	
	@Test
	public void testStopListLength() {
		List<Stop> stops = dao.getStopsAllStops();
		assertTrue(stops.size() == 37);
	}
	
	@Test (expected = RuntimeException.class)
	public void testThrowsException() {
		dao.getTimeOfNextTrain(null, null);
	}
	
	
	@Test
	public void testWeekdayAfternoonTrainTime() {
		LocalDateTime mondayAfternoon = LocalDateTime.of(2016,8,8,16,00,00,123);
		when(mockFormatter.formatDateToMatchDatabase(mondayAfternoon)).thenReturn("20160808");
		when(mockFormatter.formatTimeToMatchDatabase(mondayAfternoon)).thenReturn("16:00:00.123");
		LocalTime expected = LocalTime.of(16,01,00);
		when(mockFormatter.formatReturnStringToLocalTime("16:01:00")).thenReturn(expected);
		
		LocalTime nextTrain = dao.getTimeOfNextTrain(unionSta, mondayAfternoon);
		assertEquals(expected, nextTrain);
	}
	
	@Test
	public void testSaturdayAfternoonTrainTime() {
		LocalDateTime saturdayAfternoon = LocalDateTime.of(2016,8,13,16,00,00,123);
		when(mockFormatter.formatDateToMatchDatabase(saturdayAfternoon)).thenReturn("20160813");
		when(mockFormatter.formatTimeToMatchDatabase(saturdayAfternoon)).thenReturn("16:00:00.123");
		LocalTime expected = null;
		when(mockFormatter.formatReturnStringToLocalTime(null)).thenReturn(expected);
		
		LocalTime nextTrain = dao.getTimeOfNextTrain(unionSta, saturdayAfternoon);
		assertEquals(expected, nextTrain);
	}
	
	@Test
	public void testLateNightTrainTime() {
		LocalDateTime weekdayWeeHours = LocalDateTime.of(2016,8,8,1,00,00,123);
		when(mockFormatter.formatDateToMatchDatabase(weekdayWeeHours)).thenReturn("20160808");
		when(mockFormatter.formatTimeToMatchDatabase(weekdayWeeHours)).thenReturn("25:00:00.123");
		LocalTime expected = LocalTime.of(01,12,00);
		when(mockFormatter.formatReturnStringToLocalTime("25:12:00")).thenReturn(expected);

		LocalTime nextTrain = dao.getTimeOfNextTrain(unionSta, weekdayWeeHours);
		assertEquals(expected, nextTrain);
	}
	
	@Test
	public void testBetweenLastAndFirstTrainsTime() {
		LocalDateTime weekdayEarlyMorning = LocalDateTime.of(2016,8,8,3,00,00,123);
		when(mockFormatter.formatDateToMatchDatabase(weekdayEarlyMorning)).thenReturn("20160808");
		when(mockFormatter.formatTimeToMatchDatabase(weekdayEarlyMorning)).thenReturn("27:00:00.123");
		LocalTime expected = LocalTime.of(04,26,00);
		when(mockFormatter.formatReturnStringToLocalTime("04:26:00")).thenReturn(expected);

		LocalTime nextTrain = dao.getTimeOfNextTrain(unionSta, weekdayEarlyMorning);
		assertEquals(expected, nextTrain);
	}
	
	@Test
	public void testHolidayScheduleChangeTrainTime() {
		LocalDateTime fourthOfJuly = LocalDateTime.of(2016,7,4,16,00,00,123);
		when(mockFormatter.formatDateToMatchDatabase(fourthOfJuly)).thenReturn("20160704");
		when(mockFormatter.formatTimeToMatchDatabase(fourthOfJuly)).thenReturn("16:00:00.123");
		LocalTime expected = LocalTime.of(16,06,00);
		when(mockFormatter.formatReturnStringToLocalTime("16:06:00")).thenReturn(expected);

		LocalTime nextTrain = dao.getTimeOfNextTrain(unionSta, fourthOfJuly);
		
		assertEquals(expected, nextTrain);
	}

}
