import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Read {
    private static String FOLDER = "C:\\Users\\explo\\Downloads\\";
    private static final String URL_FILE = "XW8DX41U09K015111";
    private static final String URL_START = "https://exist.ru/";
    private static WebDriver webDriver;

    public static void main(String[] args) {
        File file = new File(FOLDER + URL_FILE + ".txt");
        List<String> list = new ArrayList<String>();
        List<Item> itemList = new ArrayList<Item>();
        BufferedReader bufferedReader = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String readLine;
        try {
            while ((readLine = bufferedReader.readLine()) != null) {
                System.out.println(readLine);
                list.add(readLine);

            }
            System.out.println("размер масива:" + list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\explo\\Downloads\\chromedriver_win32\\chromedriver.exe");
        webDriver = new ChromeDriver();


        webDriver.get(URL_START);
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < list.size(); i++) {
            try {
                boolean capcha = chekCapcha();
                if (capcha) {
                    while (sc.nextInt() != 0) {
                    }
                    i--;
                    webDriver.get(URL_START);
                    continue;
                }


                WebElement search = webDriver.findElement(By.className("header-search__search-input"));
                search.sendKeys(list.get(i));

                WebElement submit = webDriver.findElement(By.className("header-search__search-submit-btn"));

              capcha = chekCapcha();
                if (capcha) {
                    while (sc.nextInt() != 0) {
                    }
                    i--;
                    webDriver.get(URL_START);
                    continue;
                }

                submit.click();
                Thread.sleep(2000);

                capcha = chekCapcha();
                if (capcha) {
                    while (sc.nextInt() != 0) {
                    }
                    i--;
                    webDriver.get(URL_START);
                    continue;
                }


                Thread.sleep(2000);


                boolean skipIs = false;
                WebElement element1 = null;
                try {
                    element1 = webDriver.findElement(By.className("user-banner__skip"));
                    if (element1.isDisplayed()) {
                        System.out.println(11111111);
                        skipIs = true;
                    }
                } catch (NoSuchElementException e) {

                }

                if (skipIs) {
                    Thread.sleep(6000);
                    element1.click();
                }
                WebElement div = webDriver.findElement(By.className("row-container"));

                Item item = new Item();
                item.setIdMashine("Skoda " + URL_FILE);
                WebElement nameContainer = div.findElement(By.className("name-container"));
                WebElement partno = nameContainer.findElement(By.className("partno"));
                //  System.out.println(partno.getText().replace(" ", ""));
                item.setId(partno.getText().replace(" ", ""));

                WebElement description = div.findElement(By.cssSelector("a"));
                //   System.out.println(description.getText());
                item.setDescription(description.getText());

                WebElement allOffers = div.findElement(By.className("allOffers"));
                WebElement element = allOffers.findElement(By.cssSelector("span[class='price']"));


                System.out.println(element.getText());
                item.setCost(element.getText());

                itemList.add(item);
                System.out.println(itemList.size());
            } catch (NoSuchElementException e) {
                System.out.println(e);
                System.out.println("нет для id: " + list.get(i));
                continue;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        ExcelWriter excelWriter = new ExcelWriter(itemList);
        excelWriter.start();


    }

    private static boolean chekCapcha() {
        boolean capchaIs = false;
        try {

            WebElement capcha2 = webDriver.findElement(By.className("recaptcha-checkbox-checkmark"));
            if (capcha2.isDisplayed()) {
                capchaIs = true;
            }
        } catch (NoSuchElementException e) {
            capchaIs = false;
        }

        return capchaIs;


    }
}
