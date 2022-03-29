package com.appliance.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.appliance.entity.Appliance;
import com.appliance.repository.ApplianceRepository;

@Service
public class ApplianceServiceImpl implements ApplianceService {

	private static final Logger logger = LoggerFactory.getLogger(ApplianceServiceImpl.class);

	@Autowired
	private ApplianceRepository repository;

	@Override
	public Appliance getSingleAppliance(Integer serialNumber) {
		Appliance appl = null;

		try {
			appl = repository.getById(serialNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return appl;
	}

	@Override
	public Appliance addAppliance(Appliance appliance) {
		return repository.save(appliance);
	}

	@Override
	public void updateAppliance(Appliance appliance, Integer serialNumber) {
		repository.findAll().stream().map(appl -> {
			if (appl.getSerialNumber().equals(serialNumber)) {
				appl.setBrand(appliance.getBrand());
				appl.setModel(appliance.getModel());
				appl.setDateBought(appliance.getDateBought());
			}
			return appl;
		}).collect(Collectors.toList());
		repository.save(appliance);

	}

	@Override
	public void deleteAppliance(Integer serialNum) {
		repository.deleteById(serialNum);
		logger.info("delete appliance by Id");

	}

	@Override
	public void deleteAllAppliances() {
		repository.deleteAll();

	}

	@Override
	public List<Appliance> applianceByModel(String model) {
		return repository.findAll().stream().filter(appliance -> appliance.getModel().equalsIgnoreCase(model))
				.collect(Collectors.toList());
	}

	@Override
	public List<Appliance> applianceByBrand(String brand) {
		return repository.findAll().stream().filter(appliance -> appliance.getBrand().equalsIgnoreCase(brand))
				.collect(Collectors.toList());

	}

	@Override
	public List<Appliance> applianceByStatus(String status) {
		return repository.findAll().stream().filter(appliance -> appliance.getStatus().equalsIgnoreCase(status))
				.collect(Collectors.toList());

	}

	@Override
	public List<Appliance> applianceByDate(Date dateBought) {
		return repository.findAll().stream().filter(appliance -> appliance.getDateBought().equals(dateBought))
				.collect(Collectors.toList());
	}

	@Override
	public List<Appliance> getAllAppliances(Integer userId) {
		return repository.findAll().stream().filter(appliance -> appliance.getUserid().equals(userId))
				.collect(Collectors.toList());
	}

}
