package vn.iotstar.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "imagedata")
public class ImageData {
	@Id
	private String id;

    private String name;
    private String type;
    private byte[] imageData;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public byte[] getImageData() {
		return imageData;
	}
	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}
	public ImageData(String id, String name, String type, byte[] imageData) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.imageData = imageData;
	}
	public ImageData() {
		super();
	}
    
}
