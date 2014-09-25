package com.nhn.pinpoint.profiler.context;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.nhn.pinpoint.bootstrap.context.ServerMetaData;
import com.nhn.pinpoint.bootstrap.context.ServerMetaDataHolder;
import com.nhn.pinpoint.bootstrap.context.ServiceInfo;

/**
 * @author hyungil.jeong
 */
public class DefaultServerMetaDataHolder implements ServerMetaDataHolder {

    private String serverName;
    private String vmArgs;
    private final Queue<ServiceInfo> serviceInfos;

    public DefaultServerMetaDataHolder() {
        this.serviceInfos = new ConcurrentLinkedQueue<ServiceInfo>();
    }

    @Override
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    @Override
    public void setVmArgs(String vmArgs) {
        this.vmArgs = vmArgs;
    }

    @Override
    public void addServiceInfo(String serviceName, List<String> serviceLibs) {
        ServiceInfo serviceInfo = new DefaultServiceInfo(serviceName, serviceLibs);
        this.serviceInfos.add(serviceInfo);
    }

    @Override
    public ServerMetaData getServerMetaData() {
        String serverName = this.serverName == null ? "" : this.serverName;
        String vmArgs = this.vmArgs == null ? "" : this.vmArgs;
        List<ServiceInfo> serviceInfos = new ArrayList<ServiceInfo>(this.serviceInfos);
        return new DefaultServerMetaData(serverName, vmArgs, serviceInfos);
    }

}
