package mobile_tests;

import dto.UserLombok;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.RegistrationScreen;
import screens.SearchScreen;
import screens.SplashScreen;
import utils.UserFactory;

public class RegistrationTests extends TestBase{

    @Test
    public void registrationPositiveTest(){
        UserLombok userLombok = UserFactory.positiveUser();
        new SplashScreen(driver).goToSearchScreen();
        SearchScreen searchScreen = new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnRegistration();
        RegistrationScreen registrationScreen = new RegistrationScreen(driver);
        registrationScreen.typeRegistrationForm(userLombok);
        registrationScreen.clickIAgree();
        registrationScreen.clickBtnYalla();
//       Assert.assertEquals(registrationScreen.getToastText(),"Registration success!",
//                "Сообщение об успешной регистрации не совпало!"); или:

        Assert.assertTrue(registrationScreen.validateToastMessage("Registration success!"));

    }
}
