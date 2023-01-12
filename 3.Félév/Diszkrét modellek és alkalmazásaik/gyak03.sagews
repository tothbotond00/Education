︠08b6c704-e07c-49f6-98ff-3c8358a4f04a︠


# relációk

# A = {1, 2}
# R = {(1, 2), (2, 1)} részhalmaz AxA
# 1 R relációban ál 2-vel
# R szimm: minden a, b eleme A-ra: (a, b) eleme R -nek -> (b, a) eleme az R-nek # aRb -> bRa
# R reflexív: minden a eleme A-ra: (a, a) eleme R-nek
# pl. reflexív R = {(1, 1), (2, 2), (2, 1)}
# pl. nem reflexív: R = {(1, 1), (2, 1)}
# tranzitív: minden a, b, c eleme A-ra: aRb és bRc -> aRc
# pl. R = {(1, 2), (2, 3), (1, 3)}

# fsor2fel5

def Is_Reflexive(X, L):
    for a in X: # i = 1; i = 2
        if (a, a) not in L:
            return False
    return True


Is_Reflexive({1, 2}, [(1, 1), (2, 2), (2, 1)])

︡d11d14ad-df10-4fd6-b0f0-eec7ed7bdd5b︡{"stdout":"True\n"}︡{"done":true}
︠5460e86c-8c59-49f2-9c99-c8be30a3a87e︠

Is_Reflexive({1, 2, 3, 4}, [(1, 1), (2, 2), (3, 3), (4, 4)])

︡4fc92c78-1538-4456-9078-1b0276a1c15d︡{"stdout":"True\n"}︡{"done":true}
︠e1f1a902-1564-410c-9831-319187007187︠
Is_Reflexive({1, 2, 3}, [(1, 1), (1, 2), (2, 2)])

︡f19f9272-fdbc-4fdb-9873-17ecd70d831b︡{"stdout":"False\n"}︡{"done":true}
︠5595a021-627c-4b49-9b6f-91fab7c751d0︠

# tranzitív: minden a, b, c eleme A-ra: aRb és bRc -> aRc
# minden a, b, c, eleme {1, 2, 3, 4}-re:
# a = 1
# b = 1
# c = 1

# a = 1
# b = 1
# c = 2

# ...

def Is_Transitive(X, L):
    for a in X:
        for b in X:
            for c in X:
                if (a, b) in L and (b, c) in L and (a, c) not in L:
                    return False
    return True


Is_Transitive({1, 2, 3, 4}, [(1, 2), (2, 3), (1, 4), (2, 2)])

︡ab6dc962-8877-484b-a2ca-e6241e54bde0︡{"stdout":"False\n"}︡{"done":true}
︠66b740d5-4829-4155-a37a-90d068959afe︠

Is_Transitive({1, 2, 3}, [(1,1), (1,2), (1,3), (2,1), (2,2), (2,3), (3,1), (3,2), (3,3)])

︡adc455b4-a6ca-4a11-9820-19a10c189554︡{"stdout":"True\n"}︡{"done":true}
︠f1237685-bcae-4b17-a726-5c403cf0ffe7s︠

# fsor3fel1

# N = 2 -> [8, 9]
# N = 3 -> [8, 9, 10]
# N = 4 -> [32, 33, 34, 35]

# N = 3
# 2, 3, 4, 5, 6, 7, 8, 9, 10, 11

is_prime(6)

︡c6036c3e-6618-42a3-801b-1b3751b961d7︡{"stdout":"False\n"}︡{"done":true}
︠9c10266b-1498-4ba0-a33d-eae2290f509c︠


def Compound_List(N):
    result = []
    counter = 2
    while len(result) < N:
        if is_prime(counter):
            result = []
        else:
            result.append(counter)
        counter += 1
    return result

Compound_List(3)
Compound_List(4)
Compound_List(5)
Compound_List(10)
Compound_List(100)

︡48439fa0-ab69-46af-84db-5cceb4edce98︡{"stdout":"[8, 9, 10]\n"}︡{"stdout":"[24, 25, 26, 27]\n"}︡{"stdout":"[24, 25, 26, 27, 28]\n"}︡{"stdout":"[114, 115, 116, 117, 118, 119, 120, 121, 122, 123]\n"}︡{"stdout":"[370262, 370263, 370264, 370265, 370266, 370267, 370268, 370269, 370270, 370271, 370272, 370273, 370274, 370275, 370276, 370277, 370278, 370279, 370280, 370281, 370282, 370283, 370284, 370285, 370286, 370287, 370288, 370289, 370290, 370291, 370292, 370293, 370294, 370295, 370296, 370297, 370298, 370299, 370300, 370301, 370302, 370303, 370304, 370305, 370306, 370307, 370308, 370309, 370310, 370311, 370312, 370313, 370314, 370315, 370316, 370317, 370318, 370319, 370320, 370321, 370322, 370323, 370324, 370325, 370326, 370327, 370328, 370329, 370330, 370331, 370332, 370333, 370334, 370335, 370336, 370337, 370338, 370339, 370340, 370341, 370342, 370343, 370344, 370345, 370346, 370347, 370348, 370349, 370350, 370351, 370352, 370353, 370354, 370355, 370356, 370357, 370358, 370359, 370360, 370361]"}︡{"stdout":"\n"}︡{"done":true}
︠e6e5ee87-d067-4d78-b20f-19860d91198c︠

Compound_List(1000) # a második megoldás kiszámolja

︡ffc1e631-0cef-4a6d-a1e3-776a216d6210︡{"stderr":"Error in lines 1-1\n"}︡{"stderr":"Traceback (most recent call last):\n  File \"/cocalc/lib/python3.9/site-packages/smc_sagews/sage_server.py\", line 1230, in execute\n    exec(\n  File \"\", line 1, in <module>\n  File \"\", line 5, in Compound_List\n  File \"/ext/sage/sage-9.3/local/lib/python3.9/site-packages/sage/arith/misc.py\", line 517, in is_prime\n    return n.is_prime()\n  File \"src/cysignals/signals.pyx\", line 320, in cysignals.signals.python_check_interrupt\nKeyboardInterrupt\n"}︡{"done":true}
︠12b6ad22-823b-4ff2-8505-f595079f8f07︠

︡858e557c-91bb-42b2-8ddd-9125ee10b443︡
︠87696ec8-dc18-4d24-85fb-207722285cd4︠

def Compound_List(N):
    return [ factorial(N+1)+2 .. factorial(N+1) + N + 1]

Compound_List(1000) # kiszámolta

# N = 3
# 4!+2 .. 4!+4
# 4!+2, 4!+3, 4!+4
# 1*2*3*4+2, 1*2*3*4+3, 1*2*3*4+4

︡6eedb5ac-77d2-498a-95aa-e0cba9150658︡
︠6e0961ca-cf9a-4305-9707-1e19a004dbd0︠

[2..10]

︡a2d49670-3484-4779-ac98-dd0cb877e7d6︡{"stdout":"[2, 3, 4, 5, 6, 7, 8, 9, 10]\n"}︡{"done":true}
︠3cdf24c0-9dde-449f-982a-78b87bb0704f︠


# fsor3fel3

len(prime_range(11))

︡a0b3ec33-c740-4196-a61d-1b849e5deeac︡{"stdout":"4\n"}︡{"done":true}
︠eaf36cbe-aa0e-4a35-8145-376b4453ab7es︠

n=1000
len(prime_range(n))

︡92fa0ef2-9440-4174-87ca-920c30ac8971︡{"stdout":"168\n"}︡{"done":true}
︠644e3edd-0150-4a17-b026-9dd93034c9c0s︠



f = [len(prime_range(n)) for n in range (1,1000)]
a = list_plot(f, plotjoined=True, color='blue')
b = plot(x/log(x), (1,1000), color='red')
a + b

︡86a014e0-2826-4ff7-965c-bab9a5b590bd︡{"file":{"filename":"/home/user/.sage/temp/project-ebbfac0e-4293-4429-9189-39a1bb1e293a/882/tmp_gsxv9ulh.svg","show":true,"text":null,"uuid":"366cea2b-7f37-4bc9-b95c-09663c463189"},"once":false}︡{"done":true}
︠b17db76b-a1dc-4381-b521-219ea147cab3︠








