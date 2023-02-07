
from django.urls import path, include
from django.views.generic.base import TemplateView

from user import views

app_name = 'user'
urlpatterns = [
    #path('', TemplateView.as_view(template_name="user/welcome.html"), name='user_view'),
    path('', TemplateView.as_view(template_name="home/welcome.html"), name='user_view'),
    path('details/', views.LoggedUserView.as_view(), name="user_details"),
    path('<int:pk>/update', views.UpdateRegisteredUserView.as_view(), name='user_update'),
    path('register/', TemplateView.as_view(template_name="user/user_register.html"), name="user_register"),
    path('register/owner', views.PizzeriaRegisterView.as_view(), name="owner_register"),
    path('register/reviewer', views.CustomerReviewerRegisterView.as_view(), name="reviewer_register"),
]