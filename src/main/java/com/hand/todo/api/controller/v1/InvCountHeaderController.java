package com.hand.todo.api.controller.v1;

import com.hand.todo.api.dto.WorkInfoDTO;
import io.choerodon.core.domain.Page;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.mybatis.pagehelper.annotation.SortDefault;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.mybatis.pagehelper.domain.Sort;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.ApiOperation;
import org.hzero.core.base.BaseController;
import org.hzero.core.util.Results;
import org.hzero.mybatis.helper.SecurityTokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hand.todo.app.service.InvCountHeaderService;
import com.hand.todo.domain.entity.InvCountHeader;
import com.hand.todo.domain.repository.InvCountHeaderRepository;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * Inventory Count Header Table(InvCountHeader)表控制层
 *
 * @author Zamzam
 * @since 2024-11-28 11:14:35
 */

@RestController("invCountHeaderController.v1")
@RequestMapping("/v1/{organizationId}/inv-count-headers")
public class InvCountHeaderController extends BaseController {

    @Autowired
    private InvCountHeaderRepository invCountHeaderRepository;

    @Autowired
    private InvCountHeaderService invCountHeaderService;

    @ApiOperation(value = "Inventory Count Header Table List")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping
    public ResponseEntity<Page<InvCountHeader>> list(InvCountHeader invCountHeader, @PathVariable Long organizationId,
                                                     @ApiIgnore @SortDefault(value = InvCountHeader.FIELD_COUNT_HEADER_ID,
                                                             direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<InvCountHeader> list = invCountHeaderService.selectList(pageRequest, invCountHeader);
        return Results.success(list);
    }

    @ApiOperation(value = "Inventory Count Header Table Detail")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/{countHeaderId}/detail")
    public ResponseEntity<InvCountHeader> detail(@PathVariable Long countHeaderId) {
        InvCountHeader invCountHeader = invCountHeaderRepository.selectByPrimary(countHeaderId);
        return Results.success(invCountHeader);
    }

    @ApiOperation(value = "Create or Update Inventory Count Header Table")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping
    public ResponseEntity<?> save(@PathVariable Long organizationId, @RequestBody WorkInfoDTO invCountHeaders) {
//        validObject(invCountHeaders);
//        SecurityTokenHelper.validTokenIgnoreInsert(invCountHeaders);
//        invCountHeaders.forEach(item -> item.setTenantId(organizationId));
//        invCountHeaderService.saveData(invCountHeaders);
        invCountHeaderService.saveHeader(organizationId, invCountHeaders);
        return Results.success("Success");
    }

    @ApiOperation(value = "Remove Inventory Count Header Table")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @DeleteMapping
    public ResponseEntity<?> remove(@RequestBody List<InvCountHeader> invCountHeaders) {
        SecurityTokenHelper.validToken(invCountHeaders);
        invCountHeaderRepository.batchDeleteByPrimaryKey(invCountHeaders);
        return Results.success();
    }

}

