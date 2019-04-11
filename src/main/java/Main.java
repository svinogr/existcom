import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String URL_START = "http://www.elcats.ru/skoda/?carvin=XW8DX41U09K015111";
    private static String ID_AUTO = "XW8DX41U09K015111";
    private static String LOGIN ="9214456798";
    private static String PASS ="77D5F6";

    public static void main(String[] args) {
        List<String> strings = new ArrayList<String>();
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\explo\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get(URL_START);
        WebElement input = webDriver.findElement(By.name("ctl00$cphMasterPage$txbVIN"));
        input.sendKeys(ID_AUTO);

        Scanner sc = new Scanner(System.in);

        WebElement submit = webDriver.findElement(By.name("ctl00$cphMasterPage$btnFindByVIN"));
        submit.click();

        System.out.println("залогинится введите 4");
        while (sc.nextInt() != 4) {

        }
        WebElement login = webDriver.findElement(By.name("ctl00$cphMasterPage$txbUserLogin"));
        login.sendKeys(LOGIN);

        WebElement pass = webDriver.findElement(By.name("ctl00$cphMasterPage$txbUserPassword"));
        pass.sendKeys(PASS);

        WebElement submitLogAndPass = webDriver.findElement(By.name("ctl00$cphMasterPage$UserLoginAjax"));
        submitLogAndPass.click();


        int finish = 0;
        System.out.println("для начала введите 0");
        while (sc.nextInt() != finish) {
            System.out.println("для начала введите 1");
            while (sc.nextInt() != 1) {

            }
            List<WebElement> elements = webDriver.findElements(By.tagName("a"));

            for (WebElement element : elements) {
                String title = element.getAttribute("title");
                if (title.equals("Нажмите чтобы узнать цену")) {
                    strings.add(element.findElement(By.tagName("b")).getText());
                }

            }
            System.out.println("razmer " + strings.size());
            System.out.println("перейдите на новую группу и для нового списка введите 2");
            while (sc.nextInt() != 2) {

            }
            System.out.println("для завершения введите 0");

        }
        try {
            FileWriter writer = new FileWriter("C:\\Users\\explo\\Downloads\\"+ID_AUTO+".txt", true);
            BufferedWriter bufferedWriter  = new BufferedWriter(writer);

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < strings.size(); i++) {
              bufferedWriter.write(strings.get(i));
              bufferedWriter.newLine();


            }
          bufferedWriter.flush();
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
