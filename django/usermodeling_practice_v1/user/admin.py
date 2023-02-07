from django.contrib import admin
from django.contrib.auth.admin import UserAdmin
from user.models import RegisteredUser, Role

# Default Password for users:
# 5u4BvvRFDRW9jtu

# The code formatting is available in Visual Studio Code (VSCode) 
# through the following shortcuts or key combinations:
#     On Windows Shift + Alt + F.
#     On macOS Shift + Option + F.
#     On Linux Ctrl + Shift + I.

# Register your models here.
@admin.register(RegisteredUser)
class RegisteredUserAdmin(admin.ModelAdmin):
    # fields = ['roles']  # Set this to modify what fields to show in admin-site
    list_display =['username', 'email', 'first_name', 'last_name', 'is_staff', 'get_roles']

    def get_roles(self, obj):
        return ", ".join([str(role) for role in obj.roles.all()])

    class Meta:
            ordering = ['-id']

admin.site.register(Role)
#admin.site.register(RegisteredUser)
