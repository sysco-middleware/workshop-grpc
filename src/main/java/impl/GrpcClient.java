package impl;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import no.sysco.middleware.workshops.InvoiceOuterClass;
import no.sysco.middleware.workshops.InvoiceServiceGrpc;

public class GrpcClient {
    public static void main(String[] args) throws InterruptedException {

        final ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 8080)
                .usePlaintext(true)
                .build();

        final InvoiceServiceGrpc.InvoiceServiceBlockingStub stub = InvoiceServiceGrpc.newBlockingStub(channel);

        final InvoiceOuterClass.CreateRequest request = InvoiceOuterClass.CreateRequest.newBuilder()
                .setAmount(100.00f)
                .setCustomerId("XYZ123")
                .setState(InvoiceOuterClass.State.NEW)
                .build();


        final InvoiceOuterClass.Invoice response = stub.create(request);
        System.out.println(response);
        channel.shutdown();
//        channel.awaitTermination(10000,TimeUnit.SECONDS);
    }

}
