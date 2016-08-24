package transit.dao;

import static org.junit.Assert.*;

import java.time.*;

import org.junit.Before;
import org.junit.Test;

public class SqliteJDBCDaoDateTimeFormatterTest {
	
	SqliteJDBCDaoDateTimeFormatter formatter;
	LocalDateTime test4pm2016July1;
	LocalDateTime test1am2016July2;
	
	@Before
	public void initialize() {
		formatter = new SqliteJDBCDaoDateTimeFormatter();
		test4pm2016July1 = LocalDateTime.of(2016,07,01,16,00,00,00);
		test1am2016July2 = LocalDateTime.of(2016,07,02,01,00,00,00);
	}
	
	@Test
	public void testFormatTime() {
		assertEquals("16:00:00", formatter.formatTimeToMatchDatabase(test4pm2016July1));
	}

	@Test
	public void testFormatTimeLateNight() {
		assertEquals("25:00:00", formatter.formatTimeToMatchDatabase(test1am2016July2));
	}
	
	@Test
	public void testFormatDate() {
		assertEquals("20160701", formatter.formatDateToMatchDatabase(test4pm2016July1));
	}
	
	@Test
	public void testFormatReturnString() {
		assertEquals(test4pm2016July1.toLocalTime(), 
				formatter.formatReturnStringToLocalTime("16:00:00"));
	}
	
	@Test
	public void testFormatReturnStringLateNight() {
		assertEquals(test1am2016July2.toLocalTime(), 
				formatter.formatReturnStringToLocalTime("25:00:00"));
	}
}
