package dev.backend.eduverse.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.backend.eduverse.dto.ModuleDto;
import dev.backend.eduverse.exception.ResourceNotFoundException;
import dev.backend.eduverse.model.Course;
import dev.backend.eduverse.model.CourseModule;
import dev.backend.eduverse.repository.CourseRepository;
import dev.backend.eduverse.repository.ModuleRepository;
import dev.backend.eduverse.service.ModuleService;

@Service
public class ModuleServiceImpl implements ModuleService {

	private ModuleRepository moduleRepository;

	private CourseRepository courseRepository;

	private final ModelMapper modelMapper = new ModelMapper();

	public ModuleServiceImpl(ModuleRepository moduleRepository, CourseRepository courseRepository) {
		this.moduleRepository = moduleRepository;
		this.courseRepository = courseRepository;
	}

	@Override
	public ModuleDto createModule(ModuleDto moduleDto) {
		Course course = courseRepository.findById(moduleDto.getCourseId())
				.orElseThrow(() -> new ResourceNotFoundException("Course", "Id", moduleDto.getCourseId()));
		CourseModule courseModule = modelMapper.map(moduleDto, CourseModule.class);
		courseModule.setCourse(course);
		courseModule = moduleRepository.save(courseModule);
		return modelMapper.map(courseModule, ModuleDto.class);
	}

	@Override
	public ModuleDto updateModule(ModuleDto moduleDto, Long id) {
		CourseModule existModule = moduleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("CourseModule", "Id", id));
		existModule.setName(moduleDto.getName());
		existModule.setContent(moduleDto.getContent());
		existModule.setDuration(moduleDto.getDuration());

		Course course = courseRepository.findById(moduleDto.getCourseId())
				.orElseThrow(() -> new ResourceNotFoundException("Course", "Id", moduleDto.getCourseId()));
		existModule.setCourse(course);

		CourseModule saveModule = moduleRepository.save(existModule);
		return modelMapper.map(saveModule, ModuleDto.class);
	}

	@Override
	public void deleteModule(Long id) {
		moduleRepository.deleteById(id);
	}

	@Override
	public List<ModuleDto> getAllModules() {
		List<CourseModule> courseModules = moduleRepository.findAll();
		return courseModules.stream().map(courseModule -> modelMapper.map(courseModule, ModuleDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public ModuleDto getById(Long id) {
		CourseModule courseModule = moduleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("CourseModule", "Id", id));
		return modelMapper.map(courseModule, ModuleDto.class);
	}

	@Override
	public List<ModuleDto> getByCourseId(Long courseId) {
		List<CourseModule> courseModules = moduleRepository.findByCourseId(courseId);
		return courseModules.stream().map(courseModule -> modelMapper.map(courseModules, ModuleDto.class))
				.collect(Collectors.toList());

	}

}