package fourschlag.services.db;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.policies.DefaultRetryPolicy;
import com.datastax.driver.mapping.MappingManager;

public class CassandraConnection {
    private Cluster cluster;
    private Session session;
    private MappingManager manager;

    public CassandraConnection() {
        cluster = Cluster
                .builder()
                .addContactPoint(ClusterEndpoints.NODE1.getAdress())
                .withRetryPolicy(DefaultRetryPolicy.INSTANCE)
                .build();
        session = cluster.connect(KeyspaceNames.ORIGINAL_VERSION.getKeyspace());
        manager = new MappingManager(session);
    }

    public Session getSession() {
        return session;
    }

    public void closeConnection() {
        cluster.close();
    }

    public MappingManager getManager() { return manager; }
}