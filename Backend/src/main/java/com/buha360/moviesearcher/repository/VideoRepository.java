package com.buha360.moviesearcher.repository;

import com.buha360.moviesearcher.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long>, QuerydslPredicateExecutor<Video> {

}