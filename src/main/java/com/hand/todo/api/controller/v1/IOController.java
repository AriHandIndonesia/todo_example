package com.hand.todo.api.controller.v1;

import com.hand.todo.app.service.IOService;
import com.hand.todo.config.SwaggerTags;
import feign.Response;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import org.hzero.boot.file.dto.FileDTO;
import org.hzero.core.base.BaseController;
//import org.hzero.core.util.Results;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import com.hand.todo.api.dto.fileInfoDTO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Api(tags = SwaggerTags.FILE)
@RestController("iOController.v1")
@RequestMapping("/v1/file")
public class IOController extends BaseController {
    @Autowired
    IOService ioService;

    //String fileName, String grouping, String uploadDirectory, String storageConfiguration, String fileType
    @Permission(level = ResourceLevel.SITE)
    @PostMapping("/upload")
    public String upload (@RequestParam(name = "filename") String fileName,
                          @RequestParam(name = "group") String grouping,
                          @RequestParam(name = "uploaddirectory") String uploadDirectory,
                          @RequestParam(name = "storage") String storageConfiguration,
                          @RequestParam(name = "filetype") String fileType,
                          @RequestParam(name = "organizationId") Long organizationId){
        return ioService.uploadFile(organizationId, fileName,grouping,uploadDirectory,storageConfiguration,fileType);
    }
    @Permission(level = ResourceLevel.SITE)
    @PostMapping("/getFiles")
    public List<FileDTO> getFiles (@RequestParam(name = "organizationId") Long organizationId,
                                   @RequestParam(name = "group") String grouping,
                                   @RequestParam(name = "uuid") String attachmentUUID){
        return ioService.getFile(organizationId, grouping, attachmentUUID, null);
    }
    @Permission(level = ResourceLevel.SITE)
    @PostMapping("/download")
    public String download (@RequestParam(name = "organizationId") Long organizationId,
                                   @RequestParam(name = "fileKey") String fileKey){
        return ioService.downloadFile(organizationId, fileKey);
    }
    @Permission(level = ResourceLevel.SITE)
    @PostMapping("/delete")
    public String delete (@RequestParam(name = "organizationId") Long organizationId,
                            @RequestParam(name = "group") String grouping,
                          @RequestParam(name = "urls") List<String> urls){
        return ioService.deleteFile(organizationId, grouping, urls);
    }

    @Permission(level = ResourceLevel.SITE)
    @PostMapping("/watermark")
    public Response watermark (@RequestParam(name = "organizationId") Long organizationId,
                            @RequestParam(name = "key") String key,
                            @RequestParam(name = "watermarkCode") String watermarkCode){
        Response response = ioService.watermarkFile(organizationId, key, watermarkCode);
        return response;
    }

    //from mr zeki code

//    @ApiOperation(value = "File info by UUID")
//    @Permission(level = ResourceLevel.ORGANIZATION)
//    @PostMapping("/file-info-by-uuid")
//    public ResponseEntity<Map<String, List<FileDTO>>> fileInfoByUUID(@PathVariable long organizationId, fileInfoDTO fileInfoDTO){
//        return Results.success(ioService.getInfoByUUID(organizationId,fileInfoDTO));
//    }
//
//    @ApiOperation(value = "File upload")
//    @Permission(level = ResourceLevel.ORGANIZATION)
//    @PostMapping("/upload-file")
//    public ResponseEntity<Map<String, Objects>> uploadFile(@PathVariable long organizationId, fileInfoDTO fileInfoDTO){
//        return Results.success(ioService.uploadFileZ(organizationId,fileInfoDTO));
//    }
//
//    @ApiOperation(value = "File download")
//    @Permission(level = ResourceLevel.ORGANIZATION)
//    @PostMapping("/download-file")
//    public ResponseEntity<Map<String, Objects>> downloadFile(@PathVariable long organizationId, fileInfoDTO fileInfoDTO, HttpServletResponse httpServletResponse){
//        return Results.success(ioService.downloadFileZ(organizationId,fileInfoDTO, httpServletResponse));
//    }
//
//    @ApiOperation(value = "File delete by url")
//    @Permission(level = ResourceLevel.ORGANIZATION)
//    @PostMapping("/delete-file-by-url")
//    public ResponseEntity<Map<String, Objects>> deleteFileByUrl(@PathVariable long organizationId, fileInfoDTO fileInfoDTO){
//        return Results.success(ioService.deleteFileByUrl(organizationId,fileInfoDTO));
//    }
}
