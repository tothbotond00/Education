from random import random
from socket import socket, AF_INET, SOCK_STREAM
from select import select
import struct
import random
from time import sleep

server_addr = ('localhost',10000)
packer = struct.Struct('ici')

def getChar():
    r  = random.randint(1, 4)
    if r == 1:
        return b'+'
    if r == 2:
        return b'-'
    if r == 3:
        return b'*'
    if r == 4:
        return b'/'

with socket(AF_INET, SOCK_STREAM) as client:
    client.connect(server_addr)
    
    while True:
        values = (random.randint(1, 100), getChar(), random.randint(1,100))
        packed_data = packer.pack(*values)
        client.sendall(packed_data)
        data = client.recv(8)
        print("Kaptam:",struct.unpack('!i', data))
        sleep(1)
