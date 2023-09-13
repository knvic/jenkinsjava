package guru.qa.tests;

import guru.qa.pages.PracticeFormPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.logevents.SelenideLogger.step;

public class PracticeFormWithPageObjectTests extends RemoteBaseTest {
    PracticeFormPage practiceFormPage = new PracticeFormPage();

    @Feature("Запуск в Jenkins")
    @Story("Заполнение тестовой формы вариант 2")
    @Owner("krivorotovnv")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://github.com")
    @DisplayName("Запуск проекта заполнения формы с использованием Jenkins")
    @Tag("remote")
    @Test
    void fullTestPracticeForm() {
        step("Open", () -> {
            practiceFormPage
                    .openPage()
                    .deleteBannerAndFooter();
        });

        step("Input Data", () -> {
            practiceFormPage
                    .setFirstName("firstName")
                    .setLastName("lastName")
                    .setUserEmail("aaa@bbb.cc")
                    .setUserNumber("1234567890")
                    .setGender("Female")
                    .setBirth("2022", "July", "27")
                    .setSubjects("Chemistry")
                    .setHobbies("Reading")
                    .uploadPicture("img/picture.png")
                    .setCurrentAddress("Russia")
                    .setStateAndCity("Uttar Pradesh", "Agra")
                    .submit()
                    .checkModalDialogeTitle("Thanks for submitting the form");
        });

        step("Check input", () -> {
            practiceFormPage
                    .checkResult(List.of("firstName", "lastName",
                            "aaa@bbb.cc", "1234567890", "Female",
                            "27 July,2022", "Chemistry", "Reading",
                            "picture.png", "Russia",
                            "Uttar Pradesh Agra"));
        });

    }
}
