# To run the script, django_extensions are needed:
# python3 manage.py runscript load_data

import csv
from autoseller.models import AutoBrand, AutoModel, AutoProfile


def run():
    fpath = "autoseller/data/mtcars.csv"
    #    
    AutoProfile.objects.all().delete()
    AutoModel.objects.all().delete()
    AutoBrand.objects.all().delete()
    
    
    ##
    with open(fpath, 'r') as csvfile:
        # Fields: 
        # Brand,Model,mpg,cyl,disp,hp,drat,wt,qsec,vs,am,gear,carb
        record_reader = csv.DictReader(csvfile, delimiter=',')
        for record in record_reader:
            autobrand, _ = AutoBrand.objects.get_or_create(name=record['Brand'])
            automodel, _ = AutoModel.objects.get_or_create(brand=autobrand, name=record['Model'])
            #
            AutoProfile.objects.create(
                model = automodel,
                mpg  = record['mpg'],
                cyl  = record['cyl'],
                disp = record['disp'],
                hp   = record['hp'],
                drat = record['drat'],
                wt   = record['wt'],
                qsec = record['qsec'],
                vs   = record['vs'],
                am   = record['am'],
                gear = record['gear'],
                carb = record['carb'])            