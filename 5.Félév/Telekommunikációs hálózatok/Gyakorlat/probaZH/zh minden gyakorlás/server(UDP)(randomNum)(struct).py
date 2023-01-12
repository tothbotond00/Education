from socket import socket, AF_INET, SOCK_DGRAM
import random
import struct

server_addr = ('localhost',5051)
winNumber = [] 
hitNum = 0

with socket(AF_INET, SOCK_DGRAM) as server:
    server.bind(server_addr)

    data,client_addr = server.recvfrom(struct.calcsize('i i i i i i'))
    data = struct.unpack("iiiiii",data)
    for i in range(5):
        winNumber.append(random.randint(1,20))
    for i in winNumber:
        for j in range(5):
            if( i == int(data[j])):
                hitNum += 1
    winnerPrice = int(data[5]) * hitNum
    server.sendto(struct.pack("i", winnerPrice),client_addr)
    server.close()