package org.gcs.cassandra.repository;

import java.util.List;
import java.util.UUID;

import org.gcs.cassandra.entity.ScanLocation;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface ScanLocationRepository extends CassandraRepository<ScanLocation, UUID> {
  @AllowFiltering
  List<ScanLocation> findByCoordinates(String coordinates);
}