package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PromoBannerComponent {
    WebDriverWait wait;

    By promoImageBy = By.cssSelector("img[alt='flowchart main']");
    By promoTextBy = By.xpath("//*[contains(normalize-space(),'Exclusive offers coming soon')]");

    public PromoBannerComponent(WebDriver driver) {

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isDisplayed() {
        List<By> promoBannerElements = List.of(
                promoImageBy,
                promoTextBy
        );

        for (By element : promoBannerElements) {
            boolean isVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(element)
            ).isDisplayed();

            if (!isVisible) {
                return false;
            }
        }return true;
    }
}
