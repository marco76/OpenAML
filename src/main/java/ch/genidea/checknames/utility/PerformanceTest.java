package ch.genidea.checknames.utility;

public class PerformanceTest {
	   private long startTime = 0;
	      // run your code here
	      long stopTime = 0;
	      long runTime = 0;


	     public void startTime(){
	      startTime = System.currentTimeMillis();
	     }

	     public long stopTime (){
	         stopTime = System.currentTimeMillis();
	         runTime = stopTime - startTime;
	         return runTime;
	     }
	}
