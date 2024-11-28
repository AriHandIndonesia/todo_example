package com.hand.todo.app.service.impl;

import com.hand.todo.app.service.IOService;
import com.hand.todo.config.UploadConfig;
import feign.Response;
import org.hzero.boot.file.FileClient;
import org.hzero.boot.file.dto.FileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class IOServiceImpl implements IOService {

    @Autowired
    FileClient fileClient;

    @Autowired
    UploadConfig uploadConfig;

    @Override
    public String uploadFile(Long organizationId, String fileName, String grouping, String uploadDirectory, String storageConfiguration, String fileType) {
        String newFileName = fileName + "." + fileType;

        String uuid = UUID.randomUUID().toString().substring(0,31);

        //get file from folder

        File file = new File(uploadConfig.uploadPath + newFileName);
        byte[] bytes = new byte[(int) file.length()];

        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(bytes);  // Read file into byte array
        } catch (IOException e) {
            e.printStackTrace();
        }


        //upload attachment method
        //upload file method
        String result = fileClient.uploadAttachment(organizationId,
                grouping,
                uploadDirectory,
                uuid,
                newFileName,
                fileType,
                storageConfiguration,
                bytes);

        //upload file method
//        String result = fileClient.uploadFile(organizationId,
//                grouping,
//                uploadDirectory,
//                newFileName,
//                fileType,
//                storageConfiguration,
//                bytes);

        return result;
    }

    @Override
    public List<FileDTO> getFile(Long organizationId, String grouping, String attachmentUUID, String fileName) {
        List<FileDTO> result = fileClient.getAttachmentFiles(organizationId, grouping, attachmentUUID);
        if (fileName != null){
            FileDTO file = new FileDTO();
            for (int i = 0; i < result.size(); i++) {
                if (result.get(i).getFileName() == fileName){
                    file = result.get(i);
                    result = new ArrayList<>();
                    result.add(file);
                    return result;
                }
            }

        }
        return result;
    }

    @Override
    public String downloadFile(Long organizationId, String fileKey) {
        InputStream file = fileClient.downloadFile(organizationId, fileKey);
        //get file name
        String filename = fileKey.substring(fileKey.indexOf("@"));

        byte[] buffer = new byte[50000];
        int bytesRead;
        String result = "";
        try (FileOutputStream outputStream = new FileOutputStream(uploadConfig.downloadPath + filename)) {
            // Read data from the input stream and write it to the file
            while ((bytesRead = file.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);  // Write a chunk of data to the file
            }
            result = "File has been written successfully.";
            System.out.println("File has been downloaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            result = "Error writing file: " + e.getMessage();
            System.err.println("Error writing file: " + e.getMessage());
        }
        return result;
    }

    @Override
    public String deleteFile(Long organizationId, String grouping, List<String> urls) {
        List<FileDTO> files = fileClient.getFiles(organizationId, grouping, urls);
        fileClient.deleteFileByUrl(organizationId,grouping,urls);
        String fileName = files.get(0).getFileName();
        return "File " + fileName + " successfully deleted";
    }

    @Override
    public Response watermarkFile(Long organizationId, String fileKey, String watermarkCode) {
        String context = "TEST";
        return fileClient.watermarkByKey(organizationId, fileKey, watermarkCode, context);
    }
//    deleteFile(Long organizationId, String bucketName, String attachmentUUID, List<String> urls)
}
