package com.hand.todo.app.service;

import com.hand.todo.api.dto.WorkFlowDTO;
import org.hzero.boot.workflow.dto.RunTaskHistory;

import java.util.List;

public interface WorkFlowService {
    String startWorkFlow(Long organizationId, WorkFlowDTO workFlowDTO);
    String withdrawWorkFlow(Long organizationId, WorkFlowDTO workFlowDTO);
    List<RunTaskHistory> getApproveHistory(Long organizationId, WorkFlowDTO workFlowDTO);
}
