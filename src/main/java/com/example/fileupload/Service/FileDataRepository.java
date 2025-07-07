package com.example.fileupload.Service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fileupload.Entity.FileData;

@Repository
public interface FileDataRepository extends JpaRepository<FileData, Long>{

	
	
}
