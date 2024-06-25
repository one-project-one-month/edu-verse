/*
 * @Author : Alvin
 * @Date : 5/11/2024
 * @Time : 9:00 PM
 * @Project_Name : eduverse
 */
package dev.backend.eduverse.service.impl;

import dev.backend.eduverse.dto.PathwayDto;
import dev.backend.eduverse.exception.NameAlreadyExistException;
import dev.backend.eduverse.exception.ServiceException;
import dev.backend.eduverse.model.Pathway;
import dev.backend.eduverse.repository.PathwayRepository;
import dev.backend.eduverse.service.PathwayService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class PathwayServiceImpl implements PathwayService {

	@Autowired
	private final PathwayRepository pathwayRepository;

	private final Logger logger = LoggerFactory.getLogger(PathwayServiceImpl.class);

	private final ModelMapper modelMapper;

	public PathwayServiceImpl(PathwayRepository pathwayRepository, ModelMapper modelMapper) {
		this.pathwayRepository = pathwayRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public boolean createPathway(PathwayDto pathwayDTO) {
		logger.info("Entering the creation process");
		try {
			if (pathwayRepository.existsByName(pathwayDTO.getName())) {
				String errorMessage = "Pathway with name '" + pathwayDTO.getName() + "' already exists";
				logger.error(errorMessage);
				throw new NameAlreadyExistException(errorMessage);
			} else {
				Pathway pathway = modelMapper.map(pathwayDTO, Pathway.class);
				pathwayRepository.save(pathway);
				logger.info("Pathway created successfully");
				return true; // Creation successfully
			}
		} catch (DataIntegrityViolationException e) {
			logger.error("Failed to create pathway due to data integrity violation", e);
			throw e;
		} catch (NameAlreadyExistException e) { // Catch NameAlreadyExistException first
			throw e;
		} catch (Exception e) {
			return false; // Creation failed
		}
	}

	@Override
	public List<PathwayDto> getAllPathway() {
		logger.info("Entering the get all pathway process");

		try {
			List<Pathway> pathways = pathwayRepository.findAll();
			int numberOfPathways = pathways.size(); // Get the number of retrieved pathway list
			logger.info("Retrieved {} pathways", numberOfPathways);
			List<PathwayDto> pathwayDTOs = pathways.stream().map(pathway -> modelMapper.map(pathway, PathwayDto.class))
					.collect(Collectors.toList());
			return pathwayDTOs;
		} catch (Exception e) {
			logger.error("Error occurred while retrieving pathway list", e);
			throw new RuntimeException("Failed to retrieve pathway list", e);
		}
	}

	@Override
	public PathwayDto getPathwayByID(Long id) {
		logger.info("Retrieving pathway by ID: {}", id);
		Optional<Pathway> pathwayOptional = pathwayRepository.findById(id);
		Pathway pathway = pathwayOptional
				.orElseThrow(() -> new EntityNotFoundException("Entity is not found with this id" + id));
		return modelMapper.map(pathway, PathwayDto.class);
	}

	@Override
	public boolean updatePathway(PathwayDto pathwayDTO, Long id) {
		logger.info("Updating pathway with ID: {}", id);

		// Retrieve existing pathway from the repository
		Pathway existingPathway = pathwayRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Pathway not found with ID: " + id));

		modelMapper.map(pathwayDTO, existingPathway);

		try {
			pathwayRepository.save(existingPathway);
			logger.info("Pathway updated successfully");
			return true; // Update process successful
		} catch (Exception e) {
			logger.error("Failed to update pathway with ID: {}", id, e);
			return false; // Update process failed
		}
	}

	@Override
	public boolean deletePathway(Long id) {
		logger.info("Deleting pathway with ID: {}", id);

		Optional<Pathway> pathwayOptional = pathwayRepository.findById(id);
		if (pathwayOptional.isEmpty()) {
			logger.warn("Pathway not found with ID: {}", id);
			return false;
		}

		try {
			pathwayRepository.deleteById(id);
			logger.info("Pathway deleted successfully");
			return true; // Deletion successful
		} catch (Exception e) {
			logger.error("Failed to delete pathway with ID: {}", id, e);
			return false; // Deletion failed
		}
	}

	@Override
	public List<PathwayDto> readPathwayByPagination(int pageNumber, int pageSize) {
		pageNumber = Math.max(pageNumber, 1);
	    pageSize = (pageSize < 1) ? 10 : pageSize;
	    
	    int offset = (pageNumber - 1) * pageSize;
		try {
			List<Pathway> courseList = pathwayRepository.paginate(pageSize, offset);
			return courseList.stream()
					.map(pathway -> modelMapper.map(pathway, PathwayDto.class))
					.collect(Collectors.toList());
		} catch (Exception e) {
			logger.error("Failed to retrieve pathways with pagination", e);
	        throw new ServiceException("An error occurred while retrieving pathways", e);
		}
	}
}
