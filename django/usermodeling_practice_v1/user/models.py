from django.db import models
from django.contrib.auth.models import AbstractUser, PermissionsMixin

# Create your models here.


class Role(models.Model):
    ADMIN = 1
    OWNER = 2
    REVIEWER = 3
    ROLE_CHOICES = ((ADMIN,    'admin'),
                    (OWNER,    'owner'),
                    (REVIEWER, 'reviewer'))

    id = models.PositiveSmallIntegerField(
        choices=ROLE_CHOICES, primary_key=True)

    def __str__(self):
        return self.get_id_display()


class RegisteredUser(AbstractUser):
    roles = models.ManyToManyField(Role)
    is_staff = models.BooleanField(default=False)
    is_active = models.BooleanField(default=True)


