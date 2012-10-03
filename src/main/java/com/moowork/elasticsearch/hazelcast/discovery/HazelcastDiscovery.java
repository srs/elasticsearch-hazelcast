package com.moowork.elasticsearch.hazelcast.discovery;

import org.elasticsearch.ElasticSearchException;
import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.component.AbstractLifecycleComponent;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.internal.Nullable;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.discovery.Discovery;
import org.elasticsearch.discovery.InitialStateDiscoveryListener;
import org.elasticsearch.node.service.NodeService;

public final class HazelcastDiscovery
    extends AbstractLifecycleComponent<Discovery>
    implements Discovery
{
    @Inject
    public HazelcastDiscovery( final Settings settings )
    {
        super( settings );
    }

    @Override
    protected void doStart()
        throws ElasticSearchException
    {
    }

    @Override
    protected void doStop()
        throws ElasticSearchException
    {
    }

    @Override
    protected void doClose()
        throws ElasticSearchException
    {
    }

    @Override
    public DiscoveryNode localNode()
    {
        return null;
    }

    @Override
    public void addListener( final InitialStateDiscoveryListener listener )
    {
    }

    @Override
    public void removeListener( final InitialStateDiscoveryListener listener )
    {
    }

    @Override
    public String nodeDescription()
    {
        return null;
    }

    @Override
    public void setNodeService( @Nullable final NodeService nodeService )
    {
    }

    @Override
    public void publish( final ClusterState clusterState )
    {
    }
}
