# Auto seller Example

This example is intended to show how to perform a connection to a PostgreSQL database.

Key for connecting and uploading data from a csv file:

1. Install in you environments the following packages with pip: `psycopg2`-- (additional library may be required such `libp-dev` ), `django-extensions`.

2.  Add the following in your project's `settings.py`

   ````python
   INSTALLED_APPS = [
       ...
       "django_extensions",
   ]
   ````

   also in the same file modify the databases section with the following:

   ````python
   # Database
   # https://docs.djangoproject.com/en/4.1/ref/settings/#databases
   
   DATABASES = {
       'default': {
           'ENGINE': 'django.db.backends.postgresql_psycopg2',
           'NAME': 'yourdb', 
           'USER': 'username', 
           'PASSWORD': 'yourpassword',
           'HOST': '127.0.0.1', 
           'PORT': '5432',
       }
   }
   ````

3.  To upload data into the your database, specify the model in your app's `models.py` and run the `makemigrations` and `migrate`. To upload existing data from a `csv` file in your project root create a `scripts` folder and inside create an empty file called `__init__.py`. In the same directory you may create an uploader according to your model. For example:

   ````python
   # load_data.py
   # To run the script, django_extensions are needed:
   # python3 manage.py runscript load_data
   
   import csv
   from autoseller.models import yourmodels
   
   def run():
       fpath = "autoseller/data/mtcars.csv"
       #    
       yourmodels.objects.all().delete()
       ##
       with open(fpath, 'r') as csvfile:
           record_reader = csv.DictReader(csvfile, delimiter=',')
           for record in record_reader:
               yourfield1, _ = ModelA.objects.get_or_create(name=record['somefield'])
               yourfield2, _ = ModelB.objects.get_or_create(model=yourfield1, name=record['otherfield'])
               # and so on...
                   
   ````

4. To run your script for data loading, simply run:

   ````bash
   python3 manage.py runscript load_data
   ````

   

