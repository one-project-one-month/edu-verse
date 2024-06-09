/*
 * @Author : Alvin
 * @Date : 5/11/2024
 * @Time : 9:00 PM
 * @Project_Name : eduverse
 */
package dev.backend.eduverse.service.impl;

import dev.backend.eduverse.dto.AdminRoleDto;
import dev.backend.eduverse.exception.ServiceException;
import dev.backend.eduverse.model.AdminRole;
import dev.backend.eduverse.repository.AdminRoleRepository;
import dev.backend.eduverse.service.AdminRoleService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminRoleServiceImpl implements AdminRoleService {

	@Autowired
	private final AdminRoleRepository adminRoleRepository;

	private final Logger logger = LoggerFactory.getLogger(AdminRoleServiceImpl.class);

	private final ModelMapper modelMapper;

	public AdminRoleServiceImpl(AdminRoleRepository adminRoleRepository, ModelMapper modelMapper) {
		this.adminRoleRepository = adminRoleRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public boolean createAdminRole(AdminRoleDto adminRoleDTO) {
		logger.info("Entering the creation process");
		AdminRole adminRole = modelMapper.map(adminRoleDTO, AdminRole.class);
		try {
			adminRoleRepository.save(adminRole);
			logger.info("AdminRole created successfully");
			return true; // Creation successfully
		} catch (Exception e) {
			return false; // Creation failed
		}
	}

	@Override
	public List<AdminRoleDto> getAllAdminRole() {
		logger.info("Entering the get all ADMIN role process");

		try {
			List<AdminRole> adminRoles = adminRoleRepository.findAll();
			int numberOfAdminRoles = adminRoles.size(); // Get the number of retrieved ADMIN role list
			logger.info("Retrieved {} ADMIN role list", numberOfAdminRoles);
			List<AdminRoleDto> adminRoleDTOs = adminRoles.stream()
					.map(adminRole -> modelMapper.map(adminRole, AdminRoleDto.class)).collect(Collectors.toList());
			return adminRoleDTOs;
		} catch (Exception e) {
			logger.error("Error occurred while retrieving ADMIN role list", e);
			throw new RuntimeException("Failed to retrieve ADMIN role list", e);
		}
	}

	@Override
	public AdminRoleDto getAdminRoleByID(Long id) {
		logger.info("Retrieving adminRole by ID: {}", id);
		Optional<AdminRole> adminRoleOptional = adminRoleRepository.findById(id);
		AdminRole adminRole = adminRoleOptional
				.orElseThrow(() -> new EntityNotFoundException("Entity is not found with this id" + id));
		return modelMapper.map(adminRole, AdminRoleDto.class);
	}

	@Override
	public boolean updateAdminRole(AdminRoleDto adminRoleDTO, Long id) {
		logger.info("Updating adminRole with ID: {}", id);

		// Retrieve existing ADMIN role from the repository
		AdminRole existingAdminRole = adminRoleRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("ADMIN role not found with ID: " + id));

		modelMapper.map(adminRoleDTO, existingAdminRole);

		try {
			adminRoleRepository.save(existingAdminRole);
			logger.info("ADMIN role updated successfully");
			return true; // Update process successful
		} catch (Exception e) {
			logger.error("Failed to update adminRole with ID: {}", id, e);
			return false; // Update process failed
		}
	}

	@Override
	public boolean deleteAdminRole(Long id) {
		logger.info("Deleting adminRole with ID: {}", id);

		Optional<AdminRole> adminRoleOptional = adminRoleRepository.findById(id);
		if (adminRoleOptional.isEmpty()) {
			logger.warn("ADMIN role not found with ID: {}", id);
			return false;
		}

		try {
			adminRoleRepository.deleteById(id);
			logger.info("ADMIN rolenRole deleted successfully");
			return true; // Deletion successful
		} catch (Exception e) {
			logger.error("Failed to delete ADMIN role with ID: {}", id, e);
			return false; // Deletion failed
		}
	}

	@Override
	public List<AdminRoleDto> readAdminRoleByPagniation(int pageNumber, int pageSize) throws IllegalAccessException {
		pageNumber = Math.max(pageNumber, 1);
	    pageSize = (pageSize < 1) ? 10 : pageSize;
	    
	    int offset = (pageNumber - 1) * pageSize;	
		try {
			List<AdminRole> adminRoleList = adminRoleRepository.paginate(pageSize, offset);			
			return adminRoleList.stream()
					.map(adminRole -> modelMapper.map(adminRole, AdminRoleDto.class))
					.collect(Collectors.toList());
		} catch (Exception e) {
			logger.error("Failed to retrieve admin roles with pagination", e);
	        throw new ServiceException("An error occurred while retrieving admin roles", e);
		}
	}
}
