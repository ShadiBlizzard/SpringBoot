package com.spring.springboot.services.implementation;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.springboot.dto.ImagesDto;
import com.spring.springboot.entities.Images;
import com.spring.springboot.repository.ImagesRepository;
import com.spring.springboot.services.ImagesService;
import com.spring.springboot.utils.MapperUtils;

@Service
@Transactional(readOnly = false)
public class ImagesServiceImplementation implements ImagesService {

	@Autowired
	private ImagesRepository imagesRepository;
	
	@Autowired
	private MapperUtils mapper;
	
	@Override
	@Transactional(readOnly = true)
	public ImagesDto findById(Integer student) {
		Optional<Images> opt = imagesRepository.findById(student);
		Images image = opt.get();
		return mapper.map(image, ImagesDto.class);
	}
	
	@Override
	public ImagesDto save(ImagesDto dto) {
		Images image = mapper.map(dto, Images.class);
		Images newImage = imagesRepository.save(image);
		return mapper.map(newImage, ImagesDto.class);
	}

	@Override
	public ImagesDto delete(ImagesDto dto) {
		Optional<Images> opt = imagesRepository.findById(dto.getId());
		if(!opt.isPresent())
			throw new IllegalStateException();
		Images images = opt.get();
		imagesRepository.delete(images);
		return dto;
	}

	@Override
	public ImagesDto update(ImagesDto dto) {
		if(!imagesRepository.findById(dto.getId()).isPresent())
			throw new IllegalStateException();
		Images images = imagesRepository.save(mapper.map(dto, Images.class));
		return mapper.map(images, ImagesDto.class);
	}
	
}
