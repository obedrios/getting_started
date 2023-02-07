from django.contrib import admin
from unesco.models import Category, Iso, Region, Site, State

# Register your models here.
admin.site.register(Category)
admin.site.register(Iso)
admin.site.register(Region)
admin.site.register(Site)
admin.site.register(State)