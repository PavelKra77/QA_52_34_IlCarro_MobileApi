package screens;

import dto.UserLombok;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationScreen extends BaseScreen{
    public RegistrationScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id="com.telran.ilcarro:id/editRegName")
    WebElement inputName;
    @AndroidFindBy(id = "com.telran.ilcarro:id/editRegLastName")
    WebElement inputLastName;
    @AndroidFindBy(id = "com.telran.ilcarro:id/editRegEmail")
    WebElement inputEmail;
    @AndroidFindBy(id = "com.telran.ilcarro:id/editRegPassword")
    WebElement inputPassword;
    @AndroidFindBy(id = "com.telran.ilcarro:id/checkBoxAgree")
    WebElement checkboxIAgree;
    @AndroidFindBy(id = "com.telran.ilcarro:id/regBtn")
    WebElement btnYalla;
    @AndroidFindBy(xpath = "//android.widget.Toast[@text='Registration success!']")  //*[@text='Registration success!']
    WebElement toastMessageRegistrationSuccess;

//    public String getToastText() {
//        return toastMessageRegistrationSuccess.getText(); } или:

    public boolean validateToastMessage(String text){
        return isTextInElementPresent(toastMessageRegistrationSuccess, text);}

    public void typeRegistrationForm(UserLombok userLombok){
        System.out.println("CREATED USER OBJECT: " + userLombok);
        inputName.sendKeys(userLombok.getFirstName());
        inputLastName.sendKeys(userLombok.getLastName());
        inputEmail.sendKeys(userLombok.getUsername());
        inputPassword.sendKeys(userLombok.getPassword());
    }

    public void clickIAgree(){
        clickWait(checkboxIAgree);
    }

    public void clickBtnYalla(){
        clickWait(btnYalla);
    }
}
