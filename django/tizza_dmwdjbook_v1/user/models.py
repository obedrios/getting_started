from django.db import models
from django.contrib.auth.models import AbstractUser, PermissionsMixin


# Create your models here.

class RegisteredUser(AbstractUser):
    is_staff = models.BooleanField(default=False)
    is_active = models.BooleanField(default=True)
    is_business = models.BooleanField(default=False)
    is_customer = models.BooleanField(default=False)
