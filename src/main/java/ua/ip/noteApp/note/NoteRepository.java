package ua.ip.noteApp.note;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NoteRepository extends JpaRepository<NoteDAO, UUID> {
    @Query("from NoteDAO n where n.user.id = :id")
    List<NoteDAO> findByUser_Id(@Param("id") UUID id);
}


