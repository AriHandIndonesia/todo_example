package com.hand.todo.app.service;


import feign.Response;
import org.hzero.boot.file.dto.FileDTO;

import java.util.List;

public interface IOService {
    String uploadFile(Long organizationId, String fileName, String grouping, String uploadDirectory, String storageConfiguration, String fileType);
    List<FileDTO> getFile(Long organizationId, String grouping, String attachmentUUID, String fileName);
    String downloadFile(Long organizationId, String fileKey);
    String deleteFile(Long organizationId, String grouping, List<String> urls);
    Response watermarkFile(Long organizationId, String fileKey, String watermarkCode);
}
