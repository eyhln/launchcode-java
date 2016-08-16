package transit;

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
        List<Stop> stopsAllStops = metrolinkDao.getStopsAllStops();
        for (Stop stop : stopsAllStops) {
            appOutput.print(String.format("--- %s ---", stop.getStopName()));
        }
    }

    private MetrolinkDao metrolinkDao;
    private AppOutput appOutput;

    public void setMetrolinkDao(MetrolinkDao metrolinkDao) {
        this.metrolinkDao = metrolinkDao;
    }

    public void setAppOutput(AppOutput appOutput) {
        this.appOutput = appOutput;
    }
}
