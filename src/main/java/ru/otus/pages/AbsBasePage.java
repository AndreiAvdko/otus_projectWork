package ru.otus.pages;

import components.HeaderBlock;
import org.openqa.selenium.WebDriver;
import pageobject.AbsPageObject;

public abstract class AbsBasePage extends AbsPageObject {
    public String BASE_URL = System.getProperty("base.url", "https://otus.ru");
    public String path = "";
    public HeaderBlock header;
    public AbsBasePage(WebDriver driver, String path) {
        super(driver);
        this.path = path;
    }

    public AbsBasePage open() {
        driver.get(BASE_URL + path);
        driver.manage().window().maximize();
        header = new HeaderBlock(driver);
        return this;
    }
    public void close() {
        this.driver.close();
    }
}
