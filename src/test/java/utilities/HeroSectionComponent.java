package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HeroSectionComponent {
    WebDriverWait wait;
    By bannerImageBy = By.cssSelector("main img[alt='banner main']");

    public HeroSectionComponent(WebDriver driver) {this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isDisplayed() {
        boolean isVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(bannerImageBy)).isDisplayed();
        if (!isVisible) {
            return false;
        }
        return true;
    }
}
