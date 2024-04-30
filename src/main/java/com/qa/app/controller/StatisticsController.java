package com.qa.app.controller;

import com.qa.app.dao.response.UserDao;
import com.qa.app.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/add/coin/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserDao> addCoin(@PathVariable Long id) {
        return ResponseEntity.ok(statisticsService.addCoin(id));
    }

    @GetMapping("/add/refill/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserDao> addRefill(@PathVariable Long id) {
        return ResponseEntity.ok(statisticsService.addRefill(id));
    }

    @GetMapping("/add/victory/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserDao> addVictory(@PathVariable Long id) {
        return ResponseEntity.ok(statisticsService.addVictory(id));
    }

    @GetMapping("/add/bug/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserDao> addBug(@PathVariable Long id) {
        return ResponseEntity.ok(statisticsService.addBug(id));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserDao> getStatistics(@PathVariable Long id) {
        return ResponseEntity.ok(statisticsService.getStatistics(id));
    }
}
