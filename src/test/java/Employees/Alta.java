package Employees;

import io.cucumber.java.en.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Alta {

    String e = System.setProperty("webdriver.chrome.driver", "src/test/resources/browserBinaries/chromedriver.exe");
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, 10);


    /* BACKGROUND */

    @Given("me encuentro en la pantalla principal de RESTool App")
    public void validateMainView() {
        driver.get("https://dsternlicht.github.io/RESTool/#/characters?search=");
        driver.manage().window().maximize();
        String title = driver.findElement(By.xpath("//h1")).getText();
        Assert.assertEquals(title, "RESTool App");
    }

    @When("doy click en la pestaña Employees")
    public void clickOnLinkEmployees(){
        driver.findElement(By.xpath("//a[@href='#/employees']")).click();
    }

    @Then("compruebo que me redirija al modulo de Employees")
    public void ValidateRedirectionToModuleEmployees(){
        String title = driver.findElement(By.xpath("//h2[contains(text(),'Employees')]")).getText();
        Assert.assertEquals(title, "Employees");
    }

    /* ALTA ITEM */

    @When("Doy click en el botón Add Item")
    public void clickOnAddItem(){
        driver.findElement(By.xpath("//button[@class='button add-item green']")).click();
    }

    @And("Ingreso {string} en el campo name")
    public void insertName(String arg0){
        driver.findElement(By.cssSelector("div.popup-content section form div:first-child input")).sendKeys(arg0, Keys.TAB, Keys.ENTER);
    }

    @And("Selecciono la opcion {string} en el campo job title")
    public void selectOption(String arg){
        driver.findElement(By.xpath("//option[contains(@value, '"+arg+"')]")).click();
    }

    @And("Doy click en el botón submit")
    public void clickOnSubmit(){
        driver.findElement(By.xpath("//button[text()='Submit' and contains(@class, 'green')]")).click();
    }

    @Then("Compruebo la alerta con el texto Great Success!")
    public void CheckTheSuccessAlert(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Great Success!')]")));
    }

    @And("Visualizo {string} en la tabla de employees")
    public void checkEmployeeAddedInTable(String arg){
        driver.findElement(By.xpath("//label[contains(text(), 'Search')]//following::input[@value='']")).sendKeys(arg, Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td//following::span[contains(text(), '"+arg+"')]")));
    }

    /* EDIT ITEM */

    @When("Busco mi {string} y doy click en el botón de Update Item")
    public void clickOnUpdateItem(String arg){
        driver.findElement(By.xpath("//label[contains(text(), 'Search')]//following::input[@value='']")).sendKeys(arg, Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'actions-wrapper')]//button[@title='Edit']")));
        driver.findElement(By.xpath("//div[contains(@class, 'actions-wrapper')]//button[@title='Edit']")).click();
        ////button[contains(@title, 'Edit')]
    }

    @And("Ingreso {string} en el campo Name")
    public void ClearInputAndInsertName(String arg){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.popup-content section form div.form-row i")));
        driver.findElement(By.cssSelector("div.popup-content section form div.form-row i")).click();
        driver.findElement(By.cssSelector("div.popup-content section form div.form-row input")).sendKeys(arg, Keys.TAB, Keys.ENTER);
    }

    /* DELETE ITEM */

    @When("Busco mi {string} y doy click en el botón de Delete Item")
    public void clickOnDeleteItem(String arg){
        driver.findElement(By.xpath("//label[contains(text(), 'Search')]//following::input[@value='']")).sendKeys(arg, Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='pure-table']")));
        driver.findElement(By.xpath("//div[contains(@class, 'actions-wrapper')]//button[@title='Delete']")).click();
    }

    @And("Doy click en el Alert Aceptar")
    public void clickOnAcceptAlert(){
        Alert alert = driver.switchTo().alert();
        String alertContent = alert.getText();
        Assert.assertEquals("Are you sure you want to delete this item?", alertContent);
        alert.accept();
    }

    @And("Visualizo la inexistencia de {string} en la tabla de employees")
    public void visualizeDeleteItem(String arg){
        driver.findElement(By.xpath("//label[contains(text(), 'Search')]//following::input[@value='']")).sendKeys(arg, Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Nothing to see here. Result is empty.')]")));
    }

}

