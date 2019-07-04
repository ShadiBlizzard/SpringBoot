package com.spring.springboot.services.implementation;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.springboot.dto.ImagesDto;
import com.spring.springboot.entities.Images;
import com.spring.springboot.exceptions.ApiResponse;
import com.spring.springboot.exceptions.ImageInsertionException;
import com.spring.springboot.repository.ImagesRepository;
import com.spring.springboot.services.ImagesService;
import com.spring.springboot.utils.MapperUtils;
import com.spring.springboot.utils.StringUtils;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
@Transactional(readOnly = false)
public class ImagesServiceImplementation implements ImagesService {

	@Autowired
	private ImagesRepository imagesRepository;
	
	@Autowired
	private MapperUtils mapper;
	
	@Override
	@Transactional(readOnly = true)
	public ImagesDto findById(Integer student) throws ObjectNotFoundException {
		Optional<Images> opt = imagesRepository.findById(student);
		Images image = opt.get();
		if (image == null)
			throw new ObjectNotFoundException(String.format(StringUtils.NOT_FOUND_ID, StringUtils.IMAGE, student.intValue()));
		return mapper.map(image, ImagesDto.class);
	}
	
	@Override
	public ImagesDto save(ImagesDto dto) throws ImageInsertionException {
		Images image = mapper.map(dto, Images.class);
		Images newImage = imagesRepository.save(image);
		if (newImage == null)
			throw new ImageInsertionException(StringUtils.IMAGE_INSERTION_ERROR);
		
		return mapper.map(newImage, ImagesDto.class);
	}

	@Override
	public ApiResponse delete(Integer id) throws ObjectNotFoundException {
		Optional<Images> opt = imagesRepository.findById(id);
		if(!opt.isPresent())
			throw new ObjectNotFoundException(String.format(StringUtils.NOT_FOUND_ID, StringUtils.IMAGE, id));
		Images images = opt.get();
		imagesRepository.delete(images);
		return new ApiResponse(HttpStatus.OK, "Image deleted successfully");
	}

	@Override
	public ImagesDto update(ImagesDto dto) throws ObjectNotFoundException {
		if(!imagesRepository.findById(dto.getId()).isPresent())
			throw new ObjectNotFoundException(String.format(StringUtils.NOT_FOUND_ID, StringUtils.IMAGE, dto.getId()));
		Images images = imagesRepository.save(mapper.map(dto, Images.class));
		return mapper.map(images, ImagesDto.class);
	}
	
}
