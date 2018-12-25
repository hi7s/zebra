package ${packageName}.web.form;

import ${packageName}.entity.${className}Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ${className}Form {

    <#list columnList as prop>
    private ${prop.javaType} ${prop.javaName};
    </#list>
    <#list columnList as prop>

    public ${prop.javaType} get${prop.javaName?cap_first}() {
        return ${prop.javaName};
    }

    public void set${prop.javaName?cap_first}(${prop.javaType} ${prop.javaName}) {
        this.${prop.javaName} = ${prop.javaName};
    }
    </#list>

    public ${className}Form() {

    }

    public ${className}Form(${className}Entity ${className?uncap_first}Entity) {
        <#list columnList as prop>
        this.${prop.javaName?uncap_first} = ${className?uncap_first}Entity.get${prop.javaName?cap_first}();
        </#list>
    }

    public ${className}Entity toPrototype() {
        ${className}Entity ${className?uncap_first}Entity = new ${className}Entity();
        <#list columnList as prop>
        ${className?uncap_first}Entity.set${prop.javaName?cap_first}(this.${prop.javaName?uncap_first});
        </#list>
        return ${className?uncap_first}Entity;
    }
}