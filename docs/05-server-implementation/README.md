# Server implementation
[HOME](../../README.md)

The steps involved in implementing a server is split into 2 parts.
- Implementing InvoiceService methods and provide a business logic.
- Bootstrap gRPC server.

We have already implemented these for you in `no.sysco.middleware.workshops.impl` package. In this package you will see three files.
- GrpcClient.java : We will discuss this in [next](../06-client-implementation/README.md) section.
- GrpcServiceImpl.java : This implements the service methods and provides the core business logic.
- GrpcServer.java : This file bootstraps the grpc server, add the service implementation to the server.

Lets discuss last two here.
### GrpcServiceImpl.java

The **InvoiceService** and the **methods** that we defined in the protobuf file are available to us as static abstract classes in `no.sysco.middleware.workshops.InvoiceServiceGrpc` class. The code generation process provides an abstract static class `InvoiceServiceGrpc.InvoiceServiceImplBase`. This class contains abstract definitions of all methods. Lets have a more closer look at these
```
  public static abstract class InvoiceServiceImplBase implements io.grpc.BindableService {
    public void create(no.sysco.middleware.workshops.InvoiceOuterClass.CreateRequest request, io.grpc.stub.StreamObserver<no.sysco.middleware.workshops.InvoiceOuterClass.Invoice> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CREATE, responseObserver);
    }
    public void get(no.sysco.middleware.workshops.InvoiceOuterClass.InvoiceRequest request,io.grpc.stub.StreamObserver<no.sysco.middleware.workshops.InvoiceOuterClass.Invoice> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET, responseObserver);
    }
    public void delete(no.sysco.middleware.workshops.InvoiceOuterClass.InvoiceRequest request, io.grpc.stub.StreamObserver<no.sysco.middleware.workshops.InvoiceOuterClass.DeleteResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_DELETE, responseObserver);
    }
    public void update(no.sysco.middleware.workshops.InvoiceOuterClass.UpdateRequest request,io.grpc.stub.StreamObserver<no.sysco.middleware.workshops.InvoiceOuterClass.Invoice> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_UPDATE, responseObserver);
    }
    public void list(com.google.protobuf.Empty request, io.grpc.stub.StreamObserver<no.sysco.middleware.workshops.InvoiceOuterClass.Invoice> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_LIST, responseObserver);
    }
    ...
    ... other methods
    ...
    }
  }
```
To implement these business methods we need to extend **InvoiceServiceImplBase** class and override its methods. We do that in class **InvoiceServiceImpl.java**. 
Open [this](../../src/main/java/impl/InvoiceServiceImpl.java) file.

Instead of connecting to a database or any other persistance storage, we make use of `Map<String,Invoice>` to store these values. We start with defining a field store as `Map<String,Invoice>` and add 5 random invoices to it.
```
private Map<String, Invoice> generateRandomInvoices() {
    Random r = new Random();
    return IntStream.rangeClosed(1, 5).mapToObj(i -> Invoice.newBuilder()
            .setAmount(r.nextFloat() * 100)
            .setCustomerId(UUID.randomUUID().toString())
            .setId(UUID.randomUUID().toString())
            .setState(InvoiceOuterClass.State.NEW)
            .build()).collect(Collectors.toMap(Invoice::getId, invoice -> invoice, (a, b) -> b));
}
```

_create method_ : Prepares an Invoice from request and adds it to the store.
```
public void create(CreateRequest request, StreamObserver<Invoice> responseObserver) {
   final String id = UUID.randomUUID().toString();
   final Invoice invoice = Invoice.newBuilder()
           .setAmount(request.getAmount())
           .setCustomerId(request.getCustomerId())
           .setId(id)
           .setState(InvoiceOuterClass.State.NEW)
           .build();
   store.put(id, invoice);
   responseObserver.onNext(invoice);
   responseObserver.onCompleted();
}
```
_get method_ : Retrieves an Invoice by id.
```
public void get(InvoiceRequest request, StreamObserver<Invoice> responseObserver) {
   if (store != null && !store.isEmpty()) {
       final String id = request.getId();
       final Invoice invoice = store.get(id);
       responseObserver.onNext(invoice);
   } else {
        responseObserver.onNext(null);
   }
   responseObserver.onCompleted();
}
```
_delete method_ : Deletes an existing invoice by id
```
public void delete(InvoiceRequest request, StreamObserver<DeleteResponse> responseObserver) {
    if (store != null && !store.isEmpty()) {
       final Invoice invoice = store.get(request.getId());
       store.remove(request.getId());
       DeleteResponse res = DeleteResponse.newBuilder()
               .setId(request.getId())
               .setOk(true)
               .build();
       responseObserver.onNext(res);
    } else {
       DeleteResponse res = DeleteResponse.newBuilder()
               .setOk(false)
               .setId(request.getId()).build();
       responseObserver.onNext(res);
    }
    responseObserver.onCompleted();
}
```

_update method_ : Updates an Invoice. Only State and Amount can be updated.
```
public void update(InvoiceOuterClass.UpdateRequest request, StreamObserver<Invoice> responseObserver) {
   if (store != null && !store.isEmpty()) {
       final Invoice invoice = store.get(request.getId());
       final Invoice newInvoice = Invoice.newBuilder().
              setAmount(request.getAmount()).
              setCustomerId(invoice.getCustomerId()).
              setState(invoice.getState()).
              setId(request.getId()).build();
       store.replace(request.getId(), newInvoice);
       responseObserver.onNext(newInvoice);
   } else {
       responseObserver.onNext(null);
   }
   responseObserver.onCompleted();
}
```
_list method_ : Gets a list of invoices in store. An important point to note here is that protocol will not return a Collection of invoices. Instead it will stream each invoice in the store one at a time.
```
public void list(Empty request, StreamObserver<Invoice> responseObserver) {
    store.forEach((k, v) -> responseObserver.onNext(v));
    responseObserver.onCompleted();
}
```

### GrpcServer.java
Here we add the implementation done in previous step as service and run a gRPC server to listen for requests from clients and return the service responses.

```
public class GrpcServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        io.grpc.Server server = ServerBuilder.forPort(8080)
                .addService(new InvoiceServiceImpl())
                .build();

        server.start();
        System.out.println("Server started");
        server.awaitTermination();
    }
}
```

As you can see this bootstraps a server to listen to port 8080, adds InvoiceServiceImpl as service and starts the server. In the [Next](../06-client-implementation/README.md) section we will have a look at creating a gRPC client and calling methods on gRPC server.