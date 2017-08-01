import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.util.concurrent.TimeUnit;


public class Config {

    protected static WebDriver driver;
    protected static WebDriverWait wait;

    public Config (){
    }

    @BeforeMethod
    protected void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Slavik\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--incognito");
        this.driver = new ChromeDriver(options);
        this.wait = new WebDriverWait(this.driver, 15, 500);
        this.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        this.driver.get("http://odpublic.net");
    }

    @AfterMethod
    protected void finish() {
        this.driver.close();
    }
}

