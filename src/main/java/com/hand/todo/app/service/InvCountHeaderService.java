package com.hand.todo.app.service;

import com.hand.todo.api.dto.WorkInfoDTO;
import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import com.hand.todo.domain.entity.InvCountHeader;

import java.util.List;

/**
 * Inventory Count Header Table(InvCountHeader)应用服务
 *
 * @author Zamzam
 * @since 2024-11-28 11:14:35
 */
public interface InvCountHeaderService {

    /**
     * 查询数据
     *
     * @param pageRequest     分页参数
     * @param invCountHeaders 查询条件
     * @return 返回值
     */
    Page<InvCountHeader> selectList(PageRequest pageRequest, InvCountHeader invCountHeaders);

    /**
     * 保存数据
     *
     * @param invCountHeaders 数据
     */
    void saveData(List<InvCountHeader> invCountHeaders);
    void saveHeader(Long organizationId, WorkInfoDTO workInfoDTO);

}

