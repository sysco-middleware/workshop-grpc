# Payload and service definition.
[HOME](../../README.md)

Defining payload an services in gRPC is extremely easy. We will start with defining the payload, which represent strongly typed requests and responses for methods exposed by rpc Service. The payload is represented in protobuf format and is called a `message`.

## Protobuf message anatomy
### Defining a message type
First let's look at a very simple example. Let's say you want to define a search request message format, where each search request has a query string, the particular page of results you are interested in, and a number of results per page. Here's the .proto file you use to define the message type. 
```
syntax = "proto3";

message SearchRequest {
  string query = 1;
  int32 page_number = 2;
  int32 result_per_page = 3;
}
```
- The first line of the file specifies that you're using proto3 syntax: if you don't do this the protocol buffer compiler will assume you are using proto2. This must be the first non-empty, non-comment line of the file.
- The SearchRequest message definition specifies three fields (name/value pairs), one for each piece of data that you want to include in this type of message. Each field has a name and a type.

### Specifying Field Types
In the above example, all the fields are scalar types: two integers (page_number and result_per_page) and a string (query). However, you can also specify composite types for your fields, including enumerations and other message types. 

### Assigning Field Numbers
As you can see, each field in the message definition has a unique number. These field numbers are used to identify your fields in the message binary format, and should not be changed once your message type is in use. Note that field numbers in the range 1 through 15 take one byte to encode, including the field number and the field's type. Field numbers in the range 16 through 2047 take two bytes. So you should reserve the numbers 1 through 15 for very frequently occurring message elements. Remember to leave some room for frequently occurring elements that might be added in the future.

### Specifying Field Rules
Message fields can be one of the following:
- _singular_: a well-formed message can have zero or one of this field (but not more than one).
- _repeated_: this field can be repeated any number of times (including zero) in a well-formed message. The order of the repeated values will be preserved.

Source: [protobuf](https://developers.google.com/protocol-buffers/docs/proto3)

## Defining Payload and Service definition for our example

### Defining payload

Refer to [invoice.proto](../../src/main/proto/invoice.proto) for the complete reference.

We start by defining the payload for our application. Create a file **invoice.proto** under the directory **src/main/proto** and add the below content.
```
syntax = "proto3";
import "google/protobuf/empty.proto";
option java_package = "no.sysco.middleware.workshops";

message Invoice {
    string id = 1;
    float amount = 2;
    string customerId = 3;
    State state = 4;
}
enum State {
    NEW = 0;
    PAID = 1;
    FAILED = 2;
}
message CreateRequest {
    float amount = 1;
    string customerId = 2;
    State state = 3;
}
message InvoiceRequest {
    string id = 1;
}
message DeleteResponse {
    string id = 1;
    bool ok = 2;
}
message UpdateRequest {
    string id = 1;
    float amount = 2;
    State state =3;
}
```

Note above that we have defined an enum called State. This means that an invoice can only hold a value defined in this enum.
Also notice that we have `option` field to define java package. There are other such options available which you can reference [here](https://developers.google.com/protocol-buffers/docs/proto3#options).

### Defining Service
Lets define the service and the methods our api supports. At the end of the **invoice.proto** file, add the below content.
```
service InvoiceService {
    rpc Create (CreateRequest) returns (Invoice);
    rpc Get (InvoiceRequest) returns (Invoice);
    rpc Delete (InvoiceRequest) returns (DeleteResponse);
    rpc Update (UpdateRequest) returns (Invoice);
    rpc List(google.protobuf.Empty) returns(stream Invoice);
}
```
We first start with defining the name for our service which is **InvoiceService** in our case. Next we add five operations called **Create**,**Get**,**Delete**,**Update** and **List**.

Here first four methods are `synchronous` blocking operations, while the last method is streaming operation. Also note that input type for List operation is Empty which is a way to represent void in protobuf. This method does not expect any input but would return stream of invoices to the client.

[Next](../04-code-generation/README.md) we will look into code generation from our .proto files 