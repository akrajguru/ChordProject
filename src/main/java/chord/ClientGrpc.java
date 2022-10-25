package chord;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.49.1)",
    comments = "Source: chord.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ClientGrpc {

  private ClientGrpc() {}

  public static final String SERVICE_NAME = "chord.Client";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<chord.Chord.InsertKeyRequest,
      chord.Chord.InsertKeyReply> getInsertKeyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "insertKey",
      requestType = chord.Chord.InsertKeyRequest.class,
      responseType = chord.Chord.InsertKeyReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<chord.Chord.InsertKeyRequest,
      chord.Chord.InsertKeyReply> getInsertKeyMethod() {
    io.grpc.MethodDescriptor<chord.Chord.InsertKeyRequest, chord.Chord.InsertKeyReply> getInsertKeyMethod;
    if ((getInsertKeyMethod = ClientGrpc.getInsertKeyMethod) == null) {
      synchronized (ClientGrpc.class) {
        if ((getInsertKeyMethod = ClientGrpc.getInsertKeyMethod) == null) {
          ClientGrpc.getInsertKeyMethod = getInsertKeyMethod =
              io.grpc.MethodDescriptor.<chord.Chord.InsertKeyRequest, chord.Chord.InsertKeyReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "insertKey"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  chord.Chord.InsertKeyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  chord.Chord.InsertKeyReply.getDefaultInstance()))
              .setSchemaDescriptor(new ClientMethodDescriptorSupplier("insertKey"))
              .build();
        }
      }
    }
    return getInsertKeyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<chord.Chord.FingerTableListRequest,
      chord.Chord.FingerTableListReply> getGetFingerTableMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getFingerTable",
      requestType = chord.Chord.FingerTableListRequest.class,
      responseType = chord.Chord.FingerTableListReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<chord.Chord.FingerTableListRequest,
      chord.Chord.FingerTableListReply> getGetFingerTableMethod() {
    io.grpc.MethodDescriptor<chord.Chord.FingerTableListRequest, chord.Chord.FingerTableListReply> getGetFingerTableMethod;
    if ((getGetFingerTableMethod = ClientGrpc.getGetFingerTableMethod) == null) {
      synchronized (ClientGrpc.class) {
        if ((getGetFingerTableMethod = ClientGrpc.getGetFingerTableMethod) == null) {
          ClientGrpc.getGetFingerTableMethod = getGetFingerTableMethod =
              io.grpc.MethodDescriptor.<chord.Chord.FingerTableListRequest, chord.Chord.FingerTableListReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getFingerTable"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  chord.Chord.FingerTableListRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  chord.Chord.FingerTableListReply.getDefaultInstance()))
              .setSchemaDescriptor(new ClientMethodDescriptorSupplier("getFingerTable"))
              .build();
        }
      }
    }
    return getGetFingerTableMethod;
  }

  private static volatile io.grpc.MethodDescriptor<chord.Chord.AddServerRequest,
      chord.Chord.AddServerResponse> getAddServerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "addServer",
      requestType = chord.Chord.AddServerRequest.class,
      responseType = chord.Chord.AddServerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<chord.Chord.AddServerRequest,
      chord.Chord.AddServerResponse> getAddServerMethod() {
    io.grpc.MethodDescriptor<chord.Chord.AddServerRequest, chord.Chord.AddServerResponse> getAddServerMethod;
    if ((getAddServerMethod = ClientGrpc.getAddServerMethod) == null) {
      synchronized (ClientGrpc.class) {
        if ((getAddServerMethod = ClientGrpc.getAddServerMethod) == null) {
          ClientGrpc.getAddServerMethod = getAddServerMethod =
              io.grpc.MethodDescriptor.<chord.Chord.AddServerRequest, chord.Chord.AddServerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "addServer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  chord.Chord.AddServerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  chord.Chord.AddServerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ClientMethodDescriptorSupplier("addServer"))
              .build();
        }
      }
    }
    return getAddServerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<chord.Chord.GetKeysRequest,
      chord.Chord.GetKeysResponse> getGetKeysMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getKeys",
      requestType = chord.Chord.GetKeysRequest.class,
      responseType = chord.Chord.GetKeysResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<chord.Chord.GetKeysRequest,
      chord.Chord.GetKeysResponse> getGetKeysMethod() {
    io.grpc.MethodDescriptor<chord.Chord.GetKeysRequest, chord.Chord.GetKeysResponse> getGetKeysMethod;
    if ((getGetKeysMethod = ClientGrpc.getGetKeysMethod) == null) {
      synchronized (ClientGrpc.class) {
        if ((getGetKeysMethod = ClientGrpc.getGetKeysMethod) == null) {
          ClientGrpc.getGetKeysMethod = getGetKeysMethod =
              io.grpc.MethodDescriptor.<chord.Chord.GetKeysRequest, chord.Chord.GetKeysResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getKeys"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  chord.Chord.GetKeysRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  chord.Chord.GetKeysResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ClientMethodDescriptorSupplier("getKeys"))
              .build();
        }
      }
    }
    return getGetKeysMethod;
  }

  private static volatile io.grpc.MethodDescriptor<chord.Chord.ServerKeyRequest,
      chord.Chord.ServerKeyResponse> getGetServerFromKeyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getServerFromKey",
      requestType = chord.Chord.ServerKeyRequest.class,
      responseType = chord.Chord.ServerKeyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<chord.Chord.ServerKeyRequest,
      chord.Chord.ServerKeyResponse> getGetServerFromKeyMethod() {
    io.grpc.MethodDescriptor<chord.Chord.ServerKeyRequest, chord.Chord.ServerKeyResponse> getGetServerFromKeyMethod;
    if ((getGetServerFromKeyMethod = ClientGrpc.getGetServerFromKeyMethod) == null) {
      synchronized (ClientGrpc.class) {
        if ((getGetServerFromKeyMethod = ClientGrpc.getGetServerFromKeyMethod) == null) {
          ClientGrpc.getGetServerFromKeyMethod = getGetServerFromKeyMethod =
              io.grpc.MethodDescriptor.<chord.Chord.ServerKeyRequest, chord.Chord.ServerKeyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getServerFromKey"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  chord.Chord.ServerKeyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  chord.Chord.ServerKeyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ClientMethodDescriptorSupplier("getServerFromKey"))
              .build();
        }
      }
    }
    return getGetServerFromKeyMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ClientStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClientStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClientStub>() {
        @java.lang.Override
        public ClientStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClientStub(channel, callOptions);
        }
      };
    return ClientStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ClientBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClientBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClientBlockingStub>() {
        @java.lang.Override
        public ClientBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClientBlockingStub(channel, callOptions);
        }
      };
    return ClientBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ClientFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClientFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClientFutureStub>() {
        @java.lang.Override
        public ClientFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClientFutureStub(channel, callOptions);
        }
      };
    return ClientFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class ClientImplBase implements io.grpc.BindableService {

    /**
     */
    public void insertKey(chord.Chord.InsertKeyRequest request,
        io.grpc.stub.StreamObserver<chord.Chord.InsertKeyReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getInsertKeyMethod(), responseObserver);
    }

    /**
     */
    public void getFingerTable(chord.Chord.FingerTableListRequest request,
        io.grpc.stub.StreamObserver<chord.Chord.FingerTableListReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetFingerTableMethod(), responseObserver);
    }

    /**
     */
    public void addServer(chord.Chord.AddServerRequest request,
        io.grpc.stub.StreamObserver<chord.Chord.AddServerResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddServerMethod(), responseObserver);
    }

    /**
     */
    public void getKeys(chord.Chord.GetKeysRequest request,
        io.grpc.stub.StreamObserver<chord.Chord.GetKeysResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetKeysMethod(), responseObserver);
    }

    /**
     */
    public void getServerFromKey(chord.Chord.ServerKeyRequest request,
        io.grpc.stub.StreamObserver<chord.Chord.ServerKeyResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetServerFromKeyMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getInsertKeyMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                chord.Chord.InsertKeyRequest,
                chord.Chord.InsertKeyReply>(
                  this, METHODID_INSERT_KEY)))
          .addMethod(
            getGetFingerTableMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                chord.Chord.FingerTableListRequest,
                chord.Chord.FingerTableListReply>(
                  this, METHODID_GET_FINGER_TABLE)))
          .addMethod(
            getAddServerMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                chord.Chord.AddServerRequest,
                chord.Chord.AddServerResponse>(
                  this, METHODID_ADD_SERVER)))
          .addMethod(
            getGetKeysMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                chord.Chord.GetKeysRequest,
                chord.Chord.GetKeysResponse>(
                  this, METHODID_GET_KEYS)))
          .addMethod(
            getGetServerFromKeyMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                chord.Chord.ServerKeyRequest,
                chord.Chord.ServerKeyResponse>(
                  this, METHODID_GET_SERVER_FROM_KEY)))
          .build();
    }
  }

  /**
   */
  public static final class ClientStub extends io.grpc.stub.AbstractAsyncStub<ClientStub> {
    private ClientStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClientStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClientStub(channel, callOptions);
    }

    /**
     */
    public void insertKey(chord.Chord.InsertKeyRequest request,
        io.grpc.stub.StreamObserver<chord.Chord.InsertKeyReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getInsertKeyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getFingerTable(chord.Chord.FingerTableListRequest request,
        io.grpc.stub.StreamObserver<chord.Chord.FingerTableListReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetFingerTableMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addServer(chord.Chord.AddServerRequest request,
        io.grpc.stub.StreamObserver<chord.Chord.AddServerResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddServerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getKeys(chord.Chord.GetKeysRequest request,
        io.grpc.stub.StreamObserver<chord.Chord.GetKeysResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetKeysMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getServerFromKey(chord.Chord.ServerKeyRequest request,
        io.grpc.stub.StreamObserver<chord.Chord.ServerKeyResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetServerFromKeyMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ClientBlockingStub extends io.grpc.stub.AbstractBlockingStub<ClientBlockingStub> {
    private ClientBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClientBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClientBlockingStub(channel, callOptions);
    }

    /**
     */
    public chord.Chord.InsertKeyReply insertKey(chord.Chord.InsertKeyRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getInsertKeyMethod(), getCallOptions(), request);
    }

    /**
     */
    public chord.Chord.FingerTableListReply getFingerTable(chord.Chord.FingerTableListRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetFingerTableMethod(), getCallOptions(), request);
    }

    /**
     */
    public chord.Chord.AddServerResponse addServer(chord.Chord.AddServerRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddServerMethod(), getCallOptions(), request);
    }

    /**
     */
    public chord.Chord.GetKeysResponse getKeys(chord.Chord.GetKeysRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetKeysMethod(), getCallOptions(), request);
    }

    /**
     */
    public chord.Chord.ServerKeyResponse getServerFromKey(chord.Chord.ServerKeyRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetServerFromKeyMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ClientFutureStub extends io.grpc.stub.AbstractFutureStub<ClientFutureStub> {
    private ClientFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClientFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClientFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<chord.Chord.InsertKeyReply> insertKey(
        chord.Chord.InsertKeyRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getInsertKeyMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<chord.Chord.FingerTableListReply> getFingerTable(
        chord.Chord.FingerTableListRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetFingerTableMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<chord.Chord.AddServerResponse> addServer(
        chord.Chord.AddServerRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddServerMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<chord.Chord.GetKeysResponse> getKeys(
        chord.Chord.GetKeysRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetKeysMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<chord.Chord.ServerKeyResponse> getServerFromKey(
        chord.Chord.ServerKeyRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetServerFromKeyMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_INSERT_KEY = 0;
  private static final int METHODID_GET_FINGER_TABLE = 1;
  private static final int METHODID_ADD_SERVER = 2;
  private static final int METHODID_GET_KEYS = 3;
  private static final int METHODID_GET_SERVER_FROM_KEY = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ClientImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ClientImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_INSERT_KEY:
          serviceImpl.insertKey((chord.Chord.InsertKeyRequest) request,
              (io.grpc.stub.StreamObserver<chord.Chord.InsertKeyReply>) responseObserver);
          break;
        case METHODID_GET_FINGER_TABLE:
          serviceImpl.getFingerTable((chord.Chord.FingerTableListRequest) request,
              (io.grpc.stub.StreamObserver<chord.Chord.FingerTableListReply>) responseObserver);
          break;
        case METHODID_ADD_SERVER:
          serviceImpl.addServer((chord.Chord.AddServerRequest) request,
              (io.grpc.stub.StreamObserver<chord.Chord.AddServerResponse>) responseObserver);
          break;
        case METHODID_GET_KEYS:
          serviceImpl.getKeys((chord.Chord.GetKeysRequest) request,
              (io.grpc.stub.StreamObserver<chord.Chord.GetKeysResponse>) responseObserver);
          break;
        case METHODID_GET_SERVER_FROM_KEY:
          serviceImpl.getServerFromKey((chord.Chord.ServerKeyRequest) request,
              (io.grpc.stub.StreamObserver<chord.Chord.ServerKeyResponse>) responseObserver);
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

  private static abstract class ClientBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ClientBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return chord.Chord.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Client");
    }
  }

  private static final class ClientFileDescriptorSupplier
      extends ClientBaseDescriptorSupplier {
    ClientFileDescriptorSupplier() {}
  }

  private static final class ClientMethodDescriptorSupplier
      extends ClientBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ClientMethodDescriptorSupplier(String methodName) {
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
      synchronized (ClientGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ClientFileDescriptorSupplier())
              .addMethod(getInsertKeyMethod())
              .addMethod(getGetFingerTableMethod())
              .addMethod(getAddServerMethod())
              .addMethod(getGetKeysMethod())
              .addMethod(getGetServerFromKeyMethod())
              .build();
        }
      }
    }
    return result;
  }
}
