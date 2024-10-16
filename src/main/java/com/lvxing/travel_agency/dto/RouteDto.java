package com.lvxing.travel_agency.dto;

import com.lvxing.travel_agency.entity.Attractions;
import com.lvxing.travel_agency.entity.Route;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RouteDto extends Route {
    private List<Attractions> attractions = new ArrayList<>();

}
