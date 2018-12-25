package ${packageName}.dao;

import ${packageName}.entity.${className}Entity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ${className}Dao {

    ${className}Entity get(@Param("id") ${keyType} id);

    ${className}Entity getById(@Param("id") ${keyType} id);

    List<${className}Entity> getList(Map<String, Object> filter);

    void insert(${className}Entity ${className?uncap_first}Entity);

    void update(${className}Entity ${className?uncap_first}Entity);

    void delete(@Param("id") ${keyType} id);

    void deleteById(@Param("id") ${keyType} id);
}