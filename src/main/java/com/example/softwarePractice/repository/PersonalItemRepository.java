package com.example.softwarePractice.repository;

import com.example.softwarePractice.domain.PersonalItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonalItemRepository extends JpaRepository<PersonalItem, Long> {

}
