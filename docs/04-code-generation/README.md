# Code generation
[HOME](../../README.md)

Now that we have configured gradle and provided our payload and service definitions, its time to generate code from our .proto files. If you have followed the instructions in the last 2 sections, generating code is piece of cake.

Follow below instructions to generate code from protobuf.
- Clone this repository, if you have not done it already.
- Open terminal and go to the project root.
- If you are on linux or mac, then type `./gradlew build` in the terminal.
- If you are on windows, then type `gradlew build` in the terminal.
- Go to the directory `<root>/src/main/java/no/sysco/middleware/workshops`, you should see 2 newly generated files **InvoiceOuterClass.java** and **InvoiceServiceGrpc.java**
- **InvoiceOuterClass.java** contains java code for our payload. All the messages and enums defined in **invoice.proto** will be available in this class.
- **InvoiceServiceGrpc.java** contains java code representing Service and Methods defined in proto file. It also contains means to implement the InvoiceService on the server and generate stubs at client to call rpc methods.

We will discuss more on how to use these generated classes in our code in the [next](../05-server-implementation/README.md) sections. 