package com.ng.excel.exceltool.repository;

import com.ng.excel.exceltool.model.Comments;
import com.ng.excel.exceltool.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentsRepository extends CrudRepository<Comments,Integer> {

    List<Comments> findByWeiboId(int weiboId);
}
