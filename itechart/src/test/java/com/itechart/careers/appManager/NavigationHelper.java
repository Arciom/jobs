package com.itechart.careers.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver driver) {
        super(driver);
    }

    public void careers() {
        click(By.linkText("CAREERS"));
    }

    public void qaEngineer() {
        click(By.xpath("//h3[.='QA ENGINEER Minsk']"));
    }
}
