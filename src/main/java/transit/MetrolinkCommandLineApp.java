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
  	try {
  		printWaitingTimeForNextTrainAtUserLocationStop();
  	} catch (IllegalArgumentException e) {
 	    	appOutput.print("Error: not a valid stop name");
  	} catch (NoTrainTimesException e) {
  		appOutput.print("No train times found for today.");
  	}
  }	
  	
  private void printWaitingTimeForNextTrainAtUserLocationStop() throws NoTrainTimesException {	
  	List <Stop> stopsAllStops = printAndReturnListOfStops(); 
    userLocation = getUserLocation(stopsAllStops); 
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
    Stop currentUserLocation = selectStopBasedOnNameMatch(location, stopsAllStops);
    return currentUserLocation;
  }
  
		  private String promptUserForCurrentLocation() {
		  	appOutput.print("\nEnter the name of your current Metrolink Station location: ");
		  	return appInput.getUserMetrolinkLocation();
		  }
		  
		  private Stop selectStopBasedOnNameMatch(String location, List<Stop> stopsAllStops) {
		  	Pattern userInput = createUserInputPattern(location);
		    Stop currentUserLocation = 
		    		selectStopByTestingInputAgainstAllStopNames(userInput, stopsAllStops);
		    throwExceptionIfInputDoesNotMatchAnyStop(currentUserLocation);
		    return currentUserLocation;
		  } 
		  
		  private Pattern createUserInputPattern(String location) {
		  	Pattern userInput = Pattern.compile(location.trim(), Pattern.CASE_INSENSITIVE);
		  	return userInput;
		  }
		  
		  private Stop selectStopByTestingInputAgainstAllStopNames
		  																			(Pattern userInput, List<Stop> stopsAllStops) {
		  	Stop currentUserLocation = new Stop();
		    for (Stop stop: stopsAllStops) {
		    	Matcher matcher = userInput.matcher(stop.getStopName());
		    	if (matcher.matches()) 
		    		currentUserLocation = stop;
		    } 
		    return currentUserLocation;
		  }
		  
		  private void throwExceptionIfInputDoesNotMatchAnyStop(Stop currentUserLocation) {
		  	 if (currentUserLocation.getStopName() == null) {
		  		 throw new IllegalArgumentException();
		     }
		  }
	  
  public void printWaitTimeForNextTrain(LocalDateTime currentDateTime) throws NoTrainTimesException {
    printProcessHasBegunMessage();
    LocalTime arrivalTimeOfNextTrain = getArrivalTimeOfNextTrain(currentDateTime);
    Long waitingTimeInMinutes = calculateWaitingTime(currentDateTime, arrivalTimeOfNextTrain);
    printWaitingTimeMessage(waitingTimeInMinutes);
  }
  
  		private void printProcessHasBegunMessage() {
  	    appOutput.print("Fetching arrival time...");
  		}
  		
  		private LocalTime getArrivalTimeOfNextTrain(LocalDateTime currentDateTime) throws NoTrainTimesException {
  			LocalTime arrivalTimeOfNextTrain = metrolinkDao.getTimeOfNextTrain(userLocation, currentDateTime);
 			 	if(arrivalTimeOfNextTrain == null) {
		    	throw new NoTrainTimesException();
		    }
 			 	return arrivalTimeOfNextTrain;
  		}
  		
  		private Long calculateWaitingTime(LocalDateTime currentDateTime, LocalTime arrivalTimeOfNextTrain) {
  			LocalTime currentTime = currentDateTime.toLocalTime();
  	    return Duration.between(currentTime, arrivalTimeOfNextTrain).toMinutes();
  		}
  		
  		private void printWaitingTimeMessage(Long waitingTimeInMinutes) {
  			 String messageSuffix = formatMessageSuffix(waitingTimeInMinutes);
  		    appOutput.print("The next train is arriving in " + messageSuffix + ".");
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
