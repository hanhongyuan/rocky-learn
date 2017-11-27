package com.rocky.hadoop.rpc.demo;


public interface ClientServiceProtocol
{
    final long versionID = 1L;

    String service(String msg);
}
