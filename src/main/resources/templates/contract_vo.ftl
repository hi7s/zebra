package ${packageName}.web.vo;

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
public class ${className}Vo {

    <#list columnList as prop>
    /**
     * ${prop.comment}
     */
    private ${prop.javaType} ${prop.javaName};
    </#list>

    public ${className}Vo(${className}Entity ${className?uncap_first}Entity) {
        <#list columnList as prop>
        this.${prop.javaName?uncap_first} = ${className?uncap_first}Entity.get${prop.javaName?cap_first}();
        </#list>
    }
}