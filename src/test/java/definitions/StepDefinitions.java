package definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import static com.EvolutionGaming.Helper.*;
import static com.EvolutionGaming.PageObjects.*;

public class StepDefinitions {

    @Given("I will go to the home page")
    public void iWillGoToTheHomePage() {
        driver.get(url);
        Assert.assertEquals(driver.getCurrentUrl(), url); // Verifies that website is accessed correctly
    }

    @And("^I will choose a random category$")
    public void iWillChooseARandomCategory() throws InterruptedException {
        clickByRandom(mainCategory);
    }

    @And("I will choose a random sub-category \\(If needed)")
    public void iWillChooseARandomSubCategoryIfNeeded() throws InterruptedException {
        while (isEmpty(subCategory))
            clickByRandom(subCategory);
        System.out.println("Memos are listed");
    }

    @Then("^I will pick a random ad")
    public void iWillPickARandomAd() throws InterruptedException {
        Assert.assertFalse(findElement(showSelected).isDisplayed(), "Show Selected counter is displayed"); // Verifies that "Show selected" quantity is not displayed
        int index = clickByRandom(memoCheckbox);
        Thread.sleep(500);
        Assert.assertTrue(findElements(memoCheckbox, index).isSelected(), "Checkbox is not selected"); // Verifies that checkbox is selected
        Assert.assertEquals(getSelectedQuantity(showSelected), "1", "Show Selected quantity is not 1"); // Verifies that "Show selected" counter is displayed and equals to 1
    }

    @And("I will add a memo to the favorites")
    public void iWillAddAMemoToTheFavorites() throws InterruptedException {
        if (isEmpty(addToMemo)) {
            Assert.assertFalse(findElement(memoCounter).isDisplayed(), "Memo counter is displayed"); // Verifies that Memo counter is not displayed
            clickBy(addToMemo);
            Assert.assertTrue(findElement(alertMessage).isDisplayed(), "Alert message is not Displayed"); // Verifies that alert message is displayed after adding the memo to the favorites
            Assert.assertEquals(getSelectedQuantity(memoCounter), "1"); // Verifies that memo counter is displayed and equals to 1
        } else
            System.out.println("No ads found for this category");
        Thread.sleep(1000);
    }
}
