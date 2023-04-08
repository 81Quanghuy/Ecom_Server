package vn.iotstar.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import vn.iotstar.service.StorageService;

@RestController
public class UploadFileImages{
		
	@Autowired
	private StorageService service;
	
	@RequestMapping(value = "/images", method = RequestMethod.GET)
	@ResponseBody()
	public ResponseEntity<ByteArrayResource> getImage(@RequestParam(name = "photo", required = false) String photo) {
		if (!photo.equals("") || photo != null) {
			try {
				Path filename = Paths.get("src/main/resources/static/image", photo);
				byte[] buffer = Files.readAllBytes(filename);
				ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
				return ResponseEntity.ok().contentLength(buffer.length)
						.contentType(MediaType.parseMediaType("image/png")).body(byteArrayResource);
			} catch (Exception e) {

			}
		}
		return ResponseEntity.badRequest().build();

	}
	
//	@RequestMapping(value = "/api/upload-images", method = RequestMethod.POST)
//	@ResponseBody()
//	public String uploadImages(@RequestParam(name = "file", required = false) MultipartFile file )throws Exception {
//	
//		String path = application.getRealPath("/");
//
//		try {
//		
//			MultipartFile getFile = file;
//			String filePath = path + "/resources/images/user/" + user.getAvatar();
//			getFile.getName().transferTo(Path.of(filePath))
//			String filePath = path + "/resources/images/admin/product/" + product.getListimage();
//			product.getListImageFile().transferTo(Path.of(filePath));
//			product.setListImageFile(null);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}

	@RequestMapping(value = "/images", method = RequestMethod.POST)
	public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
		String uploadImage = service.uploadImage(file);
		return ResponseEntity.status(HttpStatus.OK)	
				.body(uploadImage);
	}

	@RequestMapping(value = "/images/{fileName}", method = RequestMethod.GET)
	public ResponseEntity<?> downloadImage(@PathVariable String fileName){
		byte[] imageData=service.downloadImage(fileName);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imageData);
	}
}
