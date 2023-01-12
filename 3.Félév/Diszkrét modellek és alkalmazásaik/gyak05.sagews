︠a665dc51-fce6-48b2-a8d7-e28dc2dc463cso︠
#Keresse meg a p(x) = 54x^4 + 36x^3 - 102x^2 - 72x - 12 polinom gyökeit. Bontsa gyöktényezős alakra (faktorizálja) az egész, racionális, valós és komplex számok halmaza felett.

a = 3
a
type(a)

a = 2.7
type(a)
a = CC(3 + 5*I)
type(a)
p = ZZ['x'](x^2-5)
p
p.roots()

p1 = ZZ['x'] (54*x^4 + 36*x^3 - 102*x^2 - 72*x - 12)
factor(p1)
p1.roots()

p2 = QQ['x'] (54*x^4 + 36*x^3 - 102*x^2 - 72*x - 12)
factor(p2)
p2.roots()

p3 = RR['x'] (54*x^4 + 36*x^3 - 102*x^2 - 72*x - 12)
factor(p3)
p3.roots()

p4 = CC['x'] (54*x^4 + 36*x^3 - 102*x^2 - 72*x - 12)
factor(p4)
p4.roots()
︡71637c73-4def-4f55-9ccd-3ee8e851fb9e︡{"stdout":"3\n"}︡{"stdout":"<class 'sage.rings.integer.Integer'>\n"}︡{"stdout":"<class 'sage.rings.real_mpfr.RealLiteral'>\n"}︡{"stdout":"<class 'sage.rings.complex_mpfr.ComplexNumber'>\n"}︡{"stdout":"x^2 - 5\n"}︡{"stdout":"[]\n"}︡{"stdout":"2 * 3 * (3*x + 1)^2 * (x^2 - 2)\n"}︡{"stdout":"[]\n"}︡{"stdout":"(54) * (x + 1/3)^2 * (x^2 - 2)\n"}︡{"stdout":"[(-1/3, 2)]\n"}︡{"stdout":"(54.0000000000000) * (x - 1.41421356237310) * (x + 0.333333333333333)^2 * (x + 1.41421356237310)\n"}︡{"stdout":"[(-1.41421356237310, 1), (-0.333333333333333, 2), (1.41421356237310, 1)]\n"}︡{"stdout":"(54.0000000000000) * (x - 1.41421356237310) * (x + 0.333333333333333)^2 * (x + 1.41421356237310)\n"}︡{"stdout":"[(-1.41421356237310, 1), (-0.333333333333333, 2), (1.41421356237310, 1)]\n"}︡{"done":true}
︠364484c0-38e2-4afe-b383-3708adba6525so︠

#Végezze el az 5 + 4 és 5 / 4 műveleteket Z7-ben.
a = ZZ(5/4)
a
︡51a7fd6f-05eb-4e48-9cc0-e35d3ce4d26d︡{"stderr":"Error in lines 1-1\nTraceback (most recent call last):\n  File \"/cocalc/lib/python3.9/site-packages/smc_sagews/sage_server.py\", line 1230, in execute\n    exec(\n  File \"\", line 1, in <module>\n  File \"sage/structure/parent.pyx\", line 898, in sage.structure.parent.Parent.__call__ (build/cythonized/sage/structure/parent.c:9338)\n    return mor._call_(x)\n  File \"sage/rings/rational.pyx\", line 4164, in sage.rings.rational.Q_to_Z._call_ (build/cythonized/sage/rings/rational.cpp:32500)\n    raise TypeError(\"no conversion of this rational to integer\")\nTypeError: no conversion of this rational to integer\n"}︡{"done":true}









