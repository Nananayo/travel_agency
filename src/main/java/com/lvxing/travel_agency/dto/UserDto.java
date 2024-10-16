package com.lvxing.travel_agency.dto;

import com.lvxing.travel_agency.entity.Orders;
import com.lvxing.travel_agency.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDto extends User {
    private List<Orders> orders = new ArrayList<>();
}
