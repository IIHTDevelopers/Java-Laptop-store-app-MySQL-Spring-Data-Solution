package com.laptopstore.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class LaptopDTO {

	private Long id;

	@NotBlank(message = "Name can not be blank")
	@NotNull
	@Min(3)
	private String name;

	@Positive
	@NotNull
	private Double price;

	@NotBlank(message = "Brand can not be blank")
	@Min(3)
	@NotNull
	private String brand;

	@NotBlank(message = "Storage can not be blank")
	@Min(3)
	@NotNull
	private String storage;

	@NotBlank(message = "Ram can not be blank")
	@NotNull
	private String ram;

	@NotBlank(message = "Processor can not be blank")
	@Min(3)
	@NotNull
	private String processor;

	public LaptopDTO() {
		super();
	}

	public LaptopDTO(Long id, @NotBlank(message = "Name can not be blank") String name, @Positive Double price,
			@NotBlank(message = "Brand can not be blank") String brand,
			@NotBlank(message = "Storage can not be blank") String storage,
			@NotBlank(message = "Ram can not be blank") String ram,
			@NotBlank(message = "Processor can not be blank") String processor) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.brand = brand;
		this.storage = storage;
		this.ram = ram;
		this.processor = processor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	@Override
	public String toString() {
		return "LaptopDTO [id=" + id + ", name=" + name + ", price=" + price + ", brand=" + brand + ", storage="
				+ storage + ", ram=" + ram + ", processor=" + processor + "]";
	}
}
