package POM;

import java.io.*; 
import java.time.Duration; 
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

public class POM_Implementation {
    WebDriver driver;
    JavascriptExecutor js;
    WebDriverWait wait;
    TakesScreenshot ts;
    
    public POM_Implementation(WebDriver driver) {
        this.driver = driver;
    }
    By fnamefield    = By.id("name");
    By emailfield    = By.id("email");
    By phonefield    = By.id("phone");
    By addressfield  = By.id("textarea");
    By radiobutton   = By.id("male");
    By sundaycheckbox   = By.id("sunday");
    By mondaycheckbox   = By.id("monday");
    By tuesdaycheckbox  = By.id("tuesday");
    By countrydropdown  = By.id("country");
    By colorselect      = By.id("colors");
    By Listselect       = By.id("animals");
    By datepicker1      = By.id("datepicker");
    By submitbutton     = By.className("submit-btn");
    By singlefileupload = By.id("singleFileInput");
    By dynamicbtn       = By.name("start");
    By alertbutton      = By.id("alertBtn");
    By searchbox = By.id("Wikipedia1_wikipedia-search-input");
    By searchbutton = By.xpath("//*[@id='Wikipedia1_wikipedia-search-form']/div/span[2]/span[2]/input");
    By searchsuggestion = By.partialLinkText("Laptop");
    By mousehover = By.xpath("//*[@id='HTML3']/div[1]/div/button");
    By source     = By.id("draggable");
    By destination= By.id("droppable");
    

    public void Name_Element(String fname) throws InterruptedException {
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,100)");
        Thread.sleep(2000);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement firstname =
            wait.until(ExpectedConditions.elementToBeClickable(fnamefield));
        firstname.click();
        firstname.sendKeys(fname);
        System.out.println("Name entered: " + fname);
        Thread.sleep(1000);
    }
    
    public void radio_checkbox() throws InterruptedException {
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,900)");
        driver.findElement(radiobutton).click();       
        Thread.sleep(1000);
        driver.findElement(sundaycheckbox).click();    
        driver.findElement(mondaycheckbox).click();    
        driver.findElement(tuesdaycheckbox).click();   
        Select s = new Select(driver.findElement(countrydropdown));
        s.selectByVisibleText("India");
        Thread.sleep(1000);
        Select color = new Select(driver.findElement(colorselect));
        color.selectByVisibleText("White");
        driver.findElement(datepicker1).sendKeys("08/20/2026");
        driver.findElement(datepicker1).sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        driver.findElement(submitbutton).click();
        System.out.println("TC02 Radio, Checkbox, Dropdowns done");
    }
    
    public void search(String searchitem) throws InterruptedException {
        WebElement text = driver.findElement(searchbox);
        text.click();
        Thread.sleep(2000);
        text.sendKeys(searchitem);
        Thread.sleep(3000);

        driver.findElement(searchbutton).click();
        Thread.sleep(8000);
        String parentWindow = driver.getWindowHandle();

        driver.findElement(By.partialLinkText("Laptop")).click();
        Thread.sleep(5000);  

        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
            }
        }

        System.out.println("New window title: " + driver.getTitle());
        Thread.sleep(3000);
        driver.close();
        driver.switchTo().window(parentWindow);
        System.out.println("TC06 Search Completed Successfully");
    }
    
    public void alertaccept() throws InterruptedException {
        driver.findElement(alertbutton).click();
        Alert al = driver.switchTo().alert();
        Thread.sleep(1000);
        al.accept();
        System.out.println("Simple Alert Accepted");
        driver.findElement(By.id("confirmBtn")).click();
        Alert confirmAlert = driver.switchTo().alert();
        Thread.sleep(1000);
        confirmAlert.dismiss();
        System.out.println("Confirm Alert Dismissed");
        driver.findElement(By.id("promptBtn")).click();
        Alert promptAlert = driver.switchTo().alert();
        Thread.sleep(1000);
        promptAlert.accept();
        System.out.println("Prompt Alert Accepted");
    }
    
    public void draganddrop() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1400)");
        Thread.sleep(1000);
        WebElement drag = driver.findElement(By.id("draggable"));
        WebElement drop = driver.findElement(By.id("droppable"));
        Actions d = new Actions(driver);
        d.dragAndDrop(drag, drop).perform();
        System.out.println("Successfully Dragged and Dropped the Element");
        Thread.sleep(1000);
    }
    
    public void broken_links() throws InterruptedException, IOException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,2700)");
        Thread.sleep(1000);
        driver.findElement(By.linkText("Errorcode 400")).click();
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("Screenshot/Error400.png"));
        System.out.println("Screenshot saved for Error 400");
        driver.navigate().back();
        Thread.sleep(1000);
        driver.findElement(By.linkText("Errorcode 404")).click();
        System.out.println("Page Title: " + driver.getTitle());
        driver.navigate().back();
        Thread.sleep(1000);
    }
    
    public void upload() throws InterruptedException {
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1700)");
        Thread.sleep(3000);
        
        WebElement singlefile = driver.findElement(By.id("singleFileInput"));
        singlefile.sendKeys("D:\\Wipro Training\\Assignment SDET_1.docx");
        System.out.println("Single File Uploaded Successfully");
        Thread.sleep(3000);

        WebElement multiplefiles = driver.findElement(By.id("multipleFilesInput"));
        multiplefiles.sendKeys("D:\\Wipro Training\\Assignment SDET_1.docx"
            + "\n" + "D:\\Wipro Training\\Assignment SDET_2.docx");
        System.out.println("Multiple Files Uploaded Successfully");
        Thread.sleep(3000);
    }
    
    public void page_table() throws InterruptedException {
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,2700)");
        Thread.sleep(3000);

        driver.findElement(By.xpath("//*[@id='productTable']/tbody/tr[1]/td[4]/input")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='productTable']/tbody/tr[2]/td[4]/input")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='productTable']/tbody/tr[3]/td[4]/input")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='productTable']/tbody/tr[4]/td[4]/input")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='productTable']/tbody/tr[5]/td[4]/input")).click();
        Thread.sleep(1000);

        driver.findElement(By.linkText("2")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='productTable']/tbody/tr[1]/td[4]/input")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='productTable']/tbody/tr[2]/td[4]/input")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='productTable']/tbody/tr[3]/td[4]/input")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='productTable']/tbody/tr[4]/td[4]/input")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='productTable']/tbody/tr[5]/td[4]/input")).click();
        Thread.sleep(1000);

        driver.findElement(By.linkText("3")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='productTable']/tbody/tr[1]/td[4]/input")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='productTable']/tbody/tr[2]/td[4]/input")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='productTable']/tbody/tr[3]/td[4]/input")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='productTable']/tbody/tr[4]/td[4]/input")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='productTable']/tbody/tr[5]/td[4]/input")).click();
        Thread.sleep(1000);

        driver.findElement(By.linkText("4")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='productTable']/tbody/tr[1]/td[4]/input")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='productTable']/tbody/tr[2]/td[4]/input")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='productTable']/tbody/tr[3]/td[4]/input")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='productTable']/tbody/tr[4]/td[4]/input")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='productTable']/tbody/tr[5]/td[4]/input")).click();
        Thread.sleep(1000);

        System.out.println("20 checkboxes clicked successfully in 4 pages ");
    }
    
    public void forms(String firstparagraph, String secondparagraph, String thirdparagraph)
            throws InterruptedException {
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,3000)");
        Thread.sleep(3000);

        driver.findElement(By.id("input1")).sendKeys(firstparagraph);
        Thread.sleep(2000);
        driver.findElement(By.id("btn1")).click();
        System.out.println("Form 1 Submitted Successfully");
        Thread.sleep(2000);

        driver.findElement(By.id("input2")).sendKeys(secondparagraph);
        Thread.sleep(2000);
        driver.findElement(By.id("btn2")).click();
        System.out.println("Form 2 Submitted Successfully");
        Thread.sleep(2000);

        driver.findElement(By.id("input3")).sendKeys(thirdparagraph);
        Thread.sleep(2000);
        driver.findElement(By.id("btn3")).click();
        System.out.println("Form 3 Submitted Successfully");
        Thread.sleep(2000);
    }
    
    public void dynamicbtn() throws InterruptedException {
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,900)");
        Thread.sleep(2000);

        driver.findElement(By.name("start")).click();
        System.out.println("Successfully Clicked the Dynamic Start Button");
        Thread.sleep(3000);

        driver.findElement(By.name("stop")).click();
        System.out.println("Successfully Clicked the Dynamic Stop Button");
        Thread.sleep(2000);
    }
    
    public void newtab() throws InterruptedException {
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
        Thread.sleep(2000);

        String oldwindow = driver.getWindowHandle();

        driver.findElement(By.xpath("//*[@id='HTML4']/div[1]/button")).click();
        Thread.sleep(3000);

        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            if (!window.equals(oldwindow)) {
                driver.switchTo().window(window);
            }
        }

        System.out.println("Switched to new tab: " + driver.getTitle());
        Thread.sleep(2000);

        driver.close();
        driver.switchTo().window(oldwindow);
        System.out.println("Successfully returned to parent window ");
        Thread.sleep(2000);
    }
    
    public void movetoelement() throws InterruptedException {
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,700)");
        Thread.sleep(2000);

        WebElement moveto = driver.findElement(By.xpath("//*[@id='HTML3']/div[1]/div/button"));

        Actions a = new Actions(driver);
        a.moveToElement(moveto).perform();

        System.out.println("Mouse successfully hovered over element");
        Thread.sleep(3000);

    }

}
