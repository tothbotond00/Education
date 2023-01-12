︠838e078a-355a-4eee-b1a0-4234dbe41024︠
#kulcs generálás

p1 = next_prime(132451234123412351234123412351234)
p2 = next_prime(123321415234571236842163478213485)
n = p1 * p2
print(n)
phi_n =  (p1-1) * (p2-1)
print(phi_n)

gcd(phi_n, 5)
e = 5

#RSA publikus kulcs : (16334073641664745592268899285495389367288394969503857911880007953 , 5)

︡d88724c4-0f72-4d82-b8e5-ff7f1a6fa529︡{"stdout":"16334073641664745592268899285495389367288394969503857911880007953\n"}︡{"stdout":"16334073641664745592268899285495133594639036985915781624989443108\n"}︡{"stdout":"1\n"}︡{"done":true}
︠6ff8dda2-a5c2-4f7e-9d59-46a832e9afb6s︠

#d-t előállító képlet : d = (k * phi_n + 1) / e
#5d = (k* 16334073641664745592268899285495133594639036985915781624989443108 + 1)

#ismeretlenek: d, k
# 5 d - 16334073641664745592268899285495133594639036985915781624989443108 k = 1

#ismeretlenek d, k
xgcd (5, -16334073641664745592268899285495133594639036985915781624989443108)
︡1fab8704-1992-461d-a37e-0d000975cd6a︡{"stdout":"(1, -6533629456665898236907559714198053437855614794366312649995777243, -2)\n"}︡{"done":true}
︠16b74c09-cffe-4555-9c3b-81e580fa2f59s︠

t = 1

# U = -6533629456665898236907559714198053437855614794366312649995777243
# V = 2
d = 16334073641664745592268899285495133594639036985915781624989443108 * t  -6533629456665898236907559714198053437855614794366312649995777243
k = 5 * t + 2

print (d)
print (k)
︡7e8a4936-156b-4c6d-874a-8c0479397351︡{"stdout":"9800444184998847355361339571297080156783422191549468974993665865\n"}︡{"stdout":"7\n"}︡{"done":true}
︠f79e1cfa-30b8-480e-bb3d-c65425c56a8ds︠

d = 9800444184998847355361339571297080156783422191549468974993665865

#privát kulcs = 9800444184998847355361339571297080156783422191549468974993665865

#Próba

def RSA_encrypt(plain, public_key):
    #plaintext -> 256-os számrendszerbeli szám
    plaintext = list(plain)
    plaintext.reverse()
    m = 0
    i = 0
    for ch in plaintext:
        m += ord(ch) * 256^i
        i += 1
    #return m^public_key[1] % public_key[0]
    return power_mod(m, public_key[1], public_key[0])

def RSA_decrypt(cipher, private_key, modulus):
    m = power_mod(cipher, private_key, modulus)
    plain_list = []
    while m > 0:
        plain_list.append(chr(m % 256))
        m //= 256
    plain_list.reverse()
    return "".join(plain_list)

RSA_encrypt("Süllyed a harosz",(16334073641664745592268899285495389367288394969503857911880007953 , 5) )
︡245860df-4534-4383-ac93-9fbf71d7749a︡{"stdout":"5626442477445640714783208274882808969160070411330462640743966592\n"}︡{"done":true}
︠41b15c3a-1d34-4711-84ee-d51c0c436ba8︠

c = 5626442477445640714783208274882808969160070411330462640743966592

RSA_decrypt(c, 9800444184998847355361339571297080156783422191549468974993665865, 16334073641664745592268899285495389367288394969503857911880007953)

#MŰKÖDIK
︡cb52cc52-1d4a-41b7-a6e5-c9eff082e253︡{"stdout":"'Süllyed a harosz'\n"}︡{"done":true}
︠72cc16c5-55b7-420c-be02-ecb6a34d040fs︠
#Vigenére-cipher

def vigenere_encrypt(plaintext,key) :
    if len(plaintext) != len(key) :
        raise ValueError("Key is not proper")
    alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    l = len(alphabet)
    result = list()
    for i in range(len(plaintext)) :
        result.append(alphabet[(alphabet.index(plaintext[i]) + alphabet.index(key[i]) ) % l]  )
    return "".join(result)

vigenere_encrypt("ATTACKATDAWN","LEMONLEMONLE")
︡96a4993d-0fce-46fa-92f5-99d5a4992c2e︡{"stdout":"'LXFOPVEFRNHR'\n"}︡{"done":true}
︠590fc8d3-2d6a-4938-a575-ebf9dec0b187︠

def vigenere_decrypt(plaintext,key) :
    if len(plaintext) != len(key) :
        raise ValueError("Key is not proper")
    alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    l = len(alphabet)
    result = list()
    for i in range(len(plaintext)) :
        result.append(alphabet[(alphabet.index(plaintext[i]) - alphabet.index(key[i]) ) % l]  )
    return "".join(result)

vigenere_decrypt("LXFOPVEFRNHR","LEMONLEMONLE")
︡64be7d71-7c9c-499a-85ca-a3db7426b7ab︡{"stdout":"'ATTACKATDAWN'\n"}︡{"done":true}
︠17d3dc1b-5e05-443c-9adc-1549bd623283s︠

#Ez könnyen bővíthető betűkkel, tehát nagyon dinamikus!

def gen_V_key(inputstring, desired_key_len) :
    if len(inputstring) >= desired_key_len :
        return inputstring[0 : desired_key_len ]
    result = ""
    q = desired_key_len // len(inputstring)
    r = desired_key_len % len(inputstring)
    for i in range(q) :
        result += inputstring
    result += inputstring[0 : r]
    return result


gen_V_key("LEMON", 12)
︡206b99fe-b29c-459a-bfae-dc65046ad95c︡{"stdout":"'LEMONLEMONLE'\n"}︡{"done":true}










