package com.laptopstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.laptopstore.dto.LaptopDTO;
import com.laptopstore.exception.ResourceNotFoundException;
import com.laptopstore.service.LaptopService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/laptops")
public class LaptopController {

	private final LaptopService laptopService;

	@Autowired
	public LaptopController(LaptopService laptopService) {
		this.laptopService = laptopService;
	}

	@GetMapping
	public ResponseEntity<Page<LaptopDTO>> getAllLaptops(Pageable pageable) {
		Page<LaptopDTO> laptops = laptopService.getAllLaptops(pageable);
		return new ResponseEntity<>(laptops, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<LaptopDTO> getLaptopById(@PathVariable Long id) {
		LaptopDTO laptopDTO = laptopService.getLaptopById(id);
		return new ResponseEntity<>(laptopDTO, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<LaptopDTO> createLaptop(@RequestBody @Valid LaptopDTO laptopDTO) {
		LaptopDTO createdLaptop = laptopService.createLaptop(laptopDTO);
		return new ResponseEntity<>(createdLaptop, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<LaptopDTO> updateLaptop(@PathVariable Long id, @RequestBody @Valid LaptopDTO laptopDTO) {
		try {
			LaptopDTO updatedLaptop = laptopService.updateLaptop(id, laptopDTO);
			return new ResponseEntity<>(updatedLaptop, HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteLaptop(@PathVariable Long id) {
		boolean isDeleted = laptopService.deleteLaptop(id);
		if (isDeleted) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/search/by-name")
	public ResponseEntity<List<LaptopDTO>> searchLaptopsByName(@RequestParam String name) {
		List<LaptopDTO> laptops = laptopService.searchLaptopsByName(name);
		return new ResponseEntity<>(laptops, HttpStatus.OK);
	}

	@GetMapping("/search/by-price")
	public ResponseEntity<List<LaptopDTO>> searchLaptopsByPrice(@RequestParam Double price) {
		List<LaptopDTO> laptops = laptopService.searchLaptopsByPrice(price);
		return new ResponseEntity<>(laptops, HttpStatus.OK);
	}

	@GetMapping("/search/by-brand")
	public ResponseEntity<List<LaptopDTO>> searchLaptopsByBrand(@RequestParam String brand) {
		List<LaptopDTO> laptops = laptopService.searchLaptopsByBrand(brand);
		return new ResponseEntity<>(laptops, HttpStatus.OK);
	}

	@GetMapping("/search")
	public ResponseEntity<List<LaptopDTO>> searchLaptops(@RequestParam(required = false) String name,
			@RequestParam(required = false) Double price, @RequestParam(required = false) String brand) {
		List<LaptopDTO> laptops = laptopService.searchLaptops(name, price, brand);
		return new ResponseEntity<>(laptops, HttpStatus.OK);
	}
}
