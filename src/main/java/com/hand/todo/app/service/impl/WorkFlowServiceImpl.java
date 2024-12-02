package com.hand.todo.app.service.impl;

import com.hand.todo.api.dto.WorkFlowDTO;
import com.hand.todo.api.dto.WorkInfoDTO;
import com.hand.todo.app.service.InvCountHeaderService;
import com.hand.todo.app.service.WorkFlowService;
import com.hand.todo.domain.entity.InvCountHeader;
import com.hand.todo.domain.repository.InvCountHeaderRepository;
import io.choerodon.core.exception.CommonException;
import org.hzero.boot.workflow.WorkflowClient;
import org.hzero.boot.workflow.dto.RunInstance;
import org.hzero.boot.workflow.dto.RunTaskHistory;
import org.jsoup.select.Evaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class WorkFlowServiceImpl implements WorkFlowService {
    @Autowired
    private WorkflowClient workflowClient;

    @Autowired
    InvCountHeaderService invCountHeaderService;

    @Autowired
    InvCountHeaderRepository invCountHeaderRepository;

    @Override
    public String startWorkFlow(Long organizationId, WorkFlowDTO workFlowDTO) {
        //check document name
        InvCountHeader countHeader = invCountHeaderRepository.getId(workFlowDTO.getBussinessKey());

        // Check if the count header ID is null, indicating the business key was not found
        if (countHeader == null || countHeader.getCountHeaderId() == null) {
            throw new CommonException("demo-47835.workflow_not_found", workFlowDTO.getBussinessKey());
        }
        RunInstance workFlowStart = workflowClient.startInstanceByFlowKey(organizationId,workFlowDTO.getFlowKey(),workFlowDTO.getBussinessKey(),workFlowDTO.getDimension(),workFlowDTO.getStarter(),workFlowDTO.getVariableMap());
//        WorkInfoDTO workInfoDTO = new WorkInfoDTO();
//        workInfoDTO.setWorkFlowId(workFlowStart.getDeploymentId());
//        workInfoDTO.setCountNumber(workFlowStart.getBusinessKey());
//        workInfoDTO.setCountStatus("DRA");
//        invCountHeaderService.saveHeader(organizationId,workInfoDTO);
        return workFlowStart.getStatus();
    }

    @Override
    public String withdrawWorkFlow(Long organizationId, WorkFlowDTO workFlowDTO) {
        workflowClient.flowWithdrawFlowKey(organizationId,workFlowDTO.getFlowKey(),workFlowDTO.getBussinessKey());
        return "withdraw success";
    }

    @Override
    public List<RunTaskHistory> getApproveHistory(Long organizationId, WorkFlowDTO workFlowDTO) {
        return workflowClient.approveHistoryByFlowKey(organizationId, workFlowDTO.getFlowKey(), workFlowDTO.getBussinessKey());
    }

}
