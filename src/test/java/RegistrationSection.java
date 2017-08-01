import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class RegistrationSection {

    protected static WebDriver driver;
    protected static WebDriverWait wait;

    public RegistrationSection (WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    protected void sleep(int time) {
        try {
            Thread.sleep(time * 1000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    //  Page elements
    private By openRegSectionButton = By.cssSelector("a[id=\"btnRegOpen\"]");
    private By nameRegField = By.cssSelector("input[name=\"users_models_RegisterForm[name]\"]");
    private By familyRegField = By.cssSelector("input[name=\"users_models_RegisterForm[family]\"]");
    private By loginRegField = By.cssSelector("input[name=\"users_models_RegisterForm[login]\"]");
    private By emailRegField = By.cssSelector("input[name=\"users_models_RegisterForm[email]\"]");
    private By passwordRegField = By.cssSelector("input[name=\"users_models_RegisterForm[password]\"]");
    private By passwordRepeatRegField = By.cssSelector("input[name=\"users_models_RegisterForm[password_repeat]\"]");
    private By submitRegButton = By.cssSelector("input[id=\"submitRegistration\"]");
    private By emailRegFieldErrorLabel = By.cssSelector("div[id=\"users_models_RegisterForm_email_em_\"]");
    private By passwordRegFieldErrorLabel = By.cssSelector("div[id=\"users_models_RegisterForm_password_em_\"]");


    // Data for tests
    private String userName = "TEST";
    private String userFamily = "TEST";
    private String userLogin = "TEST_ACC";
    private String userEmail = "testfortest@gmail.com";
    private String userPassword = "123456";
    private String valueForCheckNumberInField = "9";
    private String valueForCheckSymbolInField = ";";
    private String valueForCheckNonlatinSymbolInField = "я";
    private String valueForCheckPassLessMinLength  = "12345";
    private String valueForCheckPassOVerMaxLength  = "123456789-123456789-123456789-123456789-123456789-123456789-123$#";
    private String valueForCheckEmailWithoutAtSymbol  = "test.test.com";
    private String valueForCheckEmailWithTwoPeriodsAfterAtSymbol  = "test@test..com";


    // Methods
    protected void clickOpenRegSectionButton () {
        this.driver.findElement(this.openRegSectionButton).click();;
    }
    protected void setRegName (String userRegName) {
        this.driver.findElement(this.nameRegField).sendKeys(userRegName);
    }
    protected void setRegFamily (String userRegFamily) {
        this.driver.findElement(this.familyRegField).sendKeys(userRegFamily);
    }
    protected void setRegLogin (String userRegLogin) {
        this.driver.findElement(this.loginRegField).sendKeys(userRegLogin);
    }
    protected void setRegEmail (String userRegEmail) {
        this.driver.findElement(this.emailRegField).sendKeys(userRegEmail);
    }
    protected void setRegPassword (String userRegPassword) {
        this.driver.findElement(this.passwordRegField).sendKeys(userRegPassword);
    }
    protected void setRegPasswordRepeat (String userRegPasswordRepeat) {
        this.driver.findElement(this.passwordRepeatRegField).sendKeys(userRegPasswordRepeat);
    }
    protected void clickRegSubmitButton () {
        this.driver.findElement(this.submitRegButton).click();;
    }


    //   Test methods
    protected void registerAcc() {
        this.clickOpenRegSectionButton();
        this.setRegName(this.userName);
        this.setRegFamily(this.userFamily);
        this.setRegLogin(this.userLogin);
        this.setRegEmail(this.userEmail);
        this.setRegPassword(this.userPassword);
        this.setRegPasswordRepeat(this.userPassword);
        this.clickRegSubmitButton();
        this.sleep(5);
        Assert.assertEquals(this.driver.switchTo().alert().getText(), "Для подтверждения регистрации, проверьте почту", "");
        this.driver.switchTo().alert().accept();
    }
        //   Email field
    protected void noAtSymbolInEmailField() {
        this.clickOpenRegSectionButton();
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(submitRegButton));
        this.setRegEmail(valueForCheckEmailWithoutAtSymbol);
        this.clickRegSubmitButton();
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(emailRegFieldErrorLabel));
        Assert.assertEquals(this.driver.findElement(emailRegFieldErrorLabel).getText(), "E-mail не является правильным E-Mail адресом.");
    }
    protected void twoPeriodsInEmailField() {
        this.clickOpenRegSectionButton();
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(submitRegButton));
        this.setRegEmail(valueForCheckEmailWithTwoPeriodsAfterAtSymbol);
        this.clickRegSubmitButton();
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(emailRegFieldErrorLabel));
        Assert.assertEquals(this.driver.findElement(emailRegFieldErrorLabel).getText(), "E-mail не является правильным E-Mail адресом.");
    }
        //  Password field
    protected void lessMinLengthInPasswordField() {
        this.clickOpenRegSectionButton();
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(submitRegButton));
        this.setRegPassword(valueForCheckPassLessMinLength);
        this.clickRegSubmitButton();
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(passwordRegFieldErrorLabel));
        Assert.assertEquals(this.driver.findElement(passwordRegFieldErrorLabel).getText(), "Пароль слишком короткий (Минимум: 6 симв.).");
    }
    protected void overMaxLengthInPasswordField() {
        this.clickOpenRegSectionButton();
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(submitRegButton));
        this.setRegPassword(valueForCheckPassOVerMaxLength);
        this.clickRegSubmitButton();
        Assert.assertEquals(this.driver.findElement(passwordRegField).getAttribute("value").length(), 64);
    }
}
