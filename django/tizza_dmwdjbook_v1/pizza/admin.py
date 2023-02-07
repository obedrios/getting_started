from django.contrib import admin
from pizza.models import Pizzeria, Pizza, CustomerReviewer, Likes


# Default Password for users:
# 5u4BvvRFDRW9jtu

# The code formatting is available in Visual Studio Code (VSCode)
# through the following shortcuts or key combinations:
#   On Windows Shift + Alt + F.
#   On macOS Shift + Option + F.
#   On Linux Ctrl + Shift + I.

# Register your models here.
@admin.register(Pizza)
class PizzaAdmin(admin.ModelAdmin):
    list_display = ['id', 'title', 'description', 'thumbnail_url', 'approved', 'creator']

    class Meta:
        ordering = ['-id']


@admin.register(Pizzeria)
class PizzeriaAdmin(admin.ModelAdmin):
    list_display = ['id', 'name', 'address', 'phone']

    class Meta:
        ordering = ['-id']


#
admin.site.register(Likes)
admin.site.register(CustomerReviewer)
