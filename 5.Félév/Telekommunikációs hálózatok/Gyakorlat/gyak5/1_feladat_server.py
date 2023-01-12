from socket import socket, AF_INET, SOCK_DGRAM

server_addr = ('localhost', 11000)

with socket(AF_INET, SOCK_DGRAM) as server:
    server.bind(server_addr)
    
    data, client_addr = server.recvfrom(200)
    print("Kaptam:", data.decode(), "Tole: ", client_addr)
    
    server.sendto("Hello kilens".encode(), client_addr)
    
# 188.100.22.12/32 -> 1
# 188.100.22.12/20
# min:
# 188.100 00010000 (16) - 000000000
# max:
# 188.100 00011111 (32) - 111111111 (32)