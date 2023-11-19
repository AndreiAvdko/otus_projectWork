package components.popups.headerPopups;

import components.popups.IPopup;
import components.popups.headerPopups.data.LearningPopupButtons;
import org.openqa.selenium.WebDriver;
import pageobject.AbsPageObject;

import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class LearningSectionPopup extends AbsPageObject implements IPopup {
    private String learningBlockLocator = "//nav/div[./div//p[text()='Все курсы']]";
    private String popupButtonTemplateLocator = learningBlockLocator.concat("//a[contains(text(), '%s')]");
    public LearningSectionPopup(WebDriver driver) {
        super(driver);
    }
    @Override
    public void popupShouldBeNotVisible() {
        assertThat($x(learningBlockLocator).isDisplayed()).isFalse();
    }

    @Override
    public void popupShouldBeVisible() {
        assertThat(waiters.waitElementVisible($x(learningBlockLocator))).isTrue();
    }

    public void goToPagesWithClickOnButtonWithNameInPopup(LearningPopupButtons button) {
        $x(String.format(popupButtonTemplateLocator, button.getName())).click();
    }
}
