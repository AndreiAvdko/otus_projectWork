package ru.otus.pages;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class EventCalendarPage extends AbsBasePage {
    private String scroollToElementLocator = "//a[contains(text(), 'Корпоративное обучение')]";
    private String firstTwentyCardsWithEventDataLocator = "//a[.//button[contains(text(), 'Посетить')]]//span[contains(@class, 'date-text') and not(contains(text(), ':00'))]";
    private String lastCardsWithEventsDataListLocator = "//a[.//button[contains(text(), 'Посетить')]][20]/following::a//span[contains(@class, 'date-text') and not(contains(text(), ':00'))]";
    private String showAllSortEventsLocator = "//div[./span[contains(text(), 'Ближайшие мероприятия')]]/div/div/span[text()='Все мероприятия']";
    private String showOpenVebinarsButtonsLocator = "//div[./span[contains(text(), 'Ближайшие мероприятия')]]/div/div/a[text()='Открытый вебинар']";
    private String openVebinarLabelFirstTwentyCardsLocator = "//a[.//button[contains(text(), 'Посетить')]]//div[contains(@class, 'type__text')]";
    private String openVebinarLabelLastCardsLocator = "//a[.//button[contains(text(), 'Посетить')]][20]/following::a//div[contains(@class, 'type__text')]";
    public EventCalendarPage(WebDriver driver) {
        super(driver, "https://otus.ru/events/");
    }
    public EventCalendarPage scrollToEndOfPage() {
        WebElement scrollElement = $x(scroollToElementLocator);
            int counter = 10;
            while (counter > 0) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scrollElement);
                try {
                    Thread.sleep(2500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                counter--;
            }
        return this;
    }
    public void checkThatEventDataIsCorrectOnCard() {
        SoftAssertions softly = new SoftAssertions();
        List<WebElement> dateCollectionsEventsWebElmntList = $$x(firstTwentyCardsWithEventDataLocator);
        dateCollectionsEventsWebElmntList.addAll($$x(lastCardsWithEventsDataListLocator));
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy", new Locale("ru"));
        for(WebElement eventWebElmnt : dateCollectionsEventsWebElmntList) {
            try {
                Date date = formatter.parse(eventWebElmnt.getText() + " 2023");
                softly.assertThat(date.after(new Date()))
                        .isTrue()
                        .as("Сравниваем дату мероприятия и текущую дату");
            }
            catch (ParseException e) {
                e.printStackTrace();
                logger.info("Не удалось распарсить дату как значение " + eventWebElmnt.getText());
            }
        }
    }
    public EventCalendarPage showOnlyOpenVebinarSort() {
        $x(showAllSortEventsLocator).click();
        WebElement showOpenVebinars = $x(showOpenVebinarsButtonsLocator);
        waiters.waitElementVisible(showOpenVebinars);
        showOpenVebinars.click();
        return this;
    }
    public void checkOpenVebinarLabelOnAllCards() {
        SoftAssertions softly = new SoftAssertions();
        List<WebElement> labelEventCardsCollectionWebElmntList = $$x(openVebinarLabelFirstTwentyCardsLocator);
        labelEventCardsCollectionWebElmntList.addAll($$x(openVebinarLabelLastCardsLocator));
        for(WebElement label : labelEventCardsCollectionWebElmntList) {
            assertThat(label.getText()).isEqualTo("Открытый вебинар")
                                       .as("Проверяем текст лейбла на карточке");
        }
    }
}
