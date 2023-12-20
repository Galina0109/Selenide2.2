import com.codeborne.selenide.Condition;
import com.codeborne.selenide.conditions.webdriver.Url;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Selenide.*;

public class AppCardDeliveryTaskOneTest {

    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test

    public void shouldBeSuccessfullyCompleted() {
        open ("http://localhost:9999");

        $( "[data-test-id='city'] input").setValue("Москва");
        String planningDate = generateDate(  4, "dd.mm.yyyy");
        $( "[data-test-id='data'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $( "[data-test-id='data'] input").setValue(planningDate);
        $( "[data-test-id='name']").setValue("Иванов Семен");
        $( "[data-test-id='phone']").setValue("+79633523355");
        $( "[data-test-id='agreement']").click();
        $( "button.button").click();
        $( ".notification__content")
        .shouldBe(Condition.visible, Duration.ofSeconds(15))
        .shouldHave(Condition.exactText("Встреча успешно забронирована на " + planningDate));
    }
}
