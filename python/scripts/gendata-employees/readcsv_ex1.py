#!/usr/bin/env python3


import csv

if __name__ == '__main__':
    fpath = 'data/us_states.csv'
    field_names = ['state', 'latitude', 'longitude', 'name']
    #
    with open(fpath, 'r') as csvfile:
        record_reader = csv.reader(csvfile, delimiter=',')
        for record in record_reader:
            print(record)
