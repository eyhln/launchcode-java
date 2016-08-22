package transit;

import java.time.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MetrolinkCommandLineApp {
	
  private MetrolinkDao metrolinkDao;
  private AppOutput appOutput;
  private AppInput appInput;
  

  public void setMetrolinkDao(MetrolinkDao metrolinkDao) {
      this.metrolinkDao = metrolinkDao;
  }

  public void setAppOutput(AppOutput appOutput) {
      this.appOutput = appOutput;
  }
  
  public void setAppInput(AppInput appInput) {
  	this.appInput = appInput;
  }

  public static void main(String[] varArgs) {
  	MetrolinkCommandLineApp metrolinkCommandLineApp;
		try (ClassPathXmlApplicationContext context = 
			 new ClassPathXmlApplicationContext("application-context.xml")) {
			metrolinkCommandLineApp =
          (MetrolinkCommandLineApp) context.getBean("metrolinkCommandLineApp");
		}
    metrolinkCommandLineApp.run();
  }

  private List<Stop> stopsAllStops;
  private Stop userLocation;
  
  private void run() {
  	printListOfStops(); 
    userLocation = getUserLocation(); 
    if (userLocation.getStopName() != null)
    	printArrivalTimeOfNextTrain();
  }
  
  void printListOfStops() {
    appOutput.print("Fetching metrolink stations...");
  	stopsAllStops = metrolinkDao.getStopsAllStops();
  	for (Stop stop : stopsAllStops) {
      appOutput.print(String.format("--- %s ---", stop.getStopName()));
  	}
  }
  
  Stop getUserLocation() {
  	appOutput.print("\nEnter the name of your current Metrolink Station location: ");
    Stop currentUserLocationStop = new Stop();
    String location = appInput.getUserMetrolinkLocation();
    Pattern userInput = Pattern.compile(location.trim(), Pattern.CASE_INSENSITIVE);
    for (Stop stop: stopsAllStops) {
    	Matcher matcher = userInput.matcher(stop.getStopName());
    	if (matcher.matches()) {
    		currentUserLocationStop = stop;
    	}
    } 
    if (currentUserLocationStop.getStopName() == null) {
	    appOutput.print("Error: not a valid stop name");
    }
    return currentUserLocationStop;
  }
  
  void printArrivalTimeOfNextTrain() {
  	try {
    appOutput.print("Fetching arrival time...");
  	LocalDateTime currentDateTime = LocalDateTime.now(); 
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
  
}
