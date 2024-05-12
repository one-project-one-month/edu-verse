/*
 * @Author : Alvin
 * @Date : 5/11/2024
 * @Time : 9:00 PM
 * @Project_Name : eduverse
*/
package dev.backend.eduverse.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.backend.eduverse.dto.AdminRoleDTO;

@Service
public interface AdminRoleService {
boolean createAdminRole(AdminRoleDTO adminRoleDTO);
	
	List<AdminRoleDTO> getAllAdminRole();

	AdminRoleDTO getAdminRoleByID(Long id);

	boolean updateAdminRole(AdminRoleDTO adminRoleDTO, Long id);

	boolean deleteAdminRole(Long id);
}
