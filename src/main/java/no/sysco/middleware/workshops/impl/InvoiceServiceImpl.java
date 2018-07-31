package no.sysco.middleware.workshops.impl;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import no.sysco.middleware.workshops.InvoiceOuterClass;
import no.sysco.middleware.workshops.InvoiceOuterClass.CreateRequest;
import no.sysco.middleware.workshops.InvoiceOuterClass.DeleteResponse;
import no.sysco.middleware.workshops.InvoiceOuterClass.Invoice;
import no.sysco.middleware.workshops.InvoiceOuterClass.InvoiceRequest;
import no.sysco.middleware.workshops.InvoiceServiceGrpc.InvoiceServiceImplBase;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class InvoiceServiceImpl extends InvoiceServiceImplBase {

    private static Map<String, Invoice> store = new HashMap<>();
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(InvoiceServiceImpl.class);

    public InvoiceServiceImpl() {
        store = this.generateRandomInvoices();
    }

    // Add 5 new Invoices to the store
    private Map<String, Invoice> generateRandomInvoices() {
        Random r = new Random();
        return IntStream.rangeClosed(1, 5).mapToObj(i -> Invoice.newBuilder()
                .setAmount(r.nextFloat() * 100)
                .setCustomerId(UUID.randomUUID().toString())
                .setId(UUID.randomUUID().toString())
                .setState(InvoiceOuterClass.State.NEW)
                .build()).collect(Collectors.toMap(Invoice::getId, invoice -> invoice, (a, b) -> b));
    }

    @Override
    public void list(Empty request, StreamObserver<Invoice> responseObserver) {
        logger.info(">>> list :: request :: {}", request.toString());
        store.forEach((k, v) -> responseObserver.onNext(v));
        responseObserver.onCompleted();
    }

    @Override
    public void create(CreateRequest request, StreamObserver<Invoice> responseObserver) {
        logger.info(">>> create :: request :: {}", request.toString());
        final String id = UUID.randomUUID().toString();
        final Invoice invoice = Invoice.newBuilder()
                .setAmount(request.getAmount())
                .setCustomerId(request.getCustomerId())
                .setId(id)
                .setState(InvoiceOuterClass.State.NEW)
                .build();
        store.put(id, invoice);
        logger.info(">>> create :: invoice :: \n{}", invoice.toString());
        responseObserver.onNext(invoice);
        responseObserver.onCompleted();
    }

    @Override
    public void get(InvoiceRequest request, StreamObserver<Invoice> responseObserver) {
        logger.info(">>> get :: request :: {}", request.toString());
        if (store != null && !store.isEmpty()) {
            final String id = request.getId();
            final Invoice invoice = store.get(id);
            logger.info(">>> get :: invoice :: {}", invoice.toString());
            responseObserver.onNext(invoice);
        } else {
            responseObserver.onNext(null);
        }
        responseObserver.onCompleted();
    }

    @Override
    public void delete(InvoiceRequest request, StreamObserver<DeleteResponse> responseObserver) {
        logger.info(">>> delete :: request :: {}", request.toString());
        if (store != null && !store.isEmpty()) {
            final Invoice invoice = store.get(request.getId());
            store.remove(request.getId());
            DeleteResponse res = DeleteResponse.newBuilder()
                    .setId(request.getId())
                    .setOk(true)
                    .build();
            logger.info(">> deleted :: invoice :: {} ", invoice.toString());
            responseObserver.onNext(res);
        } else {
            DeleteResponse res = DeleteResponse.newBuilder()
                    .setOk(false)
                    .setId(request.getId()).build();
            responseObserver.onNext(res);
        }
        responseObserver.onCompleted();
    }

    @Override
    public void update(InvoiceOuterClass.UpdateRequest request, StreamObserver<Invoice> responseObserver) {
        logger.info(">>> update :: request :: {}", request.toString());
        if (store != null && !store.isEmpty()) {
            final Invoice invoice = store.get(request.getId());
            final Invoice newInvoice = Invoice.newBuilder().
                    setAmount(request.getAmount()).
                    setCustomerId(invoice.getCustomerId()).
                    setState(invoice.getState())
                    .setId(request.getId()).build();
            store.replace(request.getId(), newInvoice);
            logger.info(">>> updated :: invoice :: {}", newInvoice.toString());
            responseObserver.onNext(newInvoice);
        } else {
            responseObserver.onNext(null);
        }
        responseObserver.onCompleted();
    }
}
