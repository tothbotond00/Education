from socket import socket, AF_INET, SOCK_STREAM
import random
import struct

server_addr = ('localhost',5050)
numbers = []
with socket(AF_INET, SOCK_STREAM) as client:
    client.connect(server_addr)
    for i in range(5):
        numbers.append(random.randint(1,20))
    numbers.append(400)    
    client.sendall(struct.pack('i i i i i i',numbers[0],numbers[1],numbers[2],numbers[3],numbers[4],numbers[5]))
    data = struct.unpack("i",client.recv(struct.calcsize("i")))[0]

    print('Nyertem:',data)