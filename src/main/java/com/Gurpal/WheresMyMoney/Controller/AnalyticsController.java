package com.Gurpal.WheresMyMoney.Controller;

import com.Gurpal.WheresMyMoney.Dto.AnalyticResponseDto;
import com.Gurpal.WheresMyMoney.Service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    AnalyticsService analyticsService;

    @Autowired
    public AnalyticsController(AnalyticsService analyticsService)
    {
        this.analyticsService =analyticsService;
    }

    @GetMapping
    public ResponseEntity<List<AnalyticResponseDto>> getAnalytics(Authentication authentication)
    {
        String userName = authentication.getName();
        return ResponseEntity.ok(analyticsService.getAnalysis(userName));
    }
}
