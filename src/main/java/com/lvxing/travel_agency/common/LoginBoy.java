package com.lvxing.travel_agency.common;

import com.lvxing.travel_agency.entity.Employee;
import com.lvxing.travel_agency.entity.EmployeeAll;
import lombok.Data;

@Data
public class LoginBoy {
    EmployeeAll employeeAll;
    Employee employee;
    String token;
}
