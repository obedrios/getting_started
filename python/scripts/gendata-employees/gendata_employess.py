#!/usr/bin/env python3

import csv
import os
import sys
import random


filepath = 'data'
files = ['companies.txt', 'jobpositions1000.txt', 'lastnames1000.txt', 
         'names100.txt' , 'us_states.csv', 'top_lvl_domain_names.csv']
datafiles =  [os.path.join(filepath, files[n]) for n in range(0,len(files))]


def read_singlecol_files(f):
    """Method for readeing single column file text"""
    lines = []
    with open(f, 'r') as textfile:
        lines = textfile.readlines()
    #
    lines = [s.strip() for s in lines]
    return lines


# Read last names file
last_names = list()
last_names = read_singlecol_files(datafiles[2])


# Read job positions
job_positions = list()
job_positions = read_singlecol_files(datafiles[1])


# Read the first names file
first_names = dict()
with open(datafiles[3], 'r') as csvfile:
    csvreader = csv.reader(csvfile, delimiter = '\t')
    fieldNames = next(csvreader)
    first_names[fieldNames[0]] = list()
    first_names[fieldNames[1]] = list()
    for record in csvreader:
        first_names[fieldNames[0]].append(record[0])
        first_names[fieldNames[1]].append(record[1])


# Read companies files
companies = list()
with open(datafiles[0], 'r') as csvfile:
    csvreader = csv.reader(csvfile, delimiter = '\t')
    fieldNames = next(csvreader)
    for record in csvreader:
        companies.append(record[0])



# Read the us states file
us_states  = list()
with open(datafiles[4], 'r') as csvfile:
        csvreader = csv.reader(csvfile, delimiter =',')
        next(csvreader) # skip headers
        for record in csvreader:
            us_states.append((record[0], record[3]))


# Read domains list
domains = list()
with open(datafiles[5], 'r') as csvfiles:
    csvreader = csv.reader(csvfiles, delimiter = ',')
    next(csvreader)
    for record in csvreader:
        domains.append(record[0])
    
def generate_record():
    """Generate random employee record"""
    gender = ['Male', 'Female']
    random.shuffle(gender)
    for field in [first_names[gender[0]], last_names, job_positions,
                  us_states, domains, companies, domains]:
        random.shuffle(field)
    #
    state_code, state_name = us_states[0]
    first_name = first_names[gender[0]][0]
    last_name = last_names[0]
    job_position = job_positions[0]
    age = random.randint(25,55)
    email = "%s.%s@%s%s" % (str.lower(first_name), str.lower(last_name), companies[0], domains[0])
    record  = "%s, %s, %d, %s, %s, %s, %s" % (first_name, last_name, age, job_position,
                                          state_name, state_code, email)
    #
    return record


if __name__ == '__main__':
    nargs = len(sys.argv)
    if nargs == 1:
        for i in range(0, 10):
            print(generate_record())
    elif nargs == 2:
        n = sys.argv[1]
        try:
            for i in range(0, int(n)):
                print(generate_record())
        except ValueError:
            print("Argument must be integer.\nUsage: gendata_employees n")


