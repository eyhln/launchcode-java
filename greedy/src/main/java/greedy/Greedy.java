package greedy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: mpmenne
 * Date: 6/18/14
 * Time: 3:05 AM
 */
public class Greedy {
	
	 public static void main(String[] varArgs) {
		 ApplicationContext context = 
				 new ClassPathXmlApplicationContext("application-context.xml");

		 Greedy greedy = (Greedy)context.getBean("greedy");
		 greedy.start();
	    }
	
	CoinCalculator coinCalculator;

	public Greedy(CoinCalculator coinCalculator) {
		this.coinCalculator = coinCalculator;
	}
	
	public void start() {
		
	}
   

}
