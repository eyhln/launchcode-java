package transit;

import java.time.*;
import java.util.List;

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

    private void run() {
    	List<Stop> stopsAllStops = getListOfAllStops();
      print(stopsAllStops);  
        appOutput.print("\nEnter the name of your current Metrolink Station location: ");
        String location = appInput.getUserMetrolinkLocation();
        Stop currentUserLocationStop;
        for (Stop stop: stopsAllStops) {
        	if (location.equals(stop.getStopName()))
        		currentUserLocationStop = stop;
        } 
      LocalDateTime currentDateTime = LocalDateTime.now(); 
      LocalTime currentTime = currentDateTime.toLocalTime();
      //LocalTime timeOfNextTrain = metrolinkDao.getTimeOfNextTrain(stop, currentDayTime);
        
    }
    
    List<Stop> getListOfAllStops() {
    	List<Stop> stopsAllStops = metrolinkDao.getStopsAllStops();
    	return stopsAllStops;
    }
    
    void print(List<Stop> stopsAllStops) {
    	for (Stop stop : stopsAllStops) {
        appOutput.print(String.format("--- %s ---", stop.getStopName()));
    	}
    }

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
}
