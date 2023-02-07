from django.contrib.auth.mixins import LoginRequiredMixin
from django.shortcuts import render, redirect, get_object_or_404
from django.views import View
# from django.views.generic.base import TemplateView
from django.views.generic.edit import CreateView, UpdateView, DeleteView
# from django.views.generic import CreateView, DeleteView, DetailView, ListView, UpdateView
# from django.http import HttpResponse
from django.urls import reverse_lazy
from django.db.models import Count

from user.models import RegisteredUser
from .forms import PizzeriaForm, PizzaForm
from .models import Pizza, Pizzeria, Likes


# Create your views here.
class PizzeriaList(LoginRequiredMixin, View):
    def get(self, request):
        context = dict()
        pizzerias = Pizzeria.objects.filter(owner=self.request.user)
        context['pizzeria_list'] = pizzerias  # {'pizzerias_list' : pizzerias}
        return render(request, 'pizza/pizzeria_list.html', context)


# Crete CRUD extending the View Class (The custom way)
# We use reverse_lazy() because we are in "constructor attribute" code
# that is run before urls.py is completely loaded
class PizzeriaCreate(LoginRequiredMixin, View):
    template = "pizza/pizzeria_form.html"
    success_url = reverse_lazy('pizza:pizzeria_list')
    form_class = PizzeriaForm

    def get(self, request, *args, **kwargs):
        user = RegisteredUser.objects.filter(id=request.user.id).first()
        # form = self.form_class(initial={'name': 'Default Value'})
        form = PizzeriaForm(instance=Pizzeria(owner=user), initial={'request':self.request})
        context = dict()
        context['form'] = form
        return render(request, self.template, context)

    def post(self, request, *args, **kwargs):
        form = PizzeriaForm(request.POST or None, initial={'request':self.request})
        context = dict()
        if not form.is_valid():
            context['form'] = form
            return render(request, self.template, context)
        #
        form.save()
        return redirect(self.success_url)


class PizzeriaUpdate(LoginRequiredMixin, View):
    model = Pizzeria
    template = "pizza/pizzeria_form.html"
    success_url = reverse_lazy('pizza:pizzeria_list')

    def get(self, request, pk):
        pizzeria = get_object_or_404(self.model, pk=pk)
        form = PizzeriaForm(instance=pizzeria, initial={'request': self.request})
        context = dict()
        context['form'] = form
        return render(request, self.template, context)

    def post(self, request, pk):
        pizzeria = get_object_or_404(self.model, pk=pk)
        form = PizzeriaForm(request.POST, instance=pizzeria, initial={'request': self.request})
        context = dict()
        if not form.is_valid():
            context['form'] = form
            return render(request, self.template, context)
        #
        form.save()
        return redirect(self.success_url)


class PizzeriaDelete(LoginRequiredMixin, View):
    model = Pizzeria
    template = "pizza/pizzeria_confirm_delete.html"
    success_url = reverse_lazy('pizza:pizzeria_list')

    def get(self, request, pk):
        pizzeria = get_object_or_404(self.model, pk=pk)
        # form = PizzeriaForm(instance=pizzeria)
        context = dict()
        context['pizzeria'] = pizzeria
        return render(request, self.template, context)

    def post(self, request, pk):
        pizzeria = get_object_or_404(self.model, pk=pk)
        # form = PizzeriaForm(instance=pizzeria)
        pizzeria.delete()
        return redirect(self.success_url)


class PizzaListView(LoginRequiredMixin, View):
    def get(self, request):
        template_url = 'pizza/pizza_list.html'
        # pizzerias = Pizzeria.objects.all()
        pizzerias = Pizzeria.objects.filter(owner=self.request.user)
        pizzas = Pizza.objects.all().annotate(likes_count=Count('likes'))
        #
        context = dict()
        context['pizzas'] = pizzas
        context['pizzas_count'] = pizzas.count()
        context['pizzerias'] = pizzerias
        context['pizzerias_count'] = pizzerias.count()

        return render(request, template_url, context)


class PizzaReviewView(LoginRequiredMixin, View):
    def get(self, request):
        template_url = 'pizza/pizza_review.html'
        pizzerias = Pizzeria.objects.all()
        pizzas = Pizza.objects.all()
        #
        context = dict()
        context['pizzas'] = pizzas
        context['pizzas_count'] = pizzas.count()
        context['pizzerias'] = pizzerias
        context['pizzerias_count'] = pizzerias.count()
        return render(request, template_url, context)


# Take the easy way out on the main table
# These views do not need a form because CreateView, etc.
# Build a form object dynamically based on the fields
# value in the constructor attributes
class PizzaCreate(LoginRequiredMixin, CreateView):
    model = Pizza
    template = 'pizza/pizza_form.html'
    form_class = PizzaForm
    # fields = '__all__'
    # fields = ['title', 'description', 'thumbnail_url', 'creator']
    success_url = reverse_lazy('pizza:pizza_list')

    def get_initial(self, *args, **kwargs):
        initial = super(PizzaCreate, self).get_initial(**kwargs)
        pizzeria = Pizzeria.objects.filter(owner=self.request.user)
        initial['creator'] = pizzeria[0]
        return initial

    def get_form_kwargs(self):
        kwargs = super().get_form_kwargs()
        kwargs.update({'request': self.request})
        return kwargs


class PizzaUpdate(LoginRequiredMixin, UpdateView):
    model = Pizza
    template = 'pizza/pizza_form.html'
    form_class = PizzaForm
    # fields = '__all__'
    # fields = ['title', 'description', 'thumbnail_url', 'creator']
    success_url = reverse_lazy('pizza:pizza_list')

    def get_form_kwargs(self):
        kwargs = super().get_form_kwargs()
        kwargs.update({'request': self.request})
        return kwargs


class PizzaDelete(LoginRequiredMixin, DeleteView):
    model = Pizza
    fields = '__all__'
    success_url = reverse_lazy('pizza:pizza_list')


def like_pizza(request, pizza_id):
    pizza = get_object_or_404(Pizza, id=pizza_id)
    Likes.objects.create(pizza=pizza, user=request.user)
    return redirect('pizza:pizza_list')
