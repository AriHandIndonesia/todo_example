package com.hand.todo.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class fileInfoDTO {
    @ApiModelProperty("Group")
    private String grouping;
    @ApiModelProperty("File Url")
    private String fileUrl;
    @ApiModelProperty("Attachment Uuid")
    private String attachmentUuid;
    @ApiModelProperty("File Directory")
    private String fileDirectory;
    @ApiModelProperty("File Name")
    private String fileName;
    @ApiModelProperty("File Type")
    private String fileType;
    @ApiModelProperty("File Source")
    private String fileSource;
}
