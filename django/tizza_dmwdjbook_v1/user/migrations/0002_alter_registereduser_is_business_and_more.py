# Generated by Django 4.1.6 on 2023-02-04 22:36

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ("user", "0001_initial"),
    ]

    operations = [
        migrations.AlterField(
            model_name="registereduser",
            name="is_business",
            field=models.BooleanField(default=False),
        ),
        migrations.AlterField(
            model_name="registereduser",
            name="is_customer",
            field=models.BooleanField(default=False),
        ),
    ]
