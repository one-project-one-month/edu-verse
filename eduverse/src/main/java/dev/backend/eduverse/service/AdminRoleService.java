/*
 * @Author : Alvin
 * @Date : 5/11/2024
 * @Time : 9:00 PM
 * @Project_Name : eduverse
 */
package dev.backend.eduverse.service;

import dev.backend.eduverse.dto.AdminRoleDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface AdminRoleService {
  boolean createAdminRole(AdminRoleDto adminRoleDTO);

  List<AdminRoleDto> getAllAdminRole();

  AdminRoleDto getAdminRoleByID(Long id);

  boolean updateAdminRole(AdminRoleDto adminRoleDTO, Long id);

  boolean deleteAdminRole(Long id);

  List<AdminRoleDto> readAdminRoleByPagniation(int pageNumber, int pageSize) throws IllegalAccessException;
}
