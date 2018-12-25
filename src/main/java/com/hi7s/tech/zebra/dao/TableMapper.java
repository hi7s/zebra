package com.hi7s.tech.zebra.dao;

import com.hi7s.tech.zebra.entity.Table;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TableMapper {

    List<Table> getList(Map<String, Object> filter);
}