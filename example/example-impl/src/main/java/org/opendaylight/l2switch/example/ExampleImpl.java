package org.opendaylight.l2switch.example;

import java.util.concurrent.Future;

import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.example.rev150105.ExampleService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.example.rev150105.HelloWorldInput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.example.rev150105.HelloWorldOutput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.example.rev150105.HelloWorldOutputBuilder;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.opendaylight.yangtools.yang.common.RpcResultBuilder;

import com.google.common.util.concurrent.Futures;

public class ExampleImpl implements ExampleService, AutoCloseable{

	@Override
	public Future<RpcResult<HelloWorldOutput>> helloWorld(HelloWorldInput input) {
		HelloWorldOutputBuilder outputBuilder = new HelloWorldOutputBuilder();
		outputBuilder.setGreeting("SAUGO says hello to "+input.getName());
		return Futures.immediateFuture(RpcResultBuilder.success(outputBuilder.build()).build());
	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		return;
		
	}


}
