package ru.otus;

import components.popups.headerPopups.data.LearningPopupButtons;
import factory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import ru.otus.pages.CatalogCoursesPage;
import ru.otus.pages.MainPage;

import static components.popups.headerPopups.data.LearningPopupButtons.TESTING_COURSES_BUTTON;

public class TestCoursesSectionTest {
    private WebDriver driver;
    LearningPopupButtons testingSection = TESTING_COURSES_BUTTON;

    @BeforeEach
    public void initDriver() {
        driver = new WebDriverFactory().newDriver();
    }

    @Test
    public void checkingNumberOfCoursesInTheTestingSection() {
        new MainPage(driver).open()
                            .header.moveCursorToTheLearningSectionButtonAndClickButton(testingSection);
        new CatalogCoursesPage(driver).checkSortingParameter(testingSection)
                                      .checkNumberOfCoursesCards(10);
    }

    @Test
    public void checkingCoursesDataInTestingCourses() {
        new MainPage(driver).open()
                .header.moveCursorToTheLearningSectionButtonAndClickButton(testingSection);
        new CatalogCoursesPage(driver).checkSortingParameter(testingSection)
                .checkNumberOfCoursesCards(10)
                .checkMainInfoOnCourseCard();
    }

    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
