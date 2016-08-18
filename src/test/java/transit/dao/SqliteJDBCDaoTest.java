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
		testAppOutput = new TestAppOutput();
		dao.setAppOutput(testAppOutput);
		testStop = new Stop();
		testStop.setStopName("CONVENTION CENTER METROLINK STATION");
		testDateTime = LocalDateTime.of(2016,01,01,04,00,00);
	}
	
	@Test
	public void testPrintsMessages() {
		dao.getStopsAllStops();
		assertEquals("Fetching metrolink stations...", ((TestAppOutput) testAppOutput).getString());
		dao.getTimeOfNextTrain(testStop, LocalDateTime.now());
		assertEquals("Fetching next train arrival time...", ((TestAppOutput) testAppOutput).getString());
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
	public void testGetDayOfWeekCode() {
		LocalDateTime monday = LocalDateTime.of(2016, 02, 01, 01, 00);
		LocalDateTime saturday = LocalDateTime.of(2016, 02, 06, 01, 00);
		LocalDateTime sunday = LocalDateTime.of(2016, 02, 07, 01, 00);
		
		int mondayCode = dao.getDayOfWeekCode(monday);
		int saturdayCode = dao.getDayOfWeekCode(saturday);
		int sundayCode = dao.getDayOfWeekCode(sunday);
		
		assertEquals(1,mondayCode);
		assertEquals(2,saturdayCode);
		assertEquals(3,sundayCode);
	}
	
	@Test
	public void testFormatCurrentTime() {
		LocalDateTime fiveAM = LocalDateTime.of(2016,01,01,05,00);
		String format = "05:00:00";
		
		String testFormat = dao.formatCurrentTime(fiveAM);
		
		assertEquals(format,testFormat);
	}
	
	@Test
	public void testGetTimeOfNextTrain() {
		LocalTime expected = LocalTime.of(04,19,00);
		LocalTime nextTrain = dao.getTimeOfNextTrain(testStop, testDateTime);
		assertEquals(expected, nextTrain);
	}
	
	private class TestAppOutput implements AppOutput {
		private String string;
		@Override
		public void print(String string) {
			this.string = string;
		}
		public String getString() {
			return string;
		}
	}

}
