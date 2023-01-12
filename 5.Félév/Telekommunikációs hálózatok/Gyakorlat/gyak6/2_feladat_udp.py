from socket import socket, AF_INET, SOCK_DGRAM
from struct import Struct

packer = Struct('15si')

server_addr = ('localhost', 10000)

with socket(AF_INET, SOCK_DGRAM) as server:
    server.bind(server_addr)
    
    data, client_addr = server.recvfrom(200)
    print("Kaptam:", data.decode(), "Tole: ", client_addr)
    
    data = packer.pack('localhost'.encode(),12000)
    server.sendto(data, client_addr)