/**
 * @Author : Kyaw Zaw Htet @Date : 5/10/2024 @Time : 10:25 PM @Project_Name : eduverse
 */
package dev.backend.eduverse.service.impl;

import dev.backend.eduverse.dto.AdminDto;
import dev.backend.eduverse.dto.AnnouncementDto;
import dev.backend.eduverse.exception.ResourceNotFoundException;
import dev.backend.eduverse.model.Admin;
import dev.backend.eduverse.model.Announcement;
import dev.backend.eduverse.model.Course;
import dev.backend.eduverse.repository.AdminRepository;
import dev.backend.eduverse.repository.AnnouncementRepository;
import dev.backend.eduverse.repository.CourseRepository;
import dev.backend.eduverse.service.AnnouncementService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

  private AnnouncementRepository announcementRepository;

  private AdminRepository adminRepository;

  private CourseRepository courseRepository;

  private ModelMapper modelMapper;

  @Override
  public List<AnnouncementDto> getAllAnnouncements() {

    List<Announcement> announcements = announcementRepository.findAll();

    return announcements.stream()
        .map((announcement -> modelMapper.map(announcement, AnnouncementDto.class)))
        .collect(Collectors.toList());
  }

  @Override
  public AnnouncementDto getAnnouncementById(Long id) {

    Announcement announcement =
        announcementRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Announcement", "id", id));
    return modelMapper.map(announcement, AnnouncementDto.class);
  }

  @Override
  public AnnouncementDto createAnnouncement(AnnouncementDto announcementDto) {

    Admin admin = adminRepository.findById(announcementDto.getAdminId()).orElseThrow();
    Course course = courseRepository.findById(announcementDto.getCourseId()).orElseThrow();

    Announcement announcement = modelMapper.map(announcementDto, Announcement.class);
    announcement.setAdmin(admin);
    announcement.setCourse(course);
    Announcement savedAnnouncement = announcementRepository.save(announcement);
    AnnouncementDto savedAnnouncementDto =
        modelMapper.map(savedAnnouncement, AnnouncementDto.class);
    return savedAnnouncementDto;
  }

  @Override
  public AnnouncementDto updateAnnouncement(AnnouncementDto announcementDto) {

    Announcement exitingAnnouncement =
        announcementRepository
            .findById(announcementDto.getId())
            .orElseThrow(
                () -> new ResourceNotFoundException("Announcement", "id", announcementDto.getId()));

    Admin exitingAdmin =
        adminRepository
            .findById(announcementDto.getAdminId())
            .orElseThrow(
                () -> new ResourceNotFoundException("Admin", "id", announcementDto.getAdminId()));
    Course exitingCourse =
        courseRepository
            .findById(announcementDto.getCourseId())
            .orElseThrow(
                () -> new ResourceNotFoundException("Course", "id", announcementDto.getCourseId()));

    exitingAnnouncement.setTitle(announcementDto.getTitle());
    exitingAnnouncement.setContent(announcementDto.getContent());
    exitingAnnouncement.setCreatedAt(announcementDto.getCreatedAt());
    exitingAnnouncement.setNotificationType(announcementDto.getNotificationType());
    exitingAnnouncement.setAdmin(exitingAdmin);
    exitingAnnouncement.setCourse(exitingCourse);

    Announcement updatedAnnouncement = announcementRepository.save(exitingAnnouncement);

    return modelMapper.map(updatedAnnouncement, AnnouncementDto.class);
  }

  @Override
  public void deleteAnnouncement(Long id) {

    Announcement exitingAnnouncement =
        announcementRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Announcement", "id", id));
    announcementRepository.deleteById(exitingAnnouncement.getId());
  }

  @Override
  public List<AnnouncementDto> paginate(int pageNo, int limit) {
    //if provided page number is less than 1, 1 will be the default pageNO
    pageNo = Math.max(pageNo, 1);

    //if provided size limit is less than 1, 10 will be the default size limit
    limit = (limit < 1) ? 10 : limit;

    int offset = (pageNo - 1) * limit;

    List<Announcement> announcementList = announcementRepository.paginate(limit, offset);

    return announcementList.stream()
            .map(announcement -> modelMapper.map(announcement, AnnouncementDto.class))
            .collect(Collectors.toList());
  }
}
