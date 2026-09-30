package com.example.networkmonitoring.Repository;
import com.example.networkmonitoring.Model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location , Long> {
    
}
