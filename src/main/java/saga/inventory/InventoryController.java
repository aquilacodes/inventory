package saga.inventory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private Map<String, Boolean> inventoryStore = new HashMap<>();
    private boolean simulateFailure = false;

    @PostMapping("/reserve")
    public ResponseEntity<String> reserveInventory() {
        try {
            if (simulateFailure) {
                throw new RuntimeException("Simulated failure");
            }
            // Simulate inventory reservation
            inventoryStore.put("inventory123", true);
            return ResponseEntity.ok("Inventory reserved");

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Inventory reservation failed");
        }
    }

    @PostMapping("/compensate")
    public ResponseEntity<String> compensateInventory() {
        try {
            if (simulateFailure) {
                throw new RuntimeException("Simulated failure");
            }
            // Simulate inventory compensation
            inventoryStore.remove("inventory123");
            return ResponseEntity.ok("Inventory compensation successful");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Inventory compensation failed");
        }
    }

    @PostMapping("/simulateFailure")
    public void simulateFailure(@RequestParam boolean simulateFailure) {
        this.simulateFailure = simulateFailure;
    }
}