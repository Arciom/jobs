package by.issoft.careers.appManager;

import by.issoft.careers.model.CVData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CareersHelper extends HelperBase{

    public CareersHelper(WebDriver driver) {
        super(driver);
    }

    public void fillSendCVForm(CVData cvData) {
        type(By.name("your-name"), cvData.getName());
        type(By.name("your-email"), cvData.getEmail());
        type(By.name("text-334"), cvData.getPhone());
        type(By.name("your-subject"), cvData.getSubject());
        attach(By.name("file-286"), cvData.getCv());
        type(By.name("your-message"), cvData.getMessage());
        click(By.xpath("//input[@value='Отправить']"));
    }

    public boolean isElementOnPage(By by) {
        return isElementPresent(by);
    }
}
