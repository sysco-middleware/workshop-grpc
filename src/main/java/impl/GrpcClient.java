package impl;

import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import no.sysco.middleware.workshops.InvoiceOuterClass;
import no.sysco.middleware.workshops.InvoiceServiceGrpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

public class GrpcClient {
    private static Logger logger = LoggerFactory.getLogger(GrpcClient.class);

    public static void main(String[] args) throws InterruptedException {

        final ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 8080)
                .usePlaintext(true)
                .build();

        final InvoiceServiceGrpc.InvoiceServiceBlockingStub stub = InvoiceServiceGrpc.newBlockingStub(channel);
//
//        final InvoiceOuterClass.CreateRequest request = InvoiceOuterClass.CreateRequest.newBuilder()
//                .setAmount(100.00f)
//                .setCustomerId("XYZ123")
//                .setState(InvoiceOuterClass.State.NEW)
//                .build();
//
//
//        final InvoiceOuterClass.Invoice response = stub.create(request);
//        System.out.println(response);
        final Iterator<InvoiceOuterClass.Invoice> invoices;
        try {
            invoices = stub.list(Empty.newBuilder().build());
            invoices.forEachRemaining(invoice -> logger.info("Recieved invoice :: \n{}state: {}", invoice.toString(), invoice.getState().name()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        channel.shutdown();
//        channel.awaitTermination(10000,TimeUnit.SECONDS);
    }

}
