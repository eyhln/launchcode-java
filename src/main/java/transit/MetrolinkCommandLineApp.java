package transit;

import java.time.*;
import java.util.*;
import java.util.regex.*;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MetrolinkCommandLineApp {
	
  public static void main(String[] varArgs) {
  	MetrolinkCommandLineApp metrolinkCommandLineApp;
		try (ClassPathXmlApplicationContext context = 
			 new ClassPathXmlApplicationContext("application-context.xml")) {
			metrolinkCommandLineApp =
          (MetrolinkCommandLineApp) context.getBean("metrolinkCommandLineApp");
		}
    metrolinkCommandLineApp.run();
  }

  private MetrolinkDao metrolinkDao;
  private AppOutput appOutput;
  private AppInput appInput;
  private Stop userLocation;
  
  private void run() {
  	List<Stop> stopsAllStops = printAndReturnListOfStops(); 
    userLocation = getUserLocation(stopsAllStops); 
    if (userLocation.getStopName() != null) {
    	printWaitTimeForNextTrainFromNow();
    }
  }
  
		  private void printWaitTimeForNextTrainFromNow() {
		  	LocalDateTime currentDateTime = LocalDateTime.now(); 
		  	printWaitTimeForNextTrain(currentDateTime);
		  }
  
  public List<Stop> printAndReturnListOfStops() {
    appOutput.print("Fetching metrolink stations...");
  	List<Stop> stopsAllStops = metrolinkDao.getStopsAllStops();
  	Collections.reverse(stopsAllStops);
  	for (Stop stop : stopsAllStops) {
      appOutput.print(String.format("--- %s ---", stop.getStopName()));
  	}
  	return stopsAllStops;
  }
  
  public Stop getUserLocation(List<Stop> stopsAllStops) {
  	String location = promptUserForCurrentLocation();
    Stop currentUserLocation = testInputNameAgainstAllStops(location, stopsAllStops);
    printErrorMessageIfLocationNameIsNull(currentUserLocation);
    return currentUserLocation;
  }
  
		  private String promptUserForCurrentLocation() {
		  	appOutput.print("\nEnter the name of your current Metrolink Station location: ");
		  	return appInput.getUserMetrolinkLocation();
		  }
		  
		  private Stop testInputNameAgainstAllStops(String location, List<Stop> stopsAllStops) {
		  	Pattern userInput = Pattern.compile(location.trim(), Pattern.CASE_INSENSITIVE);
		    Stop currentUserLocation = new Stop();
		    for (Stop stop: stopsAllStops) {
		    	Matcher matcher = userInput.matcher(stop.getStopName());
		    	if (matcher.matches()) 
		    		currentUserLocation = stop;
		    } 
		    return currentUserLocation;
		  }
		  
		  private void printErrorMessageIfLocationNameIsNull(Stop currentUserLocation) {
		  	 if (currentUserLocation.getStopName() == null) {
		 	    appOutput.print("Error: not a valid stop name");
		     }
		  }
	  
  public void printWaitTimeForNextTrain(LocalDateTime currentDateTime) {
  	try {
    appOutput.print("Fetching arrival time...");
    LocalTime arrivalTimeOfNextTrain = metrolinkDao.getTimeOfNextTrain(userLocation, currentDateTime);
    LocalTime currentTime = currentDateTime.toLocalTime();
    Long waitingTimeInMinutes = Duration.between(currentTime, arrivalTimeOfNextTrain).toMinutes();
    String messageSuffix = formatMessageSuffix(waitingTimeInMinutes);
    appOutput.print("The next train is arriving in " + messageSuffix + ".");
  	} catch (NullPointerException e) {
  		appOutput.print("No train times found for today.");
  	}
  }
  
  private String formatMessageSuffix(Long waitingTimeInMinutes) {
  	String messageSuffix;
    if (waitingTimeInMinutes < 1) 
    	messageSuffix = "less than 1 minute";
    else if (waitingTimeInMinutes < 2)
    	messageSuffix = "1 minute";
    else
    	messageSuffix = waitingTimeInMinutes + " minutes";
    return messageSuffix;
  }
  
  
  public void setMetrolinkDao(MetrolinkDao metrolinkDao) {
    this.metrolinkDao = metrolinkDao;
	}
	
	public void setAppOutput(AppOutput appOutput) {
	    this.appOutput = appOutput;
	}
	
	public void setAppInput(AppInput appInput) {
		this.appInput = appInput;
	}
  
}
