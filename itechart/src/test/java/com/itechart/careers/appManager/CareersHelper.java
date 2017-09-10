package com.itechart.careers.appManager;

import com.itechart.careers.model.CVData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CareersHelper extends HelperBase{

    public CareersHelper(WebDriver driver) {
        super(driver);
    }

    public void fillSendCVForm(CVData cvData) {
        type(By.xpath("//div[@class='position open']//input[@name='name']"), cvData.getName());
        type(By.xpath("//div[@class='position open']//input[@name='email']"), cvData.getEmail());
        type(By.xpath("//div[@class='position open']//input[@name='phone']"), cvData.getPhone());
        attach(By.xpath("//label[@for='file-6']"), cvData.getCv());
        type(By.xpath("//div[@class='position open']//textarea"), cvData.getMessage());
        click(By.className("buttons"));
    }

    public boolean isElementOnPage(By by) {
        return isElementPresent(by);
    }
}
