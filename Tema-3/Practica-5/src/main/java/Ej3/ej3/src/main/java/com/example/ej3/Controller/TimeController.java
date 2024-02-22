package com.example.ej3.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class TimeController {

    private LocalDate currentDate = LocalDate.now();

    @GetMapping("/date")
    public LocalDate getCurrentDate() {
        return currentDate;
    }

    @GetMapping("/date/{n}")
    public LocalDate getFutureDate(@PathVariable int n) {
        return currentDate.plusDays(n);
    }

    @PostMapping("/{date}")
    public void changeDate(@PathVariable Date date) {
        currentDate = LocalDate.of(date.getYear(), date.getMonth(), date.getDay());
    }
}
