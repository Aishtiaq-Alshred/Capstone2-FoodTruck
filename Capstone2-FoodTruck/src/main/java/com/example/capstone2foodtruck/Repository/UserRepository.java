package com.example.capstone2foodtruck.Repository;

import com.example.capstone2foodtruck.Model.Review;
import com.example.capstone2foodtruck.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

   User findUserById(Integer id);










}
