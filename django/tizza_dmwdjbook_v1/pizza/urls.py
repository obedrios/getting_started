from django.urls import path, include
from django.views.generic.base import TemplateView

from . import views

app_name = 'pizza'
urlpatterns =[
   # path('', views.index, name='index'),
   # path("", TemplateView.as_view(template_name="home/main.html"), name="index"),
   path('', views.PizzaListView.as_view(), name='pizza_list'),
   path('pizzerias/', views.PizzeriaList.as_view(), name='pizzeria_list'),
   path('pizzerias/create', views.PizzeriaCreate.as_view(), name='pizzeria_create'),
   path('pizzerias/<int:pk>/update/', views.PizzeriaUpdate.as_view(), name='pizzeria_update'),
   path('pizzerias/<int:pk>/delete/', views.PizzeriaDelete.as_view(), name='pizzeria_delete'),
   path('pizzalist/', views.PizzaListView.as_view(), name='pizza_list'),
   path('create/', views.PizzaCreate.as_view(), name='pizza_create'),
   path('update/<int:pk>/update', views.PizzaUpdate.as_view(), name='pizza_update'),
   path('delete/<int:pk>/delete', views.PizzaDelete.as_view(), name='pizza_delete'),
   path('like/<int:pizza_id>/like', views.like_pizza, name='pizza_like'),
   path('review/', views.PizzaReviewView.as_view(), name='pizza_review'),
]
