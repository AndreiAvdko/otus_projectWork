package ru.otus.pages;

import components.HeaderBlock;
import components.popups.headerPopups.data.LearningPopupButtons;
import org.assertj.core.api.SoftAssertions;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
public class CatalogCoursesPage extends AbsBasePage {
    private String checkboxFieldOfStudyInputTemplateLocator = "//div[./label[contains(text(), '%s')]]/div/input";
    private String checkboxFieldOfStudyLabelTemplateLocator = "//label[contains(text(), '%s')]";
    private String cardOfCoursesLinksListLocator = "//section[./div/h1/div[text()='Каталог']]//a";
    private String titleCourseLocator = "//section//h1";
    private String descriptionCourseLocator = "//section//h1/../div[not(.//button) and not(./following::h1)]";
    private String durationCourseLocator = "//section//p[contains(text(), 'мес')]";
    private String formatCourseLocator = "//section//p[contains(text(), 'Онлайн') or contains(text(), 'Офлайн')]";

    public CatalogCoursesPage(WebDriver driver) {
        super(driver, "/catalog/courses/");
        this.logger = logger;
    }
    @Override
    public CatalogCoursesPage open() {
        driver.get(BASE_URL + path);
        driver.manage().window().maximize();
        header = new HeaderBlock(driver);
        return this;
    }
    public CatalogCoursesPage sortCoursesWithUsingFieldOfStudyFilterCheckbox(LearningPopupButtons learningPopupButtons) {
        $x(String.format(checkboxFieldOfStudyLabelTemplateLocator, learningPopupButtons.getName())).click();
        return this;
    }
    public CatalogCoursesPage checkSortingParameter(LearningPopupButtons learningPopupButtons) {
        assertThat($x(String.format(checkboxFieldOfStudyInputTemplateLocator, learningPopupButtons.getName()))
                .isSelected())
                .isTrue()
                .as("Проверяем, что при переходе на страницу выбран нужный чекбокс");
        return this;
    }

    public CatalogCoursesPage checkNumberOfCoursesCards(int numberOfCards) {
        List<WebElement> coursesCardsList = $$x(String.format(cardOfCoursesLinksListLocator));
        for (WebElement cards : coursesCardsList) {
            assertThat(cards.isDisplayed())
                    .isTrue()
                    .as("Проверяем видимость карточек на странице");
        }
        assertThat(coursesCardsList.size())
                .isEqualTo(numberOfCards)
                .as("Проверяем количество карточек на странице");
        return this;
    }
    public void checkMainInfoOnCourseCard() {
        List<WebElement> coursesCardsList = $$x(String.format(cardOfCoursesLinksListLocator));
        List<Document> htmlDocFromCardLinkList = new ArrayList<>();
        for(WebElement card : coursesCardsList) {
            try {
                htmlDocFromCardLinkList.add(Jsoup.connect(card.getAttribute("href")).get());
            } catch (IOException e) {
                e.printStackTrace();
                logger.info("Не удалось получить данные по ссылке " + card.getAttribute("href"));
            }
        }
        SoftAssertions softly = new SoftAssertions();
        for (Document document : htmlDocFromCardLinkList) {
            String title = document.selectXpath(titleCourseLocator).text();
            String description = document.selectXpath(descriptionCourseLocator).text();
            String duration = document.selectXpath(durationCourseLocator).text();
            String format = document.selectXpath(formatCourseLocator).text();
            softly.assertThat(title)
                    .isNotEmpty()
                    .as("Проверяем, что название курса присутствует");
            softly.assertThat(description)
                    .isNotEmpty()
                    .as("Проверяем, что описание курса присутствует");
            softly.assertThat(duration)
                    .isNotEmpty()
                    .as("Проверяем, что продолжительность курса присутствует");
            softly.assertThat(format)
                    .isNotEmpty()
                    .as("Проверяем, что формат курса Онлайн или Офлайн");
            logger.info(title);
            logger.info(description);
            logger.info(duration);
            logger.info(format);
        }
    }
}
