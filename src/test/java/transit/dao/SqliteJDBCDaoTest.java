package transit.dao;

import static org.junit.Assert.*;

import java.sql.*;
import java.time.*;
import java.util.*;

import org.junit.Before;
import org.junit.Test;

import transit.AppOutput;
import transit.Stop;

public class SqliteJDBCDaoTest {
	
	SqliteJDBCDao dao;
	AppOutput testAppOutput;
	Stop testStop;
	LocalDateTime testDateTime;
	
	@Before
	public void initialize() {
		dao = new SqliteJDBCDao();
		testStop = new Stop();
		testStop.setStopName("CONVENTION CENTER METROLINK STATION");
		testDateTime = LocalDateTime.of(2016,01,01,04,00,00);
	}

	@Test
	public void testEstablishesConnection() throws SQLException {
		Connection connection = SqliteJDBCDao.getConnection();
		assertNotNull(connection);
	}
	
	@Test
	public void testStopListLength() {
		List<Stop> stops = dao.getStopsAllStops();
		assertTrue(stops.size() == 36);
	}
	
	@Test
	public void testFormatCurrentTime() {
		LocalDateTime fiveAM = LocalDateTime.of(2016,01,01,05,00,00,00);
		String format = "05:00:00";
		
		String testFormat = dao.formatTimeToMatchDatabase(fiveAM);
		
		assertEquals(format,testFormat);
	}
	
	@Test
	public void testHolidayScheduleChange() {
		Stop unionSta = new Stop();
		unionSta.setStopName("UNION STA");
		LocalDateTime fourthOfJuly = LocalDateTime.of(2016,7,4,16,00,00);
		LocalTime expected = LocalTime.of(16,06,00);
		
		LocalTime nextTrain = dao.getTimeOfNextTrain(unionSta, fourthOfJuly);
		
		assertEquals(expected, nextTrain);
	}
	
	@Test
	public void testGetTimeOfNextTrain() {
		LocalTime expected = LocalTime.of(04,19,00);
		LocalTime nextTrain = dao.getTimeOfNextTrain(testStop, testDateTime);
		assertEquals(expected, nextTrain);
	}
	
	@Test (expected = RuntimeException.class)
	public void testThrowsException() {
		Stop namelessStop = new Stop();
		dao.getTimeOfNextTrain(namelessStop, testDateTime);
	}

}
