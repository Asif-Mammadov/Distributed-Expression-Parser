# Distributed Expression Parser

This is the university project for the Advanced Object Oriented Programming course at UFAZ. The project consist of Calculator client (GUI) and the server that communicate with Remote Method Invocation. All the processing is done on the server, the commands will be entered and submited by the client. The calculator can handle complex expressions containing brackets, plus, minus, multiplication and division operations. The expression calculation is implemented usign stacks and so called infix to postfix conversion. The GUI is implemented with Swing library.

RMI implements multithreading, thus many clients can simultaneously connect to the server.

## Prerequisites

JDK Version 8 or higher (can download [here](https://adoptopenjdk.net/index.html))

## Usage

#### For administrator (server)
* Run `rmiregistry`
* From main folder compile with following command : `javac server/Server.java`
* Run via : `java server.Server`

#### For users
* Be sure that server is running
* Compile : `javac client/Client.java`
* Run via : `java client.Client`

## Errors
User can face 5 types of errors depending on the input or output.

1. **Division by zero**: where the divider is equal to 0.
2. **Negative result**: where the final result is less than 0.
3. **Malformed expression**: where there is the syntax problem in the expression.
4. **Integer overflow**: where the operand or the result is bigger than the max capacity of integer type (
2,147,483,647)
5. **Connection refused**: if the server is down.
