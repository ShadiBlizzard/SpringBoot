package com.spring.springboot.services.implementation;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.springboot.dto.ImagesDto;
import com.spring.springboot.entities.Images;
import com.spring.springboot.exceptions.ApiResponse;
import com.spring.springboot.exceptions.InvalidOperationException;
import com.spring.springboot.exceptions.ObjNotFoundException;
import com.spring.springboot.repository.ImagesRepository;
import com.spring.springboot.services.ImagesService;
import com.spring.springboot.utils.MapperUtils;
import com.spring.springboot.utils.StringUtils;


@Service
@Transactional(readOnly = false)
public class ImagesServiceImplementation implements ImagesService {

	@Autowired
	private ImagesRepository imagesRepository;
	
	@Autowired
	private MapperUtils mapper;
	
	@Override
	@Transactional(readOnly = true)
	public ApiResponse findById(Integer student) throws ObjNotFoundException {
		Optional<Images> opt = imagesRepository.findById(student);
		if (!opt.isPresent())
			throw new ObjNotFoundException(String.format(StringUtils.NOT_FOUND_ID, StringUtils.IMAGE, "id", student));
		return new ApiResponse(HttpStatus.FOUND, mapper.map(opt.get(), ImagesDto.class));
	}
	
	@Override
	public ApiResponse save(ImagesDto dto) throws InvalidOperationException {
		Optional<Images> image = imagesRepository.findById(dto.getId());
		if(image.isPresent())
			throw new InvalidOperationException(String.format(StringUtils.IMAGE_ALREADY_EXISTS, dto.getId()));
		Images newImage = imagesRepository.save(mapper.map(dto, Images.class));
		return new ApiResponse(HttpStatus.OK, mapper.map(newImage, ImagesDto.class));
	}

	@Override
	public ApiResponse delete(Integer id) throws ObjNotFoundException {
		Optional<Images> opt = imagesRepository.findById(id);
		if(!opt.isPresent())
			throw new ObjNotFoundException(String.format(StringUtils.NOT_FOUND_ID, StringUtils.IMAGE, id));
		Images images = opt.get();
		imagesRepository.delete(images);
		return new ApiResponse(HttpStatus.OK, String.format(StringUtils.DELETE_SUCCESS, StringUtils.IMAGE));
	}

	@Override
	public ApiResponse update(ImagesDto dto) throws ObjNotFoundException {
		Optional<Images> opt = imagesRepository.findById(dto.getId());
		if(!opt.isPresent())
			throw new ObjNotFoundException(String.format(StringUtils.NOT_FOUND_ID, StringUtils.IMAGE, dto.getId()));
		imagesRepository.save(mapper.map(dto, Images.class));
		return new ApiResponse(HttpStatus.OK, String.format(StringUtils.UPDATE_SUCCESS, StringUtils.IMAGE));
	}
	
}
