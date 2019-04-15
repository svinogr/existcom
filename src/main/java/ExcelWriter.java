import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelWriter {
    private List<Item> itemList;
    private Workbook workbook;
    private Sheet sheet;

    public ExcelWriter(List<Item> listl) {
        this.itemList = listl;
    }

    public void start() {
        workbook = new HSSFWorkbook();
        sheet = workbook.createSheet("ID");

        createTitle();

        inflateTable();

    }

    private void inflateTable() {
        Sheet sheet = workbook.getSheetAt(0);
        int rowNumber = 1;
        for (Item item : itemList) {
            Row row = sheet.createRow(rowNumber);

            row.createCell(0).setCellValue(item.getIdMashine());
            row.createCell(1).setCellValue(item.getId());
            row.createCell(2).setCellValue(item.getDescription());
            row.createCell(3).setCellValue(item.getCost());
            rowNumber++;
        }
        String nameFile = itemList.get(0).getIdMashine();
        File file = new File("C:\\Users\\explo\\Downloads\\" + nameFile + ".xls");

        try {
        FileOutputStream outFile = new FileOutputStream(file);

            workbook.write(outFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createTitle() {
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("Номер машины");
        row.createCell(1).setCellValue("ID запчасти");
        row.createCell(2).setCellValue("Описание");
        row.createCell(3).setCellValue("Цена");
    }
}
