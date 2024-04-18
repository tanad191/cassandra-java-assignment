package org.gcs.cassandra.repository;

import java.util.UUID;

import org.gcs.cassandra.entity.ScanLocation;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface ScanLocationRepository extends CassandraRepository<ScanLocation, UUID> {
}