package com.laptopstore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.laptopstore.dto.LaptopDTO;
import com.laptopstore.exception.ResourceNotFoundException;
import com.laptopstore.repo.LaptopRepository;
import com.laptopstore.service.LaptopService;

@Service
public class LaptopServiceImpl implements LaptopService {

	private final LaptopRepository laptopRepository;

	@Autowired
	public LaptopServiceImpl(LaptopRepository laptopRepository) {
		this.laptopRepository = laptopRepository;
	}

	@Override
	public Page<LaptopDTO> getAllLaptops(Pageable pageable) {
		return laptopRepository.findAll(pageable);
	}

	@Override
	public LaptopDTO getLaptopById(Long id) {
		Optional<LaptopDTO> laptop = laptopRepository.findById(id);
		if (laptop.isPresent()) {
			return laptop.get();
		} else {
			throw new ResourceNotFoundException("Laptop not found");
		}
	}

	@Override
	public LaptopDTO createLaptop(LaptopDTO laptopDTO) {
		return laptopRepository.save(laptopDTO);
	}

	@Override
	public LaptopDTO updateLaptop(Long id, LaptopDTO laptopDTO) {
		if (!laptopRepository.existsById(id)) {
			throw new ResourceNotFoundException("Laptop not found");
		}
		laptopDTO.setId(id);
		return laptopRepository.save(laptopDTO);
	}

	@Override
	public boolean deleteLaptop(Long id) {
		if (!laptopRepository.existsById(id)) {
			return false;
		}
		laptopRepository.deleteById(id);
		return true;
	}

	@Override
	public List<LaptopDTO> searchLaptopsByName(String name) {
		return laptopRepository.findByNameContaining(name);
	}

	@Override
	public List<LaptopDTO> searchLaptopsByPrice(Double price) {
		return laptopRepository.findByPrice(price);
	}

	@Override
	public List<LaptopDTO> searchLaptopsByBrand(String brand) {
		return laptopRepository.searchByBrand(brand);
	}

	@Override
	public List<LaptopDTO> searchLaptops(String name, Double price, String brand) {
		return laptopRepository.searchLaptops(name, price, brand);
	}
}
