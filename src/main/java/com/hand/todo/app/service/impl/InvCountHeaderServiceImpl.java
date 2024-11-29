package com.hand.todo.app.service.impl;

import com.hand.todo.api.dto.WorkInfoDTO;
import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.PageHelper;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hzero.boot.workflow.WorkflowClient;
import org.hzero.common.HZeroService;
import org.springframework.beans.factory.annotation.Autowired;
import com.hand.todo.app.service.InvCountHeaderService;
import org.springframework.stereotype.Service;
import com.hand.todo.domain.entity.InvCountHeader;
import com.hand.todo.domain.repository.InvCountHeaderRepository;

import javax.xml.crypto.Data;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Inventory Count Header Table(InvCountHeader)应用服务
 *
 * @author Zamzam
 * @since 2024-11-28 11:14:35
 */
@Service
public class InvCountHeaderServiceImpl implements InvCountHeaderService {
    @Autowired
    private InvCountHeaderRepository invCountHeaderRepository;

    @Autowired
    private WorkflowClient workflowClient;


    @Override
    public Page<InvCountHeader> selectList(PageRequest pageRequest, InvCountHeader invCountHeader) {
        return PageHelper.doPageAndSort(pageRequest, () -> invCountHeaderRepository.selectList(invCountHeader));
    }

    @Override
    public void saveData(List<InvCountHeader> invCountHeaders) {
        List<InvCountHeader> insertList = invCountHeaders.stream().filter(line -> line.getCountHeaderId() == null).collect(Collectors.toList());
        List<InvCountHeader> updateList = invCountHeaders.stream().filter(line -> line.getCountHeaderId() != null).collect(Collectors.toList());
        invCountHeaderRepository.batchInsertSelective(insertList);
        invCountHeaderRepository.batchUpdateByPrimaryKeySelective(updateList);
    }

    @Override
    public void saveHeader(Long organizationId, WorkInfoDTO workInfoDTO) {
        InvCountHeader invCountHeader = new InvCountHeader();
        invCountHeader.setTenantId(organizationId);
        invCountHeader.setCountNumber(workInfoDTO.getCountNumber());
        invCountHeader.setCountStatus(workInfoDTO.getCountStatus());
        invCountHeader.setWorkflowId(workInfoDTO.getWorkFlowId());
        InvCountHeader isInserted = invCountHeaderRepository.getId(workInfoDTO.getCountNumber());
        if (!Objects.isNull(isInserted)){
            isInserted.setTenantId(organizationId);
            isInserted.setCountNumber(workInfoDTO.getCountNumber());
            isInserted.setCountStatus(workInfoDTO.getCountStatus());
            isInserted.setWorkflowId(workInfoDTO.getWorkFlowId());
            isInserted.setApprovedTime(Date.from(Instant.now()));
            invCountHeaderRepository.updateByPrimaryKey(isInserted);
        }else {
            invCountHeaderRepository.insertSelective(invCountHeader);
        }

//        workflowClient.startInstanceByFlowKey(Long tenantId, String flowKey, String businessKey, String dimension, String starter, Map<String, Object> variableMap);
    }
}

