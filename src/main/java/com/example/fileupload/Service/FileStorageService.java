package com.example.fileupload.Service;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.fileupload.Entity.FileData;

@Service
public class FileStorageService {

	@Autowired
	FileDataRepository repository;

	public ResponseEntity<FileData> saveFile(MultipartFile file) throws IOException {
		FileData data = new FileData();
	    data.setOriginalFilename(file.getOriginalFilename());
	    data.setFileType(file.getContentType());
	    data.setFileSize(file.getSize());
	    data.setUploadedAt(LocalDateTime.now());
	    data.setData(file.getBytes());
	    return ResponseEntity.ok(repository.save(data));
	}

	public ResponseEntity<?> downloadFile(Long id) {
		FileData fileData = repository.findById(id).get();
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(fileData.getFileType()));
        headers.setContentDisposition(ContentDisposition.attachment()
                .filename(fileData.getOriginalFilename())
                .build());

        return new ResponseEntity<>(fileData.getData(), headers, HttpStatus.OK);
	}
}
