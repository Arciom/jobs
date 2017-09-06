package com.andersenlab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver driver) {
        super(driver);
    }

    protected void careers() {
        click(By.linkText("Careers"));
    }


}
