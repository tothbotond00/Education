#!/usr/bin/env python

import json
import math

with open("pontok.json") as file:
	data = json.load(file)

ponthatarok = {
	2 : 0.5,
	3 : 0.6,
	4 : 0.75,
	5 : 0.85
}

eddig = 0.0

for resz in data.keys():
	if "elert" in data[resz]:
		if "minimum" in data[resz] and data[resz]["minimum"]*data[resz]["max"] > data[resz]["elert"]:
			print("Nincs meg a minimum:", resz)
		eddig += (data[resz]["elert"] / data[resz]["max"])*0.33

for i in ponthatarok.keys():
	pont_kell = math.ceil(((ponthatarok[i]-eddig)/0.33)*data["zhPont"]["max"])
	
	result_str = ""
	if pont_kell < 0:
		result_str = "Megvan"
	elif pont_kell > data["zhPont"]['max']:
		result_str = "Remenytelen"
	elif pont_kell < data["zhPont"]["minimum"]*data["zhPont"]["max"]:
		result_str = str( data["zhPont"]["minimum"]*data["zhPont"]["max"] )
	else:
		result_str = str(pont_kell)
	print(i,":",result_str)