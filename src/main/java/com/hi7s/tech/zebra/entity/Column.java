package com.hi7s.tech.zebra.entity;

import com.hi7s.tech.zebra.util.NamingUtils;
import lombok.Data;

@Data
public class Column {

    private String name;
    private String isKey;
    private String notNull;
    private String type;
    private Long length;
    private String comment;

    public String getJavaName() {
        return NamingUtils.toCamelCase(name);
    }

    public String getJavaType() {
        switch (type) {
            case "tinyint":
                return "Byte";
            case "smallint":
                return "Short";
            case "int":
                return "Integer";
            case "bigint":
                return "Long";
            case "decimal":
                return "Float";
            case "longtext":
            case "text":
            case "varchar":
                return "String";
            case "datetime":
            case "timestamp":
                return "Date";
            case "bit":
                return "Boolean";
            default:
                return "__" + type;
        }
    }
}