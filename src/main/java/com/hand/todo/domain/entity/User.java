package com.hand.todo.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.choerodon.mybatis.annotation.ModifyAudit;
import io.choerodon.mybatis.annotation.VersionAudit;
import io.choerodon.mybatis.domain.AuditDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.hzero.common.HZeroCacheKey;
import org.hzero.core.cache.CacheValue;
import org.hzero.core.cache.Cacheable;
import org.hzero.core.util.Regexs;
import org.hzero.export.annotation.ExcelColumn;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@ApiModel("用户信息") // Swagger 实体描述
@ModifyAudit //在类上使用，启用审计字段支持，实体类加上该注解后，插入和更新会启动对 lastUpdateDate、lastUpdatedBy 自维护字段支持
@VersionAudit //在类上使用，启用objectVersionNumber自维护支持，插入一条数据objectVersionNumber默认为1，每次update后objectVersionNumber自增1
@Table(name = "todo_user") // 表映射
@JsonInclude(JsonInclude.Include.NON_NULL) // 数据返回前端时，排除为空的字段
public class User extends AuditDomain implements Cacheable {
    @Id // 主键主键，注意是 javax.persistence.Id
    @GeneratedValue //对于自增张、序列（SEQUENCE）类型的主键，需要添加该注解
    private Long id;
    @Length(max = 30) // 长度控制
    @NotBlank // 非空控制
    @ApiModelProperty("员工姓名") // Swagger 字段描述
    @ExcelColumn(en = "Employee Name")
    private String employeeName;
    @Length(max = 30)
    @NotBlank
    @Pattern(regexp = Regexs.CODE, message = "htdo.warn.user.numberFormatIncorrect") // 格式控制
    @ApiModelProperty("员工编号")
    @ExcelColumn(en = "Employee Number")
    private String employeeNumber;
    @Length(max = 60)
    @Pattern(regexp = Regexs.EMAIL, message = "htdo.warn.user.emailFormatIncorrect")
    @ApiModelProperty("员工邮箱")
    @ExcelColumn(en = "Email")
    private String email;

    @Transient
    @CacheValue(key = HZeroCacheKey.USER, primaryKey = "createdBy", searchKey = "realName",
            structure = CacheValue.DataStructure.MAP_OBJECT)
    private String createdUserName; // 创建人姓名


    //getter setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @Length(max = 30) @NotBlank String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(@Length(max = 30) @NotBlank String employeeName) {
        this.employeeName = employeeName;
    }

    public @Length(max = 30) @NotBlank @Pattern(regexp = Regexs.CODE, message = "htdo.warn.user.numberFormatIncorrect") String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(@Length(max = 30) @NotBlank @Pattern(regexp = Regexs.CODE, message = "htdo.warn.user.numberFormatIncorrect") String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public @Length(max = 60) @Pattern(regexp = Regexs.EMAIL, message = "htdo.warn.user.emailFormatIncorrect") String getEmail() {
        return email;
    }

    public void setEmail(@Length(max = 60) @Pattern(regexp = Regexs.EMAIL, message = "htdo.warn.user.emailFormatIncorrect") String email) {
        this.email = email;
    }
}
