package main;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;

public class Main {
    static String USERNAME = "";
    static String PASSWORD = "";
    static String DRIVER = "";
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", DRIVER);
        WebDriver driver;
        driver = new ChromeDriver();
        //maximize window
        driver.manage().window().maximize();
        //Launch portal
        String url = "http://portal.aait.edu.et";
        driver.get(url);
        //type username
        driver.findElement(By.id("UserName")).sendKeys(USERNAME);
        //type password
        driver.findElement(By.name("Password")).sendKeys(PASSWORD, Keys.ENTER);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@id='ml2']")).click();
        Thread.sleep(1000);
        String sourceCode = driver.getPageSource();
        String gradeTable = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[1]/div/div/table")).getText();
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("gradeReport.txt"), "utf-8"))) {
            writer.write(gradeTable);
        }
        catch (IOException e){

        }

        driver.close();
    }
}
