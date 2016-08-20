package transit;

import static org.junit.Assert.*;

import java.util.*;
import java.time.*;

import org.junit.Before;
import org.junit.Test;


public class MetrolinkCommandLineAppTest {
	
	private MetrolinkCommandLineApp app;
	private AppOutput testAppOutput;
	private AppInput testAppInput;
	private MetrolinkDao testDao;
	
	@Before
	public void initialize() {
		app = new MetrolinkCommandLineApp(); 
		testAppOutput = new TestAppOutput();
		app.setAppOutput(testAppOutput);
		testAppInput = new TestAppInput();
		app.setAppInput(testAppInput);
		testDao = new TestDao();
		app.setMetrolinkDao(testDao);
	}
	
	@Test
	public void testPrintListOfStops() {
		app.printListOfStops();
		assertEquals("--- TEST ---", ((TestAppOutput) testAppOutput).getOutput());
	}
	
	@Test
	public void testGetUserLocation() {
		Stop testStop = new Stop();
		testStop.setStopName("TEST");
		
		app.printListOfStops();
		Stop stop = app.getUserLocation();
		
		assertEquals(testStop.getStopName(), stop.getStopName());
	}
	
	@Test
	public void testPrintArrivalTimeOfNextTrain() {
		app.printArrivalTimeOfNextTrain();
		
		assertEquals("The next train is arriving in 10 minutes.", 
				((TestAppOutput) testAppOutput).getOutput());
	}
	
	private class TestAppOutput implements AppOutput {
		private String output;
		@Override
		public void print(String format) {
			output = format;
		}
		public String getOutput() {
			return output;
		}
	}
	
	private class TestAppInput implements AppInput {
		@Override
		public String getUserMetrolinkLocation() {
			return "Test";
		}
	}
	
	private class TestDao implements MetrolinkDao {

		@Override
		public List<Stop> getStopsAllStops() {
			Stop testStop1 = new Stop();
			testStop1.setStopName("TEST");
			List<Stop> stops = new ArrayList<Stop>();
			stops.add(testStop1);
			return stops;
		}

		@Override
		public LocalTime getTimeOfNextTrain(Stop stop, LocalDateTime currentDayTime) {
			Duration tenMinutes = Duration.ofMinutes(10);
			LocalTime testTime = LocalTime.now().plus(tenMinutes);
			return testTime;
		}
		
	}
	
	

}
