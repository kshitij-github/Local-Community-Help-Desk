package com.desk.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desk.main.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> { }
