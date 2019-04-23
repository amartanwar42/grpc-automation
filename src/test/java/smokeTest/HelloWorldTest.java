package smokeTest;

import clients.HelloWorldClient;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HelloWorldTest {

    @Test
    public void sayHello_Test()
    {
        HelloRequest helloRequest=HelloRequest.newBuilder()
                .setName("amar")
                .build();

        HelloWorldClient helloWorldClient=new HelloWorldClient("localhost",8080);

        HelloReply response = helloWorldClient.sayHello(helloRequest);

        Assert.assertEquals(response.getMessage(),"Testing");
    }
}
