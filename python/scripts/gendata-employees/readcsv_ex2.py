#!/usr/bin/env python3

import csv

if __name__ == '__main__':
    fpath = 'data/names100.txt'
    with open(fpath, 'r') as csvfile:
        csvreader = csv.reader(csvfile, delimiter='\t')
        field_names = next(csvreader)
        print(field_names)
        print("============")
        for row in csvreader:
            print(row)
