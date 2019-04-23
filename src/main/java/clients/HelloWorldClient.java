package clients;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloWorldClient
{
    private static final Logger logger = Logger.getLogger(HelloWorldClient.class.getName());
    private ManagedChannel channel;
    private GreeterGrpc.GreeterBlockingStub blockingStub;
    private GreeterGrpc.GreeterStub asyncStub;

    public HelloWorldClient(String host, int port) {

        this(ManagedChannelBuilder.forAddress(host,port).usePlaintext());
        blockingStub = GreeterGrpc.newBlockingStub(channel);
        asyncStub = GreeterGrpc.newStub(channel);
    }


    public HelloWorldClient(ManagedChannelBuilder<?> channelBuilder) {
        channel = channelBuilder.build();
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public HelloReply sayHello(HelloRequest helloRequest)
    {
        HelloReply helloReply=null;

        try
        {
            helloReply= blockingStub.sayHello(helloRequest);
        }
        catch (StatusRuntimeException e)
        {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
        }
        return helloReply;
    }

}
