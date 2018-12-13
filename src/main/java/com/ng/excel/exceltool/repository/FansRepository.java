package com.ng.excel.exceltool.repository;

import com.ng.excel.exceltool.model.Fans;
import com.ng.excel.exceltool.model.User;
import org.springframework.data.mapping.model.FieldNamingStrategy;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FansRepository extends CrudRepository<Fans,Integer> {

    List<Fans> findByWeiboId(int weiboId);
}
