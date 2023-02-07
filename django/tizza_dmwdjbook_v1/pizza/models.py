from django.db import models
from user.models import RegisteredUser
import json


# Web Referneces:
# https://stackoverflow.com/questions/46804573/django-annotate-count-with-filter
# https://stackoverflow.com/questions/223990/how-do-i-perform-query-filtering-in-django-templates
# https://docs.djangoproject.com/en/4.1/topics/forms/modelforms/

# Create your models here.
class Pizzeria(models.Model):
    owner = models.ForeignKey(RegisteredUser, on_delete=models.CASCADE)
    name = models.CharField(max_length=128)
    address = models.CharField(max_length=512)
    phone = models.CharField(max_length=40)

    def tojson(self):
        obj_dict = self.__dict__.copy()
        del (obj_dict['_state'])
        return json.dumps(obj_dict)

    def __str__(self):
        # return "name={}, address={}, phone={}".format(self.id, self.name, self.address, self.phone)
        return self.name

    def __repr__(self):
        return "Pizzeria(id={}, owner={}, name={}, address={}, phone={})".format(
            self.id, self.name, self.owner, self.address, self.phone)


class Pizza(models.Model):
    title = models.CharField(max_length=120)
    description = models.CharField(max_length=240)
    thumbnail_url = models.URLField()
    approved = models.BooleanField(default=False)
    creator = models.ForeignKey(Pizzeria, on_delete=models.CASCADE)

    def tojson(self):
        obj_dict = self.__dict__.copy()
        del (obj_dict['_state'])
        return json.dumps(obj_dict)

    def __str__(self):
        return "title={}, description={}, thumbnail_url={}, " \
               "approved={}, creator={{ {} }}".format(
                self.title, self.description, self.thumbnail_url,
                self.approved, self.creator)

    def __repr__(self):
        return "Pizza(id={}, title{}, description={}, thumbnail_url={}, " \
               "approved={}, creator=Pizzeria({}))".format(
                self.id, self.title, self.description, self.thumbnail_url,
                self.approved, self.creator)


class Likes(models.Model):
    user = models.ForeignKey(RegisteredUser, on_delete=models.CASCADE)
    pizza = models.ForeignKey(Pizza, on_delete=models.CASCADE)

    def __str__(self):
        return "{}:{}".format(self.user.username, self.pizza.title)


class CustomerReviewer(models.Model):
    user = models.OneToOneField(RegisteredUser, on_delete=models.CASCADE)

    def __str__(self):
        return self.user.username
