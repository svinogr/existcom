import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReaderToTxt {
    private static String FOLDER = "C:\\Users\\explo\\Downloads\\";
    private static final String URL_FILE = "XW8DX41U09K015111";
    private static final String ID_AUTO = URL_FILE;
    private static final String URL_START = "https://exist.ru/Price/?pcode=";
    private static WebDriver webDriver;
    private static List<Item> itemList;
    private static List<String> list;

    public static void main(String[] args) throws InterruptedException {

        File file = new File(FOLDER + URL_FILE + ".txt");
        list = new ArrayList<String>();
        itemList = new ArrayList<Item>();
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

            System.out.println("размер масива:" + list.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\explo\\Downloads\\chromedriver_win32\\chromedriver.exe");
        webDriver = new ChromeDriver();


        for (int i = 0; i < list.size(); i++) {
            Thread.sleep(1000);
            webDriver.get(URL_START + list.get(i));

            Thread.sleep(1000);

            boolean capcha = chekCapcha();


            if (capcha) {
                Scanner sc = new Scanner(System.in);

                while (sc.nextInt() != 0) {
                }
                i--;
                continue;
            }

            Thread.sleep(1000);

            checkBanner();

            boolean b = inflateItem(i);
            if(!b){continue;}

            saveData(i);


        }

    }

    private static void checkBanner() throws InterruptedException {
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
    }

    private static boolean inflateItem(int i) {

        boolean isHave = false;
        try {

            String itemString = list.get(i);
            Item item = new Item();
            item.setIdMashine("Skoda " + URL_FILE);
            WebElement div = webDriver.findElement(By.className("row-container"));

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
            isHave = true;
        } catch (NoSuchElementException e) {
            System.out.println("нет цены");
            isHave = false;
        }
        return isHave;

    }

    private static void saveData(int i) {
        FileWriter writer = null;
        BufferedWriter bufferedWriter = null;
        try {
            writer = new FileWriter("C:\\Users\\explo\\Downloads\\" + ID_AUTO + "-data" + ".txt", true);
            bufferedWriter = new BufferedWriter(writer);


            StringBuilder stringBuilder = new StringBuilder();
            Item item = itemList.get(itemList.size()-1);

            stringBuilder.append(item.getId() + "@" + item.getDescription() + "@" + item.getCost());

            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.newLine();


            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();

        }


    }

    private static boolean chekCapcha() {
        boolean capchaIs = false;
        try {

            WebElement capcha2 = webDriver.findElement(By.className("g-recaptcha"));
            if (capcha2.isDisplayed()) {
                capchaIs = true;
            }
        } catch (NoSuchElementException e) {
            capchaIs = false;
        }

        return capchaIs;


    }
}
