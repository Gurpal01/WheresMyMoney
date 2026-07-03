package com.Gurpal.WheresMyMoney.Controller;

import com.Gurpal.WheresMyMoney.Dto.DashboardResponseDto;
import com.Gurpal.WheresMyMoney.Service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashBoardController {

    DashboardService dashboardService;

    @Autowired
    public DashBoardController(DashboardService dashboardService)
    {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public ResponseEntity<DashboardResponseDto> getDashBoard(Authentication authentication)
    {
        String userName = authentication.getName();
        return ResponseEntity.ok(dashboardService.getDashBoard(userName));
    }
}
