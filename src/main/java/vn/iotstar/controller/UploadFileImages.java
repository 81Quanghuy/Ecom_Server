package vn.iotstar.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class UploadFileImages{
	
	@PostMapping("upload-images")
	public String uploadImages(@RequestParam("file") MultipartFile file )throws Exception {
		
		String Path_Directory = new ClassPathResource("static/images/").getFile().getAbsolutePath();
		
		String real_path = Path_Directory+File.separator+file.getOriginalFilename();
		
		Files.copy(file.getInputStream(), Paths.get(real_path),
					StandardCopyOption.REPLACE_EXISTING);
		
		System.out.println(Paths.get(Path_Directory+File.separator+file.getOriginalFilename()));
		return "sucessfully images is upload";
		
	}

}
