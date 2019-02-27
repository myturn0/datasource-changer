package com.apollo;

import com.apollo.dao.mysql.ProvinceDao;
import com.apollo.dao.pg.WeekDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：apollo
 * @since ：Created in 2019/2/27
 */
@SpringBootApplication
@RestController
public class MultiDataSourceApplication {

    @Autowired
    private ProvinceDao provinceDao;

    @Autowired
    private WeekDao weekDao;

    public static void main(String[] args) {
        SpringApplication.run(MultiDataSourceApplication.class, args);
    }

    /**
     * 用于测试mysql数据
     * @return
     */
    @GetMapping("/province")
    public Map<String, Object> testMySQL() {
        Integer counter = provinceDao.getCount();
        Map<String, Object> result = new HashMap<>();
        result.put("result", counter);
        return result;
    }

    /**
     * 用于测试postgresql数据源
     * @return
     */
    @GetMapping("/week")
    public Map<String, Object> testPG() {
        Integer counter = weekDao.getCount();
        Map<String, Object> result = new HashMap<>();
        result.put("result", counter);
        return result;
    }
}
