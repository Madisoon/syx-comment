package com.syx.comment.module.other.service;

/**
 * 描述:
 * 导出的接口类
 *
 * @author Msater Zg
 * @create 2018-02-26 20:22
 */
public interface SysExportService {
    /**
     * 导出三个图表
     *
     * @param packetNo
     * @param startTime
     * @param endTime
     * @param type
     * @param path
     * @return
     */
    String rankDataExport(String packetNo, String startTime, String endTime, String type, String path);


    /**
     * 导出已审核的任务
     *
     * @param userAccount
     * @param searchData
     * @param taskType
     * @param taskStatus
     * @param depNo
     * @param filePath
     * @return
     */
    String taskDataExport(String userAccount, String searchData, String taskType,
                          String taskStatus, String depNo, String filePath);
}
