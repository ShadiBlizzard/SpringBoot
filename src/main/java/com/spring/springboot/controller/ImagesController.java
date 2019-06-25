package com.spring.springboot.controller;


import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.springboot.entities.Images;
import com.spring.springboot.entities.Student;
import com.spring.springboot.request.ImagesJsonRequest;
import com.spring.springboot.services.ImagesService;
import com.spring.springboot.services.StudentService;

@RestController
@RequestMapping("/images")
public class ImagesController {
	
	private static final Logger logger = LoggerFactory.getLogger(ImagesController.class);

	@Autowired
	private ImagesService imagesService;

	
	@PostMapping("/coded")
	public Images insert(@RequestBody ImagesJsonRequest request) {
		return imagesService.save(request.getStudent(), request.getImage());
	}
	
	@PostMapping(value = "/file", consumes = "multipart/form-data")
	public Images insertFile(@RequestParam("studid") String studid, @RequestPart("image") MultipartFile image){
		byte[] byteImage;
		
		try {
			byteImage = image.getBytes();
			return imagesService.save(Integer.valueOf(studid), byteImage);
		} catch (IOException e) {
			logger.error("Errore generico", e);
			return null;
		}
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] getImage(@PathVariable Integer id) {
		Images image = imagesService.findById(id);
		return image.getValue();
	}
	
	@DeleteMapping(value="/delete")
	public String deleteImage(@RequestBody ImagesJsonRequest request) {
		return imagesService.delete(request.getStudent());
		
	}
	@PutMapping(value="/update", produces = { MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE})
	public byte[] updateImage(@RequestBody ImagesJsonRequest request) {
		Images image = imagesService.update(request.getStudent(), request.getImage());
		if (image!= null)
			return image.getValue();
		
		return "Image not found".getBytes();
		
	}
}
