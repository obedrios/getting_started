from django import forms
from django.contrib.auth.forms import UserCreationForm, UserChangeForm
from user.models import RegisteredUser, Role

# Default Password for users:
# 5u4BvvRFDRW9jtu

class RegisteredUserCreateForm(UserCreationForm):
    class Meta(UserCreationForm.Meta):
        model = RegisteredUser

    def save(self, commit=True):
        user = super().save(commit=False)
        roles = Role.objects.all()
    
        if commit:    
            user.save()
            user.roles.set([roles[0]]) 

        return user


# class RegisterdUserChangeForm(UserChangeForm):
#     class Meta(UserChangeForm.Meta):
#         model = RegisteredUser

