package com.ezdevz.gjett_sangen.repository;

import com.ezdevz.gjett_sangen.model.User;
import com.ezdevz.gjett_sangen.model.Video;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

    @NonNull List<Video> findAll();
    Optional<Video> findById(@NonNull Long id);
}
