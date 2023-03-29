package com.company.demodata.reactive.repository;

import com.company.demodata.reactive.model.Employee;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeRepository extends ReactiveMongoRepository<Employee, Long> {

    @Query("{ 'name': ?0 }")
    Flux<Employee> findByName(String name);
}
