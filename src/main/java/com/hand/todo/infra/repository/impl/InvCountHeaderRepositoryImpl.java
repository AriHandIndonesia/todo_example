package com.hand.todo.infra.repository.impl;

import org.apache.commons.collections.CollectionUtils;
import org.hzero.mybatis.base.impl.BaseRepositoryImpl;
import org.springframework.stereotype.Component;
import com.hand.todo.domain.entity.InvCountHeader;
import com.hand.todo.domain.repository.InvCountHeaderRepository;
import com.hand.todo.infra.mapper.InvCountHeaderMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * Inventory Count Header Table(InvCountHeader)资源库
 *
 * @author Zamzam
 * @since 2024-11-28 14:45:54
 */
@Component
public class InvCountHeaderRepositoryImpl extends BaseRepositoryImpl<InvCountHeader> implements InvCountHeaderRepository {
    @Resource
    private InvCountHeaderMapper invCountHeaderMapper;

    @Override
    public List<InvCountHeader> selectList(InvCountHeader invCountHeader) {
        return invCountHeaderMapper.selectList(invCountHeader);
    }

    @Override
    public InvCountHeader selectByPrimary(Long countHeaderId) {
        InvCountHeader invCountHeader = new InvCountHeader();
        invCountHeader.setCountHeaderId(countHeaderId);
        List<InvCountHeader> invCountHeaders = invCountHeaderMapper.selectList(invCountHeader);
        if (invCountHeaders.size() == 0) {
            return null;
        }
        return invCountHeaders.get(0);
    }

    @Override
    public InvCountHeader getId(String count_number) {
        return invCountHeaderMapper.getId(count_number);
    }

}

