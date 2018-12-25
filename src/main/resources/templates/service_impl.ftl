package ${packageName}.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ${packageName}.dao.${className}Dao;
import ${packageName}.entity.${className}Entity;
import ${packageName}.service.${className}Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ${className}ServiceImpl implements ${className}Service {

    private static final Logger LOG = LoggerFactory.getLogger(${className}ServiceImpl.class);

    @Autowired
    private ${className}Dao ${className?uncap_first}Dao;

    @Override
    public ${className}Entity get(${keyType} id) {
        return ${className?uncap_first}Dao.get(id);
    }

    @Override
    public ${className}Entity getById(${keyType} id) {
        return ${className?uncap_first}Dao.getById(id);
    }

    @Override
    public PageInfo<${className}Entity> getList() {
        return getList(1, 15);
    }

    @Override
    public PageInfo<${className}Entity> getList(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        return new PageInfo<>(${className?uncap_first}Dao.getList(new HashMap<>()));
    }

    @Override
    public PageInfo<${className}Entity> getList(${className?cap_first}Entity ${className?uncap_first}Entity, Integer page, Integer limit) {
        Map<String, Object> filter = new HashMap<>();
        <#list columnList as prop>
        filter.put("${prop.javaName}", ${className?uncap_first}Entity.get${prop.javaName?cap_first}());
        </#list>
        PageHelper.startPage(page, limit);
        return new PageInfo<>(${className?uncap_first}Dao.getList(filter));
    }

    @Override
    public void save(${className}Entity ${className?uncap_first}Entity) {
        if (${className?uncap_first}Entity.getId() == null || ${className?uncap_first}Entity.getId() < 0) {
            create(${className?uncap_first}Entity);
        } else {
            update(${className?uncap_first}Entity);
        }
    }

    @Override
    public void delete(${keyType} id) {
        ${className?uncap_first}Dao.delete(id);
    }

    @Override
    public void deleteById(${keyType} id) {
        ${className?uncap_first}Dao.deleteById(id);
    }

    private void create(${className}Entity ${className?uncap_first}Entity) {
        Date now = new Date();
        ${className?uncap_first}Entity.setCreatedAt(now.getTime());
        ${className?uncap_first}Entity.setUpdatedAt(now.getTime());
        ${className?uncap_first}Dao.insert(${className?uncap_first}Entity);
    }

    private void update(${className}Entity ${className?uncap_first}Entity) {
        ${className?uncap_first}Entity.setUpdatedAt(new Date().getTime());
        ${className?uncap_first}Dao.update(${className?uncap_first}Entity);
    }
}