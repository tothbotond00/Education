#!/usr/bin/env python

def fibonacci(n) :
    if n == 1 :
        return 0
    if n == 2 :
        return 1
    return fibonacci(n - 2) + fibonacci(n - 1)

n = int(input("Kell egy szam : "))

print(fibonacci(n))