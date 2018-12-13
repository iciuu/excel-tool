package com.ng.excel.exceltool.repository;

import com.ng.excel.exceltool.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Integer> {

    List<User> findByUserId(int userId);
}
