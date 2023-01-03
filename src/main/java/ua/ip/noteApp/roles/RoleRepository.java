package ua.ip.noteApp.roles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<RoleDAO, UUID> {
    Optional<RoleDAO> findByName(String name);
}