package com.hand.todo.api.controller.v1;

import com.hand.todo.api.dto.WorkFlowDTO;
import com.hand.todo.app.service.WorkFlowService;
import com.netflix.servo.tag.Tags;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Tag;
import org.hzero.boot.workflow.dto.RunTaskHistory;
import org.hzero.core.base.BaseController;
import org.hzero.core.util.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Inventory Count Header Table(InvCountHeader)表控制层
 *
 * @author Zamzam
 * @since 2024-11-28 11:14:35
 */

@Api(tags = "workflow")
@RestController("WorkFlowController.v1")
@RequestMapping("/v1/{organizationId}/work-flow")
public class WorkFlowController extends BaseController {
    @Autowired
    private WorkFlowService workFlowService;

    @ApiOperation(value = "start work flow")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping("/start")
    public ResponseEntity<?> start(@PathVariable Long organizationId, @RequestBody WorkFlowDTO workFlowDTO){
        workFlowService.startWorkFlow(organizationId,workFlowDTO);
        return Results.success("Started");
    }

    @ApiOperation(value = "withdraw work flow")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@PathVariable Long organizationId, @RequestBody WorkFlowDTO workFlowDTO){
        workFlowService.withdrawWorkFlow(organizationId,workFlowDTO);
        return Results.success("Withdrawed");
    }

    @ApiOperation(value = "workflow approve history")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping("/approve-history")
    public ResponseEntity<List<RunTaskHistory>> history(@PathVariable Long organizationId, @RequestBody WorkFlowDTO workFlowDTO){
        return Results.success(workFlowService.getApproveHistory(organizationId,workFlowDTO));
    }
}
