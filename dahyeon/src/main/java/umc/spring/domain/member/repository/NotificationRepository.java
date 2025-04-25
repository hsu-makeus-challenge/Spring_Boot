package umc.spring.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import umc.spring.domain.member.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {}
