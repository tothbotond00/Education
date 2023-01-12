from socket import socket, AF_INET, SOCK_DGRAM
import random
hitNum = 0
winNumber = []
proxy_addr = ('localhost', 11000)

with socket(AF_INET, SOCK_DGRAM) as server:
    server.bind(proxy_addr)

    data, client_addr = server.recvfrom(200)

    numbers = data.decode().split(':')
    for i in range(5):
        winNumber.append(random.randint(1,20))
    for i in winNumber:
        for j in range(5):
            if( i == int(numbers[j])):
                hitNum += 1
    server.sendto(str(int(numbers[5]) * hitNum ).encode(),client_addr)
    server.close()