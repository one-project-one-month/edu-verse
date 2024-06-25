/*
 * @Author : Alvin
 * @Date : 5/11/2024
 * @Time : 9:00 PM
 * @Project_Name : eduverse
 */
package dev.backend.eduverse.service;

import dev.backend.eduverse.dto.PathwayDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface PathwayService {
  boolean createPathway(PathwayDto pathwayDTO);

  List<PathwayDto> getAllPathway();

  PathwayDto getPathwayByID(Long id);

  boolean updatePathway(PathwayDto pathwayDTO, Long id);

  boolean deletePathway(Long id);
 
  List<PathwayDto> readPathwayByPagination(int pageNumber, int pageSize) throws IllegalAccessException;
}
