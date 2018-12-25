package ${packageName}.service;

import com.github.pagehelper.PageInfo;
import ${packageName}.entity.${className}Entity;

public interface ${className}Service {

    ${className}Entity get(${keyType} id);

    ${className}Entity getById(${keyType} id);

    PageInfo<${className}Entity> getList();

    PageInfo<${className}Entity> getList(Integer page, Integer limit);

    PageInfo<${className}Entity> getList(${className}Entity ${className?uncap_first}Entity, Integer page, Integer limit);

    void save(${className}Entity ${className?uncap_first}Entity);

    void delete(${keyType} id);

    void deleteById(${keyType} id);
}