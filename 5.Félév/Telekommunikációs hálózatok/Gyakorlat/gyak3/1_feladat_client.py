from socket import socket, AF_INET, SOCK_STREAM
import sys

server_addr = ('localhost',int(sys.argv[1]))

client = socket(AF_INET, SOCK_STREAM)

client.connect(server_addr)

client.sendall("Hello Server!".encode())

data = client.recv(16)
print("Kaptam:",data.decode())

client.close()