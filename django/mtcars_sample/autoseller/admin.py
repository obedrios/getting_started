from django.contrib import admin
from autoseller.models import AutoBrand, AutoModel, AutoProfile

# Register your models here.
admin.site.register(AutoBrand)
admin.site.register(AutoModel)
admin.site.register(AutoProfile)