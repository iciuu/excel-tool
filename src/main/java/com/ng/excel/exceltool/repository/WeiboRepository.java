package com.ng.excel.exceltool.repository;

import com.ng.excel.exceltool.model.User;
import com.ng.excel.exceltool.model.Weibo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WeiboRepository extends CrudRepository<Weibo,Integer> {

}
