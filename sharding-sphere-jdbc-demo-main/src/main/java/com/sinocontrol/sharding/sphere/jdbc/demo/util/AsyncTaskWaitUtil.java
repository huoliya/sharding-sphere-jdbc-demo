package com.sinocontrol.sharding.sphere.jdbc.demo.util;

import java.util.List;
import java.util.concurrent.Future;

public class AsyncTaskWaitUtil {
    /*等待循环内的任务执行完成*/
    public static void waitAllTasks(List<Future> futureList) {
        while (true) {
            if (null != futureList) {
                boolean isAllDone = true;
                for (Future future : futureList) {
                    if (null == future || !future.isDone()) {
                        isAllDone = false;
                    }
                }
                if (isAllDone) {
                    break;
                }
            }
        }
    }
}
