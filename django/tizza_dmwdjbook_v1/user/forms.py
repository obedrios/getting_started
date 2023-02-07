from django.contrib.auth.forms import UserCreationForm
from user.models import RegisteredUser

from pizza.models import CustomerReviewer


# Create your forms here.
class PizzeriaRegisterForm(UserCreationForm):
    class Meta(UserCreationForm.Meta):
        model = RegisteredUser

    def save(self, commit=True):
        user = super().save(commit=False)
        user.is_business = True
        if commit:
            user.save()
        return user


class CustomerReviewerRegisterForm(UserCreationForm):
    class Meta(UserCreationForm.Meta):
        model = RegisteredUser

    def save(self, commit=True):
        user = super().save(commit=False)
        user.is_customer = True
        if commit:
            user.save()
            reviewer = CustomerReviewer.objects.create(user=user)
            reviewer.save()
        return user
