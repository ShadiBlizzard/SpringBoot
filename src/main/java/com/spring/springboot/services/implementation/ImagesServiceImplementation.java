package com.spring.springboot.services.implementation;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.springboot.entities.Images;
import com.spring.springboot.repository.ImagesRepository;
import com.spring.springboot.services.ImagesService;

@Service
@Transactional(readOnly = false)
public class ImagesServiceImplementation implements ImagesService {

	@Autowired
	private ImagesRepository imagesRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Images findById(Integer student) {
		return imagesRepository.findById(student).get();
	}
	
	@Override
	public Images save(Integer student, byte[] image) {
		Images newImage = new Images(student, image);
		return imagesRepository.save(newImage);
	}

	@Override
	public String delete(int student) {
		Images images = imagesRepository.findById(student).get();
		if(images!= null) {
			imagesRepository.delete(images);
			return "Deletion successful!";
		}
		return "Image not found";
	}

	@Override
	public Images update(int student, byte[] image) {
		Images images = imagesRepository.findById(student).get();
		if (images!= null) {
			images.setValue(image);
			imagesRepository.save(images);
			return images;
		}
		
		return null;
	}
}
