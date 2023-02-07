from django.urls import path, include
from django.views.generic.base import TemplateView

from . import views

# Default Password for users:
# 5u4BvvRFDRW9jtu

app_name = 'user'
urlpatterns = [
    #path('', TemplateView.as_view(template_name="user/welcome.html"), name='user_view'),    
    path('', views.LoggedUserView.as_view(), name ="user_view"),
    path('register', views.RegisterUserView.as_view(), name="user_register"),
    path('<int:pk>/update', views.ChangeUserView.as_view(), name='user_update'), 
] 