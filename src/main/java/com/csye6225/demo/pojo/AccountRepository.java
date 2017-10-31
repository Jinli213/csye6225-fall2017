package com.csye6225.demo.pojo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {


}
