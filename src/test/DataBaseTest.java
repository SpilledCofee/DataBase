import java.io.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DataBaseTest {

    @Test
    void loadFile() {
    }

    @Test
    void saveFile() {
    }

    @Test
    void main() {
    }

    @Test
    void createRecord() {
    }

    @Test
    void lookUpRecord() {
    }

    @Test
    void updateRecord_UpdateProductId_Success() {
        // Simulating user input for successful update
        // reference: https://stackoverflow.com/questions/1647907/junit-how-to-simulate-system-in-testing
        String input = "OB3DU6HDPITC\na\nOB3DU6HDPITN\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String expectedInitialProductId = "OB3DU6HDPITC";
        String expectedUpdatedProductId = "OB3DU6HDPITN";

        DataBase database = new DataBase();

        assertEquals(expectedInitialProductId, database.records.get(0).getProduct_id());

        database.updateRecord();

        assertEquals(expectedUpdatedProductId, database.records.get(0).getProduct_id());
    }

    @Test
    void updateRecord_UpdateQuanity_Success(){
        // Simulating user input for successful update
        // reference: https://stackoverflow.com/questions/1647907/junit-how-to-simulate-system-in-testing
        String input = "OB3DU6HDPITC\nb\n200";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        int expectedInitialQuantity = 261;
        int expectedUpdatedQuantity = 200;

        DataBase database = new DataBase();

        assertEquals(expectedInitialQuantity, database.records.get(0).getQuantity());

        database.updateRecord();

        assertEquals(expectedUpdatedQuantity, database.records.get(0).getQuantity());
    }

    @Test
    void updateRecord_UpdateWholesaleCost_Success(){
        // Simulating user input for successful update
        // reference: https://stackoverflow.com/questions/1647907/junit-how-to-simulate-system-in-testing
        String input = "OB3DU6HDPITC\nc\n6.73";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        double expectedInitialWholesaleCost = 7.73;
        double expectedUpdatedWholesaleCost = 6.73;

        DataBase database = new DataBase();

        assertEquals(expectedInitialWholesaleCost, database.records.get(0).getWholesale_cost());

        database.updateRecord();

        assertEquals(expectedUpdatedWholesaleCost, database.records.get(0).getWholesale_cost());
    }

    @Test
    void updateRecord_UpdateSalePrice_Success(){
        // Simulating user input for successful update
        // reference: https://stackoverflow.com/questions/1647907/junit-how-to-simulate-system-in-testing
        String input = "OB3DU6HDPITC\nd\n12.50";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        double expectedInitialSalePrice = 12.31;
        double expectedUpdatedSalePrice = 12.50;

        DataBase database = new DataBase();

        assertEquals(expectedInitialSalePrice, database.records.get(0).getSale_price());

        database.updateRecord();

        assertEquals(expectedUpdatedSalePrice, database.records.get(0).getSale_price());
    }

    @Test
    void updateRecord_UpdateSupplierId_Success(){
        // Simulating user input for successful update
        // reference: https://stackoverflow.com/questions/1647907/junit-how-to-simulate-system-in-testing
        String input = "OB3DU6HDPITC\ne\nFCPHOUWN";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String expectedInitialSupplierId = "FCPHOUWX";
        String expectedUpdatedSupplierId = "FCPHOUWN";

        DataBase database = new DataBase();

        assertEquals(expectedInitialSupplierId, database.records.get(0).getSupplier_id());

        database.updateRecord();

        assertEquals(expectedUpdatedSupplierId, database.records.get(0).getSupplier_id());
    }

    @Test
    void updateRecord_UpdateRecord_InvalidProductId() {
        // Simulating user input for invalid Product Id
        // reference: https://stackoverflow.com/questions/1647907/junit-how-to-simulate-system-in-testing
        String input = "OB3DU6HDPITX";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String expectedProductId = "OB3DU6HDPITC";
        int expectedQuantity = 261;
        double expectedWholesaleCost = 7.73;
        double expectedSalePrice = 12.31;
        String expectedSupplierId = "FCPHOUWX";

        DataBase database = new DataBase();

        assertEquals(expectedProductId, database.records.get(0).getProduct_id());
        assertEquals(expectedQuantity, database.records.get(0).getQuantity());
        assertEquals(expectedWholesaleCost, database.records.get(0).getWholesale_cost());
        assertEquals(expectedSalePrice, database.records.get(0).getSale_price());
        assertEquals(expectedSupplierId, database.records.get(0).getSupplier_id());

        database.updateRecord();

        assertEquals(expectedProductId, database.records.get(0).getProduct_id());
        assertEquals(expectedQuantity, database.records.get(0).getQuantity());
        assertEquals(expectedWholesaleCost, database.records.get(0).getWholesale_cost());
        assertEquals(expectedSalePrice, database.records.get(0).getSale_price());
        assertEquals(expectedSupplierId, database.records.get(0).getSupplier_id());
    }

    @Test
    void updateRecord_UpdateRecord_InvalidAttribute() {
        // Simulating user input for invalid attribute
        // reference: https://stackoverflow.com/questions/1647907/junit-how-to-simulate-system-in-testing
        String input = "OB3DU6HDPITC\nf";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String expectedProductId = "OB3DU6HDPITC";
        int expectedQuantity = 261;
        double expectedWholesaleCost = 7.73;
        double expectedSalePrice = 12.31;
        String expectedSupplierId = "FCPHOUWX";

        DataBase database = new DataBase();

        assertEquals(expectedProductId, database.records.get(0).getProduct_id());
        assertEquals(expectedQuantity, database.records.get(0).getQuantity());
        assertEquals(expectedWholesaleCost, database.records.get(0).getWholesale_cost());
        assertEquals(expectedSalePrice, database.records.get(0).getSale_price());
        assertEquals(expectedSupplierId, database.records.get(0).getSupplier_id());

        database.updateRecord();

        assertEquals(expectedProductId, database.records.get(0).getProduct_id());
        assertEquals(expectedQuantity, database.records.get(0).getQuantity());
        assertEquals(expectedWholesaleCost, database.records.get(0).getWholesale_cost());
        assertEquals(expectedSalePrice, database.records.get(0).getSale_price());
        assertEquals(expectedSupplierId, database.records.get(0).getSupplier_id());

    }

    @Test
    void deleteRecord() {
    }
}