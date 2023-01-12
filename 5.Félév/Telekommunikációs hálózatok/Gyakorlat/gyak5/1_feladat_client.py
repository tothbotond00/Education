from socket import socket, AF_INET, SOCK_DGRAM

server_addr = ('localhost', 10000)

with socket(AF_INET, SOCK_DGRAM) as client:
    client.sendto("Hello Server".encode(), server_addr)
    
    data, _ = client.recvfrom(200)
    print("Kaptam:", data.decode())