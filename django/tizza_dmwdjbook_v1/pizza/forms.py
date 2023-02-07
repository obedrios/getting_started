from django import forms
from django.forms import ModelForm, HiddenInput
from pizza.models import Pizza, Pizzeria, CustomerReviewer, Likes


# Web References:
# https://docs.djangoproject.com/en/4.1/topics/forms/modelforms/
# https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django/Forms

# Create the form class.
class PizzaForm(ModelForm):
    class Meta:
        model = Pizza
        # fields = '__all__'
        fields = ['title', 'description', 'thumbnail_url', 'creator']
        # Using widgets properties to disable a widget
        # widgets = {'creator': forms.widgets.Select(attrs={'readonly': True,
        #                                                   'disabled': True})}

    # This method works too, for disable widget in form
    def __init__(self, *args, **kwargs):
        self.request = kwargs.pop("request")  # store value of request
        # print(self.request.user)
        super().__init__(*args, **kwargs)
        # self.fields['creator'].widget.attrs.update({'disabled': True})
        # self.fields['creator'].required = False
        # self.fields['creator'].widget = HiddenInput()
        self.fields['creator'].queryset = self.fields['creator'].queryset.filter(
                                                                 owner=self.request.user)


class PizzeriaForm(ModelForm):
    class Meta:
        model = Pizzeria
        fields = '__all__'

    def __init__(self, *args, **kwargs):
        self.initial = kwargs['initial']
        self.request = self.initial['request']

        super().__init__(*args, **kwargs)
        self.fields['owner'].queryset = self.fields['owner'].queryset.filter(id=self.request.user.id)


class LikesForm(ModelForm):
    class Meta:
        model = Likes
        fields = '__all__'
