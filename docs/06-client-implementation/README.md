# Implementing gRPC client 
[HOME](../../README.md)

So far we have defined our payload and services, generated code from these protobuf definition and implemented server. Now its time to implement the gRPC client and run our code.

To keep the example simple, we will perform below operation in sequence to show the responses for all the operations.
- Create an invoice.
- Get the invoice.
- Update the Amount and Status of invoice.
- Delete the invoice.
- List all the invoices in the store.

Like other examples, we have already implemented these for you. Open [file](../../src/main/java/impl/GrpcClient.java):

_create an invoice_
```
private static String createInvoice(final InvoiceServiceGrpc.InvoiceServiceBlockingStub stub) {
    final InvoiceOuterClass.CreateRequest request = InvoiceOuterClass.CreateRequest.newBuilder()
                .setAmount(100.00f)
                .setCustomerId(UUID.randomUUID().toString())
                .setState(InvoiceOuterClass.State.NEW)
                .build();

    final InvoiceOuterClass.Invoice invoice = stub.create(request);
    return invoice.getId();
 }
```
_get an invoice by id_
```
private static void getInvoice(final String id, final InvoiceServiceGrpc.InvoiceServiceBlockingStub stub) {
    final InvoiceOuterClass.InvoiceRequest request = InvoiceOuterClass.InvoiceRequest.newBuilder().setId(id).build();
    final InvoiceOuterClass.Invoice invoice = stub.get(request);
}
```
_update an invoice_
```
private static void updateInvoice(final String id, final InvoiceServiceGrpc.InvoiceServiceBlockingStub stub) {
    final InvoiceOuterClass.UpdateRequest request = InvoiceOuterClass.UpdateRequest.newBuilder()
                .setAmount(200.0f)
                .setId(id)
                .setState(InvoiceOuterClass.State.FAILED)
                .build();
    final InvoiceOuterClass.Invoice response = stub.update(request);
}
```
_delete an invoice_
```
private static void deleteInvoice(final String id, final InvoiceServiceGrpc.InvoiceServiceBlockingStub stub) {
    final InvoiceOuterClass.InvoiceRequest request = InvoiceOuterClass.InvoiceRequest.newBuilder().setId(id).build();
    final InvoiceOuterClass.DeleteResponse response = stub.delete(request);
}
```
_list all invoices_
```
private static void listInvoices(final InvoiceServiceGrpc.InvoiceServiceBlockingStub stub) {
    final Iterator<InvoiceOuterClass.Invoice> invoices;
    try {
        invoices = stub.list(Empty.newBuilder().build());
        invoices.forEachRemaining(invoice -> logger.info(">>> listInvoices :: \n{}state: {}", invoice.toString(), invoice.getState().name()));
    } catch (Exception e) {
        e.printStackTrace();
    }
}
```

Now that we have implemented our client logic, lets create a channel and a stub to talk to server. We will start sending request to server using the stubs
```
public static void main(String[] args) throws InterruptedException {

    final ManagedChannel channel = ManagedChannelBuilder
            .forAddress("localhost", 8080)      // send request on localhost:8080
            .usePlaintext(true)                 // setting for skipping TLS/SSL security to keep things simple
            .build();

    final InvoiceServiceGrpc.InvoiceServiceBlockingStub stub = InvoiceServiceGrpc.newBlockingStub(channel);

    final String id = createInvoice(stub);
    getInvoice(id, stub);
    updateInvoice(id, stub);
    deleteInvoice(id, stub);
    listInvoices(stub);
    channel.shutdown();
}
```

## Running the examples