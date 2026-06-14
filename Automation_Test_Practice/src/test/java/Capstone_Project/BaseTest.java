package Capstone_Project;

import org.testng.annotations.*;
import POM.POM_Implementation;
import java.io.*;
import org.apache.poi.xssf.usermodel.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    POM_Implementation p;

    // DataProvider reads Name from Excel
    @DataProvider(name="NameExcel")
    public Object[][] nameexcel() throws IOException {
        FileInputStream fs = new FileInputStream("D:\\Wipro Training\\NameData.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fs);
        XSSFSheet sh = wb.getSheet("sheet1");
        int rows = sh.getPhysicalNumberOfRows();
        int cols = sh.getRow(0).getLastCellNum();
        Object[][] data = new Object[rows-1][cols];
        for (int i=1; i<rows; i++)
            for (int j=0; j<cols; j++)
                data[i-1][j] = sh.getRow(i).getCell(j).toString();
        fs.close(); wb.close();
        return data;
    }

    // DataProvider for search
    @DataProvider(name="searchdata")
    public Object[][] data() {
        return new Object[][] { {"Laptop"} };
    }

    // DataProvider for paragraph text boxes
    @DataProvider(name="paragraph")
    public Object[][] paragraphbox() {
        return new Object[][] {
            {
                "The ocean is deep and wide",
                "Stars shine bright at night",
                "Rivers flow to the sea"
            }
        };
    }
    
    @BeforeMethod
    public void beforeMethod() throws Exception {
        driver = new ChromeDriver();
        p = new POM_Implementation(driver);
        driver.get("https://testautomationpractice.blogspot.com/");
        Thread.sleep(3000);
        driver.manage().window().maximize();
    }

    @Test(dataProvider="NameExcel", priority=1)
    public void TC01_EnterName(String fname) throws Exception {
        p.Name_Element(fname);
    }

    @Test(priority=2)
    public void TC02_RadioAndCheckBox() throws Exception {
        p.radio_checkbox();
    }

    @Test(priority=3)
    public void TC03_UploadFiles() throws Exception {
        p.upload();
    }

    @Test(priority=4)
    public void TC04_PaginationTable() throws Exception {
        p.page_table();
    }

    @Test(dataProvider="paragraph", priority=5)
    public void TC05_Forms(String p1, String p2, String p3) throws Exception {
        p.forms(p1, p2, p3);
    }

    @Test(dataProvider="searchdata", priority=6)
    public void TC06_Search(String item) throws Exception {
        p.search(item);
    }

    @Test(priority=7)
    public void TC07_DynamicButton() throws Exception {
        p.dynamicbtn();
    }

    @Test(priority=8)
    public void TC08_Alerts() throws Exception {
        p.alertaccept();
    }

    @Test(priority=9)
    public void TC09_WindowHandling() throws Exception {
        p.newtab();
    }

    @Test(priority=10)
    public void TC10_MouseHover() throws Exception {
        p.movetoelement();
    }

    @Test(priority=11)
    public void TC11_DragAndDrop() throws Exception {
        p.draganddrop();
    }

    @Test(priority=12)
    public void TC12_BrokenLinks() throws Exception {
        p.broken_links();
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}
