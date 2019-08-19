package mirea.geps.core.service.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<FileDb, String> {

    Optional<FileDb> findByFileNameAndUser(String filename, UserDb user);

}
