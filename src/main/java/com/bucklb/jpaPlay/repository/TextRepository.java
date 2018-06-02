package com.bucklb.jpaPlay.repository;

import com.bucklb.jpaPlay.model.Text;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TextRepository extends JpaRepository<Text,Long> {

    // Tutorial suggested the PAGE approach.  Not sure it helps much though
    List<Text> findByPersonId(Long personId, Pageable pageable);

}
