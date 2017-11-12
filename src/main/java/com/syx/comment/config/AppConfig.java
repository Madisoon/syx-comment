package com.syx.comment.config;

import com.fantasi.common.db.DBPool;
import com.fantasi.common.db.IDBPool;
import com.fantasi.common.db.dao.BaseDao;
import com.fantasi.common.db.dao.BaseDictDao;
import com.fantasi.common.db.dao.BaseTableDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class AppConfig {
    @Autowired
    private Environment env;
    @Autowired
    @Qualifier("dbPool")
    private IDBPool pool;

    @Bean
    public IDBPool dbPool() {

        DBPool pool = new DBPool();
        pool.init(env.getProperty("spring.datasource.driver-class-name"),
                env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
                env.getProperty("spring.datasource.password"));
        return pool;
    }

    public
    @Bean
    BaseTableDao tableDao() {
        return new BaseTableDao(pool);
    }

    public
    @Bean
    BaseDao baseDao() {
        return new BaseDao(pool);
    }

    public
    @Bean
    BaseDictDao baseDictDao() {
        return new BaseDictDao(pool, "guidance_task_main");
    }
}
