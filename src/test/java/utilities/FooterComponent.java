package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FooterComponent {

    WebDriver driver;
    WebDriverWait wait;

    By offersLinkBy = By.xpath("(//a[normalize-space()='Offers'])[last()]");
    By contactUsLinkBy = By.xpath("//a[normalize-space()='Contact Us']");
    By privacyPolicyLinkBy = By.xpath("//a[normalize-space()='Privacy Policy']");
    By careersLinkBy = By.xpath("//a[normalize-space()='Careers']");
    By newsletterEmailInputBy = By.id("newsletter-email");
    By subscribeButtonBy = By.xpath("//button[normalize-space()='Subscribe to Newsletter']");
    By copyrightTextBy = By.xpath("//*[contains(normalize-space(),'BrowserStack Demo')]");

    public FooterComponent(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isDisplayed() {
        List<By> footerElements = List.of(
                offersLinkBy,
                contactUsLinkBy,
                privacyPolicyLinkBy,
                careersLinkBy,
                newsletterEmailInputBy,
                subscribeButtonBy,
                copyrightTextBy
        );

        for (By element : footerElements) {
            boolean isVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isDisplayed();
            if (!isVisible) {
                return false;
            }
        }return true;
    }

    public void subscribeToNewsletter(String email) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(newsletterEmailInputBy));
        input.clear();
        input.sendKeys(email);
        wait.until(ExpectedConditions.elementToBeClickable(subscribeButtonBy)).click();
    }
}
