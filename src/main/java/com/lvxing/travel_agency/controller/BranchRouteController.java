package com.lvxing.travel_agency.controller;


import com.lvxing.travel_agency.service.IBranchstoreService;
import com.lvxing.travel_agency.service.IRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2024-10-08
 */
@RestController
@RequestMapping("/branchRoute")
public class BranchRouteController {
    @Autowired
    private IBranchstoreService branchService;
    @Autowired
    private IRouteService routeService;

}
