import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Browser {

    public static void main(String[] args) {
        /**
         * 1. Download chrome driver file
         * 2. Help application to know where is the driver file
         * 3. Create chromeDriver Object
         * 4. Open a website in chrome browser
         * */

        // If OS is mac use chromedriver, if OS is Windows chromedriver.exe

        String os = "mac";
        WebDriver chromeDriver = null; // equalsIgnoreCase() mac == Mac
        // Call getChromeDriver() to get ChromeDriver
        // reassign the WebDriver value
        chromeDriver = getChromeDriver(os, chromeDriver);
        String url = "https://the-internet.herokuapp.com/";
        chromeDriver.get(url);

        // Let's do something here
        // uploadFile(chromeDriver);
        // selectDropdownOptionByValue(chromeDriver, "2");
        checkACheckBox(chromeDriver);

        cleanUp(chromeDriver);

    }

    public static WebDriver getChromeDriver(String os, WebDriver chromeDriver ){
        String chromeDriverPath;
        if (os.equalsIgnoreCase("mac")){
            chromeDriverPath = System.getProperty("user.dir") + "/drivers/mac/chromedriver";
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            chromeDriver = new ChromeDriver();
        }

        else if (os.equalsIgnoreCase("windows")){
            chromeDriverPath = System.getProperty("user.dir") + "/drivers/windows/chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            chromeDriver = new ChromeDriver();
        }
        return chromeDriver;
    }

    public static void  cleanUp(WebDriver chromeDriver){
        // This method is responsible for cleanup after each test
        chromeDriver.quit();
    }

    public static String uploadFile(WebDriver webDriver){
        /** Go to Upload Page
         * 1. Choose File
         * 2. Click upload
         * 3. Verify upload succeeded
         * */
        // Go to Upload Page
    webDriver.findElement(By.linkText("File Upload")).click();
    System.out.println("You are in Upload Page");
    // Choose File
    webDriver.findElement(By.id("file-upload")).sendKeys("/Users/jahidul/IdeaProjects/WebBrowserOperation/data/lambda.png");
    // Click upload
    webDriver.findElement(By.id("file-submit")).click();
    // Go to the tag named h3 and get the tag value. After upload tag value shall be "File Uploaded!"
    String actualMessage =  webDriver.findElement(By.tagName("h3")).getText(); // getText() method provides you tag Value of the element or address
    System.out.println(actualMessage);
    //  Verify the message using program
    return actualMessage;

    }

    public static void selectDropdownOptionByValue(WebDriver webDriver, String value){
        webDriver.findElement(By.linkText("Dropdown")).click();
        System.out.println("In Dropdown Page");
        WebElement element = webDriver.findElement(By.id("dropdown"));
        Select select = new Select(element);
        select.selectByValue(value);

    }

    /**
     * Play with other Select method
     * */
    public static void checkACheckBox(WebDriver webDriver) {
        webDriver.findElement(By.linkText("Checkboxes")).click();
        webDriver.findElement(By.xpath("//form[@id=\"checkboxes\"]/input[1]")).click();
    }
}
