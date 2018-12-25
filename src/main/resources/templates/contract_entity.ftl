package ${packageName}.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ${className}Entity implements Serializable {

    <#list columnList as prop>
    /**
     * ${prop.comment}
     */
    private ${prop.javaType} ${prop.javaName};
    </#list>
}