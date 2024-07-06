package saga.inventory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class InventoryControllerTest {

    private final InventoryController inventoryController = new InventoryController();

    @Test
    public void testReserveInventory_Success() {
        ResponseEntity<String> response = inventoryController.reserveInventory();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Inventory reserved", response.getBody());
    }

    @Test
    public void testReserveInventory_Failure() {
        // Simulate failure by making inventory reservation logic fail
        inventoryController.simulateFailure(true);
        ResponseEntity<String> response = inventoryController.reserveInventory();
        assertEquals(500, response.getStatusCodeValue());
        assertEquals("Inventory reservation failed", response.getBody());
    }

    @Test
    public void testCompensateInventory_Success() {
        ResponseEntity<String> response = inventoryController.compensateInventory();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Inventory compensation successful", response.getBody());
    }

    @Test
    public void testCompensateInventory_Failure() {
        // Simulate failure by making inventory compensation logic fail
        inventoryController.simulateFailure(true);
        ResponseEntity<String> response = inventoryController.compensateInventory();
        assertEquals(500, response.getStatusCodeValue());
        assertEquals("Inventory compensation failed", response.getBody());
    }
}