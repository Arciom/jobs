package by.issoft.careers.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver driver) {
        super(driver);
    }

    public void vacancies() {
        click(By.linkText("Вакансии"));
    }

    public void qaEngineer() { click(By.xpath("//div[@id='faqs-wrap clearfix']/div[16]/h2"));    }

    public void sendResume() { click(By.xpath("//img[@title='sent']")); }
}
