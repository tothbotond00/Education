from socket import SOCK_STREAM, socket, AF_INET, SOCK_DGRAM
from struct import Struct

packer = Struct('15si')

server_addr = ('localhost', 10000)
server2_addr = ()

with socket(AF_INET, SOCK_DGRAM) as client:
    client.sendto("GET".encode(),server_addr)
    
    data, _ = client.recvfrom(packer.size)
    print("Kaptam", data)
    d = packer.unpack(data)
    server2_addr = (d[0].strip("\0"), d[1])
    
with socket(AF_INET, SOCK_STREAM) as client:
    client.connect(server2_addr)
    client.sendall("Hello Server!".encode())
    data = client.recv(200)
    print(data.decode())