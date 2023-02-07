from django.contrib.auth import login
from django.contrib.auth.mixins import LoginRequiredMixin
from django.views import View
from django.views.generic import CreateView, DeleteView, DetailView, ListView, UpdateView

from django.shortcuts import render, get_object_or_404, redirect
from django.urls import reverse, reverse_lazy

from user.forms import PizzeriaRegisterForm, CustomerReviewerRegisterForm
from user.models import RegisteredUser


# Create your views here.
class LoggedUserView(LoginRequiredMixin, View):
    def get(self, request):
        context = dict()
        current_user = request.user
        user_type = 'owner'
        if current_user.is_customer:
            user_type = 'reviewer'
        context['user_type'] = user_type
        return render(request, 'user/user_details.html', context)


class PizzeriaRegisterView(CreateView):
    model = RegisteredUser
    form_class = PizzeriaRegisterForm
    template_name = 'user/registereduser_form.html'

    def get_context_data(self, **kwargs):
        kwargs['user_type'] = 'owner'
        return super().get_context_data(**kwargs)

    def form_valid(self, form):
        user = form.save()
        login(self.request, user)
        return redirect('user:user_details')


class CustomerReviewerRegisterView(CreateView):
    model = RegisteredUser
    form_class = CustomerReviewerRegisterForm
    template_name = 'user/registereduser_form.html'

    def get_context_data(self, **kwargs):
        kwargs['user_type'] = 'reviewer'
        return super().get_context_data(**kwargs)

    def form_valid(self, form):
        user = form.save()
        login(self.request, user)
        return redirect('user:user_details')


class UpdateRegisteredUserView(LoginRequiredMixin, UpdateView):
    model = RegisteredUser
    #fields = '__all__'
    fields = ['username', 'first_name', 'last_name', 'password', 'email']
    success_url = reverse_lazy("user:user_details")