import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginAndLogoutTest {
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://velmet.ua/");
        //driver.manage().window().maximize();
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    public void testLogin(){
        //define elements
        WebElement authorizButton = driver.findElement(By.xpath("//*[@id='new_header']/div[1]/div/div[3]/div[3]/button/i"));
        WebElement loginElement = driver.findElement(By.id("input-emails"));
        WebElement passwElement = driver.findElement(By.id("input-passwords"));
        WebElement submitButton = driver.findElement(By.xpath("//*[@id='quick-login']/button"));

        //letter of approval
        WebElement approvalButton = driver.findElement(By.xpath("//*[@id='modal_container_warning']/div/div/div[2]/div/button"));
        approvalButton.click();

        //open authorization window
        authorizButton.click();

        //check and login
        WebElement authTitle = driver.findElement(By.xpath("//*[@id='quick-login-register']/div[1]/h3"));
        Assert.assertEquals(authTitle.getAttribute("class"), "modal-title");
        loginElement.sendKeys("uaryaz@gmail.com");
        passwElement.sendKeys("gfhjkmVelmet");
        submitButton.click();

        //check result
        WebElement officeButton = driver.findElement(By.xpath("//*[@id='new_header']/div[1]/div/div[3]/div[3]/button"));
        Assert.assertEquals(officeButton.getAttribute("class"), "reset_button quick_login");

        //refresh page
        driver.navigate().refresh();
        Assert.assertEquals(officeButton.getAttribute("class"), "reset_button quick_login");
    }
}
