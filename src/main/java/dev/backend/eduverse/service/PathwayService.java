/*
 * @Author : Alvin
 * @Date : 5/11/2024
 * @Time : 9:00 PM
 * @Project_Name : eduverse
 */
package dev.backend.eduverse.service;

import dev.backend.eduverse.dto.PathwayDTO;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface PathwayService {
  boolean createPathway(PathwayDTO pathwayDTO);

  List<PathwayDTO> getAllPathway();

  PathwayDTO getPathwayByID(Long id);

  boolean updatePathway(PathwayDTO pathwayDTO, Long id);

  boolean deletePathway(Long id);
 
  List<PathwayDTO> readPathwayByPagination(int pageNumber, int pageSize) throws IllegalAccessException;
}
