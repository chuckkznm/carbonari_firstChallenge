package Deads;

import io.cucumber.java.en.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Deads {
    String e = System.setProperty("webdriver.chrome.driver", "src/test/resources/browserBinaries/chromedriver.exe");
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, 10);


    /* BACKGROUND */

    @Given("me encuentro en el main de RESTool App")
    public void validateMainView() {
        driver.get("https://dsternlicht.github.io/RESTool/#/characters?search=");
        driver.manage().window().maximize();
        String title = driver.findElement(By.xpath("//h2")).getText();
        Assert.assertEquals(title, "Cast & Characters");
    }

    @When("doy click en la pestaña Deads")
    public void clickOnLinkEmployees(){
        driver.findElement(By.xpath("//a[@href='#/deads']")).click();
    }

    @Then("Compruebo que me encuentro en el modulo de Deads")
    public void ValidateRedirectionToModuleEmployees(){
        String title = driver.findElement(By.xpath("//h2[contains(text(),'Deads')]")).getText();
        Assert.assertEquals(title, "Deads");
    }

    /* ADD ITEM */

    @When("doy click en el botón Add Item en Deads")
    public void clickOnAddItem(){
        driver.findElement(By.xpath("//button[@class='button add-item green']")).click();
    }

    @And("Ingreso {string} en el campo name en Deads")
    public void insertName(String arg){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.popup-content section form")));
        String value = driver.findElement(By.cssSelector("div.popup section form div.form-row:first-child input")).getAttribute("Value");
        if (value != "") {
            driver.findElement(By.cssSelector("div.popup section form div.form-row:first-child input")).clear();
            driver.findElement(By.cssSelector("div.popup-content section form div:first-child input[value='"+value+"']")).sendKeys(arg);
        }else{
            driver.findElement(By.cssSelector("div.popup-content section form div:first-child input")).sendKeys(arg);
        }
    }

    @And("Ingreso {string} en el campo reason en Deads")
    public void insertReasonDead(String arg){
        String value = driver.findElement(By.xpath("//div[@class='popup-content']//following::form//following::div[position()=1 and @class='form-row row']//following::input")).getAttribute("Value");
        if(value != "") {
            driver.findElement(By.xpath("//div[@class='popup-content']//following::form//following::div[position()=1 and @class='form-row row']//following::input")).clear();
            driver.findElement(By.xpath("//div[@class='popup-content']//following::form//following::div[position()=1 and @class='form-row row']//following::input")).sendKeys(arg);
        }else{
            driver.findElement(By.xpath("//div[@class='popup-content']//following::form//following::div[position()=1 and @class='form-row row']//following::input")).sendKeys(arg);
        }
    }

    @And("doy click en el botón submit en Deads")
    public void clickOnSubmit(){
        driver.findElement(By.xpath("//button[@class='button  green']")).click();
    }

    @Then("compruebo la alerta con el texto Great Success! en Deads")
    public void checkTheSuccessAlert(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Great Success!')]")));
    }

    @And("Visualizo {string} en la tabla de Deads")
    public void checkDeadAddedInTable(String arg){
        driver.findElement(By.xpath("//label[contains(text(), 'Search')]//following::input[@value='']")).sendKeys(arg, Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='pure-table']//following::span[text()='"+arg+"']")));
    }

    /* UPDATE ITEM */

    @When("doy click en el botón de Update Item en Deads")
    public void clickOnUpdateItem(){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'actions-wrapper')]//button[@title='Edit']")));
        driver.findElement(By.xpath("//div[contains(@class, 'actions-wrapper')]//button[@title='Edit']")).click();
    }

    /* DELETE ITEM */

    @When("busco mi {string} y doy click en el botón de Delete Item en Deads")
    public void clickOnDeleteItem(String arg){
        driver.findElement(By.xpath("//label[contains(text(), 'Search')]//following::input[@value='']")).sendKeys(arg, Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='pure-table']")));
        driver.findElement(By.xpath("//div[contains(@class, 'actions-wrapper')]//button[@title='Delete']")).click();
    }

    @And("Doy click en el Alert Aceptar en Deads")
    public void clickOnAcceptAlert(){
        Alert alert = driver.switchTo().alert();
        String alertContent = alert.getText();
        Assert.assertEquals("Are you sure you want to delete this item?", alertContent);
        alert.accept();
    }

    @And("Visualizo la inexistencia de {string} en la tabla de Deads")
    public void visualizeDeleteItem(String arg){
        driver.navigate().back();
        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='query-params-form']")));
        driver.findElement(By.xpath("//label[contains(text(), 'Search')]//following::input[@value='']")).sendKeys(arg, Keys.ENTER);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//table[@class='pure-table']//following::td//following::span[contains(text(), '"+arg+"')]")));
    }
}
