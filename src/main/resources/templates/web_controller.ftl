package ${packageName}.web.controller;

import com.github.pagehelper.PageInfo;
import ${packageName}.entity.${className}Entity;
import ${packageName}.service.${className}Service;
import ${packageName}.web.JsonResult;
import ${packageName}.web.form.${className}Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${className?uncap_first}")
public class ${className}Controller {

    @Autowired
    private ${className}Service ${className?uncap_first}Service;

    @RequestMapping("list")
    public JsonResult list() {
        PageInfo<${className}Entity> pageInfo = ${className?uncap_first}Service.getList();
        return new JsonResult(pageInfo.getList(), pageInfo.getTotal());
    }

    @RequestMapping(value = "get")
    public JsonResult get(@RequestParam ${keyType} id) {
        ${className?cap_first}Entity ${className?uncap_first}Entity = ${className?uncap_first}Service.get(id);
        return new JsonResult().put("${className?uncap_first}", new ${className?cap_first}Form(${className?uncap_first}Entity));
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public JsonResult save(@RequestBody ${className?cap_first}Form ${className?uncap_first}Form) {
        ${className?uncap_first}Service.save(${className?uncap_first}Form.toPrototype());
        return new JsonResult();
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public JsonResult delete(@RequestBody ${keyType} id) {
        ${className?uncap_first}Service.delete(id);
        return new JsonResult();
    }
}