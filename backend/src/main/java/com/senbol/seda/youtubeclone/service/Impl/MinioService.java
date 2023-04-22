package com.senbol.seda.youtubeclone.service.Impl;

import com.senbol.seda.youtubeclone.dto.VideoFileDto;
import com.senbol.seda.youtubeclone.service.IFileService;
import io.minio.*;
import io.minio.messages.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class MinioService implements IFileService {

    @Autowired
    private final MinioClient minioClient;

    @Value("${minio.bucket.name}")
    private String defaultBucketName;

    @Value("${minio.default.folder}")
    private String defaultBaseFolder;

    public List<VideoFileDto> getListObjects() {
        List<VideoFileDto> objects = new ArrayList<>();
        try {
            Iterable<Result<Item>> result = minioClient.listObjects(ListObjectsArgs.builder()
                    .bucket(defaultBucketName)
                    .recursive(true)
                    .build());
            for (Result<Item> item : result) {
                objects.add(VideoFileDto.builder()
                        .filename(item.get().objectName())
                        .size(item.get().size())
                        .url(getPreSignedUrl(item.get().objectName()))
                        .build());
            }
            return objects;
        } catch (Exception e) {
            log.error("getListObjects:: Error occurred while getting list objects from minio: ", e);
        }

        return objects;
    }

    private String getPreSignedUrl(String filename) {
        return "http://localhost:8080/file/".concat(filename);
    }

    @Override
    public String uploadFile(MultipartFile multipartFile) {
        File file = new File("/tmp/" + multipartFile.getName());
        file.canWrite();
        file.canRead();
        try {
            String fileNameExtension = StringUtils.getFilenameExtension(file.getName());
            String key = UUID.randomUUID() + fileNameExtension;
            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                fileOutputStream.write(Objects.requireNonNull(multipartFile.getContentType()).getBytes(StandardCharsets.UTF_8));
            }
            PutObjectArgs putObjectArgs = PutObjectArgs.builder().
                    bucket(defaultBucketName)
                    .object(defaultBaseFolder + key)
                    .stream(multipartFile.getInputStream(), -1, 10485760)
                    .contentType("video/mp4")
                    .build();
            minioClient.putObject(putObjectArgs);

            return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().bucket(defaultBucketName).object(key).build());
        } catch (Exception e) {
            log.error("uploadFile:: An exception occurred while uploading the file. Error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An exception occurred while uploading the file.");
        }
    }

    public byte[] getFile(String key) {
        try {
            GetObjectArgs getObjectArgs = GetObjectArgs.builder()
                    .bucket(defaultBucketName)
                    .object(defaultBaseFolder + "/" + key)
                    .build();
            InputStream obj = minioClient.getObject(getObjectArgs);

            byte[] content = IOUtils.toByteArray(obj);
            obj.close();
            return content;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
