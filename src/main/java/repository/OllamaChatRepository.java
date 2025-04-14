package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.OllamaChatMsg;

@Repository
public interface OllamaChatRepository extends JpaRepository<OllamaChatMsg, Long> {
}
