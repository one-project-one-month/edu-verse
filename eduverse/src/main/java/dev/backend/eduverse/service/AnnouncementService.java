/**
 * @Author : Kyaw Zaw Htet @Date : 5/10/2024 @Time : 10:22 PM @Project_Name : eduverse
 */
package dev.backend.eduverse.service;

import dev.backend.eduverse.dto.AdminDto;
import dev.backend.eduverse.dto.AnnouncementDto;
import java.util.List;

public interface AnnouncementService {

  List<AnnouncementDto> getAllAnnouncements();

  AnnouncementDto getAnnouncementById(Long id);

  AnnouncementDto createAnnouncement(AnnouncementDto announcementDto);

  AnnouncementDto updateAnnouncement(AnnouncementDto announcementDto);

  void deleteAnnouncement(Long id);

  List<AnnouncementDto> paginate(int pageNo, int limit);
}
