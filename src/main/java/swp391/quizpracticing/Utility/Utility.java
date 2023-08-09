package swp391.quizpracticing.Utility;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.poi.ss.usermodel.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import swp391.quizpracticing.dto.ImportDataDTO;
import swp391.quizpracticing.dto.UserDTO;
import swp391.quizpracticing.model.Dimension;
import swp391.quizpracticing.model.Level;
import swp391.quizpracticing.model.User;
import swp391.quizpracticing.service.IDimensionService;
import swp391.quizpracticing.service.ILevelService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

public class Utility {

    private ModelMapper modelMapper;
        private static final ModelMapper mapper = new ModelMapper();

        private Utility() {
            //    Overwrite default constructor
        }

        public static String getSiteURL(HttpServletRequest request) {
            String siteURL = request.getRequestURL().toString();
            return siteURL.replace(request.getServletPath(), "");
        }

    private UserDTO convertEntityToDTO(User entity){
        return modelMapper.map(entity, UserDTO.class);
    }

    public static List<ImportDataDTO> validateFile(MultipartFile file, ILevelService iLevelService, IDimensionService iDimensionService) throws IOException {
        List<ImportDataDTO> list = new ArrayList<>();
        // Create a workbook from the uploaded XLSX file
        Workbook workbook = WorkbookFactory.create(file.getInputStream());

        // Assuming the data is in the first sheet (index 0)
        Sheet sheet = workbook.getSheetAt(0);

        // Get the first row which should contain the header
        Row headerRow = sheet.getRow(0);
        int count = 0;
        // Process the header row to get column names
        List<String> columnNames = new ArrayList<>();
        Iterator<Cell> headerIterator = headerRow.cellIterator();
        while (headerIterator.hasNext()) {
            ++count;
            Cell cell = headerIterator.next();
            String columnName = cell.getStringCellValue();
            columnNames.add(columnName);
        }

        String[] listHeader = {"level", "dimension", "content", "answer 1", "answer 2", "answer 3", "answer 4"};
        boolean isHeaderValid = Utility.areArraysEqualWithStreams(listHeader, columnNames);

        if (!isHeaderValid) {
            return null;
        }

        // check header size template
        if (count != 7) {
            return null;
        }

        // get level list
        List<Level> levels = iLevelService.listAll();

        // get dimension list
        List<Dimension> dimensions = iDimensionService.listAll();

        // Skip the header row
        Iterator<Row> rowIterator = sheet.iterator();
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }


        // Process the data row by row
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            // Assuming the data is always in the expected format
            String level = getCellValueAsString(cellIterator.next());
            String dimension = getCellValueAsString(cellIterator.next());
            String content = getCellValueAsString(cellIterator.next());
            String answer1 = getCellValueAsString(cellIterator.next());
            String answer2 = getCellValueAsString(cellIterator.next());
            String answer3 = getCellValueAsString(cellIterator.next());
            String answer4 = getCellValueAsString(cellIterator.next());

            // Do something with the extracted data (e.g., save it to a database)
            System.out.println("Level: " + level);
            System.out.println("Dimension: " + dimension);
            System.out.println("Content: " + content);
            System.out.println("Answer 1: " + answer1);
            System.out.println("Answer 2: " + answer2);
            System.out.println("Answer 3: " + answer3);
            System.out.println("Answer 4: " + answer4);

            boolean isLevelPresent = levels.stream()
                    .anyMatch(l -> l.getName().equals(level.toLowerCase()));

            boolean isDimensionPresent = dimensions.stream()
                    .anyMatch(d -> d.getName().equals(dimension.toLowerCase()));

            if(!isLevelPresent && !isDimensionPresent){
                return null;
            }

            list.add(new ImportDataDTO(level, dimension, content, answer1, answer2, answer3, answer4));
        }

        // Close the workbook when done
        workbook.close();
        return list;
    }

    public static boolean areArraysEqualWithStreams(String[] array1, List<String> array2) {
        if (array1 == null && array2 == null) {
            return false; // Both arrays are null, so they are equal
        }
        if (array1 == null || array2 == null) {
            return false; // One array is null and the other is not, so they are not equal
        }
        if (array1.length != array2.size()) {
            return false; // The arrays have different lengths, so they are not equal
        }

        // Compare each element of the arrays using streams
        return IntStream.range(0, array1.length)
                .allMatch(i -> array1[i].equals(array2.get(i).toLowerCase()));
    }

    public static String getCellValueAsString(Cell cell) {
        String cellValue;
        if (cell.getCellType() == CellType.STRING) {
            cellValue = cell.getStringCellValue();
        } else {
            // Use DataFormatter to handle numeric cells
            DataFormatter dataFormatter = new DataFormatter();
            cellValue = dataFormatter.formatCellValue(cell);
        }
        return cellValue;
    }
    
}
