#!/usr/bin/env python3

import csv
import pandas as pd

if __name__ == '__main__':
    fpath = 'data/us_states.csv'
    us_states = pd.read_csv(fpath)
    print(us_states.head())
