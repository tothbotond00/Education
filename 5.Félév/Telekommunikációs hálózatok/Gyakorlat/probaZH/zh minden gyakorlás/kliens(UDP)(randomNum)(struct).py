from socket import socket, AF_INET, SOCK_DGRAM
import random
import struct

server_addr = ('localhost',5051)
numbers = []
with socket(AF_INET, SOCK_DGRAM) as client:


    for i in range(5):
        numbers.append(random.randint(1,20))
    numbers.append(400)    
    client.sendto(struct.pack('i i i i i i',numbers[0],numbers[1],numbers[2],numbers[3],numbers[4],numbers[5]),server_addr)
    data = client.recv(200)
    data = struct.unpack("i",data)[0] 
    print('Nyertem:',data)