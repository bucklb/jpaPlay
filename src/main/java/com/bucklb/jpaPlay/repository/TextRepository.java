package com.bucklb.jpaPlay.repository;

import com.bucklb.jpaPlay.model.Text;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextRepository extends JpaRepository<Text,Long> {
    Page<Text> findByPersonId(Long personId, Pageable pageable);

//  Looks like there are conventions that need to be worked with/around
//    Page<Text> findByMagic(Long personId, Pageable pageable);


}
