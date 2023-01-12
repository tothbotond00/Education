from socket import socket, AF_INET, SOCK_DGRAM
import random

numbers = ''

proxy_addr = ('localhost', 11000)

with socket(AF_INET, SOCK_DGRAM) as client:

    for i in range(5):
        numbers += str(random.randint(1,20)) + ':'
    numbers+= '400' 
    client.sendto(numbers.encode(),proxy_addr)
    data,_ = client.recvfrom(200)

    print('Nyertem:',data.decode())