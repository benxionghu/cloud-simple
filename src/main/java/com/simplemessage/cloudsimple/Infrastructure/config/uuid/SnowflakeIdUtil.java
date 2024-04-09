package com.simplemessage.cloudsimple.Infrastructure.config.uuid;

import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

/**
 * 雪花Id
 *
 * @Author: benxiong.hu
 * @CreateAt: 2023/03/15 21:40:33
 * @ModifyAt: 2023/03/15 21:40:33
 * @Version 1.0
 */
@Slf4j
public class SnowflakeIdUtil {

    final static short method = 1;

    @PostConstruct
    public void initSnowflake() {
        log.info("init SnowflakeId");
        IdGeneratorOptions options = new IdGeneratorOptions();
        options.WorkerIdBitLength = 6;
        options.SeqBitLength = 6;
        options.Method = method;
        options.WorkerId = 1;
        YitIdHelper.setIdGenerator(options);
    }
}
