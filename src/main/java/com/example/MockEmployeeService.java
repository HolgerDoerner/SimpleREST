package com.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.example.bean.EmployeeBean;

public class MockEmployeeService implements UserService {
    private static ConcurrentHashMap<Integer, EmployeeBean> employeeList;
    
    static {
        try {
            employeeList = Files.lines(Paths.get("MOCK_DATA.csv"), StandardCharsets.UTF_8)
                .map(line -> line.split(","))
                .map(array -> new EmployeeBean(Integer.parseInt(array[0]),
                                                array[1],
                                                array[2],
                                                LocalDate.parse(array[3], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                                                array[4],
                                                array[5],
                                                array[6],
                                                array[7],
                                                array[8]))
                    .collect(Collectors.toConcurrentMap(e -> e.getId(), e -> e, (a, b) -> b, ConcurrentHashMap::new));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public List<EmployeeBean> getEmployees() {
        return employeeList.values().stream().collect(Collectors.toList());
    }

    @Override
    public EmployeeBean getEmployeeById(Integer id) {
        return employeeList.getOrDefault(id, new EmployeeBean());
    }

    @Override
    public EmployeeBean deleteEmployee(Integer id) {
        return employeeList.remove(id);
    }

    @Override
    public EmployeeBean updateEmployee(EmployeeBean employee) {
        if (!employeeList.containsKey(employee.getId())) {
            return null;
        }


        return null;
    }
}