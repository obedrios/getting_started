
from django.contrib.auth import login, authenticate
from django.contrib.auth.mixins import LoginRequiredMixin
from django.shortcuts import render, redirect
from django.views import View
from django.views.generic import CreateView, ListView, UpdateView

from django.urls import reverse_lazy, reverse

from user.forms import RegisteredUserCreateForm
from user.models import RegisteredUser

# Default Password for users:
# 5u4BvvRFDRW9jtu

# Create your views here.
class LoggedUserView(LoginRequiredMixin, View):
    def get(self, request):
        context = dict()
        current_user = request.user
        #user_roles = [str(n) for n in current_user.roles.all()]
        user_roles = current_user.roles.all()
        context['user_roles'] = user_roles
        return render(request, 'user/welcome.html', context)


class RegisterUserView(CreateView):
    template_name = 'user/signup.html'
    model = RegisteredUser
    form_class = RegisteredUserCreateForm

    def form_valid(self, form):
        user = form.save()
        login(self.request, user)
        #return redirect('user:user_view')            
        return redirect(reverse('user:user_update', kwargs={'pk': user.id}))
            

class ChangeUserView(LoginRequiredMixin, UpdateView):
    model = RegisteredUser
    #fields = '__all__'    
    fields = ['username', 'first_name', 'last_name', 'password', 'email', 'roles']
    success_url = reverse_lazy("user:user_view")
