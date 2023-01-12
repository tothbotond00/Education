from socket import socket, AF_INET, SOCK_STREAM
import struct
import sys

if len(sys.argv) == 1 :
    print("Kérlek add meg a szöveges állomány elérését!")
    exit(1)

server_addr = ("localhost",10000)
packer = struct.Struct("3s10sf")
file_name = sys.argv[1]

with open(file_name) as file:
    lines = [line.rstrip() for line in file]

with socket(AF_INET, SOCK_STREAM) as client:
    client.connect(server_addr)
    for line in lines:
        data = line.split(' ')
        values = (b'IN', bytes(data[0],'utf-8'), float(data[1]))
        packed_data = packer.pack(*values)
        client.sendall(packed_data)
        data = client.recv(200)
        print(data.decode())
        
    values = (b'OUT', b'homero1',0.0)
    packed_data = packer.pack(*values)
    client.sendall(packed_data)
    data = client.recv(200)
    print(data.decode())
    
    values = (b'OUT', b'termosz',0.0)
    packed_data = packer.pack(*values)
    client.sendall(packed_data)
    data = client.recv(200)
    print(data.decode())
    
    values = (b'OUT', b'termosz3',0.0)
    packed_data = packer.pack(*values)
    client.sendall(packed_data)
    data = client.recv(200)
    print(data.decode())