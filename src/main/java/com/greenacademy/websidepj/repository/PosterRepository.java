package com.greenacademy.websidepj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenacademy.websidepj.entity.Poster;

@Repository
public interface PosterRepository extends JpaRepository<Poster, Long> {

}
