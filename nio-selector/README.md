The NIO selector wakes up infinitely in this situation..

0. server waits for connection
1. client connects and write message
2. server accepts and register OP_READ
3. server reads message and remove OP_READ from interest op set
4. client close the connection
5. server write message (without any reading.. surely OP_READ is not set)
6. server's select wakes up infinitely with return value 0
