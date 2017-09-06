package com.andersenlab;

import com.andersenlab.model.CVData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CareersHelper extends HelperBase{

    public CareersHelper(WebDriver driver) {
        super(driver);
    }

    protected void fillSendCVForm(CVData cvData) {
        type(By.name("name"), cvData.getName());
        type(By.name("email"), cvData.getEmail());
        dropdownList(By.name("city"), cvData.getPlace());
        attach(By.xpath(".//*[@id='job-form-dropzone']/div/div[4]/div/div"), cvData.getCv());
   //     click(By.cssSelector("button.button.job-form__button"));
    }

    protected boolean isElementOnPage(By by) {
      return isElementPresent(by);
    }

    protected void clickFindYourRoll() {
        click(By.cssSelector("button.button.job-form__button"));
    }
}
