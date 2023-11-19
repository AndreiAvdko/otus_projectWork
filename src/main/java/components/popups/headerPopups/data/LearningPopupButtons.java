package components.popups.headerPopups.data;

import ru.otus.pages.CatalogCoursesPage;
import ru.otus.pages.EventCalendarPage;

public enum LearningPopupButtons {
    TESTING_COURSES_BUTTON("Тестирование", CatalogCoursesPage.class),
    INTENSIVE_BUTTON("Интенсивы", CatalogCoursesPage.class),
    STARTS_COURSES_CALENDAR_BUTTON("Календарь запуска курсов", EventCalendarPage.class),
    EVENT_CALENDAR_BUTTON("Календарь мероприятий", EventCalendarPage.class);

    private final String name;
    private final Class pageClass;
    LearningPopupButtons(String name, Class pageClass) {
        this.name = name;
        this.pageClass = pageClass;
    }
    public String getName() {
        return name;
    }

    public Class getPageClass() {
        return pageClass;
    }

}
