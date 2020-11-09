package Cast;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.junit.internal.runners.statements.ExpectException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CastCharacters {
    private String name;

    String e = System.setProperty("webdriver.chrome.driver", "src/test/resources/browserBinaries/chromedriver.exe");
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, 10);

    /* BACKGROUND */

    @Given("me encuentro en la pantalla principal de RESTool App en C&C")
    public void openBrowser() {
        driver.get("https://dsternlicht.github.io/RESTool/#/characters?search=");
        driver.manage().window().maximize();

    }

    @Then("compruebo que me encuentro en C&C")
    public void validateMainView(){
        String title = driver.findElement(By.xpath("//h2")).getText();
        Assert.assertEquals(title, "Cast & Characters");
    }

    /* ADD ITEM */

    @When("doy click en el botón ADD ITEM en C&C")
    public void clickOnAddItem(){
        driver.findElement(By.xpath("//button[@class='button add-item green']")).click();
    }

    @Then("valido que abra el modal en C&C")
    public void validateOpenModal(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='popup-content']")));
    }

    @And("Ingreso {string} en el campo Name en C&C")
    public void insertName(String arg){
        driver.findElement(By.cssSelector("div.popup-content section form div:nth-child(2) input")).sendKeys(arg);
    }

    @And("Ingreso {string} en el capo realName en C&C")
    public void insertRealName(String arg){
        //this.name = arg;
        driver.findElement(By.cssSelector("div.popup-content section form div:nth-child(3) input")).sendKeys(arg, Keys.TAB, Keys.ENTER);
    }

    @And("Selecciono la opción {string} en el campo Location en C&C")
    public void insertOption(String arg) {
        driver.findElement(By.cssSelector("option[value='" + arg + "']")).click();
    }

    @And("Doy click en el botón Submit de C&C")
    public void clickOnSubmit(){
        driver.findElement(By.cssSelector("button[class='button  green']")).click();
    }

    @Then("Compruebo la alerta con el texto Great Success! en C&C")
    public void validateSuccessAlert(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Great Success!')]")));
    }

    @And("Visualizo {string} en la tabla de C&C")
    public void validateUserInTable(String arg){
        driver.findElement(By.xpath("//label[contains(text(), 'Search')]//following::input[@value='']")).sendKeys(arg, Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), '"+arg+"')]")));
    }

    /* EDIT ITEM */

    @When("busco mi nuevo item y doy click en el botón de Update Item en C&C")
    public void clickOnUpdateItem(){
        //driver.findElement(By.xpath("//label[contains(text(), 'Search')]//following::input[@value='']")).sendKeys(name, Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='pure-table']")));
        driver.findElement(By.xpath("//div[@class='actions-wrapper']//button[@Title='Edit']")).click();
    }

    @And("Ingreso {string} en el campo NumberTest en C&C")
    public void insertTestNumber(String arg){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='popup-content']//following::form")));
        String value = driver.findElement(By.xpath("//div[@class='form-row row']//following::input[@placeholder=0]")).getAttribute("Value");
        if(value != ""){
            driver.findElement(By.cssSelector("div.popup div.form-row:nth-child(1) input")).clear();
            driver.findElement(By.xpath("//label[text()='Number Test']//following::input[@value='"+value+"']")).sendKeys(arg);
        }else{
            driver.findElement(By.xpath("//label[text()='Number Test']//following::input[@value='']")).sendKeys(arg);
        }
    }

    /* DELETE ITEM */

    @When("Busco el item {string} y doy click en el botón de Delete Item en C&C")
    public void clickOnDeleteItem(String arg){
        driver.findElement(By.xpath("//label[contains(text(), 'Search')]//following::input[@value='']")).sendKeys(arg, Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), '"+arg+"')]")));
        driver.findElement(By.xpath("//div[@class='actions-wrapper']//button[@title='Delete']")).click();
    }

    @And("Doy click en el Alert Aceptar en C&C")
    public void clickOnAcceptAlert(){
        Alert alert = driver.switchTo().alert();
        String alertContent = alert.getText();
        Assert.assertEquals("Are you sure you want to delete this item?", alertContent);
        alert.accept();
    }

    @And("Visualizo la inexistencia de {string} en la tabla de C&C")
    public void valideDeleteItem(String arg){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[contains(text(), '"+arg+"')]")));
    }


}
