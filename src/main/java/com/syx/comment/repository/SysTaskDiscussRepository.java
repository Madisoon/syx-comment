package com.syx.comment.repository;

import com.syx.comment.entity.SysTaskDiscuss;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 描述:
 * 评论的repository
 *
 * @author Msater Zg
 * @create 2018-02-07 13:14
 */
public interface SysTaskDiscussRepository extends JpaRepository<SysTaskDiscuss, Long> {
    /**
     * 根据id获取所有的评论
     *
     * @param taskId
     * @return
     */
    List<SysTaskDiscuss> findSysTaskDiscussByTaskId(Long taskId);
}
