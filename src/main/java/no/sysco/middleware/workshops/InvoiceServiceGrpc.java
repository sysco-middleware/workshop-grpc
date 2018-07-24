package no.sysco.middleware.workshops;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.7.0)",
    comments = "Source: Invoice.proto")
public final class InvoiceServiceGrpc {

  private InvoiceServiceGrpc() {}

  public static final String SERVICE_NAME = "InvoiceService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<no.sysco.middleware.workshops.InvoiceOuterClass.CreateRequest,
      no.sysco.middleware.workshops.InvoiceOuterClass.Invoice> METHOD_CREATE =
      io.grpc.MethodDescriptor.<no.sysco.middleware.workshops.InvoiceOuterClass.CreateRequest, no.sysco.middleware.workshops.InvoiceOuterClass.Invoice>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "InvoiceService", "Create"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              no.sysco.middleware.workshops.InvoiceOuterClass.CreateRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              no.sysco.middleware.workshops.InvoiceOuterClass.Invoice.getDefaultInstance()))
          .setSchemaDescriptor(new InvoiceServiceMethodDescriptorSupplier("Create"))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<no.sysco.middleware.workshops.InvoiceOuterClass.InvoiceRequest,
      no.sysco.middleware.workshops.InvoiceOuterClass.Invoice> METHOD_GET =
      io.grpc.MethodDescriptor.<no.sysco.middleware.workshops.InvoiceOuterClass.InvoiceRequest, no.sysco.middleware.workshops.InvoiceOuterClass.Invoice>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "InvoiceService", "Get"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              no.sysco.middleware.workshops.InvoiceOuterClass.InvoiceRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              no.sysco.middleware.workshops.InvoiceOuterClass.Invoice.getDefaultInstance()))
          .setSchemaDescriptor(new InvoiceServiceMethodDescriptorSupplier("Get"))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<no.sysco.middleware.workshops.InvoiceOuterClass.InvoiceRequest,
      no.sysco.middleware.workshops.InvoiceOuterClass.DeleteResponse> METHOD_DELETE =
      io.grpc.MethodDescriptor.<no.sysco.middleware.workshops.InvoiceOuterClass.InvoiceRequest, no.sysco.middleware.workshops.InvoiceOuterClass.DeleteResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "InvoiceService", "Delete"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              no.sysco.middleware.workshops.InvoiceOuterClass.InvoiceRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              no.sysco.middleware.workshops.InvoiceOuterClass.DeleteResponse.getDefaultInstance()))
          .setSchemaDescriptor(new InvoiceServiceMethodDescriptorSupplier("Delete"))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<no.sysco.middleware.workshops.InvoiceOuterClass.UpdateRequest,
      no.sysco.middleware.workshops.InvoiceOuterClass.Invoice> METHOD_UPDATE =
      io.grpc.MethodDescriptor.<no.sysco.middleware.workshops.InvoiceOuterClass.UpdateRequest, no.sysco.middleware.workshops.InvoiceOuterClass.Invoice>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "InvoiceService", "Update"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              no.sysco.middleware.workshops.InvoiceOuterClass.UpdateRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              no.sysco.middleware.workshops.InvoiceOuterClass.Invoice.getDefaultInstance()))
          .setSchemaDescriptor(new InvoiceServiceMethodDescriptorSupplier("Update"))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static InvoiceServiceStub newStub(io.grpc.Channel channel) {
    return new InvoiceServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static InvoiceServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new InvoiceServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static InvoiceServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new InvoiceServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class InvoiceServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void create(no.sysco.middleware.workshops.InvoiceOuterClass.CreateRequest request,
        io.grpc.stub.StreamObserver<no.sysco.middleware.workshops.InvoiceOuterClass.Invoice> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CREATE, responseObserver);
    }

    /**
     */
    public void get(no.sysco.middleware.workshops.InvoiceOuterClass.InvoiceRequest request,
        io.grpc.stub.StreamObserver<no.sysco.middleware.workshops.InvoiceOuterClass.Invoice> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET, responseObserver);
    }

    /**
     */
    public void delete(no.sysco.middleware.workshops.InvoiceOuterClass.InvoiceRequest request,
        io.grpc.stub.StreamObserver<no.sysco.middleware.workshops.InvoiceOuterClass.DeleteResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_DELETE, responseObserver);
    }

    /**
     */
    public void update(no.sysco.middleware.workshops.InvoiceOuterClass.UpdateRequest request,
        io.grpc.stub.StreamObserver<no.sysco.middleware.workshops.InvoiceOuterClass.Invoice> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_UPDATE, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_CREATE,
            asyncUnaryCall(
              new MethodHandlers<
                no.sysco.middleware.workshops.InvoiceOuterClass.CreateRequest,
                no.sysco.middleware.workshops.InvoiceOuterClass.Invoice>(
                  this, METHODID_CREATE)))
          .addMethod(
            METHOD_GET,
            asyncUnaryCall(
              new MethodHandlers<
                no.sysco.middleware.workshops.InvoiceOuterClass.InvoiceRequest,
                no.sysco.middleware.workshops.InvoiceOuterClass.Invoice>(
                  this, METHODID_GET)))
          .addMethod(
            METHOD_DELETE,
            asyncUnaryCall(
              new MethodHandlers<
                no.sysco.middleware.workshops.InvoiceOuterClass.InvoiceRequest,
                no.sysco.middleware.workshops.InvoiceOuterClass.DeleteResponse>(
                  this, METHODID_DELETE)))
          .addMethod(
            METHOD_UPDATE,
            asyncUnaryCall(
              new MethodHandlers<
                no.sysco.middleware.workshops.InvoiceOuterClass.UpdateRequest,
                no.sysco.middleware.workshops.InvoiceOuterClass.Invoice>(
                  this, METHODID_UPDATE)))
          .build();
    }
  }

  /**
   */
  public static final class InvoiceServiceStub extends io.grpc.stub.AbstractStub<InvoiceServiceStub> {
    private InvoiceServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private InvoiceServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected InvoiceServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new InvoiceServiceStub(channel, callOptions);
    }

    /**
     */
    public void create(no.sysco.middleware.workshops.InvoiceOuterClass.CreateRequest request,
        io.grpc.stub.StreamObserver<no.sysco.middleware.workshops.InvoiceOuterClass.Invoice> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CREATE, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void get(no.sysco.middleware.workshops.InvoiceOuterClass.InvoiceRequest request,
        io.grpc.stub.StreamObserver<no.sysco.middleware.workshops.InvoiceOuterClass.Invoice> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void delete(no.sysco.middleware.workshops.InvoiceOuterClass.InvoiceRequest request,
        io.grpc.stub.StreamObserver<no.sysco.middleware.workshops.InvoiceOuterClass.DeleteResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DELETE, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void update(no.sysco.middleware.workshops.InvoiceOuterClass.UpdateRequest request,
        io.grpc.stub.StreamObserver<no.sysco.middleware.workshops.InvoiceOuterClass.Invoice> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UPDATE, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class InvoiceServiceBlockingStub extends io.grpc.stub.AbstractStub<InvoiceServiceBlockingStub> {
    private InvoiceServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private InvoiceServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected InvoiceServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new InvoiceServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public no.sysco.middleware.workshops.InvoiceOuterClass.Invoice create(no.sysco.middleware.workshops.InvoiceOuterClass.CreateRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CREATE, getCallOptions(), request);
    }

    /**
     */
    public no.sysco.middleware.workshops.InvoiceOuterClass.Invoice get(no.sysco.middleware.workshops.InvoiceOuterClass.InvoiceRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET, getCallOptions(), request);
    }

    /**
     */
    public no.sysco.middleware.workshops.InvoiceOuterClass.DeleteResponse delete(no.sysco.middleware.workshops.InvoiceOuterClass.InvoiceRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_DELETE, getCallOptions(), request);
    }

    /**
     */
    public no.sysco.middleware.workshops.InvoiceOuterClass.Invoice update(no.sysco.middleware.workshops.InvoiceOuterClass.UpdateRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_UPDATE, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class InvoiceServiceFutureStub extends io.grpc.stub.AbstractStub<InvoiceServiceFutureStub> {
    private InvoiceServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private InvoiceServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected InvoiceServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new InvoiceServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<no.sysco.middleware.workshops.InvoiceOuterClass.Invoice> create(
        no.sysco.middleware.workshops.InvoiceOuterClass.CreateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CREATE, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<no.sysco.middleware.workshops.InvoiceOuterClass.Invoice> get(
        no.sysco.middleware.workshops.InvoiceOuterClass.InvoiceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<no.sysco.middleware.workshops.InvoiceOuterClass.DeleteResponse> delete(
        no.sysco.middleware.workshops.InvoiceOuterClass.InvoiceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DELETE, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<no.sysco.middleware.workshops.InvoiceOuterClass.Invoice> update(
        no.sysco.middleware.workshops.InvoiceOuterClass.UpdateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_UPDATE, getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE = 0;
  private static final int METHODID_GET = 1;
  private static final int METHODID_DELETE = 2;
  private static final int METHODID_UPDATE = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final InvoiceServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(InvoiceServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE:
          serviceImpl.create((no.sysco.middleware.workshops.InvoiceOuterClass.CreateRequest) request,
              (io.grpc.stub.StreamObserver<no.sysco.middleware.workshops.InvoiceOuterClass.Invoice>) responseObserver);
          break;
        case METHODID_GET:
          serviceImpl.get((no.sysco.middleware.workshops.InvoiceOuterClass.InvoiceRequest) request,
              (io.grpc.stub.StreamObserver<no.sysco.middleware.workshops.InvoiceOuterClass.Invoice>) responseObserver);
          break;
        case METHODID_DELETE:
          serviceImpl.delete((no.sysco.middleware.workshops.InvoiceOuterClass.InvoiceRequest) request,
              (io.grpc.stub.StreamObserver<no.sysco.middleware.workshops.InvoiceOuterClass.DeleteResponse>) responseObserver);
          break;
        case METHODID_UPDATE:
          serviceImpl.update((no.sysco.middleware.workshops.InvoiceOuterClass.UpdateRequest) request,
              (io.grpc.stub.StreamObserver<no.sysco.middleware.workshops.InvoiceOuterClass.Invoice>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class InvoiceServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    InvoiceServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return no.sysco.middleware.workshops.InvoiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("InvoiceService");
    }
  }

  private static final class InvoiceServiceFileDescriptorSupplier
      extends InvoiceServiceBaseDescriptorSupplier {
    InvoiceServiceFileDescriptorSupplier() {}
  }

  private static final class InvoiceServiceMethodDescriptorSupplier
      extends InvoiceServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    InvoiceServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (InvoiceServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new InvoiceServiceFileDescriptorSupplier())
              .addMethod(METHOD_CREATE)
              .addMethod(METHOD_GET)
              .addMethod(METHOD_DELETE)
              .addMethod(METHOD_UPDATE)
              .build();
        }
      }
    }
    return result;
  }
}
