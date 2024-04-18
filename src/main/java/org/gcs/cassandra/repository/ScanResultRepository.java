package org.gcs.cassandra.repository;

import java.util.UUID;

import org.gcs.cassandra.entity.ScanResult;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface ScanResultRepository extends CassandraRepository<ScanResult, UUID> {
}