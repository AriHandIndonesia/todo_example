package com.hand.todo.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.choerodon.mybatis.annotation.ModifyAudit;
import io.choerodon.mybatis.annotation.VersionAudit;
import io.choerodon.mybatis.domain.AuditDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.hzero.export.annotation.ExcelColumn;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@ApiModel("任务信息")
@ModifyAudit
@VersionAudit
@Table(name = "todo_task")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Task extends AuditDomain {
    public static final String FIELD_ID = "id";
    public static final String FIELD_EMPLOYEE_ID = "employeeId";
    public static final String FIELD_STATE = "state";
    public static final String FIELD_TASK_DESCRIPTION = "taskDescription";

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "用户ID不能为空")
    @ApiModelProperty("用户ID")
    private Long EmployeeId;

    @ApiModelProperty("任务状态")
    @ExcelColumn(en = "State")
    private String state;

    @ApiModelProperty("任务编号")
    @ExcelColumn(en = "Task Number")
    private String taskNumber;

    @Length(max = 240)
    @ApiModelProperty("任务描述")
    @ExcelColumn(en = "Task Description")
    private String taskDescription;

    @NotNull
    @ApiModelProperty("租户ID")
    private Long tenantId;

    @Transient
    @ApiModelProperty("员工编号")
    private String employeeNumber;

    @Transient
    @ApiModelProperty("员工姓名")
    private String employeeName;

    @ApiModelProperty("taskType")
    @ExcelColumn(en = "Task Type")
    private String taskType;

    /**
     * Generate a task number
     */

    public void generateTaskNumber(){
        this.taskNumber = UUID.randomUUID().toString().replace("-","");
    }

    /**
     * Getter setter
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "用户ID不能为空") Long getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(@NotNull(message = "用户ID不能为空") Long employeeId) {
        EmployeeId = employeeId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public @Length(max = 240) String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(@Length(max = 240) String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public @NotNull Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(@NotNull Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
}
