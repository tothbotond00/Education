from socket import socket, AF_INET, SOCK_STREAM
from select import select
from time import sleep

server_addr = ('localhost',10000)

with socket(AF_INET, SOCK_STREAM) as client:
    client.connect(server_addr)
    
    while True:
        client.sendall("Hello Server!".encode())
        data = client.recv(200)
        print("Kaptam:",data.decode())
        sleep(1)