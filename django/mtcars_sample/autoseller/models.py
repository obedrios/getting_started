from django.db import models

# Create your models here.
class AutoBrand(models.Model):
    name = models.CharField(unique=True, max_length=128, default="")

    def __str__(self):
        return self.name

class AutoModel(models.Model):
    name = models.CharField(max_length=128, default="")
    brand = models.ForeignKey("AutoBrand", on_delete=models.DO_NOTHING, null=False)

    def __str__(self):
        return self.name
    
    class Meta:
        unique_together = ['name', 'brand']

class AutoProfile(models.Model):
    model = models.ForeignKey("AutoModel", on_delete=models.DO_NOTHING, null=False) 
    mpg	  = models.FloatField(null=True)
    cyl   = models.SmallIntegerField(null=True)
    disp  = models.FloatField(null=True)
    hp    = models.FloatField(null=True)
    drat  = models.FloatField(null=True)
    wt    = models.FloatField(null=True)
    qsec  = models.FloatField(null=True)
    vs    = models.SmallIntegerField(null=True)
    am    = models.SmallIntegerField(null=True)
    gear  = models.SmallIntegerField(null=True)
    carb  = models.SmallIntegerField(null=True)

    def __str__(self):
        return "{}, (Hp={}, Gears={})".format(self.model, self.hp, self.gear)