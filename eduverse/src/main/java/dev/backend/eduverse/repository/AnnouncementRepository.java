/**
 * @Author : Kyaw Zaw Htet @Date : 5/10/2024 @Time : 10:22 PM @Project_Name : eduverse
 */
package dev.backend.eduverse.repository;

import dev.backend.eduverse.model.Admin;
import dev.backend.eduverse.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    @Query(value = "SELECT * FROM announcement ORDER BY id LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Announcement> paginate(@Param("limit") int limit, @Param("offset") int offset);
}
