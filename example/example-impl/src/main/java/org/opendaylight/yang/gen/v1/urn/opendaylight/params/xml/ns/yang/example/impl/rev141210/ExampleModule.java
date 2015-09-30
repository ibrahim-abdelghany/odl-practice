package org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.example.impl.rev141210;

import org.opendaylight.controller.sal.binding.api.BindingAwareBroker;
import org.opendaylight.l2switch.example.ExampleImpl;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.example.rev150105.ExampleService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.example.impl.rev141210.AbstractExampleModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleModule extends AbstractExampleModule {
    
	private static final Logger log = LoggerFactory.getLogger(ExampleModule.class);
	
	public ExampleModule(org.opendaylight.controller.config.api.ModuleIdentifier identifier, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver) {
        super(identifier, dependencyResolver);
    }

    public ExampleModule(org.opendaylight.controller.config.api.ModuleIdentifier identifier, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver, org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.example.impl.rev141210.ExampleModule oldModule, java.lang.AutoCloseable oldInstance) {
        super(identifier, dependencyResolver, oldModule, oldInstance);
    }

    @Override
    public void customValidation() {
        // add custom validation form module attributes here.
    }

    @Override
    public java.lang.AutoCloseable createInstance() {
    	log.info("Example implementation instance created.");
    	final ExampleImpl example = new ExampleImpl();
    	final BindingAwareBroker.RpcRegistration<ExampleService> rpcRegistration = getRpcRegistryDependency()
                .addRpcImplementation(ExampleService.class, example);
    	
    	final class AutoCloseableExample implements AutoCloseable {

        @Override
        public void close() throws Exception {
                rpcRegistration.close();
                closeQuietly(example);
                log.info("Example implementation torn down.");
            }

            private void closeQuietly(final AutoCloseable resource) {
                try {
                    resource.close();
                } catch (final Exception e) {
                    log.debug("Ignoring exception while closing {}", resource, e);
                }
            }
        }

        AutoCloseable ret = new AutoCloseableExample();
        log.info("Example implementation instance created. Successful!");
        return ret;
    }

}
