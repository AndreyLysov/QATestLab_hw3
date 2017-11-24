import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = getDriver();
        driver.get("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");
        driver.manage().window().maximize();
        driver.findElement(By.name("email")).sendKeys("webinar.test@gmail.com");
        driver.findElement(By.name("passwd")).sendKeys("Xcg7299bnSmMuRLp9ITw");
        driver.findElement(By.name("submitLogin")).submit();
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.className("employee_avatar_small")));

        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//ul[@class='menu']/li[@id='subtab-AdminCatalog']/a"))).
                build().perform();

        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath
                        ("//ul[@class='menu']/li[@id='subtab-AdminCatalog']/ul/li[@id='subtab-AdminCategories']/a")));

        driver.findElement
                (By.xpath("//ul[@class='menu']/li[@id='subtab-AdminCatalog']/ul/li[@id='subtab-AdminCategories']/a"))
                .click();

        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath
                        ("//a[@title='Добавить категорию']")));
        driver.findElement(By.xpath("//a[@title='Добавить категорию']")).click();

        String categoryName = "Category name";

        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.name("name_1")));
        driver.findElement(By.name("name_1")).sendKeys(categoryName);

        driver.findElement(By.id("category_form_submit_btn")).submit();

        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='alert alert-success']")));

        driver.findElement(By.id("desc-category-back")).click();

        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='categoryFilter_name']")));

        driver.findElement(By.xpath("//input[@name='categoryFilter_name']")).
                sendKeys(categoryName + Keys.ENTER);

        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//table[@id='table-category']/tbody/tr/td[3][contains(text(), 'Category name')]")));


        driver.quit();
    }

    public static WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
        return new ChromeDriver();
    }
}
