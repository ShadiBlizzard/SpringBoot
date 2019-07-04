package com.spring.springboot.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.spring.springboot.exceptions.InvalidOperationException;
import com.spring.springboot.exceptions.ObjNotFoundException;
import com.spring.springboot.services.ImagesService;


@RestController
@RequestMapping("/images")
public class ImagesController {
	

	@Autowired
	private ImagesService imagesService;

	
	@PostMapping("/")
	public ResponseEntity<ApiResponse> insert(@Valid @RequestBody ImagesDto dto) throws InvalidOperationException {
		ApiResponse image = imagesService.save(dto);
		return new ResponseEntity<>(image, image.getStatus());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ApiResponse> getImage(@PathVariable Integer id) throws ObjNotFoundException {
		ApiResponse image = imagesService.findById(id);
		return new ResponseEntity<>(image, image.getStatus());
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<ApiResponse> deleteImage(@PathVariable Integer id) throws ObjNotFoundException {
		ApiResponse result = imagesService.delete(id);
		return new ResponseEntity<>(result, result.getStatus());
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<ApiResponse> updateImage(@Valid @RequestBody ImagesDto dto, @PathVariable Integer id) throws ObjNotFoundException {
		dto.setId(id);
		ApiResponse image = imagesService.update(dto);
		return new ResponseEntity<>(image, image.getStatus());
	}
}
