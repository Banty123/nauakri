package regularrun;


import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class EditSkills{
	public EditSkills(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBys( @FindBy(className="sugTouple"))
	private List<WebElement> li;
	public List<WebElement> getList()
	{
		return li;
	}
}

public class NaukariUpdate {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe" );
		WebDriver driver=new ChromeDriver();
		driver.navigate().to("https://www.naukri.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//ul[@class='midSec menu']//div[text()='Login']")).click();
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("banty.kumar862@gmail.com");
		driver.findElement(By.xpath("//input[@name='PASSWORD']")).sendKeys("9835406398");
		driver.findElement(By.xpath("//div[@class='row']//button[.='Login']")).click();
		String mainWindow = driver.getWindowHandle();
		Set<String> s = driver.getWindowHandles();
		for(String handle : s)
		{
			driver.switchTo().window(handle);
			if(!handle.equals(mainWindow))
			{
				driver.close();
			}
		}
		driver.switchTo().window(mainWindow);
		WebDriverWait wait = new WebDriverWait(driver, 3000);
		WebElement element = driver.findElement(By.xpath("//div[@class='popout profile-section card']//img"));
		wait.until(ExpectedConditions.visibilityOf(element));
		driver.findElement(By.xpath("//div[@class='popout profile-section card']//img")).click();
		driver.findElement(By.xpath("(//span[@class='edit icon'])[2]")).click();
		if(new Date().getDate() % 2 == 0)
		{
			driver.findElement(By.xpath("//input[@type='text']")).sendKeys("git");
			
			EditSkills es = new EditSkills(driver);
			List<WebElement> li = es.getList();
			for (Iterator<WebElement> iterator = li.iterator(); iterator.hasNext();)
			{
				WebElement webElement = (WebElement) iterator.next();
				System.out.println(webElement.getText());
				/*if(webElement.getText().equalsIgnoreCase("Github"));
				{
					Actions a = new Actions(driver);
					a.moveToElement(webElement).click().build();
				}*/
			}
			//driver.findElement(By.xpath("//button[@id='saveKeySkills']")).click();
		}

	}
}
