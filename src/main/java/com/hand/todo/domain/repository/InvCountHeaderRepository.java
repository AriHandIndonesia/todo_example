package com.hand.todo.domain.repository;

import com.hand.todo.api.dto.WorkInfoDTO;
import org.hzero.mybatis.base.BaseRepository;
import com.hand.todo.domain.entity.InvCountHeader;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Inventory Count Header Table(InvCountHeader)资源库
 *
 * @author Zamzam
 * @since 2024-11-28 11:14:34
 */
@Repository
public interface InvCountHeaderRepository extends BaseRepository<InvCountHeader> {
    /**
     * 查询
     *
     * @param invCountHeader 查询条件
     * @return 返回值
     */
    List<InvCountHeader> selectList(InvCountHeader invCountHeader);

    /**
     * 根据主键查询（可关联表）
     *
     * @param countHeaderId 主键
     * @return 返回值
     */
    InvCountHeader selectByPrimary(Long countHeaderId);
    InvCountHeader getId(String count_number);
}
