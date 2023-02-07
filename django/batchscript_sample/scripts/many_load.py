import csv  
from unesco.models import Category, Iso, Region, Site, State

# https://docs.python.org/3/library/csv.html
# https://django-extensions.readthedocs.io/en/latest/runscript.html
# To run the script, django_extensions are needed:
# python3 manage.py runscript many_load

def run():
    fpath = 'unesco/data/whc-sites-2018-clean.csv'

    Category.objects.all().delete()
    Iso.objects.all().delete()
    Region.objects.all().delete()
    Site.objects.all().delete()
    State.objects.all().delete()

    with open(fpath, 'r') as csvfile:
         record_reader = csv.DictReader(csvfile, delimiter=',')
         # header_csv = next(record_reader)    
         # ['name', 'description', 'justification', 'year', 
         #  'longitude', 'latitude', 'area_hectares', 'category', 
         #  'state', 'region', 'iso'])     

         for record in record_reader:
            #   Category.objects.create(name=record['category'])
            #   Iso.objects.create(name=record['iso'])
            #   Region.objects.create(name=record['region'])
            #   State.objects.create(name=record['state'])
            category, _ = Category.objects.get_or_create(name=record['category'])
            iso,      _ = Iso.objects.get_or_create(name=record['iso'])
            region,   _ = Region.objects.get_or_create(name=record['region'])
            state,    _ = State.objects.get_or_create(name=record['state'])
            #
            #  
            # area_hectares=float(record['area_hectares']) if record['area_hectares'] else 'empty'
            Site.objects.create(
                name=record['name'],   year=record['year'], 
                latitude=float(record['latitude']), longitude=float(record['longitude']),
                description=record['description'], justification=record['justification'],
                area_hectares=float(record['area_hectares']) if record['area_hectares'] else 0.0,
                category=category, region=region, iso=iso, state=state)

