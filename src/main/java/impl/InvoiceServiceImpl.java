package impl;

import io.grpc.stub.StreamObserver;
import no.sysco.middleware.workshops.InvoiceOuterClass;
import no.sysco.middleware.workshops.InvoiceServiceGrpc;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

public final class InvoiceServiceImpl extends InvoiceServiceGrpc.InvoiceServiceImplBase {

    static Map<String, InvoiceOuterClass.Invoice> invoices = new HashMap<>();
    static Logger logger = Logger.getLogger(InvoiceServiceImpl.class.getName());

    @Override
    public void create(InvoiceOuterClass.CreateRequest request, StreamObserver<InvoiceOuterClass.Invoice> responseObserver) {

        final String id = UUID.randomUUID().toString();
        final InvoiceOuterClass.Invoice invoice = InvoiceOuterClass.Invoice.newBuilder()
                .setAmount(request.getAmount())
                .setCustomerId(request.getCustomerId())
                .setId(id)
                .setState(InvoiceOuterClass.State.NEW)
                .build();
        invoices.put(id, invoice);
        logger.info("Created invoice" + invoice.toString());
        responseObserver.onNext(invoice);

        responseObserver.onCompleted();
    }

    @Override
    public void get(InvoiceOuterClass.InvoiceRequest request, StreamObserver<InvoiceOuterClass.Invoice> responseObserver) {
        if (invoices != null && !invoices.isEmpty()) {
            final String id = request.getId();
            final InvoiceOuterClass.Invoice invoice = invoices.get(id);
            logger.info("Getting invoice" + invoice.toString());
            responseObserver.onNext(invoice);
        } else {
            responseObserver.onNext(null);
        }
        responseObserver.onCompleted();
    }

    @Override
    public void delete(InvoiceOuterClass.InvoiceRequest request, StreamObserver<InvoiceOuterClass.DeleteResponse> responseObserver) {
        if (invoices != null && !invoices.isEmpty()) {
            final InvoiceOuterClass.Invoice invoice = invoices.get(request.getId());
            invoices.remove(request.getId());
            InvoiceOuterClass.DeleteResponse res = InvoiceOuterClass.DeleteResponse.newBuilder()
                    .setId(request.getId())
                    .setOk(true)
                    .build();
            logger.info("Deleting invoice" + invoice.toString());
            responseObserver.onNext(res);
        } else {
            InvoiceOuterClass.DeleteResponse res = InvoiceOuterClass.DeleteResponse.newBuilder()
                    .setOk(false)
                    .setId(request.getId()).build();
            responseObserver.onNext(res);
        }
        responseObserver.onCompleted();
    }

    @Override
    public void update(InvoiceOuterClass.UpdateRequest request, StreamObserver<InvoiceOuterClass.Invoice> responseObserver) {
        if (invoices != null && !invoices.isEmpty()) {
            final InvoiceOuterClass.Invoice invoice = invoices.get(request.getId());
            final InvoiceOuterClass.Invoice newInvoice = InvoiceOuterClass.Invoice.newBuilder().
                    setAmount(request.getAmount()).
                    setCustomerId(invoice.getCustomerId()).
                    setState(invoice.getState())
                    .setId(request.getId()).build();
            invoices.replace(request.getId(), newInvoice);
            logger.info("Updating invoice" + newInvoice.toString());
            responseObserver.onNext(newInvoice);
        } else {
            responseObserver.onNext(null);
        }
        responseObserver.onCompleted();
    }
}
