package com.company.demodata.reactive.service;

import com.company.demodata.reactive.model.Employee;
import com.company.demodata.reactive.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Mono<Employee> create(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Mono<Employee> findById(long id)
    {
        return employeeRepository.findById(id);
    }

    public Flux<Employee> findByName(String name)
    {
        return employeeRepository.findByName(name);
    }

    public Flux<Employee> findAll()
    {
        return employeeRepository.findAll();
    }

    public Mono<Employee> update(Employee employee)
    {
        return employeeRepository.save(employee);
    }

    public Mono<Void> delete(long id)
    {
        return employeeRepository.deleteById(id);
    }
}
