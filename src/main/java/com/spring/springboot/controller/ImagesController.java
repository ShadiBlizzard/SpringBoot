package com.spring.springboot.controller;


import java.io.IOException;


import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.springboot.entities.Images;
import com.spring.springboot.request.ImagesJsonRequest;
import com.spring.springboot.services.ImagesService;

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
	
	//DA RIVEDERE, PASSARE L'IMMAGINE COME FORM-DATA
	/*@PostMapping(value = "/file", consumes = { "application/json", "multipart/form-data" })
	public Images insertFile(@RequestBody ImagesJsonRequest request, MultipartFile image){
		byte[] byteImage;
		
		try {
			byteImage = image.getBytes();
			byte[] imageCoded = Base64.encodeBase64(byteImage);
			return imagesService.save(request.getStudent(), imageCoded);
		} catch (IOException e) {
			logger.error("Errore generico", e);
			return null;
		}
	}*/
	
	@GetMapping(value = "/{id}", produces = MediaType.IMAGE_PNG_VALUE)
	public @ResponseBody byte[] getImage(@PathVariable Integer id) {
		Images image = imagesService.findById(id);
		return image.getValue();
	}
}