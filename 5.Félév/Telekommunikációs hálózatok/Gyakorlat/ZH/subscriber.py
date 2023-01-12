from socket import socket, AF_INET, SOCK_STREAM, SOCK_DGRAM
import struct

topicValues = []

udp_addr = ('localhost', 11000)
server_addr = ("localhost",10000)
packer = struct.Struct("3s10sf")

with socket(AF_INET, SOCK_STREAM) as client:
    client.connect(server_addr)
    
    values = (b'REG', b'homero1',0.0)
    packed_data = packer.pack(*values)
    client.sendall(packed_data)
    data = client.recv(200)
    print(data.decode())
    with socket(AF_INET, SOCK_DGRAM) as server:
        server.bind(udp_addr)
        while (True):
            data, client_addr = server.recvfrom(200)
            topicValues.append(float(data.decode()))
            print(data.decode())
            server.sendto("ACK".encode(), client_addr)
    