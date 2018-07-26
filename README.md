# [WIP] workshop-grpc
This repository is setup to provide a basic understanding of the gRPC protocol and introduce the steps required to setup a project. This repository also works as a complimentary code repository for [Sysco-Blog](adfasdf)

## Goals
- Introduction to gRPC protocol.
- Setting up a gradle project for grpc.
- Code generation in gRPC projects.
- Client-Server implementations.
- Unit testing gRPC applications.

## Introduction

gRPC is a application development framework created by Google in 2015. It borrows a lot of concepts from RPC / CORBA framework where a client can call a method on a server through a stub, as if it was available locally to client. 

In addition, its based on HTTP/2 protocol which provides performance gains along with additional capabilities like stream processing, connection multiplexing and inbuilt SSL/TLS support . It uses a serialization framework called protocol buffer  under the hood to binary encode the data being transferred.

This makes gRPC a very good candidate for creating microservices. It also has a lot of support for cloud, being a part of Cloud Native project and works seamlessly on container orchestration frameworks like kubernetes. 

If you are used to building microservices using REST + JSON, transition to gRPC would feel like a next natural progression.

To build REST apis, we use something like:
```
REST API = HTTP1.1 + REST + JSON
```
In gRPC we would handle the same scenario using
```
gRPC API = HTTP2 + RPC + Protobuf
```
While both approaches are used to serve client-server communication, there are few notable differences.
- gRPC allows stream processing and also implements server push.
- gRPC allows single connection to multiple all data transfer between client and server. This is different from HTTP1.1 application where each data transfer needs to open and close a new connection
- gRPC enables data transfer in binary format using protobuf serialization under the hood. REST+JSON is still mostly text based.
- gRPC has inbuilt support for security using (SSL/TLS)
- gRPC provides more flexibility on the types of operations client can perform on  server. This is different from REST which strictly encourages to use only CRUD operations on the object.

In this workshop we will work from scratch to develop a working prototype that simulates a <>. Go through the sections below in sequence to understand the problem statement and step-by-step process to setup a gRPC project.
1. [Problem statement](./docs/01-problem-statement/README.md)
2. [Project setup](./docs/02-project-setup/README.md)
3. [Defining payload and services](./docs/03-defining-payload/README.md)
4. [Code generation from protobuf to java](./docs/04-code-generation/README.md)
5. [Server implementation](./docs/05-server-implementation/README.md)
6. [Client implementation](./docs/06-client-implementation/README.md)
7. [Testing gRPC applications](./docs/07-testing-grpc-services/README.md)
