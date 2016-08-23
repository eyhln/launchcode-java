package transit;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.*;
import java.time.*;

import org.junit.Before;
import org.junit.Test;

public class MetrolinkCommandLineAppTest {
	
	private MetrolinkCommandLineApp app;
	private AppOutput testAppOutput;
	private AppInput mockAppInput;
	private MetrolinkDao mockDao;
	
	@Before
	public void initialize() {
		app = new MetrolinkCommandLineApp(); 
		createDependencies();
		associateDependencies();
	}
	
	private void createDependencies() {
		testAppOutput = new TestAppOutput();		
		mockAppInput = mock(AppInput.class);
		mockDao = mock(MetrolinkDao.class);
	}
	
	private void associateDependencies() {
		app.setAppOutput(testAppOutput);
		app.setAppInput(mockAppInput);
		app.setMetrolinkDao(mockDao);
	}
	
	@Test
	public void testPrintListOfStops() {
		List<Stop> testStopList = buildListOfStops();
		when(mockDao.getStopsAllStops()).thenReturn(testStopList);
		app.printAndReturnListOfStops();
		assertEquals("--- TEST ---", ((TestAppOutput) testAppOutput).getString());
	}
	
	private List<Stop> buildListOfStops() {
		Stop testStop1 = new Stop();
		testStop1.setStopName("TEST");
		List<Stop> stops = new ArrayList<Stop>();
		stops.add(testStop1);
		return stops;
	}
	
	@Test
	public void testGetUserLocation() {
		when(mockAppInput.getUserMetrolinkLocation()).thenReturn("Test");
		List<Stop> testStopList = buildListOfStops();
		
		Stop stop = app.getUserLocation(testStopList);
		
		assertEquals("TEST", stop.getStopName());
	}
	
	@Test 
	public void testGetUserLocationInvalidName() {
		when(mockAppInput.getUserMetrolinkLocation()).thenReturn("Invalid");
		List<Stop> testStopList = buildListOfStops();
		
		app.getUserLocation(testStopList);
		
		assertEquals("Error: not a valid stop name", ((TestAppOutput) testAppOutput).getString());
	}
	
	@Test
	public void testPrintArrivalTimeOfNextTrain() {
		LocalDateTime testDateTime = LocalDateTime.of(2016,01,01,04,00,00);
		LocalTime testTime = testDateTime.toLocalTime();
		Duration tenMinutes = Duration.ofMinutes(10);
		LocalTime testTrainArrivalTime = testTime.plus(tenMinutes);
		when(mockDao.getTimeOfNextTrain(null, testDateTime)).thenReturn(testTrainArrivalTime);
		
		app.printWaitTimeToNextTrain(testDateTime);
		
		assertEquals("The next train is arriving in 10 minutes.", 
				((TestAppOutput) testAppOutput).getString());
	}
	
	@Test
	public void testPrintArrivalTimeOfNextTrainNoTimesFound() {
		when(mockDao.getTimeOfNextTrain(null, null)).thenReturn(null);
		
		app.printWaitTimeToNextTrain(null);
		
		assertEquals("No train times found for today.", ((TestAppOutput) testAppOutput).getString());
	}
	
	private class TestAppOutput implements AppOutput {
		String string;
		@Override
		public String print(String format) {
			string = format;
			return format;
		}
		public String getString() {
			return string;
		}
		
	}

}
