package com.hi7s.tech.zebra.service;

import com.hi7s.tech.zebra.dao.ColumnMapper;
import com.hi7s.tech.zebra.dao.TableMapper;
import com.hi7s.tech.zebra.entity.Column;
import com.hi7s.tech.zebra.entity.Table;
import com.hi7s.tech.zebra.util.NamingUtils;
import com.hi7s.tech.zebra.util.PathUtils;
import freemarker.template.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class RobotService {

    @Value("${source.name}")
    private String dbName;

    @Value("${source.table.list}")
    private String tableList;

    @Value("${source.table.prefix}")
    private String tablePrefix;

    @Value("${source.primary.key.type}")
    private String keyType;

    @Value("${target.name}")
    private String projectName;

    @Value("${target.project.package.name}")
    private String packageName;

    @Value("${target.project.module.contract.name}")
    private String contractName;

    @Value("${target.project.module.name}")
    private String moduleName;

    @Autowired
    private TableMapper tableMapper;
    @Autowired
    private ColumnMapper columnMapper;

    public void generate() {
        if (StringUtils.isNotEmpty(tableList)) {
            Arrays.asList(tableList.split("\\W")).forEach(this::generate);
            return;
        }
        // generate all table from database
        getTables().stream().map(Table::getName).forEach(this::generate);
    }

    private void generate(String tableName) {
        log.info("====: {} :====", tableName);
        // prepare
        Map<String, Object> root = new HashMap<>();
        root.put("tableName", tableName);
        root.put("keyType", keyType);
        root.put("columnList", getColumns(tableName));
        root.put("packageName", packageName);
        root.put("className", NamingUtils.toTitleCase(tableName.replaceFirst(tablePrefix, "")));
        // generate data
        generateEntity(root);
        generateDao(root);
        generateMapper(root);
        // generate service
        generateService(root);
        generateServiceImpl(root);
        // generate web
        generateForm(root);
        generateVo(root);
        generateController(root);
    }

    private List<Table> getTables() {
        return tableMapper.getList(new HashMap<String, Object>() {{
            this.put("dbName", dbName);
        }});
    }

    private List<Column> getColumns(String tableName) {
        return columnMapper.getList(new HashMap<String, Object>() {{
            this.put("dbName", dbName);
            this.put("tableName", tableName);
        }});
    }

    private void generateController(Map<String, Object> root) {
        String fileDirectory = PathUtils.toHome(moduleName, "web", "controller");
        forceMkdir(fileDirectory);
        String fileName = String.format("%sController.java", root.get("className"));
        String filePath = PathUtils.merge(fileDirectory, fileName);
        render("web_controller.ftl", root, filePath);
    }

    private void generateServiceImpl(Map<String, Object> root) {
        String fileDirectory = PathUtils.toHome(moduleName, "service", "impl");
        forceMkdir(fileDirectory);
        String fileName = String.format("%sServiceImpl.java", root.get("className"));
        String filePath = PathUtils.merge(fileDirectory, fileName);
        render("service_impl.ftl", root, filePath);
    }

    private void generateDao(Map<String, Object> root) {
        String fileDirectory = PathUtils.toHome(moduleName, "dao");
        forceMkdir(fileDirectory);
        String fileName = String.format("%sMapper.java", root.get("className"));
        String filePath = PathUtils.merge(fileDirectory, fileName);
        render("dao.ftl", root, filePath);
    }

    private void generateForm(Map<String, Object> root) {
        String fileDirectory = PathUtils.toHome(moduleName, "web", "form");
        forceMkdir(fileDirectory);
        String fileName = String.format("%sForm.java", root.get("className"));
        String filePath = PathUtils.merge(fileDirectory, fileName);
        render("contract_form.ftl", root, filePath);
    }

    private void generateEntity(Map<String, Object> root) {
        String fileDirectory = PathUtils.toHome(moduleName, "entity");
        forceMkdir(fileDirectory);
        String fileName = String.format("%sEntity.java", root.get("className"));
        String filePath = PathUtils.merge(fileDirectory, fileName);
        render("contract_entity.ftl", root, filePath);
    }

    private void generateVo(Map<String, Object> root) {
        String fileDirectory = PathUtils.toHome(moduleName, "web", "vo");
        forceMkdir(fileDirectory);
        String fileName = String.format("%sVo.java", root.get("className"));
        String filePath = PathUtils.merge(fileDirectory, fileName);
        render("contract_vo.ftl", root, filePath);
    }

    private void generateService(Map<String, Object> root) {
        String fileDirectory = PathUtils.toHome(moduleName, "service");
        forceMkdir(fileDirectory);
        String fileName = String.format("%sService.java", root.get("className"));
        String filePath = PathUtils.merge(fileDirectory, fileName);
        render("contract_service.ftl", root, filePath);
    }

    private void generateMapper(Map<String, Object> root) {
        String fileDirectory = PathUtils.toHome(moduleName, "mybatis", "mapper");
        forceMkdir(fileDirectory);
        String fileName = String.format("%sMapper.xml", root.get("className"));
        String filePath = PathUtils.merge(fileDirectory, fileName);
        render("mybatis_mapper.ftl", root, filePath);
    }

    private void forceMkdir(String fileDirectory) {
        try {
            FileUtils.forceMkdir(new File(fileDirectory));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void render(String name, Map<String, Object> root, String filePath) {
        log.info("Done: {}", filePath);
        Writer writer;
        try {
            writer = new FileWriter(filePath);
        } catch (IOException e) {
            log.error("invalid target path", e);
            return;
        }
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        URL templateUrl = getClass().getClassLoader().getResource("templates");
        Assert.notNull(templateUrl, "there is no available template");
        try {
            cfg.setDirectoryForTemplateLoading(new File(templateUrl.getPath()));
        } catch (IOException e) {
            log.error("invalid template path", e);
            return;
        }
        cfg.setObjectWrapper(new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_23).build());
        cfg.setDefaultEncoding("UTF-8");
        cfg.setOutputEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        Template template;
        try {
            template = cfg.getTemplate(name);
        } catch (IOException e) {
            log.error("invalid template file", e);
            return;
        }
        try {
            template.process(root, writer);
        } catch (TemplateException | IOException e) {
            log.error("invalid template file", e);
            return;
        }
        try {
            writer.close();
        } catch (IOException e) {
            log.error("invalid writer state", e);
        }
    }
}