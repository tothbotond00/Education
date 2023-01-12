from random import random
from socket import socket, AF_INET, SOCK_STREAM
from select import select
from time import sleep
import struct
import random
import sys

##FUNCTIONS AND DATA
start = 1
end = 100
finished = False

def evaluate(start, end, operand, correct,pivot):
    if (operand == '<' and correct) :
        return start, pivot - 1
    elif (operand == '>' and not correct) :
        return start, pivot    
    elif(operand == '>' and correct) :
        return pivot + 1, end
    elif(operand == '<' and not correct):
        return pivot, end
        
def createPivot(start, end):
    return (end + start) // 2

def makeOperand(start, end):
    if start == end :
        return b'='
    r  = random.randint(1, 2)
    if r == 1:
        return b'<'
    else:
        return b'>'

def evaluateResponse(finished, responseCharacter):
    if (responseCharacter == 'Y'):
        return True, False
    elif (responseCharacter == 'V' or responseCharacter == 'K'):
        return True, False
    elif (responseCharacter == 'N'):
        return False, False
    elif (responseCharacter == 'I'):
        return False, True

##CLIENT COMMUNICATION
server_addr = ((sys.argv[1]),int(sys.argv[2]))
packer = struct.Struct('ci')

with socket(AF_INET, SOCK_STREAM) as client:
    client.connect(server_addr)
    
    while not finished:
        operand = makeOperand(start,end)
        pivot = createPivot(start, end)
        values = (operand, createPivot(start, end))
        packed_data = packer.pack(*values)
        client.sendall(packed_data)
        
        data = client.recv(packer.size)
        data = packer.unpack(data)
        responseCharacter = data[0].decode()
        finished, correct = evaluateResponse(finished, responseCharacter)
        if not finished :
            start, end  = evaluate(start, end, operand.decode(), correct, pivot)
        sleep(random.randint(1,5))
