package ru.otus;

import components.popups.headerPopups.data.LearningPopupButtons;
import factory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import ru.otus.pages.EventCalendarPage;
import ru.otus.pages.MainPage;

import static components.popups.headerPopups.data.LearningPopupButtons.EVENT_CALENDAR_BUTTON;


public class EventSectionTest {
    private WebDriver driver;
    LearningPopupButtons testingSection = EVENT_CALENDAR_BUTTON;

    @BeforeEach
    public void initDriver() {
        driver = new WebDriverFactory().newDriver();
    }

    @Test
    public void checkValidationDateUpcomingEvents() {
        new MainPage(driver).open()
                            .header.moveCursorToTheLearningSectionButtonAndClickButton(testingSection);
        new EventCalendarPage(driver).scrollToEndOfPage()
                                     .checkThatEventDataIsCorrectOnCard();
    }

    @Test
    public void checkSortWithOpenVebinarValue() {
        new MainPage(driver).open()
                .header.moveCursorToTheLearningSectionButtonAndClickButton(testingSection);
        new EventCalendarPage(driver).showOnlyOpenVebinarSort()
                                     .scrollToEndOfPage()
                                     .checkOpenVebinarLabelOnAllCards();
    }

    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}