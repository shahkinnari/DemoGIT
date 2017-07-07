package configuration;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

/**
 * <p>
 * Example of a WebDriver implementation that has delegates all methods to a static instance (REAL_DRIVER) that is only
 * created once for the duration of the JVM. The REAL_DRIVER is automatically closed when the JVM exits. This makes
 * scenarios a lot faster since opening and closing a browser for each scenario is pretty slow.
 * To prevent browser state from leaking between scenarios, cookies are automatically deleted before every scenario.
 * </p>
 * <p>
 * A new instance of SharedDriver is created for each Scenario and passed to yor Stepdef classes via Dependency Injection
 * </p>
 * <p>
 * As a bonus, screenshots are embedded into the report for each scenario. (This only works
 * if you're also using the HTML formatter).
 * </p>
 * <p>
 * A new instance of the SharedDriver is created for each Scenario and then passed to the Step Definition classes'
 * constructor. They all receive a reference to the same instance. However, the REAL_DRIVER is the same instance throughout
 * the life of the JVM.
 * </p>
 */
public class SharedDriver extends EventFiringWebDriver{
	Process p;

	private static final WebDriver REAL_DRIVER = new FirefoxDriver();
	//private static Scenario sc; 
	
	
    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            REAL_DRIVER.close();
        }
    };
    static {
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }


    public SharedDriver() throws MalformedURLException {
    	
		super(REAL_DRIVER);
    }

    @Override
    public void close() {
        if (Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException("You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
        }
        super.close();
        
        
        try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        p.destroy();
    }

   @Before
    public void deleteAllCookies() {
	   System.out.println("in Before==== automation");
        manage().deleteAllCookies();
    }

   @After
    public void embedScreenshot(Scenario scenario) throws IOException {
	   
        try {
        	
        	  File jsonfile = new File("target");
      		File htmlreport = new File("");
      		File jarlib = new File("lib/cucumber-sandwich.jar");

      		
          System.out.println("===== generating Report ====");
      		String cmd = "java -jar " + jarlib.getAbsolutePath() + " -f "
      				+ jsonfile.getAbsolutePath() + " -o "
      				+ htmlreport.getAbsolutePath();
      		
      		System.out.println("cmd :" + cmd);
      		Runtime runtime = Runtime.getRuntime();
      		 p = runtime.exec(cmd);
      	 
      		 //System.out.println("java -jar "+json_report_file_jar_path+" -f "+json_report_file_path+" -o "+html_report_file_path+"");
      		
            byte[] screenshot = getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
            
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
        }
        try {
		} catch (Exception e) {
			// TODO: handle exception
		}
        
    }
   
}