from socket import socket, AF_INET, SOCK_DGRAM
import random
import struct

winNumber = []
numbers = []
serv_addr = ('localhost', 9020)
hitNumber = 0

with socket(AF_INET, SOCK_DGRAM) as server:
    server.bind(serv_addr)  

    for i in range(5):
        winNumber.append(random.randint(1,20))

    data,history_addrs = server.recvfrom(struct.calcsize('i i i i i i') + 20)
    data = struct.unpack("i i i i i i",data)
    print(data)
    for i in range(4):
        for j in range(4):
            if data[j] == winNumber[i]:
                hitNumber += 1
    print(winNumber)
    server.sendto(struct.pack("i i i i i",winNumber[0],winNumber[1],winNumber[2],winNumber[3],winNumber[4]),history_addrs)
    server.sendto(str(data[5] * hitNumber).encode(), history_addrs)
    