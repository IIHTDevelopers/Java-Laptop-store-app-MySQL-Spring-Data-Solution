package com.laptopstore.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.laptopstore.dto.LaptopDTO;

public interface LaptopRepository extends JpaRepository<LaptopDTO, Long> {

	List<LaptopDTO> findByNameContaining(String name);

	List<LaptopDTO> findByPrice(Double price);

	@Query("SELECT l FROM LaptopDTO l WHERE l.brand = :brand")
	List<LaptopDTO> searchByBrand(@Param("brand") String brand);

	@Query("SELECT l FROM LaptopDTO l WHERE l.name LIKE %:name% AND l.price = :price AND l.brand = :brand")
	List<LaptopDTO> searchLaptops(@Param("name") String name, @Param("price") Double price,
			@Param("brand") String brand);
}
