package com.example.rest_api.repository;

import com.example.rest_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
                // repozytorium mapuje nazwy mateod na określone polecenia SQL
@Repository     // interfejs do realizacji poleceń z okrośloną encją w bazie danych
public interface UserRepository extends JpaRepository<User, Integer> {
}
