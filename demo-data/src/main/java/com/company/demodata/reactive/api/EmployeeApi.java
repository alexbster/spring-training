package com.company.demodata.reactive.api;

import com.company.demodata.reactive.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.company.demodata.reactive.service.EmployeeService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/api/employees")
public class EmployeeApi {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Mono<Employee>> create(@RequestBody Employee employee) {
        var result = employeeService.create(employee);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Employee>> findById(@PathVariable int id)
    {
        var result = employeeService.findById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/search")
    public ResponseEntity<Flux<Employee>> findByName(@RequestParam String name)
    {
        var result = employeeService.findByName(name);
        return ResponseEntity.ok(result);
    }

    /**
     * This method is used to return a stream of data to the client.
     * @return
     */
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    //@ResponseStatus(HttpStatus.OK)
    public Flux<Employee> findAll()
    {
        return employeeService.findAll();
    }

    @PutMapping
    public ResponseEntity<Mono<Employee>> update(@RequestBody Employee employee)
    {
        var result =  employeeService.update(employee);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id)
    {
        employeeService.delete(id);
    }

}
