from ctypes import sizeof
from socket import socket, AF_INET, SOCK_STREAM
import struct

server_addr = ('localhost',10000)

client = socket(AF_INET, SOCK_STREAM)

client.connect(server_addr)


######################
packer = struct.Struct('ici')

values = (30, b'+', 5)
packed_data = packer.pack(*values)

client.sendall(packed_data)

data = client.recv(8)
print("Kaptam:",struct.unpack('!i', data))
######################


client.close()