from socket import socket, AF_INET, SOCK_STREAM
import random
import struct

server_addr = ('localhost',5050)
winNumber = [] 
hitNum = 0

with socket(AF_INET, SOCK_STREAM) as server:
    server.bind(server_addr)
    server.listen(1)
    client, client_addr = server.accept()
    print('Kapcsolodott:',client_addr)
    data = struct.unpack("i i i i i i",client.recv(struct.calcsize('i i i i i i')))
    for i in range(5):
        winNumber.append(random.randint(1,20))
    for i in winNumber:
        for j in range(5):
            if( i == int(data[j])):
                hitNum += 1
    winnerPrice = int(data[5]) * hitNum
    client.sendall(struct.pack("i", winnerPrice))
    client.close()