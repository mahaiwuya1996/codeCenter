package com.config.server.service;

import com.config.server.pojo.ReqLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: LiWei
 * @date: 2020/10/23 16:45
 */
@Slf4j
@Service
public class LogService {

    @Async("logExecutor")
    public void saveLog(ReqLog requestLog) throws InterruptedException {
        // 模拟入库需要的时间
        Thread.sleep(2000);
        log.info("请求日志保存成功:{}",requestLog);
    }


}
