package com.lvxing.travel_agency.dto;

import com.lvxing.travel_agency.entity.BranchRoute;
import com.lvxing.travel_agency.entity.Branchstore;
import com.lvxing.travel_agency.entity.Route;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class BranchDto extends Branchstore {
    private List<Route> routes =new ArrayList<>();


}
