package vn.iotstar.service;


import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import vn.iotstar.entity.ImageData;
import vn.iotstar.repository.StorageRepository;
import vn.iotstar.util.ImageUtils;

@Service
public class StorageService {
	
    @Autowired
    private StorageRepository repository;
    

    public Optional<ImageData> findByName(String fileName) {
		return repository.findByName(fileName);
	}

	public void delete(ImageData entity) {
		repository.delete(entity);
	}

	public void deleteById(String id) {
		repository.deleteById(id);
	}

	@SuppressWarnings("unused")
	public ImageData uploadImage(MultipartFile file) throws IOException {

    	ImageData entity = new ImageData();
    	entity.setId(UUID.randomUUID().toString().split("-")[0]);
    	entity.setName(file.getOriginalFilename());
    	entity.setType(file.getContentType());
    	entity.setImageData(ImageUtils.compressImage(file.getBytes()));
    	
    	
        if (entity != null) {
        	repository.save(entity);
            return entity;
        }
        return null;
    }

    public byte[] downloadImage(String fileName){
        Optional<ImageData> dbImageData = repository.findByName(fileName);
        byte[] images=ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }
}