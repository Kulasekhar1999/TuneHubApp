package com.kodnest.tunehub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodnest.tunehub.entity.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer>{
public List<Playlist> findAll();
}
