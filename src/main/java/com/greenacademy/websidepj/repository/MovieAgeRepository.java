package com.greenacademy.websidepj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenacademy.websidepj.entity.MovieAge;

@Repository
public interface MovieAgeRepository extends JpaRepository<MovieAge, Long> {

}
