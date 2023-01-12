︠d39bec9b-396c-42a1-a467-5b3cbeac8419︠
#1-2
gcd(616,3)
xgcd (3, -616)

︡26b8894f-aebd-4539-935f-4ac810379c27︡{"stdout":"1\n"}︡{"stdout":"(1, -205, -1)\n"}︡{"done":true}
︠c42336b7-87bb-4f6e-ba8e-079618674b79s︠

#3
def foo(points, limit) :
    res = {}
    for key, value in points.items() :
        if value < limit :
            res[key] = 'failed'
        else :
            res[key] = 'passed'
    return res


foo({"Peter A.": 40, "Kyle W.": 70, "Homer S.": 30}, 50)

︡460b3391-a526-4dd3-8b9d-ad32387cbad6︡{"stdout":"{'Peter A.': 'failed', 'Kyle W.': 'passed', 'Homer S.': 'failed'}\n"}︡{"done":true}
︠d2d0df1b-5c93-45a6-a75d-aab616800d37s︠
#4
def rotate(strg, n):
    return strg[n:] + strg[:n]

def encrypt(plaintext,key) :
    if len(str(key)) != len(plaintext)+1 :
        raise ValueError
    res = ""
    i = 0
    for ch in plaintext :
        code = ord(ch) + int(str(key)[i])
        i = i + 1
        res = res + (chr(code))
    rot =  int(str(key)[i])
    while rot > 0 :
        res = rotate(res,-1)
        rot = rot-1
    return res

encrypt("alma",12341)

def decrypt(ciphertext,key) :
    if len(str(key)) != len(ciphertext)+1 :
        raise ValueError
    res = ciphertext
    i = len(str(key))-1
    rot =  int(str(key)[i])
    while rot > 0 :
        res = rotate(res,1)
        rot = rot-1
    i = 0
    res2 = ""
    for ch in res :
        code = ord(ch) - int(str(key)[i])
        i = i + 1
        res2 = res2 + (chr(code))
    return res2

decrypt("ebnp",12341)

︡327ca595-7feb-4b26-81fd-53c6a8b3e705︡{"stdout":"'ebnp'\n"}︡{"stdout":"'alma'\n"}︡{"done":true}
︠42c4b31a-7eca-4a19-9068-da27f8aa9b93s︠
#5

def gen_prime(num):
    i = num -1
    res = 10
    while i > 0 :
        res = res * 10
        i = i - 1
    return next_prime(res)

def gen_RSA_public_key(modulus_len) :
    if (modulus_len < 0 or not modulus_len.is_integer()) :
        raise ValueError
    p1 = gen_prime(modulus_len / 2)
    p2 = next_prime(p1)
    n = p1 * p2
    phi_n = (p1-1) * (p2-1)
    e = 2
    while not gcd(phi_n, e) == 1 :
        e = next_prime(e)
    return (n ,e)

gen_RSA_public_key(10)

︡0d5fb545-f25c-4d9b-a9a0-dfe473d0cba0︡{"stdout":"(10002200057, 5)\n"}︡{"done":true}
︠403aa3ba-3f2a-474f-86bf-16ab39bee3bc︠
#6

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

# m^e mod n == c^d mod n

cipher = 6465221599
key = (10002200057, 5)

c = cipher
e = 5
n = 10002200057

def prime_factors(n):
    i = 2
    factors = []
    while i * i <= n:
        if n % i:
            i += 1
        else:
            n //= i
            factors.append(i)
    if n > 1:
        factors.append(n)
    return factors

p1 = prime_factors(n)[0]
p2 = prime_factors(n)[1]

phi_n = (p1-1) * (p2 -1)

#d-t előállító képlet : d = (k * phi_n + 1) / e

#ismeretlenek d, k
xgcd (e, -phi_n)
t = 1
d = phi_n * t  -2000400007
RSA_decrypt(c, d, n)

︡f4c6fc96-6084-41e7-92b3-4292983754a7︡{"stdout":"(1, -2000400007, -1)\n"}︡{"stdout":"'hajo'\n"}︡{"done":true}
︠48e07aea-d714-4399-a0b0-f62df15a3ea3︠









