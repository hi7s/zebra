<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${packageName}.dao.${className}Dao">
    <resultMap id="defaultMap" type="${packageName}.entity.${className}Entity">
        <#list columnList as prop>
        <result column="${prop.name}" property="${prop.javaName}"/>
        </#list>
    </resultMap>

    <select id="get" parameterType="${keyType}" resultMap="defaultMap">
        select
          <#list columnList as prop>
          `${prop.name}`<#if prop_has_next>,</#if>
          </#list>
        from
          `${tableName}`
        where
          `deleted_at` is null and `id` = ${r'#'}{id}
    </select>

    <select id="getById" parameterType="${keyType}" resultMap="defaultMap">
        select
          <#list columnList as prop>
          `${prop.name}`<#if prop_has_next>,</#if>
          </#list>
        from
          `${tableName}`
        where
          `deleted_at` is null and `id` = ${r'#'}{id}
    </select>

    <select id="getList" resultMap="defaultMap">
        select
          <#list columnList as prop>
          `${prop.name}`<#if prop_has_next>,</#if>
          </#list>
        from
          `${tableName}`
        where
          `deleted_at` is null
          <#list columnList as prop>
          <if test="${prop.javaName} != null">and `${prop.name}` = ${r'#{'}${prop.javaName}${r'}'}</if>
          </#list>
        order by
          `updated_at` desc, `id` asc
    </select>

    <insert id="insert" parameterType="${packageName}.entity.${className}Entity" useGeneratedKeys="true" keyProperty="id">
        insert into `${tableName}` (
          <#list columnList as prop>
          `${prop.name}`<#if prop_has_next>,</#if>
          </#list>
        ) values (
          <#list columnList as prop>
          ${r'#{'}${prop.javaName}${r'}'}<#if prop_has_next>,</#if>
          </#list>
        )
    </insert>

    <update id="update" parameterType="${packageName}.entity.${className}Entity">
        update
          `${tableName}`
        set
          <#list columnList as prop>
          `${prop.name}` = ${r'#{'}${prop.javaName}${r'}'}<#if prop_has_next>,</#if>
          </#list>
        where
          `id` = ${r'#{'}id${r'}'}
    </update>

    <delete id="delete" parameterType="${keyType}">
        update
          `${tableName}`
        set
          `deleted_at` = unix_timestamp(now()) * 1000
        where
          `id` = ${r'#{'}id${r'}'}
    </delete>

    <delete id="deleteById" parameterType="${keyType}">
        update
          `${tableName}`
        set
          `deleted_at` = unix_timestamp(now()) * 1000
        where
          `id` = ${r'#{'}id${r'}'}
    </delete>
</mapper>