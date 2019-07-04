package com.spring.springboot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.springboot.dto.ImagesDto;
import com.spring.springboot.exceptions.ApiResponse;
import com.spring.springboot.exceptions.ImageInsertionException;
import com.spring.springboot.services.ImagesService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/images")
public class ImagesController {
	

	@Autowired
	private ImagesService imagesService;

	
	@PostMapping("/")
	public ResponseEntity<ImagesDto> insert(@RequestBody ImagesDto dto) throws ImageInsertionException {
		ImagesDto image = imagesService.save(dto);
		return new ResponseEntity<>(image, HttpStatus.FOUND);
	}

	@GetMapping(value = "/{id}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE})
	public ResponseEntity<byte[]> getImage(@PathVariable Integer id) throws ObjectNotFoundException {
		ImagesDto image = imagesService.findById(id);
		return new ResponseEntity<>(image.getValue(), HttpStatus.FOUND);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<ApiResponse> deleteImage(@PathVariable Integer id) throws ObjectNotFoundException {
		ApiResponse result = imagesService.delete(id);
		return new ResponseEntity<>(result, result.getStatus());
	}
	
	@PutMapping(value="/update", produces = { MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE})
	public ResponseEntity<byte[]> updateImage(@RequestBody ImagesDto dto) throws ObjectNotFoundException {
		ImagesDto image = imagesService.update(dto);
		return new ResponseEntity<>(image.getValue(), HttpStatus.FOUND);
	}
}
