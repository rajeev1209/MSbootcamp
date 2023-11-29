package com.learn.hotelmanagement.repository;

import com.learn.hotelmanagement.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {}
