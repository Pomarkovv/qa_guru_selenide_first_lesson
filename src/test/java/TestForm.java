import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class TestForm {

    private static final String
            FIRST_NAME = "Ivan",
            SECOND_NAME = "Pomarkov",
            EMAIL = "ivanpomarkovse@gmail.com",
            PHONE = "9293088508",
            MONTH = "March",
            YEAR = "2002",
            ADDRESS = "Kansk, Krasnoyarsiy krai, Karla Marksa, 39",
            SEX = "Male",
            DATE_OF_BEARTH = "14 March,2002",
            SUBJECT = "Maths",
            HOBBIE = "Music",
            PICTURE = "img.png",
            STATE = "Haryana",
            CITY = "Panipat";

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 5000;
    }

    @Test
    void testForm() {
        open("/automation-practice-form");

        fillForm();

        submitForm();

        $("div.modal-content").shouldBe(visible);
        $$("div.modal-content tbody tr").get(0).$$("td").get(1).shouldHave(text(FIRST_NAME + " " + SECOND_NAME));
        $$("div.modal-content tbody tr").get(1).$$("td").get(1).shouldHave(text(EMAIL));
        $$("div.modal-content tbody tr").get(2).$$("td").get(1).shouldHave(text(SEX));
        $$("div.modal-content tbody tr").get(3).$$("td").get(1).shouldHave(text(PHONE));
        $$("div.modal-content tbody tr").get(4).$$("td").get(1).shouldHave(text(DATE_OF_BEARTH));
        $$("div.modal-content tbody tr").get(5).$$("td").get(1).shouldHave(text(SUBJECT));
        $$("div.modal-content tbody tr").get(6).$$("td").get(1).shouldHave(text(HOBBIE));
        $$("div.modal-content tbody tr").get(7).$$("td").get(1).shouldHave(text(PICTURE));
        $$("div.modal-content tbody tr").get(8).$$("td").get(1).shouldHave(text(ADDRESS));
        $$("div.modal-content tbody tr").get(9).$$("td").get(1).shouldHave(text(STATE + " " + CITY));
    }

    private void fillForm() {
        $("#firstName").setValue(FIRST_NAME);
        $("#lastName").setValue(SECOND_NAME);
        $("#userEmail").setValue(EMAIL);
        $("label[for='gender-radio-1']").click();
        $("#userNumber").setValue(PHONE);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(MONTH);
        $(".react-datepicker__year-select").selectOption(YEAR);
        $(".react-datepicker__day--014").click();

        $("#subjectsInput").setValue("M").pressEnter();

        $("label[for='hobbies-checkbox-3']").click();

        $("#uploadPicture").uploadFromClasspath("files/img.png");

        $("#currentAddress").setValue(ADDRESS);

        $("#react-select-3-input").setValue("N").sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        $("#react-select-4-input").setValue("N").sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
    }

    private void submitForm() {
        $("#submit").click();
    }
}