
import org.testng.annotations.Test;

public class RegistrationSectionTestCases extends Config {
    public RegistrationSectionTestCases(){
    }

    @Test(groups = {"smoke"})
    protected void userRegistration(){
        RegistrationSection registrationSection = new RegistrationSection(this.driver, this.wait);
        registrationSection.registerAcc();
    }
    @Test(groups = {"input-fields"})
    protected void checkEmailWithoutAtSymbolIsInvalidInEmailField() {
        RegistrationSection registrationSection = new RegistrationSection(this.driver, this.wait);
        registrationSection.noAtSymbolInEmailField();
    }
    @Test(groups = {"input-fields"})
    protected void checkEmailWithTwoPeriodsAfterAtSymbolIsInvalidInEmailField() {
        RegistrationSection registrationSection = new RegistrationSection(this.driver, this.wait);
        registrationSection.twoPeriodsInEmailField();
    }
    @Test(groups = {"input-fields"})
    protected void checkLessMinLengthInPasswordField() {
        RegistrationSection registrationSection = new RegistrationSection(this.driver, this.wait);
        registrationSection.lessMinLengthInPasswordField();
    }
    @Test(groups = {"input-fields"})
    protected void checkOverMaxLengthInPasswordField() {
        RegistrationSection registrationSection = new RegistrationSection(this.driver, this.wait);
        registrationSection.overMaxLengthInPasswordField();
    }
}

