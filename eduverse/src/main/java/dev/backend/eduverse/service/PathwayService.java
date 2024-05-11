/*
 * @Author : Alvin
 * @Date : 5/11/2024
 * @Time : 9:00 PM
 * @Project_Name : eduverse
*/
package dev.backend.eduverse.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.backend.eduverse.dto.PathwayDTO;

@Service
public interface PathwayService {
	boolean createPathway(PathwayDTO pathwayDTO);
	
	List<PathwayDTO> getAllPathway();

	PathwayDTO getPathwayByID(Long id);	

	boolean updatePathway(PathwayDTO pathwayDTO, Long id);

	boolean deletePathway(Long id);
}
