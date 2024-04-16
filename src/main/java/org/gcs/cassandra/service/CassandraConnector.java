package org.gcs.cassandra.service;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;

import java.net.InetSocketAddress;

import org.springframework.context.annotation.Bean;

public class CassandraConnector {

    private CqlSessionBuilder builder;
    private CqlSession session;
    private String keyspaceName;

    public void connect(final String node, final Integer port, final String dataCenter, final String keyspace) {
        builder = CqlSession.builder();
        keyspaceName = keyspace;
        builder.addContactPoint(new InetSocketAddress(node, port));
        builder.withLocalDatacenter(dataCenter);

        session = buildSession();
    }

    public @Bean CqlSession buildSession() {
        return builder.withKeyspace(keyspaceName).build();
     }

    public CqlSession getSession() {
        return this.session;
    }

    public void close() {
        session.close();
    }

}