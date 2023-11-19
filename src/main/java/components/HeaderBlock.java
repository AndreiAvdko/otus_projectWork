package components;

import components.popups.headerPopups.LearningSectionPopup;
import components.popups.headerPopups.data.LearningPopupButtons;
import org.openqa.selenium.WebDriver;
import pageobject.AbsPageObject;

public class HeaderBlock extends AbsPageObject {
    private String headerSectionButtonTemplateSelector = "//nav/div/span[text()='%s']";

    public HeaderBlock(WebDriver driver) {
        super(driver);
    }

    public void moveCursorToTheLearningSectionButtonAndClickButton(LearningPopupButtons button) {
        LearningSectionPopup learningSectionPopup = new LearningSectionPopup(this.driver);
        learningSectionPopup.popupShouldBeNotVisible();
        action.moveToElement($x(String.format(headerSectionButtonTemplateSelector, "Обучение")))
                .perform();
        learningSectionPopup.popupShouldBeVisible();
        learningSectionPopup.goToPagesWithClickOnButtonWithNameInPopup(button);
    }



}
