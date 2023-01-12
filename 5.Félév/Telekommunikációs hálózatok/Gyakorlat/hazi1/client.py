#!/usr/bin/env python

import json
from sys import argv

###FUNCTIONS

def findLinkBetweenPoints(links, pointA, pointB):
    for link in links :
        if link['points'][0] == pointA and link['points'][1] == pointB or \
            link['points'][1] == pointA and link['points'][0] == pointB:
                return link

def checkLinkCapacity(links,circuit, demandValue):
    i = 0
    for i in range(len(circuit) -1) :
        link = findLinkBetweenPoints(links, circuit[i], circuit[i + 1])
        if (link['capacity'] < demandValue):
            return False
    return True

def reserveCircuit(links,circuit,demandValue):
    i = 0
    for i in range(len(circuit) -1) :
        link = findLinkBetweenPoints(links, circuit[i], circuit[i + 1])
        link['capacity'] -= demandValue

def reserveDemand(demand, links, possibleCircuits) :
    for circuit in possibleCircuits :
        if (circuit[0] == demand['end-points'][0] and circuit[-1] == demand['end-points'][1]) or \
        (circuit[-1] == demand['end-points'][0] and circuit[0] == demand['end-points'][1]) :
            if (checkLinkCapacity(links,circuit, demand['demand'])):
                reserveCircuit(links,circuit,demand['demand'])
                demand['reserved-circuit'] = circuit
                return True
    return False 

def freeCircuit(links,circuit,demandValue):
    i = 0
    for i in range(len(circuit) -1) :
        link = findLinkBetweenPoints(links, circuit[i], circuit[i + 1])
        link['capacity'] += demandValue
    
###MAIN

with open(argv[1]) as file:
	data = json.load(file)

endPoints = data['end-points']
switches = data['switches']
links = data['links']
possibleCircuits = data['possible-circuits']
duration = data['simulation']['duration']
demands = data['simulation']['demands']
event = 0

for i in range (duration) :
    for demand in demands:
        if demand['start-time'] == i :
            if reserveDemand(demand, links, possibleCircuits) :
                #Sikeres foglalás
                event += 1
                demand['reserved'] = True
                print(event,'.igény foglalás: ',demand['end-points'][0],'<->',demand['end-points'][1],' st:', i, ' – sikeres', sep='')
            else :
                #Sikertelen foglalás
                event += 1
                demand['reserved'] = False
                print(event,'.igény foglalás: ',demand['end-points'][0],'<->',demand['end-points'][1],' st:', i, ' – sikertelen', sep='')
        if demand['end-time'] == i and demand['reserved'] :
            #Felszabadítás foglalás esetén
            freeCircuit(links, demand['reserved-circuit'], demand['demand'])
            event += 1
            print(event,'.igény felszabadítás: ',demand['end-points'][0],'<->',demand['end-points'][1],' st:', i, sep='')