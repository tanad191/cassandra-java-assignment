package org.gcs.cassandra;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

import org.gcs.cassandra.service.CassandraConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.oss.driver.api.core.CqlSession;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        CassandraConnector connector = new CassandraConnector();
        connector.connect("127.0.0.1", 9042, "datacenter1", "oboe");
        CqlSession session = connector.getSession();

        

        connector.close();
    }
}
