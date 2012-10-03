package com.moowork.elasticsearch.hazelcast.discovery;

import java.util.List;

import org.elasticsearch.ElasticSearchException;
import org.elasticsearch.cluster.ClusterName;
import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.cluster.node.DiscoveryNodes;
import org.elasticsearch.common.collect.Lists;
import org.elasticsearch.common.component.AbstractLifecycleComponent;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.internal.Nullable;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.discovery.Discovery;
import org.elasticsearch.discovery.InitialStateDiscoveryListener;
import org.elasticsearch.discovery.zen.DiscoveryNodesProvider;
import org.elasticsearch.discovery.zen.publish.PublishClusterStateAction;
import org.elasticsearch.node.service.NodeService;
import org.elasticsearch.transport.TransportService;

public final class HazelcastDiscovery
    extends AbstractLifecycleComponent<Discovery>
    implements Discovery, DiscoveryNodesProvider, PublishClusterStateAction.NewClusterStateListener
{
    private final ClusterName clusterName;

    private final List<InitialStateDiscoveryListener> initialStateListeners;

    private DiscoveryNode localNode;

    private NodeService nodeService;

    private boolean master = false;

    private DiscoveryNodes latestDiscoNodes;

    private final PublishClusterStateAction publishClusterState;

    @Inject
    public HazelcastDiscovery( final Settings settings, final ClusterName clusterName, final TransportService transportService )
    {
        super( settings );
        this.clusterName = clusterName;
        this.initialStateListeners = Lists.newCopyOnWriteArrayList();
        this.publishClusterState = new PublishClusterStateAction( settings, transportService, this, this );
    }

    @Override
    public DiscoveryNode localNode()
    {
        return this.localNode;
    }

    @Override
    public void addListener( final InitialStateDiscoveryListener listener )
    {
        this.initialStateListeners.add( listener );
    }

    @Override
    public void removeListener( final InitialStateDiscoveryListener listener )
    {
        this.initialStateListeners.remove( listener );
    }

    @Override
    public String nodeDescription()
    {
        return this.clusterName.value() + "/" + this.localNode.id();
    }

    @Override
    public void setNodeService( @Nullable final NodeService nodeService )
    {
        this.nodeService = nodeService;
    }

    @Override
    public NodeService nodeService()
    {
        return this.nodeService;
    }

    @Override
    protected void doStart()
        throws ElasticSearchException
    {
        // TODO: Implement
    }

    @Override
    protected void doStop()
        throws ElasticSearchException
    {
        // TODO: Implement
    }

    @Override
    protected void doClose()
        throws ElasticSearchException
    {
        // TODO: Implement
    }

    @Override
    public void publish( final ClusterState clusterState )
    {
        if ( !this.master )
        {
            return;
        }

        if ( !this.lifecycle.started() )
        {
            return;
        }

        this.latestDiscoNodes = clusterState.nodes();
        this.publishClusterState.publish( clusterState );
    }

    @Override
    public DiscoveryNodes nodes()
    {
        if ( this.latestDiscoNodes != null )
        {
            return this.latestDiscoNodes;
        }

        return DiscoveryNodes.newNodesBuilder().put( this.localNode ).localNodeId( this.localNode.id() ).build();
    }

    @Override
    public void onNewClusterState( final ClusterState clusterState )
    {
        // TODO: Implement
    }
}
