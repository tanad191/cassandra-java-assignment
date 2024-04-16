package org.gcs.cassandra;

import org.gcs.cassandra.controller.ScanLocationController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "org.gcs.cassandra.cassandraconfig" })
@ComponentScan(basePackageClasses = { ScanLocationController.class })
public class CassandraJavaAssignment {
	
    public static void main(String[] args) {
		SpringApplication.run(CassandraJavaAssignment.class, args);
    }
}
