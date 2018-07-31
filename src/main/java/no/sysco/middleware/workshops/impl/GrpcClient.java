package no.sysco.middleware.workshops.impl;

import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import no.sysco.middleware.workshops.InvoiceOuterClass;
import no.sysco.middleware.workshops.InvoiceServiceGrpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.UUID;

public class GrpcClient {
    private static Logger logger = LoggerFactory.getLogger(GrpcClient.class);

    /**
     * Here we are bootstrapping the client by creating a ManagedChannel. All the server
     * related properties and behaviour can be set using the ManagedChannel Builder.
     *
     * ManagedChannel is then used to generate a client stub which would be used to call the gRPC methods.
     * The stub can be used to set client(request) behaviours. For example, in this example we are using
     * .withWaitForReady() api to wait for the connection to be available before sending any request
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        logger.info("Creating a managed channel to communicate with server");
        final ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 8080)              // send request on localhost:8080
                .usePlaintext()                                         // setting for skipping TLS/SSL security to keep things simple
                // .keepAliveTime(3,TimeUnit.MINUTES                    // setting keep-alive time
                // .keepAliveTimeout(4,TimeUnit.SECONDS)                // setting keep-alive timeout
                // .enableRetry()                                       // enable retries if the connection drops
                // .maxRetryAttempts(5)                                 // set number of retry attempt before reporting error
                .build();

        logger.info("Creating a stub to communicate with server");
        // We generate a blocking stub to call server methods
        final InvoiceServiceGrpc.InvoiceServiceBlockingStub stub = InvoiceServiceGrpc
                .newBlockingStub(channel)
                .withWaitForReady()                                     // client should wait for server to be available before trying a request
                // .withDeadlineAfter(30,TimeUnit.SECONDS)              // Wait for 30 seconds before reporting error
                // .withExecutor()                                      // provide a custom executor
                // .withMaxInboundMessageSize(2400)                     // set the default message size
                // .withCallCredentials()                               // set up credentials to establish server connection
                ;

        final String id = createInvoice(stub);
        logger.info("*********************");
        getInvoice(id, stub);
        logger.info("*********************");
        updateInvoice(id, stub);
        logger.info("*********************");
        deleteInvoice(id, stub);
        logger.info("*********************");
        listInvoices(stub);
        logger.info("*********************");
        channel.shutdown();
    }

    private static String createInvoice(final InvoiceServiceGrpc.InvoiceServiceBlockingStub stub) {
        logger.info(">>> createInvoice :: start ");
        // Create a request
        final InvoiceOuterClass.CreateRequest request = InvoiceOuterClass.CreateRequest.newBuilder()
                .setAmount(100.00f)
                .setCustomerId(UUID.randomUUID().toString())
                .setState(InvoiceOuterClass.State.NEW)
                .build();
        logger.info(">>> createInvoice :: request :: {}", request.toString());

        // Call create method on stub
        final InvoiceOuterClass.Invoice invoice = stub.create(request);
        logger.info(">>> createInvoice :: invoice :: {}", invoice.toString());

        // return invoice-id
        return invoice.getId();
    }

    private static void getInvoice(final String id, final InvoiceServiceGrpc.InvoiceServiceBlockingStub stub) {
        logger.info(">>> getInvoice :: start :: id :: {}", id);
        // Create a request
        final InvoiceOuterClass.InvoiceRequest request = InvoiceOuterClass.InvoiceRequest.newBuilder().setId(id).build();
        logger.info(">>> getInvoice :: request :: {}", request.toString());

        // Call get method on stub
        final InvoiceOuterClass.Invoice invoice = stub.get(request);
        logger.info(">>> getInvoice :: invoice :: {}", invoice.toString());
    }

    private static void updateInvoice(final String id, final InvoiceServiceGrpc.InvoiceServiceBlockingStub stub) {
        logger.info(">>> updateInvoice :: start :: id :: {}", id);

        // Create a request
        final InvoiceOuterClass.UpdateRequest request = InvoiceOuterClass.UpdateRequest.newBuilder()
                .setAmount(200.0f)
                .setId(id)
                .setState(InvoiceOuterClass.State.FAILED)
                .build();
        logger.info(">>> updateInvoice :: request :: {}", request.toString());

        // Call the method
        final InvoiceOuterClass.Invoice response = stub.update(request);
        logger.info(">>> updateInvoice :: response ::  {}", response.toString());
    }

    private static void deleteInvoice(final String id, final InvoiceServiceGrpc.InvoiceServiceBlockingStub stub) {
        logger.info(">>> deleteInvoice :: start :: id :: {}", id);
        // Create a request
        final InvoiceOuterClass.InvoiceRequest request = InvoiceOuterClass.InvoiceRequest.newBuilder().setId(id).build();
        logger.info(">>> deleteInvoice :: request :: {}", request.toString());
        // Call the method
        final InvoiceOuterClass.DeleteResponse response = stub.delete(request);
        logger.info(">>> deleteInvoice :: response ::  {}", response.toString());
    }

    private static void listInvoices(final InvoiceServiceGrpc.InvoiceServiceBlockingStub stub) {
        logger.info(">>> listInvoices :: start ");
        final Iterator<InvoiceOuterClass.Invoice> invoices;
        try {
            invoices = stub.list(Empty.newBuilder().build());
            invoices.forEachRemaining(invoice -> logger.info(">>> listInvoices :: \n{}state: {}", invoice.toString(), invoice.getState().name()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
