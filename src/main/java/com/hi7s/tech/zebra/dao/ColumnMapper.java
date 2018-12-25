package com.hi7s.tech.zebra.dao;

import com.hi7s.tech.zebra.entity.Column;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ColumnMapper {
    List<Column> getList(Map<String, Object> filter);
}