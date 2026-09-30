package com.example.networkmonitoring.Repository;

import com.example.networkmonitoring.Model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {

}