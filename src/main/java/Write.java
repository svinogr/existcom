import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Write {
    private static String FOLDER = "C:\\Users\\explo\\Downloads\\";
    private static final String URL_FILE = "XW8DX41U09K015111-data";
    private static String ID_AUTO = "XW8DX41U09K015111";
    public static void main(String[] args) {
        File file = new File(FOLDER + URL_FILE + ".txt");
        List<Item> list = new ArrayList<Item>();
        BufferedReader bufferedReader = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));


        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }

        String readLine;
        try {
            while ((readLine = bufferedReader.readLine()) != null) {
                System.out.println(readLine);
                String[] split = readLine.split("@");
                Item item = new Item();
                item.setIdMashine(ID_AUTO);
                item.setId(split[0]);
                item.setDescription(split[1]);
                if(split.length == 3){

                        item.setCost(split[2]);

                    }
                list.add(item);

            }
            System.out.println("размер масива:" + list);
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        ExcelWriter writer = new ExcelWriter(list);
        writer.start();
    }
}
