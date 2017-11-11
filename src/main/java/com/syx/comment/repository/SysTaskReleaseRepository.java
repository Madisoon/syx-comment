package com.syx.comment.repository;

import com.syx.comment.entity.SysTaskRelease;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述:
 * 任务发布的reposity
 *
 * @author Msater Zg
 * @create 2017-11-11 10:57
 */
public interface SysTaskReleaseRepository extends JpaRepository<SysTaskRelease, Long> {
}
