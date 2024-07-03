package com.example.admin_login_backend.controller;

import com.example.admin_login_backend.entity.DisabledSlot;
import com.example.admin_login_backend.service.DisabledSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/disabledSlots")
public class DisabledSlotController {

    @Autowired
    private DisabledSlotService disabledSlotService;

    @GetMapping
    public List<DisabledSlot> getAllDisabledSlots() throws ExecutionException, InterruptedException {
        return disabledSlotService.getAllDisabledSlots();
    }

    @PutMapping("/disable/{sala}/{date}/{timeSlot}")
    public ResponseEntity<String> updateDisabledSlot(
            @PathVariable String sala,
            @PathVariable String date,
            @PathVariable String timeSlot) {
        try {
            String result = disabledSlotService.updateDisabledSlot(sala, date, timeSlot);
            return ResponseEntity.ok(result);
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating disabled slot: " + e.getMessage());
        }
    }
}
